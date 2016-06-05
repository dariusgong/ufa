package org.ufa.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.ufa.security.captcha.api.ICaptchaProducer;
import org.ufa.security.constant.SecurityConstant;


public class CaptchaFilter implements Filter {
	private ICaptchaProducer captchaProducer;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		// 生成验证码会close outputStream,所以先执行其他filter
		// 注意,后续filter可能commit response
		// filterChain.doFilter(request, response);
		if (request instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession(false);
			if (captchaProducer == null) {
				ServletContext servletContext = req.getSession(true).getServletContext();
				ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
				captchaProducer = (ICaptchaProducer) context.getBean("captchaProducer");
			}
			String parameter = req.getParameter(SecurityConstant.CAPTCHA_CHACK_PARAMETER);
			if (parameter == null) {
				captchaProducer.createCaptcha(req, (HttpServletResponse) response);
			} else {
				captchaProducer.validateCaptcha(req, (HttpServletResponse) response);
			}

		}
	}

	@Override
	public void destroy() {
		captchaProducer = null;
	}

}
