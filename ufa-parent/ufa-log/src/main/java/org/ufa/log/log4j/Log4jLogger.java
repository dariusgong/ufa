package org.ufa.log.log4j;

import org.apache.log4j.Level;
import org.ufa.log.Logger;
import org.ufa.log.support.FailsafeLogger;
import org.ufa.log.utils.FormattingTuple;
import org.ufa.log.utils.MessageFormatter;


public class Log4jLogger implements Logger {

	private static final String FQCN = FailsafeLogger.class.getName();

	private final org.apache.log4j.Logger logger;

	public Log4jLogger(org.apache.log4j.Logger logger) {
		this.logger = logger;
	}

	@Override
	public void trace(String msg) {
		logger.log(FQCN, Level.TRACE, msg, null);
	}

	@Override
	public void trace(Throwable e) {
		logger.log(FQCN, Level.TRACE, e == null ? null : e.getMessage(), e);
	}

	@Override
	public void trace(String msg, Throwable e) {
		logger.log(FQCN, Level.TRACE, msg, e);
	}

	/*
	 * (non-Javadoc)
	 * @see org.ufa.log.Logger#trace(java.lang.String, java.lang.Object)
	 *  
	 */
	@Override
	public void trace(String format, Object arg) {
		FormattingTuple ft = MessageFormatter.format(format, arg);
		String formattedMessage = ft.getMessage();
		logger.log(FQCN, Level.TRACE, formattedMessage, null);
	}

	/*
	 * (non-Javadoc)
	 * @see org.ufa.log.Logger#trace(java.lang.String, java.lang.Object[])
	 *  
	 */
	@Override
	public void trace(String format, Object[] argArray) {
		FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
		String formattedMessage = ft.getMessage();
		logger.log(FQCN, Level.TRACE, formattedMessage, null);
	}

	@Override
	public void debug(String msg) {
		logger.log(FQCN, Level.DEBUG, msg, null);
	}

	@Override
	public void debug(Throwable e) {
		logger.log(FQCN, Level.DEBUG, e == null ? null : e.getMessage(), e);
	}

	@Override
	public void debug(String msg, Throwable e) {
		logger.log(FQCN, Level.DEBUG, msg, e);
	}

	/*
	 * (non-Javadoc)
	 * @see org.ufa.log.Logger#debug(java.lang.String, java.lang.Object)
	 *  
	 */
	@Override
	public void debug(String format, Object arg) {
		FormattingTuple ft = MessageFormatter.format(format, arg);
		String formattedMessage = ft.getMessage();
		logger.log(FQCN, Level.DEBUG, formattedMessage, null);

	}

	/*
	 * (non-Javadoc)
	 * @see org.ufa.log.Logger#debug(java.lang.String, java.lang.Object[])
	 *  
	 */
	@Override
	public void debug(String format, Object... argArray) {
		FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
		String formattedMessage = ft.getMessage();
		logger.log(FQCN, Level.DEBUG, formattedMessage, null);
	}

	@Override
	public void info(String msg) {
		logger.log(FQCN, Level.INFO, msg, null);
	}

	/*
	 * (non-Javadoc)
	 * @see org.ufa.log.Logger#info(java.lang.String, java.lang.Object)
	 *  
	 */
	@Override
	public void info(String format, Object arg) {
		FormattingTuple ft = MessageFormatter.format(format, arg);
		String formattedMessage = ft.getMessage();
		logger.log(FQCN, Level.INFO, formattedMessage, null);
	}

	/*
	 * (non-Javadoc)
	 * @see org.ufa.log.Logger#info(java.lang.String, java.lang.Object[])
	 *  
	 */
	@Override
	public void info(String format, Object... argArray) {
		FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
		String formattedMessage = ft.getMessage();
		logger.log(FQCN, Level.INFO, formattedMessage, null);
	}

	@Override
	public void info(Throwable e) {
		logger.log(FQCN, Level.INFO, e == null ? null : e.getMessage(), e);
	}

	@Override
	public void info(String msg, Throwable e) {
		logger.log(FQCN, Level.INFO, msg, e);
	}

	@Override
	public void warn(String msg) {
		logger.log(FQCN, Level.WARN, msg, null);
	}

	@Override
	public void warn(Throwable e) {
		logger.log(FQCN, Level.WARN, e == null ? null : e.getMessage(), e);
	}

	@Override
	public void warn(String msg, Throwable e) {
		logger.log(FQCN, Level.WARN, msg, e);
	}

	@Override
	public void error(String msg) {
		logger.log(FQCN, Level.ERROR, msg, null);
	}

	@Override
	public void error(Throwable e) {
		logger.log(FQCN, Level.ERROR, e == null ? null : e.getMessage(), e);
	}

	@Override
	public void error(String msg, Throwable e) {
		logger.log(FQCN, Level.ERROR, msg, e);
	}

	@Override
	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}

	@Override
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	@Override
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	@Override
	public boolean isWarnEnabled() {
		return logger.isEnabledFor(Level.WARN);
	}

	@Override
	public boolean isErrorEnabled() {
		return logger.isEnabledFor(Level.ERROR);
	}

	/*
	 * (non-Javadoc)
	 * @see org.ufa.log.Logger#warn(java.lang.String, java.lang.Object)
	 *  
	 */
	@Override
	public void warn(String format, Object arg) {
		FormattingTuple ft = MessageFormatter.format(format, arg);
		String formattedMessage = ft.getMessage();
		logger.log(FQCN, Level.WARN, formattedMessage, null);
	}

	/*
	 * (non-Javadoc)
	 * @see org.ufa.log.Logger#error(java.lang.String, java.lang.Object)
	 *  
	 */
	@Override
	public void error(String format, Object arg) {
		FormattingTuple ft = MessageFormatter.format(format, arg);
		String formattedMessage = ft.getMessage();
		logger.log(FQCN, Level.ERROR, formattedMessage, null);
	}

	@Override
	public void error(String format, Object... argArray) {
		FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
		String formattedMessage = ft.getMessage();
		logger.log(FQCN, Level.ERROR, formattedMessage, null);
		
	}

}