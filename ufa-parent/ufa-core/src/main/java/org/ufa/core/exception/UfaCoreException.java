
package org.ufa.core.exception;



public class UfaCoreException extends BaseRuntimeException {
	private static final long	serialVersionUID	= 2844135923995164739L;

	public UfaCoreException(String code, String[] params, String defaultMessage, Throwable cause) {
		super(code, params, defaultMessage, cause);
	}

	public UfaCoreException(String code, String[] params, String defaultMessage) {
		super(code, params, defaultMessage);
	}

	public UfaCoreException(String defaultMessage, Throwable cause) {
		super(defaultMessage, cause);
	}

	public UfaCoreException(String defaultMessage) {
		super(defaultMessage);
	}

}
