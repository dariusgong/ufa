/*******************************************************************************
 * Project   : ufa-core
 * Class Name: org.ufa.core.annotation.PropertyValueAnnotationBeanPostProcessor
 * Created By: Jonathan 
 * Created on: 2013-8-26 下午10:06:44
 * Copyright © 2011-2013 Natie All rights reserved.
 ******************************************************************************/
package org.ufa.core.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.springframework.beans.BeansException;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.util.ReflectionUtils;
import org.ufa.core.config.ExtendedPropertyPlaceholderConfigurer;


/**
 * <P>扫描所有标注@PropertyValue的字段, 将properties中的值设置到属性上</P>
 * @author Jonathan
 */
public class PropertyValueAnnotationBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter implements
		BeanFactoryAware {

	private ExtendedPropertyPlaceholderConfigurer propertyConfigurer;

	private ConfigurableListableBeanFactory beanFactory;

	// 创建简单类型转换器
	private SimpleTypeConverter typeConverter = new SimpleTypeConverter();

	/*
	 * (non-Javadoc)
	 * @see org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter#
	 * postProcessAfterInstantiation(java.lang.Object, java.lang.String)
	 * @author Jonathan
	 */
	@Override
	public boolean postProcessAfterInstantiation(final Object bean, String beanName) throws BeansException {
		ReflectionUtils.doWithFields(bean.getClass(), new ReflectionUtils.FieldCallback() {
			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
				PropertyValue cfg = field.getAnnotation(PropertyValue.class);
				if (cfg != null) {
					if (Modifier.isStatic(field.getModifiers())) {
						throw new IllegalStateException("@PropertyValue annotation is not supported on static fields");
					}

					// 如果开发者没有设置@PropertyValue的 value，则使用变量域的名称作为键查找配置资源
					String key = cfg.value().length() <= 0 ? field.getName() : cfg.value();
					Object value = propertyConfigurer.getProperty(key);

					if (value != null) {
						// 转换配置值成其它非String类型
						Object _value = typeConverter.convertIfNecessary(value, field.getType());
						// 使变量域可用，并且转换后的配置值注入其中
						ReflectionUtils.makeAccessible(field);
						field.set(bean, _value);
					}
				}
			}
		});

		// 通常情况下返回true即可
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.beans.factory.BeanFactoryAware#setBeanFactory(org.springframework.beans
	 * .factory.BeanFactory)
	 * @author Jonathan
	 */
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		if (!(beanFactory instanceof ConfigurableListableBeanFactory)) {
			throw new IllegalArgumentException("PropertyValueAnnotationBeanPostProcessor requires a ConfigurableListableBeanFactory");
		}
		this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
		propertyConfigurer=this.beanFactory.getBean(ExtendedPropertyPlaceholderConfigurer.class);
	}

}
