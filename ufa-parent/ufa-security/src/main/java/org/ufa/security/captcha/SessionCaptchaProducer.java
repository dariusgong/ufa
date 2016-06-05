/*******************************************************************************
 * Project   : ufa-security
 * Class Name: org.ufa.security.captcha.SessionCaptchaProducer
 * Created By: Jonathan 
 * Created on: 2013-7-19 下午10:44:57
 * Copyright © 2010-2012 KFT Pay All rights reserved.
 ******************************************************************************/
package org.ufa.security.captcha;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.support.RequestContextUtils;
import org.ufa.core.constant.ExceptionCode;
import org.ufa.core.exception.UfaCoreException;

import com.octo.captcha.Captcha;
import com.octo.captcha.service.CaptchaServiceException;



/**
 * <P>TODO</P>
 * @author Jonathan
 */
public class SessionCaptchaProducer extends AbstractCaptchaProducer {

	/**
	 * <p>TODO</p>
	 * @param request
	 * @param response
	 * @author Jonathan
	 */
	protected void generateCaptcha(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		Locale locale = RequestContextUtils.getLocale(request);
		String id = generateTicketId(session, request);
		Captcha captcha = engine.getNextCaptcha(locale);
		BufferedImage bi = getChallengeClone(captcha);
		logger.info("save captcha in session, id:{}", id);
		session.setAttribute(id, captcha);
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

	/*
	 * (non-Javadoc)
	 * @see org.ufa.security.captcha.api.ICaptchaProducer#validateCaptcha(javax.servlet.http.
	 * HttpServletRequest, java.lang.String)
	 * @author Jonathan
	 */
	@Override
	public boolean validateCaptcha(HttpServletRequest request, String captchaResponseText) {
		if (!isAvailable()) {
			return true;
		}
		HttpSession session = request.getSession(false);
		if (session == null) {
			return false;
		}
		String id = generateTicketId(session, request);
		boolean result = true;
		try {
			Captcha captcha = (Captcha) session.getAttribute(id);
			if (captcha == null)
				return false;
			result = captcha.validateResponse(captchaResponseText);
			// result = captchaProducer.validateResponseForID(id, captchaResponseText);
		} catch (CaptchaServiceException e) {
			result = false;
			logger.error(e.getMessage());
		} finally {
			logger.info("remove captcha from session, id:{}", id);
			session.removeAttribute(id);
		}

		return result;
	}

}
