package org.ufa.core.config;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


public interface UfaMessageSource {
	/**
	 * <p>
	 * 根据指定的locale,获取所有其对应的ResourceBundle
	 * </p>
	 * 
	 * @param locale
	 * @return 所有指定locale对应的ResourceBundle.如果没找到,则返回空的List
	 */
	public List<ResourceBundle> getResourceBundles(Locale locale);
}
