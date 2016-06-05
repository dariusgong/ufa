package org.ufa.cache.config.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.ufa.cache.CacheChannel;
import org.ufa.cache.CacheObject;
import org.ufa.cache.config.annotation.EvictCache;
import org.ufa.cache.config.annotation.Cacheable;
import org.ufa.cache.config.annotation.ClearCache;
import org.ufa.log.Logger;
import org.ufa.log.LoggerFactory;


@Component
@Aspect
public class CacheAspect {
	private final transient Logger logger = LoggerFactory.getLogger(CacheAspect.class);

	private CacheChannel cacheChannel = CacheChannel.getInstance();

	@Pointcut("execution(@org.ufa.cache.config.annotation.Cacheable * * (..)))")
	public void cacheable() {
		// 切点定义
	}

	@Pointcut("execution(@org.ufa.cache.config.annotation.EvictCache * * (..)))")
	public void cacheEvict() {
		// 切点定义
	}

	@Pointcut("execution(@org.ufa.cache.config.annotation.ClearCache * * (..)))")
	public void clearCache() {
		// 切点定义
	}

	@Around("cacheable() && @annotation(cacheable)")
	public Object cacheable(ProceedingJoinPoint jp, Cacheable cacheable) {
		// 获得连接点的方法签名对象
		final MethodSignature ms = (MethodSignature) jp.getSignature();
		// 获得连接点方法方法名
		final String methodName = ms.getMethod().getName();
		// 获得目标的类名
		final String className = jp.getTarget().getClass().getName();
		String key = cacheable.key();
		// 如果没有指定明确的key,则会使用定制的KEY生成机制产生KEY
		if (!StringUtils.hasText(key)) {
			key = getCacheKey(className, methodName, jp.getArgs());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("useCache : targetClassName={}, targetMethodName={}, key={}", new Object[] { className,
					methodName, key });
		}
		try {

			CacheObject cacheObject = cacheChannel.get(cacheable.region(), key);

			if (cacheObject == null || cacheObject.getValue() == null) {
				Object value = jp.proceed();
				cacheChannel.set(cacheable.region(), key, value);
				return value;
			} else {
				return cacheObject.getValue();
			}

			
		} catch (Throwable e) {
			logger.error(e);
			return null;
		}
	}

	@After("cacheEvict() && @annotation(evictCache)")
	public void cacheEvict(JoinPoint jp, EvictCache evictCache) {
		cacheChannel.evict(evictCache.region(), evictCache.key());
		if (logger.isTraceEnabled()) {
			logger.trace("public void flushCache(JoinPoint jp, EvictCache evictCache) ");
		}
	}

	@After("clearCache() && @annotation(clearCache)")
	public void clearCache(JoinPoint jp, ClearCache clearCache) {
		cacheChannel.clear(clearCache.region());
	}

	/**
	 * 通过指定的类名，方法名和此方法所有的参数值构造key. 注意：必须实现参数值的toString()方法
	 * 
	 * @param targetName 类名
	 * @param methodName 方法名
	 * @param arguments 上述方法的参数值
	 * @return
	 */
	private String getCacheKey(String targetName, String methodName, Object[] arguments) {
		final StringBuilder sb = new StringBuilder(targetName);
		sb.append('.').append(methodName);
		if ((arguments != null) && (arguments.length != 0)) {
			for (int i = 0; i < arguments.length; i++) {
				sb.append('.').append(arguments[i]);
			}
		}
		return sb.toString();
	}
}
