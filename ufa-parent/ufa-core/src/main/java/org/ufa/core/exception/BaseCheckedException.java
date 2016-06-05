/*******************************************************************************
 * Project   : ufa-core
 * Class Name: org.ufa.core.exception.BaseCheckedException
 * Created By: Jonathan 
 * Created on: 2013-7-18 下午8:44:53
 * Copyright © 2010-2012 KFT Pay All rights reserved.
 ******************************************************************************/
package org.ufa.core.exception;

import java.util.Date;
import java.util.Locale;

import org.springframework.core.NestedCheckedException;
import org.ufa.core.exception.api.IUfaException;

/**
 * <P>TODO</P>
 * @author Jonathan
 */
public class BaseCheckedException extends BaseRuntimeException implements IUfaException{

	/**
	 * TODO
	 */
	private static final long serialVersionUID = 2761366658837574378L;
	/**
	 * 异常的唯一编号,对应国际化消息KEY
	 */
	protected String code;
	/**
	 * 国际化资源所需的参数
	 */
	protected String[] params;

	/**
	 * 运行系统的服务器的本机IP地址,这个属性对监控系统非常有用
	 */
	protected String localAddress;
	/**
	 * 异常发生的时间
	 */
	protected Date time;
	/**
	 * 产生异常的类名
	 */
	protected String className;
	/**
	 * 产生异常的方法名
	 */
	protected String methodName;
	/**
	 * 注意:这里不能是object类型,否则会导致远程调用时,调用方被迫反序列化这些参数,从而导致class not found异常
	 * 产生异常时,方法的参数值.注意,所有参数类型必须具备有效的toString()
	 */
	protected String[] parameters;
	/**
	 * 产生异常时,用户的唯一ID,通常是数据库中用户的ID值,便于通过此属性定位到具体用户
	 */
	protected String userId;
	/**
	 * 产生异常时,用户的真实姓名,便于通过此属性快速定位用户,以了解情况
	 */
	protected String username;

	/**
	 * 产生异常时的客户端IP地址,注意,如果用户通过了多次代理,可能导致此属性最终不是真实客户端IP,而是某个代理服务器的IP
	 */
	protected String remoteAddress;
	/**
	 * 系统当前使用的language,通常用于国际化显示异常信息给运维人员
	 */
	protected Locale locale;

	/**
	 * 异常的严重程度
	 */
	protected UfaExceptionSeverity severity;
	/**
	 * 组装好的国际化消息,可用于打印日志信息
	 */
	protected String i18nMessage;

	/**
	 * 标识是否已经打印过日志,可用于防止重复打印日志.true表示已经打印过日志.
	 */
	protected boolean logged = false;

	/**
	 * 标识是否已经被UfaAppExceptionHandlerInterceptor或者UfaWebExceptionHandlerInterceptor处理过.
	 */
	protected boolean handled = false;

	/**
	 * 是否需要被直接抛出spring的DispatchServlet.true表示是.考虑到HandlerExceptionResolver会将异常
	 * 直接转到制定的view而不抛到DispatchServlet之外,导致上层的filter/listener catch不到某些被转换的异常
	 */
	protected boolean throwOut = false;

	/**
	 * <p> Method for constructor </p>
	 * 
	 * @param code 异常的全局唯一编号,可以作为国际化消息的KEY.不可以为NULL
	 * @param params 国际化消息中填充占位符的值,可以为NULL
	 * @param defaultMessage 默认显示的消息,当国际化消息文件中没有找到对应的条目时会显示此默认消息值,可以为NULL
	 */
	protected BaseCheckedException(String code, String[] params, String defaultMessage) {
		super(defaultMessage);
		this.code = code;
		this.params = params;
	}

	/**
	 * <p> Method for constructor </p>
	 * 
	 * @param code 异常的全局唯一编号,可以作为国际化消息的KEY.不可以为NULL
	 * @param params 国际化消息中填充占位符的值,可以为NULL
	 * @param defaultMessage 默认显示的消息,当国际化消息文件中没有找到对应的条目时会显示此默认消息值,可以为NULL
	 * @param cause 需要包装的异常对象,不可以为NULL
	 */
	protected BaseCheckedException(String code, String[] params, String defaultMessage, Throwable cause) {
		super(defaultMessage, cause);
		this.code = code;
		this.params = params;
	}

	/**
	 * <p> 在使用ufa开发的应用系统中不建议使用此方法,一般为ufa自身使用 </p>
	 * 
	 * @param defaultMessage 默认显示的消息,当国际化消息文件中没有找到对应的条目时会显示此默认消息值,可以为NULL
	 * @param cause 需要包装的异常对象,可以为NULL
	 */
	protected BaseCheckedException(String defaultMessage, Throwable cause) {
		super(defaultMessage, cause);
	}

	/**
	 * <p> 在使用ufa开发的应用系统中不建议使用此方法,一般为ufa自身使用 </p>
	 * 
	 * @param defaultMessage 默认显示的消息,当国际化消息文件中没有找到对应的条目时会显示此默认消息值,可以为NULL
	 */
	protected BaseCheckedException(String defaultMessage) {
		super(defaultMessage);
	}

