/*******************************************************************************
 * Project   : ufa-security
 * Class Name: org.ufa.security.captcha.AbstractCaptchaProducer
 * Created By: Jonathan 
 * Created on: 2013-7-20 下午9:17:33
 * Copyright © 2010-2012 KFT Pay All rights reserved.
 ******************************************************************************/
package org.ufa.security.captcha;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.ufa.core.constant.ExceptionCode;
import org.ufa.core.exception.UfaCoreException;
import org.ufa.security.captcha.api.ICaptchaProducer;
import org.ufa.security.constant.SecurityConstant;

import com.octo.captcha.Captcha;
import com.octo.captcha.engine.CaptchaEngine;


/**
 * <P>TODO</P>
 * @author Jonathan
 */
public abstract class AbstractCaptchaProducer implements ICaptchaProducer {

	protected static final Logger logger = LoggerFactory.getLogger(SessionCaptchaProducer.class);

	/**
	 * 是否适用验证码.true表示使用.
	 */
	private boolean useCaptcha;

	protected CaptchaEngine engine;

	/**
	 * @param useCaptcha 是否使用验证码.true表示使用.
	 */
	public void setUseCaptcha(boolean useCaptcha) {
		this.useCaptcha = useCaptcha;
	}

	@Override
	public boolean isAvailable() {
		return this.useCaptcha;
	}

	public void setCaptchaEngineClass(String theClassName) throws IllegalArgumentException {
		try {
			Object engine = Class.forName(theClassName).newInstance();
			if (engine instanceof com.octo.captcha.engine.CaptchaEngine) {
				this.engine = (com.octo.captcha.engine.CaptchaEngine) engine;
			} else {
				throw new IllegalArgumentException("Class is not instance of CaptchaEngine! " + theClassName);
			}
		} catch (InstantiationException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (RuntimeException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.ufa.security.captcha.api.ICaptchaProducer#createCaptcha(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 * @author Jonathan
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
		generateCaptcha(request, response);

	}

	/**
	 * <p>子类提供创建和存储captcha的实现</p>
	 * @param request
	 * @param response
	 * @author Jonathan
	 */
	protected abstract void generateCaptcha(HttpServletRequest request, HttpServletResponse response);

	protected BufferedImage getChallengeClone(Captcha captcha) {
		BufferedImage challenge = (BufferedImage) captcha.getChallenge();
		BufferedImage clone = new BufferedImage(challenge.getWidth(), challenge.getHeight(), challenge.getType());

		clone.getGraphics().drawImage(challenge, 0, 0, clone.getWidth(), clone.getHeight(), null);
		clone.getGraphics().dispose();

		return clone;
	}

	/*
	 * (non-Javadoc)
	 * @see org.ufa.security.captcha.api.ICaptchaProducer#validateCaptcha(javax.servlet.http.
	 * HttpServletRequest, java.lang.String, org.springframework.validation.BindingResult)
	 * @author Jonathan
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

	/*
	 * (non-Javadoc)
	 * @see org.ufa.security.captcha.api.ICaptchaProducer#validateCaptcha(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * @author Jonathan
	 */
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

	/*
	 * (non-Javadoc)
	 * @see org.ufa.security.captcha.api.ICaptchaProducer#validateCaptcha(javax.servlet.http.
	 * HttpServletRequest)
	 * @author Jonathan
	 */
	@Override
	public boolean validateCaptcha(HttpServletRequest request) {
		if (!isAvailable()) {
			return true;
		}
		String captchaResponseText = request.getParameter(SecurityConstant.CAPTCHA_TOKEN_PARAMTER);
		return validateCaptcha(request, captchaResponseText);
	}

	protected String generateTicketId(HttpSession session, HttpServletRequest request) {
		String id = null;
		String parameter = request.getParameter(SecurityConstant.CAPTCHA_TICKET_ID_SUFFIX);
		if (StringUtils.hasText(parameter)) {
			id = session.getId() + "#" + parameter;
		} else {// 如果为空则要注意页面只能存在一个验证码,因为以sessionid为id,每次生成验证码都会覆盖之前的验证码
			id = session.getId();
		}
		return id;
	}
}
