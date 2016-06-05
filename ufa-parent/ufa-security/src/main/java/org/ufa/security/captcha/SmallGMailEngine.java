/*******************************************************************************
 * Project   : ufa-core
 * Class Name: org.ufa.security.captcha.SmallGMailEngine
 * Created By: Jonathan 
 * Created on: 2013-7-19 上午2:29:05
 * Copyright © 2010-2012 KFT Pay All rights reserved.
 ******************************************************************************/
package org.ufa.security.captcha;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import com.jhlabs.image.PinchFilter;
import com.jhlabs.math.ImageFunction2D;
import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.GradientBackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomListColorGenerator;
import com.octo.captcha.component.image.color.SingleColorGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.deformation.ImageDeformationByBufferedImageOp;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.GlyphsPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.glyphsvisitor.GlyphsVisitors;
import com.octo.captcha.component.image.textpaster.glyphsvisitor.OverlapGlyphsVisitor;
import com.octo.captcha.component.image.textpaster.glyphsvisitor.TranslateAllToRandomPointVisitor;
import com.octo.captcha.component.image.textpaster.textdecorator.BaffleTextDecorator;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.DeformedComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

/**
 * <P>TODO</P>
 * @author Jonathan
 */
public class SmallGMailEngine extends ListImageCaptchaEngine {

	private static final String NUMERIC_CHARS = "abcdefghjkmnpqrstuvwxyz23456789";
	
	@Override
	protected void buildInitialFactories() {
		//验证码的最小长度
		int minWordLength = 4;
		//验证码的最大长度
		int maxWordLength = 4;
		//验证码中显示的字体大小
		int fontSize = 25;
		//验证码图片的高度宽度设定
		int imageWidth = 60;
		int imageHeight = 30;

		//WordGenerator dictionnaryWords = new ComposeDictionaryWordGenerator(new FileDictionary("toddlist"));
		WordGenerator words = new RandomWordGenerator(NUMERIC_CHARS);//生成的验证码为数字
		//wordtoimage components
		TextPaster randomPaster = new GlyphsPaster(minWordLength, maxWordLength, new RandomListColorGenerator(
				new Color[] { Color.WHITE }), // new Color(23, 170, 27),new Color(220, 34, 11), new Color(23, 67, 172)
				new GlyphsVisitors[] { new OverlapGlyphsVisitor(-1), new TranslateAllToRandomPointVisitor() });
		
		 //背景颜色随机生成
		BackgroundGenerator background = new UniColorBackgroundGenerator(imageWidth, imageHeight, new Color(240, 203, 109));
		//BackgroundGenerator background = new GradientBackgroundGenerator(imageWidth, imageHeight, Color.white,Color.white);
		
		FontGenerator font = new RandomFontGenerator(fontSize, fontSize, new Font[] {
		//new Font("nyala", Font.BOLD, fontSize), new Font("Bell MT", Font.PLAIN, fontSize), 去掉不好识别的字体
				new Font("nyala", Font.BOLD, fontSize) }, false);

		PinchFilter pinch = new PinchFilter();

		pinch.setAmount(-.5f);//数量
		pinch.setRadius(10);//半径
		pinch.setAngle((float) (Math.PI / 16));//角度
		pinch.setCentreX(0.5f);//X方向比例
		pinch.setCentreY(-0.01f);//Y方向比例
		pinch.setEdgeAction(ImageFunction2D.CLAMP);

		PinchFilter pinch2 = new PinchFilter();
		pinch2.setAmount(-.5f);
		pinch2.setRadius(10);
		pinch2.setAngle((float) (Math.PI / 16));
		pinch2.setCentreX(0.3f);
		pinch2.setCentreY(1.01f);
		pinch2.setEdgeAction(ImageFunction2D.CLAMP);

		PinchFilter pinch3 = new PinchFilter();
		pinch3.setAmount(-.5f);
		pinch3.setRadius(10);
		pinch3.setAngle((float) (Math.PI / 16));
		pinch3.setCentreX(0.8f);
		pinch3.setCentreY(-0.01f);
		pinch3.setEdgeAction(ImageFunction2D.CLAMP);

		List<ImageDeformation> textDef = new ArrayList<ImageDeformation>();
		textDef.add(new ImageDeformationByBufferedImageOp(pinch));
		textDef.add(new ImageDeformationByBufferedImageOp(pinch2));
		textDef.add(new ImageDeformationByBufferedImageOp(pinch3));

		//word2image 1
		WordToImage word2image = new DeformedComposedWordToImage(false, font, background, randomPaster,
				new ArrayList<ImageDeformation>(), new ArrayList<ImageDeformation>(), textDef);
		//WordToImage word2image = new ComposedWordToImage(font, background, randomPaster);

		addFactory(new GimpyFactory(words, word2image, false));
	}

}
