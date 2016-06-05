package org.ufa.security.captcha;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.ufa.core.constant.ExceptionCode;
import org.ufa.core.exception.UfaCoreException;
import org.ufa.security.captcha.api.ICaptchaProducer;
import org.ufa.security.constant.SecurityConstant;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;


/**
 * <P>基于jcaptcha,绑定spring mvc的实现,支持spring的LocaleResolver,可以根据不同的locale生成不同的验证码,适合国际化</P>
 * 
 */
public class JCaptchaProducer implements ICaptchaProducer {
	private static final Logger logger = LoggerFactory.getLogger(JCaptchaProducer.class);

	/**
	 * 是否适用验证码.true表示使用.
	 */
	private boolean useCaptcha;

	private ImageCaptchaService captchaProducer = null;

	/**
	 * @param useCaptcha
	 *            是否使用验证码.true表示使用.
	 */
	public void setUseCaptcha(boolean useCaptcha) {
		this.useCaptcha = useCaptcha;
	}

	public void setCaptchaProducer(ImageCaptchaService captchaProducer) {
		this.captchaProducer = captchaProducer;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ufa.security.web.captcha.CaptchaProducer#createCaptcha(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void createCaptcha(HttpServletRequest request, HttpServletResponse response) {
		// Set to expire far in the past.
		response.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		// return a jpeg
		response.setContentType("image/jpeg");
		// create the image with the text
		HttpSession session = request.getSession(true);
		Locale locale = RequestContextUtils.getLocale(request);
		String id = generateTicketId(session, request);
		BufferedImage bi = captchaProducer.getImageChallengeForID(id, locale);
		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
			// write the data out
			ImageIO.write(bi, "jpg", out);
			out.flush();
			if (logger.isDebugEnabled()) {
				logger.debug("生成了ID=[{}]的验证码", id);
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new UfaCoreException(ExceptionCode.JCAPTCHA_OUTPUT_EXCEPTION_CODE, null, null, e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
	}

	private String generateTicketId(HttpSession session, HttpServletRequest request) {
		String id = null;
		String parameter = request.getParameter(SecurityConstant.CAPTCHA_TICKET_ID_SUFFIX);
		if (StringUtils.hasText(parameter)) {
			id = session.getId() + "#" + parameter;
		} else {// 如果为空则要注意页面只能存在一个验证码,因为以sessionid为id,每次生成验证码都会覆盖之前的验证码
			id = session.getId();
		}
		return id;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ufa.security.web.captcha.CaptchaProducer#validateCaptcha(javax.servlet.http
	 * .HttpServletRequest, java.lang.String)
	 */
	@Override
	public boolean validateCaptcha(HttpServletRequest request, String captchaResponseText) {
		if (!isAvailable()) {
			return true;
		}
		HttpSession session = request.getSession(false);
		boolean result = true;
		if (session == null) {
			result = false;
		} else {
			try {
				String id = generateTicketId(session, request);
				result = captchaProducer.validateResponseForID(id, captchaResponseText);
			} catch (CaptchaServiceException e) {
				result = false;
				logger.error(e.getMessage());
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc) <p>Title: validateCaptcha</p>
	 *
	 * @see
	 * org.ufa.security.web.captcha.CaptchaProducer#validateCaptcha(javax.servlet.http
	 * .HttpServletRequest, java.lang.String, org.springframework.validation.BindingResult)
	 */
	@Override
	public void validateCaptcha(HttpServletRequest request, String captchaResponseText, BindingResult result) {
		if (isAvailable()) {
			boolean validateResult = validateCaptcha(request, captchaResponseText);
			if (!validateResult) {
				result.rejectValue("captchaResponse", "security.error.invalidcaptcha", "输入的验证码错误或验证码已过期");
			}
		}
	}

	@Override
	public void validateCaptcha(HttpServletRequest request, HttpServletResponse response) {
		// Set to expire far in the past.
		response.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		// return a jpeg
		response.setContentType("text/html;charset=UTF-8");
		String captchaResponseText = request.getParameter(SecurityConstant.CAPTCHA_TOKEN_PARAMTER);
		boolean validateCaptcha = true;
		if (isAvailable()) {
			validateCaptcha = validateCaptcha(request, captchaResponseText);
		}
		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
			out.print(validateCaptcha);
			out.flush();
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new UfaCoreException(ExceptionCode.JCAPTCHA_OUTPUT_EXCEPTION_CODE, null, null, e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
	}

	@Override
	public boolean validateCaptcha(HttpServletRequest request) {
		if (!isAvailable()) {
			return true;
		}
		String captchaResponseText = request.getParameter(SecurityConstant.CAPTCHA_TOKEN_PARAMTER);
		return validateCaptcha(request, captchaResponseText);
	}

	@Override
	public boolean isAvailable() {
		return this.useCaptcha;
	}
}
