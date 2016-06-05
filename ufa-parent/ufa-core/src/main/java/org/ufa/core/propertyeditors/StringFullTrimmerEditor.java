/*******************************************************************************
 * Project   : ufa-core
 * Class Name: org.ufa.core.propertyeditors.StringFullTrimmerEditor
 * Created By: Jonathan 
 * Created on: 2014-6-10 下午11:34:06
 * Copyright © 2008-2014 NATIE All rights reserved.
 ******************************************************************************/
package org.ufa.core.propertyeditors;


import org.springframework.beans.propertyeditors.StringTrimmerEditor;

/**
 * 用于RESTFUL风格的String类型Editor
 * @see org.springframework.beans.propertyeditors.StringTrimmerEditor
 * @see org.soofa.web.base.binder.DefaultBindingInitializer
 */
public class StringFullTrimmerEditor extends StringTrimmerEditor {
	private final transient String stringToDelete;
	private final transient boolean fullDelete;
	private final transient boolean emptyAsNull;

	/**
	 * 构造函数
	 * @param stringToDelete 需要删除的字符串
	 * @param fullDelete true将stringToDelete当作整体,false将stringToDelete当作a set of characters
	 * @param emptyAsNull  是否将""转成null
	 */
	public StringFullTrimmerEditor(String stringToDelete, boolean fullDelete, boolean emptyAsNull) {
		super(stringToDelete, emptyAsNull);
		this.stringToDelete = stringToDelete;
		this.fullDelete = fullDelete;
		this.emptyAsNull = emptyAsNull;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.propertyeditors.StringTrimmerEditor#setAsText(java.lang.String)
	 * 覆盖StringTrimmerEditor提供的方法，当value为stringToDeletes时替换为null
	 */
	@Override
	public void setAsText(String text) {
		if (fullDelete) {
			if (text == null) {
				setValue(null);
			} else {
				String value = text.trim();
				if (this.stringToDelete != null && stringToDelete.equals(value)) {
					value = null;
				}
				if (this.emptyAsNull && "".equals(value)) {
					setValue(null);
				} else {
					setValue(value);
				}
			}
		} else {
			super.setAsText(text);
		}
	}

}
