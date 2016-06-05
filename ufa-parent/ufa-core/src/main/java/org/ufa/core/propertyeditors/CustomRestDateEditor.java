/*******************************************************************************
 * Project   : ufa-core
 * Class Name: org.ufa.core.propertyeditors.CustomRESTDateEditor
 * Created By: Jonathan 
 * Created on: 2014-6-10 下午11:29:39
 * Copyright © 2008-2014 NATIE All rights reserved.
 ******************************************************************************/
package org.ufa.core.propertyeditors;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.util.StringUtils;
import org.ufa.core.web.constant.RestConstant;


/**
 * <P>支持用于RESTFUL风格的DATE类型Editor</P>
 * @see org.springframework.beans.propertyeditors.CustomDateEditor
 */
public class CustomRestDateEditor extends PropertyEditorSupport {
	private final boolean allowEmpty;
	private final int exactDateLength;
	private final String[] patterns;

	public CustomRestDateEditor(boolean allowEmpty, int exactDateLength) {
		this.patterns = new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd" };
		this.allowEmpty = allowEmpty;
		this.exactDateLength = exactDateLength;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if ((this.allowEmpty && !StringUtils.hasText(text))
				|| (StringUtils.hasText(text) && RestConstant.EMPTY_PATH_VARIABLE.equals(text.trim()))) {
			// Treat empty String as null value.
			setValue(null);
		} else if (text != null && this.exactDateLength >= 0 && text.length() != this.exactDateLength) {
			throw new IllegalArgumentException("Could not parse date: it is not exactly" + this.exactDateLength
					+ "characters long");
		} else {
			try {
				setValue(DateUtils.parseDate(text, patterns));
			} catch (ParseException ex) {
				throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
			}
		}
	}

	@Override
	public String getAsText() {
		Date value = (Date) getValue();
		return (value != null ? DateFormatUtils.format(value, "yyyy-MM-dd HH:mm:ss") : "");
	}
}