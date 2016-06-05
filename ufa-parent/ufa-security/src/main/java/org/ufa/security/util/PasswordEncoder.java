package org.ufa.security.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.AuthenticationException;


/**
 * <P>
 * 改变用String变量保存password的安全性问题
 * </P>
 * 
 */
public interface PasswordEncoder {

	/**
	 * <p> 用加密器对明文密码进行加密 </p>
	 * 
	 * @param password 明文密码
	 * @param charsetName 字符集,默认为UTF-8
	 * @param request 便于从request中获取各种客户端传递来的参数值
	 * @return
	 * @throws AuthenticationException
	 */
	byte[] encode(String password, String charsetName, HttpServletRequest request);
	
	
}
