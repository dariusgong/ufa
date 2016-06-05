/**
 * 
 */
package org.ufa.mail.constant;

/**
 * @author Jonathan
 *
 */
public interface MailConstant {
	/**
	 * 邮件模板的存放路径
	 */
	String MAIL_TAMPLATE_PATH = "mail/";

	/**
	 * @Fields MAIL_MODEL_KEY : 在模板中使用的model名
	 */
	String MAIL_MODEL_KEY = "mailModel";

	/**
	 * 默认邮件模板的路径
	 */
	String DEFAULT_MAIL_TAMPLATE = MAIL_TAMPLATE_PATH + "defaultTemplate.vm";
	/**
	 * 发送ERROR信息时使用的模板
	 */
	String ERROR_MAIL_TAMPLATE = MAIL_TAMPLATE_PATH + "errorMailTemplate.vm";
}
