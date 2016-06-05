package org.ufa.log.jcl;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.ufa.log.Logger;
import org.ufa.log.utils.FormattingTuple;
import org.ufa.log.utils.MessageFormatter;


/**
 * 适配CommonsLogging，依赖于commons-logging.jar <br/>
 * 有关CommonsLogging详细信息请参阅：<a target="_blank"
 * href="http://www.apache.org/">http://www.apache.org/</a>
 * 
 * 
 */
public class JclLogger implements Logger, Serializable {

	private static final long serialVersionUID = 1L;

	private final Log logger;

	public JclLogger(Log logger) {
		this.logger = logger;
	}

	@Override
	public void trace(String msg) {
		logger.trace(msg);
	}

	@Override
	public void trace(Throwable e) {
		logger.trace(e);
	}

	@Override
	public void trace(String msg, Throwable e) {
		logger.trace(msg, e);
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
		logger.trace(formattedMessage);
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
		logger.trace(formattedMessage);
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
		logger.debug(formattedMessage);
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
		logger.debug(formattedMessage);
	}

	@Override
	public void debug(String msg) {
		logger.debug(msg);
	}

	@Override
	public void debug(Throwable e) {
		logger.debug(e);
	}

	@Override
	public void debug(String msg, Throwable e) {
		logger.debug(msg, e);
	}

	@Override
	public void info(String msg) {
		logger.info(msg);
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
		logger.info(formattedMessage);
	}

	/*
	 * (non-Javadoc)
	 * @see org.ufa.log.Logger#info(java.lang.String, java.lang.Object[])
	 *  
	 */
	@Override
	public void info(String format,  Object... argArray) {
		FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
		String formattedMessage = ft.getMessage();
		logger.info(formattedMessage);
	}

	@Override
	public void info(Throwable e) {
		logger.info(e);
	}

	@Override
	public void info(String msg, Throwable e) {
		logger.info(msg, e);
	}

	@Override
	public void warn(String msg) {
		logger.warn(msg);
	}

	@Override
	public void warn(Throwable e) {
		logger.warn(e);
	}

	@Override
	public void warn(String msg, Throwable e) {
		logger.warn(msg, e);
	}

	@Override
	public void error(String msg) {
		logger.error(msg);
	}

	@Override
	public void error(Throwable e) {
		logger.error(e);
	}

	@Override
	public void error(String msg, Throwable e) {
		logger.error(msg, e);
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
		return logger.isWarnEnabled();
	}

	@Override
	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
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
		logger.warn(formattedMessage);
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
		logger.error(formattedMessage);
	}

	@Override
	public void error(String format, Object... argArray) {
		FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
		String formattedMessage = ft.getMessage();
		logger.error(formattedMessage);
		
	}

}
