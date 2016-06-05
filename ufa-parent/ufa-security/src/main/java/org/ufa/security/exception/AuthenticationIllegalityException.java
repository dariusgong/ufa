package org.ufa.security.exception;

import org.springframework.security.core.AuthenticationException;


/**
 * <P>验证码输入错误或者没有输入则抛此异常</P>
 * <p>
 * 注意:此异常只提供给扩展了spring
 * security的类使用,在JSP页面上也通过如下方式获取国际化消息key:${SPRING_SECURITY_LAST_EXCEPTION.message}
 * </p>
 * 
 */
public class AuthenticationIllegalityException extends AuthenticationException {

	private static final long serialVersionUID = 6243588281339585948L;
	private static final String CODE = "ufa-security.Illegality";

	public AuthenticationIllegalityException() {
		super(CODE);
	}

	public AuthenticationIllegalityException(String msg) {
		super(msg);
	}
}
