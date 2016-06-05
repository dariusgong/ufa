/**
 * 
 */
package org.ufa.mail.model;

public class MailModel extends MailBaseModel {
	private static final long serialVersionUID = 2672397473159549500L;
	/**
	 * 邮件模板数据
	 */
	protected String message;

	/**
	 * <p>邮件模板数据</p>
	 * @return
	 *  
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * <p>邮件模板数据</p>
	 * @param message
	 *  
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}