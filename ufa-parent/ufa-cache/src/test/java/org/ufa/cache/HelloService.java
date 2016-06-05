package org.ufa.cache;

import org.springframework.stereotype.Service;
import org.ufa.cache.config.annotation.Cacheable;
import org.ufa.cache.config.annotation.ClearCache;
import org.ufa.log.Logger;
import org.ufa.log.LoggerFactory;

/**
 * <P>测试服务</P>
 * @author Jonathan
 */
@Service
public class HelloService {
	protected final transient Logger logger = LoggerFactory.getLogger(HelloService.class);
	
	@Cacheable(region="user")
    public UserDTO getUser(String name) {
       logger.info("从数据库中返回:{}",name);
       UserDTO user=new UserDTO();
       user.setName(name);
       user.setAge("28");
       return user;
    }
	
	@ClearCache(region="user")
	public void clearCache(){
		
	}
}
