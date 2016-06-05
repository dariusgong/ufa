package org.ufa.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import org.ufa.log.Logger;
import org.ufa.log.LoggerFactory;


/**
 * <P>缓存测试</P>
 * @author Jonathan
 */
@ContextConfiguration(locations = { "/test_cache.xml" })
public class UfaCacheTest extends AbstractTestNGSpringContextTests{
	
	protected final transient Logger logger = LoggerFactory.getLogger(UfaCacheTest.class);
	
	@Autowired
	private HelloService helloService;
	
	@Test
	public void testCache(){
//		logger.info("首次调用 参数:{}","darius");
//		UserDTO user=helloService.getUser("darius");
//		logger.info("第二次调用 参数:{}","darius");
//		user=helloService.getUser("darius");
		helloService.clearCache();
		logger.info("清楚缓存后调用参数:{}","darius");
		UserDTO user1 =helloService.getUser("darius");
		logger.info("再次调用",user1);
		user1 =helloService.getUser("darius");
		helloService.clearCache();
		user1 =helloService.getUser("darius");
		helloService.clearCache();
	}
	

}
