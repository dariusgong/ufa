/*******************************************************************************
 * Project   : ufa-security
 * Class Name: org.ufa.security.exception.InvalidSignatureExcetion
 * Created By: Jonathan 
 * Created on: 2013-11-22 上午10:54:55
 * Copyright © 2010-2012 KFT Pay All rights reserved.
 ******************************************************************************/
package org.ufa.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * <P>TODO</P>
 * @author Jonathan
 */
public class InvalidSignatureExcetion extends AuthenticationException {

	/**
	 * TODO
	 */
	private static final long serialVersionUID = -4058354760659218309L;

	/**
	 * <p>Method for constructor</p>
	 * @param msg
	 * @param t
	 */
	public InvalidSignatureExcetion(String msg, Throwable t) {
		super(msg, t);
	}

	/**
	 * <p>Method for constructor</p>
	 * @param msg
	 */
	public InvalidSignatureExcetion(String msg) {
		super(msg);
	}

}
