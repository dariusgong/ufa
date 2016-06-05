package org.ufa.log;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.ufa.log.jdk.JdkLoggerAdapter;
import org.ufa.log.log4j.Log4jLoggerAdapter;
import org.ufa.log.slf4j.Slf4jLoggerAdapter;
import org.ufa.log.support.FailsafeLogger;
import org.ufa.log.jcl.JclLoggerAdapter;


/**
 * 日志输出器工厂
 * 
 */
public class LoggerFactory {

	private LoggerFactory() {
	}

	static volatile LoggerAdapter LOGGER_ADAPTER;

	private static final ConcurrentMap<String, FailsafeLogger> LOGGERS = new ConcurrentHashMap<String, FailsafeLogger>();

	// 查找常用的日志框架
	static {
		String logger = System.getProperty("ufa-log.logger");
		findLoggerAdapter(logger);
	}

	/**
	 * <p>
	 * 根据传入的logger指定提供者名称查找对应的logger
	 * </p>
	 * 
	 * @param logger
	 *  
	 */
	private static void findLoggerAdapter(String logger) {
		if ("slf4j".equals(logger)) {
			setLoggerAdapter(new Slf4jLoggerAdapter());
		} else if ("jcl".equals(logger)) {
			setLoggerAdapter(new JclLoggerAdapter());
		} else if ("log4j".equals(logger)) {
			setLoggerAdapter(new Log4jLoggerAdapter());
		} else if ("jdk".equals(logger)) {
			setLoggerAdapter(new JdkLoggerAdapter());
		} else {
			try {
				setLoggerAdapter(new Slf4jLoggerAdapter());
			} catch (Throwable e1) {
				try {
					setLoggerAdapter(new Log4jLoggerAdapter());
				} catch (Throwable e2) {
					try {
						setLoggerAdapter(new JclLoggerAdapter());
					} catch (Throwable e3) {
						setLoggerAdapter(new JdkLoggerAdapter());
					}
				}
			}
		}
	}

	public static void setLoggerAdapter(String loggerAdapter) {
		if (loggerAdapter != null && loggerAdapter.length() > 0) {
			findLoggerAdapter(loggerAdapter);
		}
	}

	/**
	 * 设置日志输出器供给器
	 * 
	 * @param loggerAdapter
	 *            日志输出器供给器
	 */
	public static void setLoggerAdapter(LoggerAdapter loggerAdapter) {
		if (loggerAdapter != null) {
			Logger logger = loggerAdapter.getLogger(LoggerFactory.class.getName());
			logger.info("using logger: " + loggerAdapter.getClass().getName());
			LoggerFactory.LOGGER_ADAPTER = loggerAdapter;
			for (Map.Entry<String, FailsafeLogger> entry : LOGGERS.entrySet()) {
				entry.getValue().setLogger(LOGGER_ADAPTER.getLogger(entry.getKey()));
			}
		}
	}

	/**
	 * 获取日志输出器
	 * 
	 * @param key
	 *            分类键
	 * @return 日志输出器, 后验条件: 不返回null.
	 */
	public static Logger getLogger(Class<?> key) {
		FailsafeLogger logger = LOGGERS.get(key.getName());
		if (logger == null) {
			LOGGERS.putIfAbsent(key.getName(), new FailsafeLogger(LOGGER_ADAPTER.getLogger(key)));
			logger = LOGGERS.get(key.getName());
		}
		return logger;
	}

	/**
	 * 获取日志输出器
	 * 
	 * @param key 分类键
	 * @return 日志输出器, 后验条件: 不返回null.
	 */
	public static Logger getLogger(String key) {
		FailsafeLogger logger = LOGGERS.get(key);
		if (logger == null) {
			LOGGERS.putIfAbsent(key, new FailsafeLogger(LOGGER_ADAPTER.getLogger(key)));
			logger = LOGGERS.get(key);
		}
		return logger;
	}

	/**
	 * 动态设置输出日志级别
	 * 
	 * @param level 日志级别
	 */
	public static void setLevel(Level level) {
		LOGGER_ADAPTER.setLevel(level);
	}

	/**
	 * 获取日志级别
	 * 
	 * @return 日志级别
	 */
	public static Level getLevel() {
		return LOGGER_ADAPTER.getLevel();
	}

	/**
	 * 获取日志文件
	 * 
	 * @return 日志文件
	 */
	public static File getFile() {
		return LOGGER_ADAPTER.getFile();
	}

}