/*******************************************************************************
 * Project   : ufa-core
 * Class Name: org.ufa.core.web.binder.DefaultBindingInitializer
 * Created By: Jonathan 
 * Created on: 2014-6-10 下午11:28:18
 * Copyright © 2008-2014 NATIE All rights reserved.
 ******************************************************************************/
package org.ufa.core.web.binder;

import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;
import org.ufa.core.propertyeditors.CustomRestDateEditor;
import org.ufa.core.propertyeditors.CustomRestNumberEditor;
import org.ufa.core.propertyeditors.StringFullTrimmerEditor;
import org.ufa.core.web.constant.RestConstant;
/**
 * <P>自定义的全局binder,目前只实现了默认的日期格式</P>
 * @author Jonathan
 */
public class DefaultBindingInitializer extends ConfigurableWebBindingInitializer implements WebBindingInitializer {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.bind.support.ConfigurableWebBindingInitializer#initBinder(org.
	 * springframework.web.bind.WebDataBinder, org.springframework.web.context.request.WebRequest)
	 */
	@Override
	public void initBinder(final WebDataBinder binder, final WebRequest request) {
		super.initBinder(binder, request);
		binder.registerCustomEditor(Date.class, new CustomRestDateEditor(true, -1));
		// 以下设置为了支持RESTFUL URL中的NULL变量值处理
		binder.registerCustomEditor(String.class, new StringFullTrimmerEditor(RestConstant.EMPTY_PATH_VARIABLE, true,
				false));
		binder.registerCustomEditor(Integer.class, new CustomRestNumberEditor(Integer.class, true));
	}

}
