/*******************************************************************************
 * Project   : ufa-core
 * Class Name: org.ufa.core.propertyeditors.CustomRESTNumberEditor
 * Created By: Jonathan 
 * Created on: 2014-6-10 下午11:34:53
 * Copyright © 2008-2014 NATIE All rights reserved.
 ******************************************************************************/
package org.ufa.core.propertyeditors;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.util.StringUtils;
import org.ufa.core.web.constant.RestConstant;

/**
 * 用于RESTFUL风格的Number类型Editor
 * 
 */
public class CustomRestNumberEditor extends CustomNumberEditor {

	/**
	 * 默认构造函数
	 * 
	 * @param numberClass java.lang.Number的子类，不能直接使用java.lang.Number
	 * @param allowEmpty
	 * @throws IllegalArgumentException、
	 * @see java.lang.Number
	 */
	public CustomRestNumberEditor(Class<? extends Number> numberClass, boolean allowEmpty)
			throws IllegalArgumentException {
		super(numberClass, allowEmpty);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.beans.propertyeditors.CustomNumberEditor#setAsText(java.lang.String)
	 * 覆盖Spring默认提供的方法，提供对EMPTY_PATH_VARIABLE的转换支持
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text) && RestConstant.EMPTY_PATH_VARIABLE.equals(text.trim())) {
			setValue(null);
		} else {
			super.setAsText(text);
		}
	}

}