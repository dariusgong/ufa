import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

import org.testng.annotations.Test;
import org.ufa.log.Logger;
import org.ufa.log.LoggerFactory;
import org.ufa.security.captcha.SmallGMailEngine;

import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;


public class CaptchaTest {

	private Logger logger=LoggerFactory.getLogger(CaptchaTest.class);
	
	@Test
	public void testCaptchaImage() throws IOException{
		DefaultManageableImageCaptchaService instance = new DefaultManageableImageCaptchaService(); 
		File file=new File("d:\\image.jpg");
		ImageOutputStream fis=new FileImageOutputStream(file);
		SmallGMailEngine smallGMailEngine =new SmallGMailEngine();
		instance.setCaptchaEngine(smallGMailEngine);
		BufferedImage bi=instance.getImageChallengeForID("123");
		ImageIO.write(bi, "jpg", fis);
		logger.debug("");
	}
}
