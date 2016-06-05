package org.ufa.log.jdk;

import java.util.logging.Level;

import org.ufa.log.Logger;


public class JdkLogger implements Logger {

	private final java.util.logging.Logger logger;

	public JdkLogger(java.util.logging.Logger logger) {
		this.logger = logger;
	}

	@Override
	public void trace(String msg) {
		logger.log(Level.FINER, msg);
	}

	@Override
	public void trace(Throwable e) {
		logger.log(Level.FINER, e.getMessage(), e);
	}

	@Override
	public void trace(String msg, Throwable e) {
		logger.log(Level.FINER, msg, e);
	}

	/*
	 * (non-Javadoc)
	 * @see org.ufa.log.Logger#trace(java.lang.String, java.lang.Object)
	 *  
	 */
	@Override
	public void trace(String format, Object arg) {
		logger.log(Level.FINER, format, arg);
	}

	/*
	 * (non-Javadoc)
	 * @see org.ufa.log.Logger#trace(java.lang.String, java.lang.Object[])
	 *  
	 */
	@Override
	public void trace(String format, Object[] argArray) {
		logger.log(Level.FINER, format, argArray);
	}

	@Override
	public void debug(String msg) {
		logger.log(Level.FINE, msg);
	}

	@Override
	public void debug(Throwable e) {
		logger.log(Level.FINE, e.getMessage(), e);
	}

	@Override
	public void debug(String msg, Throwable e) {
		logger.log(Level.FINE, msg, e);
	}

	/*
	 * (non-Javadoc)
	 * @see org.ufa.log.Logger#debug(java.lang.String, java.lang.Object)
	 *  
	 */
	@Override
	public void debug(String format, Object arg) {
		logger.log(Level.FINE, format, arg);
	}

	/*
	 * (non-Javadoc)
	 * @see org.ufa.log.Logger#debug(java.lang.String, java.lang.Object[])
	 *  
	 */
	@Override
	public void debug(String format,  Object... argArray) {
		logger.log(Level.FINE, format, argArray);
	}

	@Override
	public void info(String msg) {
		logger.log(Level.INFO, msg);
	}

	/*
	 * (non-Javadoc)
	 * @see org.ufa.log.Logger#info(java.lang.String, java.lang.Object)
	 *  
	 */
	@Override
	public void info(String format, Object arg) {
		logger.log(Level.INFO, format, arg);
	}

	/*
	 * (non-Javadoc)
	 * @see org.ufa.log.Logger#info(java.lang.String, java.lang.Object[])
	 *  
	 */
	@Override
	public void info(String format, Object... argArray) {
		logger.log(Level.INFO, format, argArray);
	}

	@Override
	public void info(String msg, Throwable e) {
		logger.log(Level.INFO, msg, e);
	}

	@Override
	public void warn(String msg) {
		logger.log(Level.WARNING, msg);
	}

	@Override
	public void warn(String msg, Throwable e) {
		logger.log(Level.WARNING, msg, e);
	}

	@Override
	public void error(String msg) {
		logger.log(Level.SEVERE, msg);
	}

	@Override
	public void error(String format, Object... argArray) {
		logger.log(Level.SEVERE, format, argArray);
	}
	
	
	@Override
	public void error(String msg, Throwable e) {
		logger.log(Level.SEVERE, msg, e);
	}

	@Override
	public void error(Throwable e) {
		logger.log(Level.SEVERE, e.getMessage(), e);
	}

	@Override
	public void info(Throwable e) {
		logger.log(Level.INFO, e.getMessage(), e);
	}

	@Override
	public void warn(Throwable e) {
		logger.log(Level.WARNING, e.getMessage(), e);
	}

	@Override
	public boolean isTraceEnabled() {
		return logger.isLoggable(Level.FINER);
	}

	@Override
	public boolean isDebugEnabled() {
		return logger.isLoggable(Level.FINE);
	}

	@Override
	public boolean isInfoEnabled() {
		return logger.isLoggable(Level.INFO);
	}

	@Override
	public boolean isWarnEnabled() {
		return logger.isLoggable(Level.WARNING);
	}

	@Override
	public boolean isErrorEnabled() {
		return logger.isLoggable(Level.SEVERE);
	}

	/*
	 * (non-Javadoc)
	 * @see org.ufa.log.Logger#warn(java.lang.String, java.lang.Object)
	 *  
	 */
	@Override
	public void warn(String format, Object arg) {
		logger.log(Level.WARNING, format, arg);
	}

	/*
	 * (non-Javadoc)
	 * @see org.ufa.log.Logger#error(java.lang.String, java.lang.Object)
	 *  
	 */
	@Override
	public void error(String format, Object arg) {
		logger.log(Level.SEVERE, format, arg);
	}

}