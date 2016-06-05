package org.ufa.log;

public interface Logger {

	/**
	 * 输出跟踪信息
	 * 
	 * @param msg 信息内容
	 */
	void trace(String msg);

	/**
	 * Log a message at the TRACE level according to the specified format
	 * and argument.
	 * 
	 * <p>
	 * This form avoids superfluous object creation when the logger is disabled for the TRACE level.
	 * </p>
	 * 
	 * @param format the format string
	 * @param arg the argument
	 * 
	 */
	void trace(String format, Object arg);

	/**
	 * Log a message at the TRACE level according to the specified format
	 * and arguments.
	 * 
	 * <p>
	 * This form avoids superfluous object creation when the logger is disabled for the TRACE level.
	 * </p>
	 * 
	 * @param format the format string
	 * @param argArray an array of arguments
	 * 
	 */
	void trace(String format, Object[] argArray);

	/**
	 * 输出跟踪信息
	 * 
	 * @param e 异常信息
	 */
	void trace(Throwable e);

	/**
	 * 输出跟踪信息
	 * 
	 * @param msg 信息内容
	 * @param e 异常信息
	 */
	void trace(String msg, Throwable e);

	/**
	 * 输出调试信息
	 * 
	 * @param msg 信息内容
	 */
	void debug(String msg);

	/**
	 * Log a message at the DEBUG level according to the specified format
	 * and argument.
	 * 
	 * <p>
	 * This form avoids superfluous object creation when the logger is disabled for the DEBUG level.
	 * </p>
	 * 
	 * @param format the format string
	 * @param arg the argument
	 */
	void debug(String format, Object arg);
	

	/**
	 * Log a message at the DEBUG level according to the specified format
	 * and arguments.
	 * 
	 * <p>
	 * This form avoids superfluous object creation when the logger is disabled for the DEBUG level.
	 * </p>
	 * 
	 * @param format the format string
	 * @param argArray an array of arguments
	 */
	void debug(String format, Object... argArray);

	/**
	 * 输出调试信息
	 * 
	 * @param e 异常信息
	 */
	void debug(Throwable e);

	/**
	 * 输出调试信息
	 * 
	 * @param msg 信息内容
	 * @param e 异常信息
	 */
	void debug(String msg, Throwable e);

	/**
	 * 输出普通信息
	 * 
	 * @param msg 信息内容
	 */
	void info(String msg);

	/**
	 * Log a message at the INFO level according to the specified format
	 * and argument.
	 * 
	 * <p>
	 * This form avoids superfluous object creation when the logger is disabled for the INFO level.
	 * </p>
	 * 
	 * @param format the format string
	 * @param arg the argument
	 */
	public void info(String format, Object arg);

	/**
	 * Log a message at the INFO level according to the specified format
	 * and arguments.
	 * 
	 * <p>
	 * This form avoids superfluous object creation when the logger is disabled for the INFO level.
	 * </p>
	 * 
	 * @param format the format string
	 * @param argArray an array of arguments
	 */
	public void info(String format, Object...arg);
	
	
	/**
	 * 输出普通信息
	 * 
	 * @param e 异常信息
	 */
	void info(Throwable e);

	/**
	 * 输出普通信息
	 * 
	 * @param msg 信息内容
	 * @param e 异常信息
	 */
	void info(String msg, Throwable e);

	/**
	 * 输出警告信息
	 * 
	 * @param msg 信息内容
	 */
	void warn(String msg);

	/**
	 * Log a message at the WARN level according to the specified format
	 * and argument.
	 * 
	 * <p>
	 * This form avoids superfluous object creation when the logger is disabled for the WARN level.
	 * </p>
	 * 
	 * @param format the format string
	 * @param arg the argument
	 */
	void warn(String format, Object arg);

	/**
	 * 输出警告信息
	 * 
	 * @param e 异常信息
	 */
	void warn(Throwable e);

	/**
	 * 输出警告信息
	 * 
	 * @param msg 信息内容
	 * @param e 异常信息
	 */
	void warn(String msg, Throwable e);

	/**
	 * 输出错误信息
	 * 
	 * @param msg 信息内容
	 */
	void error(String msg);

	/**
	 * Log a message at the ERROR level according to the specified format
	 * and argument.
	 * 
	 * <p>
	 * This form avoids superfluous object creation when the logger is disabled for the ERROR level.
	 * </p>
	 * 
	 * @param format the format string
	 * @param arg the argument
	 */
	void error(String format, Object arg);

	void error(String format, Object... argArray);
	
	
	/**
	 * 输出错误信息
	 * 
	 * @param e 异常信息
	 */
	void error(Throwable e);

	/**
	 * 输出错误信息
	 * 
	 * @param msg 信息内容
	 * @param e 异常信息
	 */
	void error(String msg, Throwable e);
	
	/**
	 * 跟踪信息是否开启
	 * 
	 * @return 是否开启
	 */
	boolean isTraceEnabled();

	/**
	 * 调试信息是否开启
	 * 
	 * @return 是否开启
	 */
	boolean isDebugEnabled();

	/**
	 * 普通信息是否开启
	 * 
	 * @return 是否开启
	 */
	boolean isInfoEnabled();

	/**
	 * 警告信息是否开启
	 * 
	 * @return 是否开启
	 */
	boolean isWarnEnabled();

	/**
	 * 错误信息是否开启
	 * 
	 * @return 是否开启
	 */
	boolean isErrorEnabled();

}