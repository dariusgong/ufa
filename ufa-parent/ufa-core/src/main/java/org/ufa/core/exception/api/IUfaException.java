package org.ufa.core.exception.api;

import java.util.Date;
import java.util.Locale;

import org.ufa.core.exception.UfaExceptionSeverity;


public interface IUfaException {
	/**
	 * <p>
	 * 全局唯一的异常编号
	 * </p>
	 */
	String getCode();

	/**
	 * 国际化资源所需的参数
	 */
	String[] getParams();

	/**
	 * @param logged
	 *            标识是否已经打印过日志,可用于防止重复打印日志.true表示已经打印过日志.
	 */
	void setLogged(boolean logged);

	/**
	 * 标识是否已经打印过日志,可用于防止重复打印日志.true表示已经打印过日志
	 */
	boolean isLogged();

	/**
	 * @param localAddress
	 *            运行系统的服务器的本机IP地址,这个属性对监控系统非常有用
	 */
	void setLocalAddress(String localAddress);

	/**
	 * 运行系统的服务器的本机IP地址,这个属性对监控系统非常有用
	 */
	String getLocalAddress();

	/**
	 * @param time
	 *            异常发生的时间
	 */
	void setTime(Date time);

	/**
	 * 异常发生的时间
	 */
	Date getTime();

	/**
	 * @param className
	 *            产生异常的类名,可能并不是最初抛出异常的类,而是最终调用者
	 */
	void setClassName(String className);

	/**
	 * 产生异常的类名,可能并不是最初抛出异常的类,而是最终调用者
	 */
	String getClassName();

	/**
	 * @param methodName
	 *            产生异常的方法名
	 */
	void setMethodName(String methodName);

	/**
	 * 产生异常的方法名
	 */
	String getMethodName();

	/**
	 * @param parameters
	 *            产生异常时,方法的参数值.注意,所有参数类型必须具备有效的toString()
	 */
	void setParameters(String[] parameters);

	/**
	 * 产生异常时,方法的参数值.注意,所有参数类型必须具备有效的toString()
	 */
	Object[] getParameters();

	/**
	 * @param userId
	 *            产生异常时,用户的唯一ID,通常是数据库中用户的ID值,便于通过此属性定位到具体用户
	 */
	void setUserId(String userId);

	/**
	 * 产生异常时,用户的唯一ID,通常是数据库中用户的ID值,便于通过此属性定位到具体用户
	 */
	String getUserId();

	/**
	 * @param username
	 *            产生异常时,用户的真实姓名,便于通过此属性快速定位用户,以了解情况
	 */
	void setUsername(String username);

	/**
	 * 产生异常时,用户的真实姓名,便于通过此属性快速定位用户,以了解情况
	 */
	String getUsername();

	/**
	 * @param remoteAddress
	 *            产生异常时的客户端IP地址,注意,如果用户通过了多次代理,可能导致此属性最终不是真实客户端IP,而是某个代理服务器的IP
	 */
	void setRemoteAddress(String remoteAddress);

	/**
	 * 产生异常时的客户端IP地址,注意,如果用户通过了多次代理,可能导致此属性最终不是真实客户端IP,而是某个代理服务器的IP
	 */
	String getRemoteAddress();

	/**
	 * @param locale
	 *            系统当前使用的language,通常用于国际化显示异常信息给运维人员
	 */
	void setLocale(Locale locale);

	/**
	 * 系统当前使用的language,通常用于国际化显示异常信息给运维人员
	 */
	Locale getLocale();

	/**
	 * @param severity
	 *            异常的严重程度,可以为NULL
	 */
	void setSeverity(UfaExceptionSeverity severity);

	/**
	 * 异常的严重程度,可能为NULL
	 */
	UfaExceptionSeverity getSeverity();

	/**
	 * @param message
	 *            组装好的国际化消息,可用于打印日志信息
	 */
	void setI18nMessage(String i18nMessage);

	/**
	 * 组装好的国际化消息,可用于打印日志信息
	 */
	String getI18nMessage();

	/**
	 * @param handled
	 *            标识是否已经被UfaAppExceptionHandlerInterceptor或者UfaWebExceptionHandlerInterceptor处理过
	 *            .
	 */
	void setHandled(boolean logged);

	/**
	 * 标识是否已经被UfaAppExceptionHandlerInterceptor或者UfaWebExceptionHandlerInterceptor处理过.
	 */
	boolean isHandled();

	/**
	 * @return 是否需要被直接抛出spring的DispatchServlet.true表示是;默认是false.考虑到HandlerExceptionResolver会将异常
	 *         直接转到制定的view而不抛到DispatchServlet之外,导致上层的filter/listener catch不到某些被转换的异常
	 */
	boolean isThrowOut();

	/**
	 * @param throwOut
	 *            是否需要被直接抛出spring的DispatchServlet.true表示是;默认是false.考虑到HandlerExceptionResolver会将异常
	 *            直接转到制定的view而不抛到DispatchServlet之外,导致上层的filter/listener catch不到某些被转换的异常
	 */
	void setThrowOut(boolean throwOut);
}
