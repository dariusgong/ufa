/*******************************************************************************
 * Project   : ufa-security
 * Class Name: org.ufa.security.exception.UfaAuthenticationException
 * Created By: Jonathan 
 * Created on: 2014-8-27 下午2:00:31
 * Copyright © 2008-2014 NATIE All rights reserved.
 ******************************************************************************/
package org.ufa.security.exception;

import org.springframework.security.core.AuthenticationException;


/**
 * <P>用户登录认证异常</P>
 * @author Jonathan
 */
public class UfaAuthenticationException extends AuthenticationException {

	/**
	 * <p>Method for constructor</p>
	 * @param msg
	 */
	public UfaAuthenticationException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
