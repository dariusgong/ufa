package org.ufa.mail;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.ufa.mail.constant.MailConstant;
import org.ufa.mail.model.MailBaseModel;
import org.ufa.mail.model.MailModel;
import org.ufa.mail.service.api.MailService;




@ContextConfiguration(locations = { "/test_mail.xml" })
public class DefaultMailServiceImplTest extends AbstractTestNGSpringContextTests {
	protected final transient Logger log = LoggerFactory.getLogger(DefaultMailServiceImplTest.class);

	@Autowired
	@Qualifier("defaultMailService")
	private transient MailService mailService;

	/**
	 * Test method for
	 * {@link com.natie.ufa.mail.DefaultMailServiceImpl#send(MailModel, Map, Map, String)}.
	 * 
	 * @throws MessagingException
	 * @throws InterruptedException
	 */
	//@Test
	public void testSendWithMailModel() throws MessagingException, InterruptedException {
		final MailModel model = assemblyMailModel();
		final Map<String, InputStreamSource> atts = assemblyAttachment();
		final Map<String, Resource> inlineResources = assemblyInlineImage();
		log.debug("======================开始调用异步发送邮件================");
		mailService.send(model, atts, inlineResources, null);
		log.debug("======================结束调用异步发送邮件================");
		Assert.assertTrue(true);
		Thread.sleep(3000);
	}

	/**
	 * Test method for
	 * {@link com.natie.ufa.mail.DefaultMailServiceImpl#send(MailBaseModel, Map, Map, Map, String)}
	 * @see com.natie.ufa.mail.DefaultMailServiceImpl#send(MailBaseModel, Map, Map, Map, String)
	 * @throws MessagingException
	 * @throws InterruptedException
	 */
	@Test
	public void testSendWithMap() throws MessagingException, InterruptedException {
		final MailBaseModel model = assemblyMailBaseModel();
		final Map<String, Object> map = new HashMap<String, Object>(1);
		map.put(MailConstant.MAIL_MODEL_KEY, model);
		map.put("message", "测试邮件模板,检查图片能否直接显示,中文是否乱码");
		final Map<String, InputStreamSource> atts = assemblyAttachment();
		final Map<String, Resource> inlineResources = assemblyInlineImage();
		log.debug("======================开始调用异步发送邮件================");
		mailService.send(model, map, atts, inlineResources, "mail/mapTestTemplate.vm");
		log.debug("======================结束调用异步发送邮件================");
		Assert.assertTrue(true);
		Thread.sleep(3000);
	}

	private MailBaseModel assemblyMailBaseModel() {
		final MailBaseModel model = new MailBaseModel();
		model.setUsername("darius");
		model.setTo(new String[] { "darius@126.com" });
		return model;
	}


	private MailModel assemblyMailModel() {
		final MailModel model = new MailModel();
		model.setUsername("darius");
		model.setTo(new String[] { "darius@126.com" });
		model.setMessage("!");
		return model;
	}


	private Map<String, Resource> assemblyInlineImage() {
		final Map<String, Resource> inlineResources = new HashMap<String, Resource>(1);
		inlineResources.put("identifier01", new ClassPathResource("com/natie/ufa/mail/login.jpg"));
		inlineResources.put("success1", new ClassPathResource("com/natie/ufa/mail/success1.jpg"));
		inlineResources.put("success2", new ClassPathResource("com/natie/ufa/mail/success2.jpg"));
		return inlineResources;
	}


	private Map<String, InputStreamSource> assemblyAttachment() {
		final Resource att1 = new ClassPathResource("com/natie/ufa/mail/pmd.txt");
		final Map<String, InputStreamSource> atts = new HashMap<String, InputStreamSource>(1);
		atts.put("att1", att1);
		return atts;
	}

}
