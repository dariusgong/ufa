package org.ufa.security.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.www.NonceExpiredException;
import org.ufa.security.exception.AuthenticationCaptchaException;
import org.ufa.security.exception.UfaAuthenticationException;


public class CustomizedAuthenticationFailureHandler implements AuthenticationFailureHandler {

	protected final Log logger = LogFactory.getLog(getClass());

	private boolean forwardToDestination = false;
	private boolean allowSessionCreation = true;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private String defaultTargetUrl = null;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		saveException(request, exception);
		String targetUrl;
		if (defaultTargetUrl != null && !defaultTargetUrl.trim().equals("")) {
			targetUrl = defaultTargetUrl;
		} else {
			targetUrl = request.getHeader("referer");
		}

		if (forwardToDestination) {
			request.getRequestDispatcher(targetUrl).forward(request, response);
		} else {
			redirectStrategy.sendRedirect(request, response, targetUrl);
		}
	}

	protected final void saveException(HttpServletRequest request, AuthenticationException exception) {
		String key = null;
		if (exception instanceof DisabledException) {
			key = "authentication.error.account.disabled";
		} else if (exception instanceof AccountExpiredException) {
			key = "authentication.error.account.expired";
		} else if (exception instanceof LockedException) {
			key = "authentication.error.account.locked";
		} else if (exception instanceof NonceExpiredException) {
			key = "authentication.error.account.expired";
		} else if (exception instanceof PreAuthenticatedCredentialsNotFoundException) {
			key = "authentication.error.account.incorrect";
		} else if (exception instanceof BadCredentialsException) {
			key = "authentication.error.account.incorrect";
		} else if (exception instanceof UsernameNotFoundException) {
			key = "authentication.error.account.incorrect";
		} else if (exception instanceof AuthenticationCredentialsNotFoundException) {
			key = "authentication.error.account.incorrect";
		} else if (exception instanceof ProviderNotFoundException) {
			key = "authentication.error.service.exception";
		} else if (exception instanceof AuthenticationServiceException) {
			key = "authentication.error.service.exception";
		} else if (exception instanceof AuthenticationCaptchaException) {
			key = "authentication.error.captcha.incorrect";
		} else if (exception instanceof CredentialsExpiredException) {
			key = "authentication.error.credentials.expired";
		} else if (exception instanceof SessionAuthenticationException) {
			key = "authentication.error.service.exception";
		}

		if (forwardToDestination) {
			request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, new UfaAuthenticationException(key));
		} else {
			HttpSession session = request.getSession(false);

			if (session != null || allowSessionCreation) {
				request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
						new UfaAuthenticationException(key));
			}
		}
	}

	protected boolean isUseForward() {
		return forwardToDestination;
	}

	/**
	 * If set to <tt>true</tt>, performs a forward to the failure destination
	 * URL instead of a redirect. Defaults to <tt>false</tt>.
	 */
	public void setUseForward(boolean forwardToDestination) {
		this.forwardToDestination = forwardToDestination;
	}

	/**
	 * Allows overriding of the behaviour when redirecting to a target URL.
	 */
	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	protected boolean isAllowSessionCreation() {
		return allowSessionCreation;
	}

	public void setAllowSessionCreation(boolean allowSessionCreation) {
		this.allowSessionCreation = allowSessionCreation;
	}

	public String getDefaultTargetUrl() {
		return defaultTargetUrl;
	}

	public void setDefaultTargetUrl(String defaultTargetUrl) {
		this.defaultTargetUrl = defaultTargetUrl;
	}

}