	/**
	 * @return 全局唯一的异常编号,对应国际化消息KEY
	 */
	@Override
	public String getCode() {
		return code;
	}

	/**
	 * @return 国际化资源所需的参数
	 */
	@Override
	public String[] getParams() {
		return params;
	}

	/**
	 * @return 运行系统的服务器的本机IP地址,这个属性对监控系统非常有用
	 */
	@Override
	public String getLocalAddress() {
		return localAddress;
	}

	/**
	 * @param localAddress 运行系统的服务器的本机IP地址,这个属性对监控系统非常有用
	 */
	@Override
	public void setLocalAddress(String localAddress) {
		this.localAddress = localAddress;
	}

	/**
	 * @return 异常发生的时间
	 */
	@Override
	public Date getTime() {
		return time;
	}

	/**
	 * @param time 异常发生的时间
	 */
	@Override
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * @return 产生异常的类名,可能并不是最初抛出异常的类,而是最终调用者
	 */
	@Override
	public String getClassName() {
		return className;
	}

	/**
	 * @param className 产生异常的类名,可能并不是最初抛出异常的类,而是最终调用者
	 */
	@Override
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return 产生异常的方法名
	 */
	@Override
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @param methodName 产生异常的方法名
	 */
	@Override
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/*
	 * (non-Javadoc)
	 * @see org.ufa.core.exception.api.IUfaException#getParameters()
	 * @author 
	 */
	@Override
	public String[] getParameters() {
		return parameters;
	}

	/*
	 * (non-Javadoc)
	 * @see org.ufa.core.exception.api.IUfaException#setParameters(java.lang.String[])
	 * @author 
	 */
	@Override
	public void setParameters(String[] parameters) {
		this.parameters = parameters;
	}

	/**
	 * @return 产生异常时,用户的唯一ID,通常是数据库中用户的ID值,便于通过此属性定位到具体用户
	 */
	@Override
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId 产生异常时,用户的唯一ID,通常是数据库中用户的ID值,便于通过此属性定位到具体用户
	 */
	@Override
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return 产生异常时,用户的真实姓名,便于通过此属性快速定位用户,以了解情况
	 */
	@Override
	public String getUsername() {
		return username;
	}

	/**
	 * @param username 产生异常时,用户的真实姓名,便于通过此属性快速定位用户,以了解情况
	 */
	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return 产生异常时的客户端IP地址,注意,如果用户通过了多次代理,可能导致此属性最终不是真实客户端IP,而是某个代理服务器的IP
	 */
	@Override
	public String getRemoteAddress() {
		return remoteAddress;
	}

	/**
	 * @param remoteAddress 产生异常时的客户端IP地址,注意,如果用户通过了多次代理,可能导致此属性最终不是真实客户端IP,而是某个代理服务器的IP
	 */
	@Override
	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	/**
	 * @return 系统当前使用的language,通常用于国际化显示异常信息给运维人员
	 */
	@Override
	public Locale getLocale() {
		return locale;
	}

	/**
	 * @param locale 系统当前使用的language,通常用于国际化显示异常信息给运维人员
	 */
	@Override
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	/**
	 * @return 异常的严重程度,可能为NULL
	 */
	@Override
	public UfaExceptionSeverity getSeverity() {
		return severity;
	}

	/**
	 * @param severity 异常的严重程度,可以为NULL
	 */
	@Override
	public void setSeverity(UfaExceptionSeverity severity) {
		this.severity = severity;
	}

	/**
	 * @return 组装好的国际化消息,可用于打印日志信息
	 */
	@Override
	public String getI18nMessage() {
		return i18nMessage;
	}

	/**
	 * @param message 组装好的国际化消息,可用于打印日志信息
	 */
	@Override
	public void setI18nMessage(String i18nMessage) {
		this.i18nMessage = i18nMessage;
	}

	/**
	 * @return 标识是否已经打印过日志,可用于防止重复打印日志.true表示已经打印过日志.
	 */
	@Override
	public boolean isLogged() {
		return logged;
	}

	/**
	 * @param logged 标识是否已经打印过日志,可用于防止重复打印日志.true表示已经打印过日志.
	 */
	@Override
	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	/**
	 * @return the handled
	 */
	@Override
	public boolean isHandled() {
		return handled;
	}

	/**
	 * @param handled the handled to set
	 */
	@Override
	public void setHandled(boolean handled) {
		this.handled = handled;
	}

	/**
	 * @return 是否需要被直接抛出spring的DispatchServlet.true表示是.考虑到HandlerExceptionResolver会将异常
	 *         直接转到制定的view而不抛到DispatchServlet之外,导致上层的filter/listener catch不到某些被转换的异常
	 */
	@Override
	public boolean isThrowOut() {
		return throwOut;
	}

	/**
	 * @param throwOut 是否需要被直接抛出spring的DispatchServlet.true表示是.考虑到HandlerExceptionResolver会将异常
	 *            直接转到制定的view而不抛到DispatchServlet之外,导致上层的filter/listener catch不到某些被转换的异常
	 */
	@Override
	public void setThrowOut(boolean throwOut) {
		this.throwOut = throwOut;
	}
}
