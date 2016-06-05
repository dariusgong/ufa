package org.ufa.mail.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.util.Assert;
import org.ufa.core.exception.UfaBizUnCheckedException;
import org.ufa.mail.constant.ExceptionCode;
import org.ufa.mail.constant.MailConstant;
import org.ufa.mail.model.MailBaseModel;
import org.ufa.mail.model.MailModel;
import org.ufa.mail.service.api.MailService;
import org.ufa.util.StringUtils;


//@Service("defaultMailService")
public class DefaultMailServiceImpl implements MailService {
	protected final transient Logger logger = LoggerFactory.getLogger(DefaultMailServiceImpl.class);
	@Autowired
	@Qualifier("mailSender")
	private transient JavaMailSender sender;
	@Autowired
	@Qualifier("velocityEngine")
	private transient VelocityEngine velocityEngine;
	/**
	 * 默认目标地址
	 */
	@Value("${mail.to.default}")
	private transient String defaultTo;
	/**
	 * 寄件人地址
	 */
	@Value("${mail.from.default}")
	private transient String from;
	/**
	 * 默认邮件标题
	 */
	@Value("${mail.subject.default}")
	private transient String defaultSubject;

	@Value("${mail.defaultEncoding}")
	private transient String encoding;

	@Async
	@Override
	public void send(MailBaseModel mailModel, String templateName, String templateContent, Map<String, Object> data,
			Map<String, InputStreamSource> attachments, Map<String, Resource> inlineResources) {
		final MimeMessage m = sender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(m, true, encoding);
			setTo(mailModel, helper);
			setCC(mailModel, helper);
			helper.setFrom(from);
			setSubject(mailModel, helper);
			helper.setSentDate(new Date());
			setText(templateName, templateContent, data, helper);
			setAttachments(attachments, helper);
			setInline(inlineResources, helper);
			sender.send(m);
		} catch (MessagingException e) {
			throw new UfaBizUnCheckedException(ExceptionCode.SEND_MAP_MAIL_EXCEPTION_CODE, null, null, e);
		}

	}

	private void setText(String templateName, String templateContent, Map<String, Object> data, MimeMessageHelper helper)
			throws MessagingException {
		Assert.hasText(templateName);
		Assert.hasText(templateContent);
		StringResourceRepository repo = StringResourceLoader.getRepository();
		if (repo.getStringResource(templateName) == null) {
			repo.putStringResource(templateName, templateContent);
		}
		final String message = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName, helper.getEncoding(),
				data == null ? new HashMap<String, Object>(0) : data);

		helper.setText(message, true);

	}

	@Async
	@Override
	public void send(MailBaseModel baseModel, Map<String, Object> data, Map<String, InputStreamSource> attachments,
			Map<String, Resource> inlineResources, String templatePath) {
		final MimeMessage m = sender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(m, true, encoding);
			setTo(baseModel, helper);
			setCC(baseModel, helper);
			helper.setFrom(from);
			setSubject(baseModel, helper);
			helper.setSentDate(new Date());
			setText(data, templatePath, helper);
			setAttachments(attachments, helper);
			setInline(inlineResources, helper);
			sender.send(m);
		} catch (MessagingException e) {
			throw new UfaBizUnCheckedException(ExceptionCode.SEND_BASE_MODEL_MAIL_EXCEPTION_CODE, null, null, e);
		}
	}

	@Async
	@Override
	public void send(MailModel mailModel, Map<String, InputStreamSource> attachments, Map<String, Resource> inlineResources,
			String templatePath) {
		final MimeMessage m = sender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(m, true, encoding);
			setTo(mailModel, helper);
			setCC(mailModel, helper);
			helper.setFrom(from);
			setSubject(mailModel, helper);
			helper.setSentDate(new Date());
			setText(mailModel, templatePath, helper);
			setAttachments(attachments, helper);
			setInline(inlineResources, helper);
			sender.send(m);
		} catch (MessagingException e) {
			throw new UfaBizUnCheckedException(ExceptionCode.SEND_MAP_MAIL_EXCEPTION_CODE, null, null, e);
		}
	}

	/**
	 * 设置嵌入式资源
	 * 
	 * @param inlineResources
	 * @param helper
	 * @throws MessagingException
	 */
	private void setInline(Map<String, Resource> inlineResources, MimeMessageHelper helper) throws MessagingException {
		// 嵌入式资源使用Content-ID来插入到mime信件中去。
		// 你加入文本和资源的顺序是非常重要的。首先，你加入文本，随后是资源。
		// 如果顺序弄反了，它将无法正常运作！
		if (inlineResources != null && !inlineResources.isEmpty()) {
			for (Map.Entry<String, Resource> entry : inlineResources.entrySet()) {
				helper.addInline(entry.getKey(), entry.getValue());
			}
		}
	}

	/**
	 * 设置邮件附件
	 * 
	 * @param attachments
	 * @param helper
	 * @throws MessagingException
	 */
	private void setAttachments(Map<String, InputStreamSource> attachments, MimeMessageHelper helper) throws MessagingException {
		if (attachments != null && !attachments.isEmpty()) {
			for (Map.Entry<String, InputStreamSource> entry : attachments.entrySet()) {
				helper.addAttachment(entry.getKey(), entry.getValue());
			}
		}
	}

	/**
	 * 设置邮件正文
	 * 
	 * @param data
	 * @param templatePath
	 * @param helper
	 *  
	 * @throws MessagingException
	 */
	private void setText(Map<String, Object> data, String templatePath, MimeMessageHelper helper) throws MessagingException {
		final String message = getMessageByVelocityEngine(templatePath, helper, data);
		helper.setText(message, true);
	}

	/**
	 * 设置邮件正文
	 * 
	 * @param mailModel
	 * @param templatePath
	 * @param helper
	 * @throws MessagingException
	 */
	private void setText(MailModel mailModel, String templatePath, MimeMessageHelper helper) throws MessagingException {
		final Map<String, Object> model = new HashMap<String, Object>(1);
		// 必须结合Velocity的vm文件一起使用,key的值为vm中通配符中的前缀,例如
		// ${mailModel.subject},则key为mailModel
		model.put(MailConstant.MAIL_MODEL_KEY, mailModel);
		final String message = getMessageByVelocityEngine(templatePath, helper, model);
		helper.setText(message, true);
	}

	/**
	 * 通过Velocity模板引擎生成需要的消息体
	 * 
	 * @param templatePath 模板路径
	 * @param helper
	 * @param model 传递数据到邮件模板的MAP
	 * @return
	 * @throws VelocityException
	 *  
	 */
	private String getMessageByVelocityEngine(String templatePath, MimeMessageHelper helper, final Map<String, Object> model)
			throws VelocityException {
		String tmpPath = null;
		if (StringUtils.isNotBlank(templatePath)) {
			tmpPath = templatePath;
		} else {
			tmpPath = MailConstant.DEFAULT_MAIL_TAMPLATE;// 默认邮件模板
		}
		final String message = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, tmpPath, helper.getEncoding(),
				model == null ? new HashMap<String, Object>(0) : model);
		return message;
	}

	/**
	 * 设置邮件主题
	 * 
	 * @param mailModel
	 * @param helper
	 * @throws MessagingException
	 */
	private void setSubject(MailBaseModel mailModel, MimeMessageHelper helper) throws MessagingException {
		if (StringUtils.isNotBlank(mailModel.getSubject())) {
			helper.setSubject(mailModel.getSubject());
		} else {
			// 注意,默认的邮件标题是从properties文件中读取的,是iso-8859-1编码,需要转换成encoding属性指定的编码
			byte[] bytesIso8859_1 = StringUtils.getBytesIso8859_1(defaultSubject);
			String newString = StringUtils.newString(bytesIso8859_1, encoding);
			helper.setSubject(newString);
		}
	}

	/**
	 * 设置抄送人
	 * 
	 * @param mailModel
	 * @param helper
	 * @throws MessagingException
	 */
	private void setCC(MailBaseModel mailModel, MimeMessageHelper helper) throws MessagingException {
		final String[] cc = mailModel.getCc();
		if (cc != null && cc.length > 0) {
			helper.setCc(cc);
		}
	}

	/**
	 * 设置收件人
	 * 
	 * @param mailModel
	 * @param helper
	 * @throws MessagingException
	 */
	private void setTo(MailBaseModel mailModel, MimeMessageHelper helper) throws MessagingException {
		final String[] to = mailModel.getTo();
		if (to != null && to.length > 0) {
			helper.setTo(to);
		} else {
			helper.setTo(defaultTo);
		}
	}

	public void setSender(JavaMailSender sender) {
		this.sender = sender;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public void setTo(String to) {
		this.defaultTo = to;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setSubject(String subject) {
		this.defaultSubject = subject;
	}

}