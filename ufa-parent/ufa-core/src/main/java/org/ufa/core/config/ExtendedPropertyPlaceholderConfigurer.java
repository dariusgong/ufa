/*******************************************************************************
 * Project   : ufa-core
 * Class Name: org.ufa.core.config.ExtendedPropertyPlaceholderConfigurer
 * Created By: Jonathan 
 * Created on: 2013-8-26 下午10:29:57
 * Copyright © 2010-2012 KFT Pay All rights reserved.
 ******************************************************************************/
package org.ufa.core.config;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;


/**
 * <P>TODO</P>
 * @author Jonathan
 */
public class ExtendedPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	private Properties props;

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
			throws BeansException {
		super.processProperties(beanFactory, props);
		this.props = props;
	}

	public Object getProperty(String key) {
		return props.get(key);
	}
}
