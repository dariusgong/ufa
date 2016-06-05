package org.ufa.mail.service.api;


import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.ufa.mail.model.MailBaseModel;
import org.ufa.mail.model.MailModel;



/**
 *
 * 发送mail的service的接口
 *  
 * 
 */
public interface MailService {

	/**
	 * 发送mail,通过自定义的实体对象传递所有数据(一般用于使用了StringResourceLoader的情况下)
	 * @param mailModel 要发送的消息model类,包含了邮件的模板数据,例如:mailModel.getMessage()
	 * @param templateName 邮件模板的名称(即唯一ID)
	 * @param templateContent 邮件模板的内容
	 * @param data 额外要通过邮件发送的信息
	 * @param attachments 附件,可以为文件或图片等资源,key为在邮件中显示的附件名.可以为null
	 * @param inlineResources 内嵌资源(图像或样式表等),需要配合邮件文本一起使用.可以为NULL
	 * @throws MessagingException 发送出现异常，调用者必须处理
	 */
	void send(MailBaseModel mailModel, String templateName, String templateContent, Map<String, Object> data,
			Map<String, InputStreamSource> attachments, Map<String, Resource> inlineResources);

	/**
	 * 发送mail,通过自定义的实体对象传递所有数据
	 * @param mailModel 要发送的消息model类,包含了消息信息
	 * @param attachments 附件,可以为文件或图片等资源,key为在邮件中显示的附件名.可以为null
	 * @param inlineResources 内嵌资源(图像或样式表等),需要配合邮件文本一起使用.可以为NULL
	 * @param templatePath 邮件模板相对classpath或者文件根路径的相对路径,例如:"conf/mail/template/defaultTemplate.vm".可以为NULL,为NULL时使用默认模板
	 * @throws MessagingException 发送出现异常，调用者必须处理
	 */
	void send(MailModel mailModel, Map<String, InputStreamSource> attachments, Map<String, Resource> inlineResources,
			String templatePath);

	/**
	 * 发送mail，通过MAP传递额外的数据
	 * @param baseModel 包括了发送邮件必不可少信息的实体
	 * @param data 额外要通过邮件发送的信息
	 * @param attachments 附件,可以为文件或图片等资源,key为在邮件中显示的附件名.可以为null.
	 * @param inlineResources 内嵌资源(图像或样式表等),需要配合邮件文本一起使用.可以为NULL
	 * @param templatePath 邮件模板相对classpath或者文件根路径的相对路径,例如:"conf/mail/template/defaultTemplate.vm".可以为NULL,为NULL时使用默认模板
	 * @throws MessagingException 发送出现异常，调用者必须处理
	 *  
	 */
	void send(MailBaseModel baseModel, Map<String, Object> data, Map<String, InputStreamSource> attachments,
			Map<String, Resource> inlineResources, String templatePath);

}
