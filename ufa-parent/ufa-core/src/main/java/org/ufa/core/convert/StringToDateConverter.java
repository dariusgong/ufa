package org.ufa.core.convert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;
import org.ufa.util.DateFormatUtils;


/**
 * <P>
 * Description: 字符串到日期的转换器,目前只支持pattern为 yyyy-MM-dd HH:mm:ss
 * </P>
 * 
 * @ClassName: StringToDateConverter
 *   2010-10-18
 */
public class StringToDateConverter implements Converter<String, Date> {
	/**
	 * 当前转换器所支持的日期pattern
	 */
	public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	protected transient Logger log = LoggerFactory.getLogger(StringToDateConverter.class);

	/*
	 * (non-Javadoc) <p>Title: convert</p>
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	public Date convert(String source) {
		if (!StringUtils.hasText(source)) {
			return null;
		}
		try {
			return DateUtils.parseDate(source, DateFormatUtils.DATE_PARSE_PATTERNS);
		} catch (ParseException e) {
			throw new RuntimeException("parse date[" + source + "] error.", e);
		}

	}

	private DateFormat getDateFormat() {
		Locale locale = Locale.getDefault();
		DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, locale);
		format.setLenient(false);
		if (format instanceof SimpleDateFormat) {
			((SimpleDateFormat) format).applyPattern(DATE_PATTERN);
		} else {
			log.warn("Unable to apply format pattern '{}'; Returned DateFormat is not a SimpleDateFormat", DATE_PATTERN);
		}
		return format;
	}
}
