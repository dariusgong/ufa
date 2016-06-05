package org.ufa.core.web.constant;


public interface TagConstant {
	/**
	 * 通过spring依赖注入获取properties信息的bean的name
	 */
	String PROPERTIES_INFO_BEAN_NAME = "base.propertiesInfo";
	/**
	 * 在properties文件中保存POM版本信息的key
	 */
	String MAVEN_VERSION_KEY = "maven.version";
	/**
	 * 在properties文件中指示是否为debug模式的key,其值只能为 true or false
	 */
	String DEBUG_MODEL_KEY = "project.debug.model";
}
