package org.ufa.security.constant;

import org.ufa.core.annotation.Constant;


/**
 * <P>
 * ufa security项目常量类
 * </P>
 * 
 *  
 */
@Constant(namespace = "ufa-security")
public interface SecurityConstant {
	/**
	 * 验证码服务filter的url-pattern
	 */
	String CAPTCHA_FILTER_URL_PATTERN = "/captcha";
	/**
	 * 验证码ID由session id + 页面传递来的参数值组成,此属性即是页面传递参数时的参数名
	 */
	String CAPTCHA_TICKET_ID_SUFFIX = "_captcha_ticket_id_suffix";

	/**
	 * 企业用户登录成功默认赋予企业用户角色
	 */
	String ENTERPRISE_ROLE = "ENTERPRISE";

	/**
	 * 标识是生成验证码,还是校验验证码.如果HTTP请求中带有此参数则表示是校验;反之是生成
	 */
	String CAPTCHA_CHACK_PARAMETER = "_captcha_check_paramter";
	/**
	 * HTTP请求时保存验证码的参数名
	 */
	String CAPTCHA_TOKEN_PARAMTER = "_captcha_token_paramter";
	/**
	 * 用于识别唯一用户的cookie的name
	 */
	String AUTHENTICATION_TOKON_COOKIE_NAME = "_auth_token_name";

	/**
	 * 保存request详细信息的key
	 */
	String REQUEST_DETAIL_KEY = "requestDetail";

	/**
	 * 存放手机验证码的Key
	 */
	String MOBILE_CODE_KEY = "mobileCode";
}
