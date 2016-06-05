/**
 * 
 */
package org.ufa.mail.model;

import org.ufa.core.model.BaseObject;

public class MailBaseModel extends BaseObject {
	private static final long serialVersionUID = 7596061054873128241L;
	/**
	 * 收件人的名字
	 */
	protected String username;
	/**
	 * 发件人地址
	 */
	protected String emailAddress;
	/**
	 * 收件人地址
	 */
	protected String[] to;
	/**
	 * 抄送人地址
	 */
	protected String[] cc;
	/**
	 * 主题
	 */
	protected String subject;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String[] getCc() {
		return cc;
	}

	public void setCc(String[] cc) {
		this.cc = cc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}