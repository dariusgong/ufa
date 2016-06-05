package org.ufa.security.captcha;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import com.jhlabs.image.PinchFilter;
import com.jhlabs.math.ImageFunction2D;
import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomListColorGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.deformation.ImageDeformationByBufferedImageOp;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.GlyphsPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.glyphsvisitor.GlyphsVisitors;
import com.octo.captcha.component.image.textpaster.glyphsvisitor.OverlapGlyphsUsingShapeVisitor;
import com.octo.captcha.component.image.textpaster.glyphsvisitor.RotateGlyphsRandomVisitor;
import com.octo.captcha.component.image.textpaster.glyphsvisitor.TranslateAllToRandomPointVisitor;
import com.octo.captcha.component.image.textpaster.glyphsvisitor.TranslateGlyphsVerticalRandomVisitor;
import com.octo.captcha.component.image.wordtoimage.DeformedComposedWordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;


/**
 * <P>  扩展google验证码,改为从固定的字符和数字中取文本 </P>
 * 
 */
public class SimpleCaptchaEngine extends ListImageCaptchaEngine {

	@Override
	protected void buildInitialFactories() {
		// word generator
		com.octo.captcha.component.word.wordgenerator.WordGenerator dictionnaryWords = new RandomWordGenerator(
				"abcdefghjkmnpqrstuvwxyz23456789");
		// wordtoimage components
		TextPaster randomPaster = new GlyphsPaster(4, 4, new RandomListColorGenerator(new Color[] {
				new Color(23, 170, 27), new Color(220, 34, 11), new Color(23, 67, 172) }), new GlyphsVisitors[] {
			new TranslateGlyphsVerticalRandomVisitor(1.0D), new OverlapGlyphsUsingShapeVisitor(1D), new TranslateAllToRandomPointVisitor()  });
		/*
		 * new TextVisitor[]{
		 * new OverlapGlyphsTextVisitor(6)
		 * }, null
		 */
		BackgroundGenerator back = new UniColorBackgroundGenerator(45,25, Color.white);

		FontGenerator shearedFont = new RandomFontGenerator(16, 16, new Font[] { new Font("nyala", Font.BOLD, 50),
				new Font("Bell MT", Font.PLAIN, 50), new Font("Credit valley", Font.BOLD, 50) }, false);

		PinchFilter pinch = new PinchFilter();

		pinch.setAmount(-.5f);
		pinch.setRadius(10);
		pinch.setAngle((float) (Math.PI / 16));
		pinch.setCentreX(0.5f);
		pinch.setCentreY(-0.01f);
		pinch.setEdgeAction(ImageFunction2D.CLAMP);

		PinchFilter pinch2 = new PinchFilter();
		pinch2.setAmount(-.6f);
		pinch2.setRadius(10);
		pinch2.setAngle((float) (Math.PI / 16));
		pinch2.setCentreX(0.3f);
		pinch2.setCentreY(1.01f);
		pinch2.setEdgeAction(ImageFunction2D.CLAMP);

		PinchFilter pinch3 = new PinchFilter();
		pinch3.setAmount(-.6f);
		pinch3.setRadius(10);
		pinch3.setAngle((float) (Math.PI / 16));
		pinch3.setCentreX(0.8f);
		pinch3.setCentreY(-0.01f);
		pinch3.setEdgeAction(ImageFunction2D.CLAMP);

		List<ImageDeformation> textDef = new ArrayList<ImageDeformation>();
		textDef.add(new ImageDeformationByBufferedImageOp(pinch));
		textDef.add(new ImageDeformationByBufferedImageOp(pinch2));
		textDef.add(new ImageDeformationByBufferedImageOp(pinch3));

		// word2image 1
		com.octo.captcha.component.image.wordtoimage.WordToImage word2image;
		word2image = new DeformedComposedWordToImage(false, shearedFont, back, randomPaster,
				new ArrayList<ImageDeformation>(), new ArrayList<ImageDeformation>(), textDef);

		this.addFactory(new com.octo.captcha.image.gimpy.GimpyFactory(dictionnaryWords, word2image, false));

	}

}
