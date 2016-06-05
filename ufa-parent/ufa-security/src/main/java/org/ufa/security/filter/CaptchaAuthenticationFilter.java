package org.ufa.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.Assert;
import org.ufa.security.captcha.api.ICaptchaProducer;
import org.ufa.security.exception.AuthenticationCaptchaException;
import org.ufa.security.exception.AuthenticationIllegalityException;
import org.ufa.security.exception.UfaAuthenticationException;


/**
 * <P> 在进入认证处理之前先校验提交的验证码是否正确,并对明文密码进行加密处理,先用自定义的加密器进行一次加密,然后基于base64算法,转换成string </P>
 *
 */
public class CaptchaAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	/**
	 * 在UsernamePasswordAuthenticationFilter中,密码保存在了string变量中,这是非常不安全的
	 * 因此,从request中取出时就立刻对其进行加密,然后再保存到string变量中
	 */
	// private PasswordEncoder passwordEncoder;
	/**
	 * 加密密码时,使用的字符集
	 */
	private String passwordCharset;
	/**
	 * 是否使用验证码.true表示使用.
	 */
	private boolean useCaptcha;

	private ICaptchaProducer captchaProducer;

	/**
	 * @param passwordEncoder the passwordEncoder to set
	 */
	// public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
	// this.passwordEncoder = passwordEncoder;
	// }

	/**
	 * @param passwordCharset the passwordCharset to set
	 */
	public void setPasswordCharset(String passwordCharset) {
		this.passwordCharset = passwordCharset;
	}

	/**
	 * @param useCaptcha 是否适用验证码.true表示使用.
	 */
	public void setUseCaptcha(boolean useCaptcha) {
		this.useCaptcha = useCaptcha;
	}

	/**
	 * @param captchaProducer the captchaProducer to set
	 */
	public void setCaptchaProducer(ICaptchaProducer captchaProducer) {
		this.captchaProducer = captchaProducer;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#
	 * attemptAuthentication(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

		UfaAuthenticationException e = (UfaAuthenticationException) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		String exceptionMessage = "";
		if (e != null) {
			exceptionMessage = e.getMessage();
		}
		String j_exception = request.getParameter("j_exception");
		if (StringUtils.isNotBlank(exceptionMessage) && !exceptionMessage.equals(j_exception)) {
			throw new AuthenticationIllegalityException();
		}

		if (useCaptcha && StringUtils.isNotBlank(exceptionMessage) && exceptionMessage.equals(j_exception)) {// 如果使用了验证码
			Assert.notNull(captchaProducer);
			boolean validateCaptcha = captchaProducer.validateCaptcha(request);
			if (!validateCaptcha) {
				// 根据UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY注释的建议,采用在AuthenticationFailureHandler中保存用户填写的信息返回到页面上
				// 具体见UfaExceptionMappingAuthenticationFailureHandler

				// //保存用户已经填写的登录名,避免重新填写
				/*
				 * String username = obtainUsername(request); // if (username == null) { // username
				 * = ""; // } // username = username.trim(); // // Place the last username attempted
				 * into HttpSession for views HttpSession session = request.getSession(false); // if
				 * (session != null || getAllowSessionCreation()) { //
				 * request.getSession().setAttribute(SPRING_SECURITY_LAST_USERNAME_KEY, username =
				 * TextEscapeUtils.escapeEntities(username); }
				 */
				throw new AuthenticationCaptchaException();
			}
		}
		return super.attemptAuthentication(request, response);
	}

	public ICaptchaProducer getCaptchaProducer() {
		return captchaProducer;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#
	 * obtainPassword(javax.servlet.http.HttpServletRequest)
	 */
	// @Override
	// protected String obtainPassword(HttpServletRequest request) {
	// // byte[] encodedLoginPassword = passwordEncoder.encode(super.obtainPassword(request),
	// passwordCharset, request);
	// byte[] encodedLoginPassword =super.obtainPassword(request).getBytes();
	// return Base64.encodeBase64String(encodedLoginPassword);
	// }

}
