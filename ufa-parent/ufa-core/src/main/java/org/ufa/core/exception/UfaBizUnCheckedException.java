package org.ufa.core.exception;


public class UfaBizUnCheckedException extends BaseRuntimeException {
	private static final long serialVersionUID = 4788463477094031410L;

	public UfaBizUnCheckedException(String code, String[] params, String defaultMessage, Throwable cause) {
		super(code, params, defaultMessage, cause);
	}

	public UfaBizUnCheckedException(String code, String[] params, String defaultMessage) {
		super(code, params, defaultMessage);
	}

	public UfaBizUnCheckedException(String code, String defaultMessage) {
		super(code, null, defaultMessage);
	}
	
	public UfaBizUnCheckedException(String defaultMessage) {
		super(defaultMessage);
	}
	
	@Override
	public synchronized Throwable fillInStackTrace() {
		// 避免操作系统的压栈操作，提升性能
		return null;
	}
}
