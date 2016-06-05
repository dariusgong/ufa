package org.ufa.core.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;


/**
 * <P>
 * 字符串到char[]数组转换器,规则如下:
 * </P>
 * <p>
 * 字符串必须以'['开头和以']'结尾,例如515251转换为数组后,实际为char[] b = new char[]{'5','1','5','2','5','1'}
 * </p>
 * 
 *  
 */
public class StringToCharArrayConverter implements Converter<String, char[]> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 *  
	 */
	public char[] convert(String source) {
		if (!StringUtils.hasText(source)) {
			return null;
		}
		return source.toCharArray();
	}

}
