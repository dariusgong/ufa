
package org.ufa.mail;

import java.util.HashMap;
import java.util.Map;

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
import org.ufa.mail.model.MailBaseModel;
import org.ufa.mail.service.api.MailService;




@ContextConfiguration(locations = { "/test_mail_stringloader.xml" })
public class DefaultMailServiceImpl2Test extends AbstractTestNGSpringContextTests {
	
	protected static final Logger logger = LoggerFactory.getLogger(DefaultMailServiceImpl2Test.class);
	@Autowired
	@Qualifier("defaultMailService")
	private transient MailService mailService;


	@Test
	public void testSendWithString() throws InterruptedException {
		final MailBaseModel model = assemblyMailBaseModel();
		final Map<String, Object> map = new HashMap<String, Object>(1);
		map.put("from", "test");
		final Map<String, InputStreamSource> atts = assemblyAttachment();
		final Map<String, Resource> inlineResources = assemblyInlineImage();
		logger.debug("======================开始调用异步发送邮件================");
		mailService.send(model, "hertz_email_template_01", "测试来自${from}的邮件!", map, atts, inlineResources);
		logger.debug("======================结束调用异步发送邮件================");
		Assert.assertTrue(true);
		Thread.sleep(3000);
	}


	private MailBaseModel assemblyMailBaseModel() {
		final MailBaseModel model = new MailBaseModel();
		model.setUsername("");
		model.setTo(new String[] { "darius@126.com" });
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
		//atts.put("attachement",new ByteArrayResource(IOUtils.toByteArray(inputStream)));
		return atts;
	}

}
