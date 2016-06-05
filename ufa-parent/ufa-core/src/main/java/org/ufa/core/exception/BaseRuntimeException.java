package org.ufa.core.exception;

import java.util.Date;
import java.util.Locale;

import org.springframework.core.NestedRuntimeException;

import org.ufa.core.exception.UfaExceptionSeverity;
import org.ufa.core.exception.api.IUfaException;


/**
 * ufa所有unchecked异常的基类,扩展NestedRuntimeException,自己对国际化消息key的支持
 * 
 * @see org.springframework.core.NestedRuntimeException
 *  
 * 
 */
public class BaseRuntimeException extends NestedRuntimeException implements IUfaException {
	private static final long			serialVersionUID	= -5900576260722009346L;

	/**
	 * 异常的唯一编号,对应国际化消息KEY
	 */
	protected String					code;
	/**
	 * 国际化资源所需的参数
	 */
	protected String[]					params;

	/**
	 * 运行系统的服务器的本机IP地址,这个属性对监控系统非常有用
	 */
	protected String					localAddress;
	/**
	 * 异常发生的时间
	 */
	protected Date						time;
	/**
	 * 产生异常的类名
	 */
	protected String					className;
	/**
	 * 产生异常的方法名
	 */
	protected String					methodName;
	/**
	 * 产生异常时,方法的参数值.注意,所有参数类型必须具备有效的toString()
	 */
	protected Object[]					parameters;
	/**
	 * 产生异常时,用户的唯一ID,通常是数据库中用户的ID值,便于通过此属性定位到具体用户
	 */
	protected String					userId;
	/**
	 * 产生异常时,用户的真实姓名,便于通过此属性快速定位用户,以了解情况
	 */
	protected String					username;

	/**
	 * 产生异常时的客户端IP地址,注意,如果用户通过了多次代理,可能导致此属性最终不是真实客户端IP,而是某个代理服务器的IP
	 */
	protected String					remoteAddress;
	/**
	 * 系统当前使用的language,通常用于国际化显示异常信息给运维人员
	 */
	protected Locale					locale;

	/**
	 * 异常的严重程度
	 */
	protected UfaExceptionSeverity	severity;
	/**
	 * 组装好的国际化消息,可用于打印日志信息
	 */
	protected String					i18nMessage;

	/**
	 * 标识是否已经打印过日志,可用于防止重复打印日志.true表示已经打印过日志.
	 */
	protected boolean					logged				= false;

	/**
	 * 标识是否已经被UfaAppExceptionHandlerInterceptor或者UfaWebExceptionHandlerInterceptor处理过.
	 */
	protected boolean					handled				= false;

	/**
	 * 是否需要被直接抛出spring的DispatchServlet.true表示是;默认是false.考虑到HandlerExceptionResolver会将异常
	 * 直接转到制定的view而不抛到DispatchServlet之外,导致上层的filter/listener catch不到某些被转换的异常
	 */
	protected boolean					throwOut			= false;

	/**
	 * <p>
	 * Method for constructor
	 * </p>
	 * 
	 * @param code
	 *            异常的全局唯一编号,可以作为国际化消息的KEY.不可以为NULL
	 * @param params
	 *            国际化消息中填充占位符的值,可以为NULL
	 * @param defaultMessage
	 *            默认显示的消息,当国际化消息文件中没有找到对应的条目时会显示此默认消息值,可以为NULL
	 */
	protected BaseRuntimeException(String code, String[] params, String defaultMessage) {
		super(defaultMessage);
		this.code = code;
		this.params = params;
	}

	/**
	 * <p>
	 * Method for constructor
	 * </p>
	 * 
	 * @param code
	 *            异常的全局唯一编号,可以作为国际化消息的KEY.不可以为NULL
	 * @param params
	 *            国际化消息中填充占位符的值,可以为NULL
	 * @param defaultMessage
	 *            默认显示的消息,当国际化消息文件中没有找到对应的条目时会显示此默认消息值,可以为NULL
	 * @param cause
	 *            需要包装的异常对象,不可以为NULL
	 */
	protected BaseRuntimeException(String code, String[] params, String defaultMessage, Throwable cause) {
		super(defaultMessage, cause);
		this.code = code;
		this.params = params;
	}

	/**
	 * <p>
	 * 在使用开发的应用系统中不建议使用此方法,一般为ufa自身使用
	 * </p>
	 * 
	 * @param defaultMessage
	 *            默认显示的消息,当国际化消息文件中没有找到对应的条目时会显示此默认消息值,可以为NULL
	 */
	protected BaseRuntimeException(String defaultMessage) {
		super(defaultMessage);
	}

	/**
	 * <p>
	 * 在使用ufa开发的应用系统中不建议使用此方法,一般为ufa自身使用
	 * </p>
	 * 
	 * @param defaultMessage
	 *            默认显示的消息,当国际化消息文件中没有找到对应的条目时会显示此默认消息值,可以为NULL
	 * @param cause
	 *            需要包装的异常对象,可以为NULL
	 */
	protected BaseRuntimeException(String defaultMessage, Throwable cause) {
		super(defaultMessage, cause);
	}

	
	@Override
	public String getCode() {
		return code;
	}


	@Override
	public String[] getParams() {
		return params;
	}

	
	@Override
	public String getLocalAddress() {
		return localAddress;
	}

	
	@Override
	public void setLocalAddress(String localAddress) {
		this.localAddress = localAddress;
	}

	
	@Override
	public Date getTime() {
		return time;
	}

	
	@Override
	public void setTime(Date time) {
		this.time = time;
	}


	@Override
	public String getClassName() {
		return className;
	}

	
	@Override
	public void setClassName(String className) {
		this.className = className;
	}

	
	@Override
	public String getMethodName() {
		return methodName;
	}

	
	@Override
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	
	@Override
	public Object[] getParameters() {
		return parameters;
	}

	
	@Override
	public void setParameters(String[] parameters) {
		this.parameters = parameters;
	}

	
	@Override
	public String getUserId() {
		return userId;
	}

	
	@Override
	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	@Override
	public String getUsername() {
		return username;
	}

	
	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	
	@Override
	public String getRemoteAddress() {
		return remoteAddress;
	}


	@Override
	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}


	@Override
	public Locale getLocale() {
		return locale;
	}


	@Override
	public void setLocale(Locale locale) {
		this.locale = locale;
	}


	@Override
	public UfaExceptionSeverity getSeverity() {
		return severity;
	}


	@Override
	public void setSeverity(UfaExceptionSeverity severity) {
		this.severity = severity;
	}


	@Override
	public String getI18nMessage() {
		return i18nMessage;
	}


	@Override
	public void setI18nMessage(String i18nMessage) {
		this.i18nMessage = i18nMessage;
	}

	@Override
	public boolean isLogged() {
		return logged;
	}


	@Override
	public void setLogged(boolean logged) {
		this.logged = logged;
	}


	@Override
	public boolean isHandled() {
		return handled;
	}


	@Override
	public void setHandled(boolean handled) {
		this.handled = handled;
	}

	@Override
	public boolean isThrowOut() {
		return throwOut;
	}

	
	@Override
	public void setThrowOut(boolean throwOut) {
		this.throwOut = throwOut;
	}

}
