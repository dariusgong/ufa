/*******************************************************************************
 * Project   : ufa-core
 * Class Name: org.ufa.core.exception.UfaBizCheckedException
 * Created By: Jonathan 
 * Created on: 2013-7-18 下午8:43:39
 * Copyright © 2010-2012 KFT Pay All rights reserved.
 ******************************************************************************/
package org.ufa.core.exception;

/**
 * <P>TODO</P>
 * @author Jonathan
 */
public class UfaBizCheckedException extends BaseCheckedException {
	
	/**
	 * TODO
	 */
	private static final long serialVersionUID = -2899754111574943443L;

	public UfaBizCheckedException(String code, String[] params, String defaultMessage, Throwable cause) {
		super(code, params, defaultMessage, cause);
	}

	public UfaBizCheckedException(String code, String[] params, String defaultMessage) {
		super(code, params, defaultMessage);
	}

	public UfaBizCheckedException(String code, String defaultMessage) {
		super(code, null, defaultMessage);
	}
	
	public UfaBizCheckedException(String defaultMessage) {
		super(defaultMessage);
	}
	
	@Override
	public synchronized Throwable fillInStackTrace() {
		// 避免操作系统的压栈操作，提升性能
		return null;
	}

}
