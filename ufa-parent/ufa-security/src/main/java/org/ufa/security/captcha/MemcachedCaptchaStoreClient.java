package org.ufa.security.captcha;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.ufa.log.Logger;
import org.ufa.log.LoggerFactory;

import com.octo.captcha.Captcha;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.captchastore.CaptchaAndLocale;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;


/**
 * <P>
 * 支持通过Memcached-java-client将验证码存储在memcached上
 * </P>
 * 
 *  
 */
public class MemcachedCaptchaStoreClient implements InitializingBean, DisposableBean{
	private static final Logger log = LoggerFactory.getLogger(MemcachedCaptchaStoreClient.class);
	private SockIOPool pool;
	private String serverList;
	private int maxSpareConnections = 100;
	private MemCachedClient mc;
	private long liveTime = 180000; // item在memcached上的存活时间,单位是毫秒

	/**
	 * @return the liveTime
	 */
	public long getLiveTime() {
		return liveTime;
	}

	/**
	 * @param liveTime the liveTime to set
	 */
	public void setLiveTime(long liveTime) {
		this.liveTime = liveTime;
	}

	/**
	 * @return the maxSpareConnections
	 */
	public int getMaxSpareConnections() {
		return maxSpareConnections;
	}

	/**
	 * @param maxSpareConnections the maxSpareConnections to set
	 */
	public void setMaxSpareConnections(int maxSpareConnections) {
		this.maxSpareConnections = maxSpareConnections;
	}

	/**
	 * @return the serverList
	 */
	public String getServerList() {
		return serverList;
	}

	/**
	 * @param serverlist the serverList to set
	 */
	public void setServerList(String serverList) {
		this.serverList = serverList;
	}
	
	
	/**
	 * <p>将captcha存入缓存</p>
	 * @param id
	 * @param captcha
	 * @throws CaptchaServiceException
	 *  Jonathan
	 */
	public void storeCaptcha(String id, Captcha captcha) throws CaptchaServiceException {
		if (log.isDebugEnabled()) {
			log.debug("将key为{}的验证码存储在memcached上.", id);
		}
		mc.set(id, new CaptchaAndLocale(captcha), new Date(System.currentTimeMillis() + liveTime));

	}

	/**
	 * <p>从缓存中取出captcha</p>
	 * @param id
	 * @return
	 * @throws CaptchaServiceException
	 *  Jonathan
	 */
	public Captcha getCaptcha(String id) throws CaptchaServiceException {
		if (log.isDebugEnabled()) {
			log.debug("从memcached上获取key为{}的验证码.", id);
		}
		CaptchaAndLocale result = (CaptchaAndLocale) mc.get(id);
		if (result != null) {
			return result.getCaptcha();
		} else {
			return null;
		}
	}
	
