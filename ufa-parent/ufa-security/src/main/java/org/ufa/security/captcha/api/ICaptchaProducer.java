package org.ufa.security.captcha.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;


/**
 * <P>验证码接口</P>
 * 
 */
public interface ICaptchaProducer {
	
	/**
	 * <p>通过Kaptcha生成验证码图片和文字后写回ServletOutputStream</p>
	 * <p>
	 * 注意:目前生成的验证码是保存在session中对应常量KEY,所以每个session中只会保存一个验证码.
	 * 如果用户同时打开多个页面都显示验证码,则最后一个生产的验证码将覆盖之前所有的验证码. 在验证失败提示用户时,最好注明可能是由于验证码过期导致
	 * </p>
	 * 
	 * @param request
	 * @param response
	 *  
	 */
	void createCaptcha(HttpServletRequest request, HttpServletResponse response);

	/**
	 * <p> 验证码校验</p>
	 * <p>
	 * 注意,校验不通过后,将需要使用国际化消息来提醒用户,调用方的国际化资源文件中必须存在key为"security.error.invalidcaptcha"的条目
	 * </p>
	 * 
	 * @param request
	 * @param captchaResponseText
	 *            输入的验证码
	 * @return true表示验证通过;false表示验证失败.
	 *
	 */
	boolean validateCaptcha(HttpServletRequest request, String captchaResponseText);

	/**
	 * <p>
	 * 支持spring controller的验证方法,如果验证不通过,将会把错误信息设置到BindingResult中.调用者通过result.hasErrors()进行判断
	 * </p>
	 * <p>
	 * 注意,校验不通过后,将需要使用国际化消息来提醒用户,调用方的国际化资源文件中必须存在key为"security.error.invalidcaptcha"的条目
	 * </p>
	 * 
	 * @param request
	 * @param captchaResponseText
	 * @param result
	 *
	 */
	void validateCaptcha(HttpServletRequest request, String captchaResponseText, BindingResult result);

	/**
	 * <p>
	 * 支持直接在filter中对验证码进行校验,校验通过将返回字符串'true',否则返回字符串'false'
	 * </p>
	 * 
	 * @param req
	 * @param response
	 *
	 */
	void validateCaptcha(HttpServletRequest req, HttpServletResponse response);

	/**
	 * <p>
	 * 验证码校验
	 * </p>
	 * 
	 * @param req
	 * @return true表示验证通过;false表示验证失败.
	 *
	 */
	boolean validateCaptcha(HttpServletRequest req);

	/**
	 * <p>
	 * 验证码校验是否生效
	 * </p>
	 * 
	 * @return true表示生效;false表示不生效,即无论验证码是否正确,调用所有的validate都会顺利通过
	 *
	 */
	boolean isAvailable();
}
