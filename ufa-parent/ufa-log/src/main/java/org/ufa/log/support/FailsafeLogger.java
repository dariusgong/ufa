package org.ufa.log.support;

import org.ufa.log.Logger;


public class FailsafeLogger implements Logger {

	private Logger logger;

	public FailsafeLogger(Logger logger) {
		this.logger = logger;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	private String appendContextMessage(String msg) {
		return msg;
	}

	@Override
	public void trace(String msg, Throwable e) {
		try {
			logger.trace(appendContextMessage(msg), e);
		} catch (Throwable t) {
		}
	}

	@Override
	public void trace(Throwable e) {
		try {
			logger.trace(e);
		} catch (Throwable t) {
		}
	}

	@Override
	public void trace(String msg) {
		try {
			logger.trace(appendContextMessage(msg));
		} catch (Throwable t) {
		}
	}

	@Override
	public void debug(String msg, Throwable e) {
		try {
			logger.debug(appendContextMessage(msg), e);
		} catch (Throwable t) {
		}
	}

	@Override
	public void debug(Throwable e) {
		try {
			logger.debug(e);
		} catch (Throwable t) {
		}
	}

	@Override
	public void debug(String msg) {
		try {
			logger.debug(appendContextMessage(msg));
		} catch (Throwable t) {
		}
	}

	@Override
	public void info(String msg, Throwable e) {
		try {
			logger.info(appendContextMessage(msg), e);
		} catch (Throwable t) {
		}
	}

	@Override
	public void info(String msg) {
		try {
			logger.info(appendContextMessage(msg));
		} catch (Throwable t) {
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.ufa.log.Logger#info(java.lang.String, java.lang.Object)
	 *  
	 */
	@Override
	public void info(String format, Object arg) {
		try {
			logger.info(format, arg);
		} catch (Throwable t) {
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.ufa.log.Logger#info(java.lang.String, java.lang.Object[])
	 *  
	 */
	@Override
	public void info(String format, Object... argArray) {
		try {
			logger.info(format, argArray);
		} catch (Throwable t) {
		}
	}

	@Override
	public void warn(String msg, Throwable e) {
		try {
			logger.warn(appendContextMessage(msg), e);
		} catch (Throwable t) {
		}
	}

	@Override
	public void warn(String msg) {
		try {
			logger.warn(appendContextMessage(msg));
		} catch (Throwable t) {
		}
	}

	@Override
	public void error(String msg, Throwable e) {
		try {
			logger.error(appendContextMessage(msg), e);
		} catch (Throwable t) {
		}
	}

	@Override
	public void error(String msg) {
		try {
			logger.error(appendContextMessage(msg));
		} catch (Throwable t) {
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.ufa.log.Logger#error(java.lang.String, java.lang.Object)
	 *  
	 */
	@Override
	public void error(String format, Object arg) {
		try {
			logger.error(format, arg);
		} catch (Throwable t) {
		}
	}

	@Override
	public void error(Throwable e) {
		try {
			logger.error(e);
		} catch (Throwable t) {
		}
	}

	@Override
	public void info(Throwable e) {
		try {
			logger.info(e);
		} catch (Throwable t) {
		}
	}

	@Override
	public void warn(Throwable e) {
		try {
			logger.warn(e);
		} catch (Throwable t) {
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.ufa.log.Logger#warn(java.lang.String, java.lang.Object)
	 *  
	 */
	@Override
	public void warn(String format, Object arg) {
		try {
			logger.warn(format, arg);
		} catch (Throwable t) {
		}
	}

	@Override
	public boolean isTraceEnabled() {
		try {
			return logger.isTraceEnabled();
		} catch (Throwable t) {
			return false;
		}
	}

	@Override
	public boolean isDebugEnabled() {
		try {
			return logger.isDebugEnabled();
		} catch (Throwable t) {
			return false;
		}
	}

	@Override
	public boolean isInfoEnabled() {
		try {
			return logger.isInfoEnabled();
		} catch (Throwable t) {
			return false;
		}
	}

	@Override
	public boolean isWarnEnabled() {
		try {
			return logger.isWarnEnabled();
		} catch (Throwable t) {
			return false;
		}
	}

	@Override
	public boolean isErrorEnabled() {
		try {
			return logger.isErrorEnabled();
		} catch (Throwable t) {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.ufa.log.Logger#debug(java.lang.String, java.lang.Object[])
	 */
	@Override
	public void debug(String format, Object... argArray) {
		try {
			logger.debug(format, argArray);
		} catch (Throwable t) {
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.ufa.log.Logger#trace(java.lang.String, java.lang.Object)
	 *  
	 */
	@Override
	public void trace(String format, Object arg) {
		try {
			logger.trace(format, arg);
		} catch (Throwable t) {
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.ufa.log.Logger#trace(java.lang.String, java.lang.Object[])
	 *  
	 */
	@Override
	public void trace(String format, Object[] argArray) {
		try {
			logger.trace(format, argArray);
		} catch (Throwable t) {
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.ufa.log.Logger#debug(java.lang.String, java.lang.Object)
	 *  
	 */
	@Override
	public void debug(String format, Object arg) {
		try {
			logger.debug(format, arg);
		} catch (Throwable t) {
		}
	}

	@Override
	public void error(String format, Object... argArray) {
		logger.error(format, argArray);
		
	}

}