	private void init() {
		if (log.isDebugEnabled()) {
			log.debug("初始化memcachedClient, serverList是{}", serverList);
		}
		Assert.notNull(serverList);
		String[] serverlist = serverList.split(",");
		pool = SockIOPool.getInstance("captchaStoreMemcachedSockIOPool", true);
		// Integer[] weights = { new Integer(5), new Integer(2) };
		int initialConnections = 10;
		int minSpareConnections = 5;
		int maxIdleTime = 1000 * 60 * 5; // 5 minutes, 一个连接最大空闲时间
		long maxBusyTime = 1000 * 30;// 等于java.nio.channels.Selector.select(long //
										// timeout)中的timeout，小于5000才有意义
		long maintThreadSleep = 1000 * 5; // 5 seconds
		int socketTimeOut = 1000 * 3; // 3 seconds, default timeout of socket reads
		boolean aliveCheck = true; // disable health check of socket on checkout
		pool.setServers(serverlist);
		pool.setInitConn(initialConnections);
		pool.setMinConn(minSpareConnections);
		pool.setMaxConn(maxSpareConnections);
		pool.setMaxIdle(maxIdleTime);
		pool.setMaxBusyTime(maxBusyTime);
		pool.setMaintSleep(maintThreadSleep);
		pool.setSocketTO(socketTimeOut);
		pool.setAliveCheck(aliveCheck);
		pool.initialize();
		mc = new MemCachedClient("captchaStoreMemcachedSockIOPool", true, true);
		// 由于MemCachedClient和SockIOPool都不会检查memcached实例是否都在正常运行,所以要手动检查一下状态
		Map<String, Map<String, String>> stats = mc.stats();
		if (stats.size() < serverlist.length) {
			log.error("配置了" + serverlist.length + "台memcached[" + serverList + "],但实际只检测到" + stats.size() + "台["
					+ stats.keySet() + "],请确认memcached集群是否全部正常运行");
		}
		if (log.isDebugEnabled()) {
			log.debug("memcached stats:");
			Map<String, String> items = null;
			for (String key1 : stats.keySet()) {
				log.debug("  " + key1 + ":");
				items = stats.get(key1);
				for (String key2 : items.keySet()) {
					log.debug("    " + key2 + ":" + items.get(key2));
				}
			}
		}

	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.DisposableBean#destroy()
	 *  Jonathan
	 */
	@Override
	public void destroy() throws Exception {
		log.info("Shuts down the MemCached.SockIOPool...");
		pool.shutDown();
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 *  Jonathan
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}

/*	
	 * (non-Javadoc)
	 * @see com.octo.captcha.service.captchastore.CaptchaStore#hasCaptcha(java.lang.String)
	 *  
	 
	@Override
	public boolean hasCaptcha(String id) {
		if (log.isDebugEnabled()) {
			log.debug("检查memcached中是否存在key为{}的验证码.", id);
		}
		return mc.keyExists(id);
	}

	
	 * (non-Javadoc)
	 * @see com.octo.captcha.service.captchastore.CaptchaStore#storeCaptcha(java.lang.String,
	 * com.octo.captcha.Captcha)
	 *  
	 
	@Override
	public void storeCaptcha(String id, Captcha captcha) throws CaptchaServiceException {
		if (log.isDebugEnabled()) {
			log.debug("将key为{}的验证码存储在memcached上.", id);
		}
		mc.set(id, new CaptchaAndLocale(captcha), new Date(System.currentTimeMillis() + liveTime));

	}

	
	 * (non-Javadoc)
	 * @see com.octo.captcha.service.captchastore.CaptchaStore#storeCaptcha(java.lang.String,
	 * com.octo.captcha.Captcha, java.util.Locale)
	 *  
	 
	@Override
	public void storeCaptcha(String id, Captcha captcha, Locale locale) throws CaptchaServiceException {
		if (log.isDebugEnabled()) {
			log.debug("将key为{}的验证码存储在memcached上.", id);
		}
		mc.set(id, new CaptchaAndLocale(captcha, locale), new Date(System.currentTimeMillis() + liveTime));

	}

	
	 * (non-Javadoc)
	 * @see com.octo.captcha.service.captchastore.CaptchaStore#removeCaptcha(java.lang.String)
	 *  
	 
	@Override
	public boolean removeCaptcha(String id) {
		if (log.isDebugEnabled()) {
			log.debug("将key为{}的验证码从memcached上移除.", id);
		}
		return mc.delete(id);
	}

	
	 * (non-Javadoc)
	 * @see com.octo.captcha.service.captchastore.CaptchaStore#getLocale(java.lang.String)
	 *  
	 
	@Override
	public Locale getLocale(String id) throws CaptchaServiceException {
		CaptchaAndLocale result = (CaptchaAndLocale) mc.get(id);
		if (result != null) {
			return result.getLocale();
		} else {
			return null;
		}
	}

	
	 * (non-Javadoc)
	 * @see com.octo.captcha.service.captchastore.CaptchaStore#getSize()
	 *  
	 
	@Override
	public int getSize() {
		// memcached无法获得一共有多少验证码
		log.debug("调用了memcached中无法实现的方法getSize()");
		return 0;
	}

	
	 * (non-Javadoc)
	 * @see com.octo.captcha.service.captchastore.CaptchaStore#getKeys()
	 *  
	 
	@Override
	public Collection getKeys() {
		// memcached无法获得一共有多少验证码
		log.debug("调用了memcached中无法实现的方法getKeys()");
		return null;
	}

	
	 * (non-Javadoc)
	 * @see com.octo.captcha.service.captchastore.CaptchaStore#empty()
	 *  
	 
	@Override
	public void empty() {
		// do nothing,不能清除整个memcached,还有其他数据存在
		log.debug("调用了memcached中不能去实现的方法empty()");
	}

	
	 * (non-Javadoc)
	 * @see com.octo.captcha.service.captchastore.CaptchaStore#initAndStart()
	 *  
	 
	@Override
	public void initAndStart() {
		// do nothing,在memcached上不需要初始化空间
		log.info("initialize and start the captcha store...");
	}

	
	 * (non-Javadoc)
	 * @see com.octo.captcha.service.captchastore.CaptchaStore#cleanAndShutdown()
	 *  
	 
	@Override
	public void cleanAndShutdown() {
		// do nothing,在memcached上不需要清理空间
		log.info("clean and shutdown the captcha store...");
	}
*/
}
