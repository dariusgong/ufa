package org.ufa.core.annotation;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;


/**
 * <P>
 * 处理使用了自定义annotation的类的接口
 * </P>
 * <p>
 * 注意:项目中可能存在多个Context,每个Context初始化时都会调用一次此接口的实现类,所以必须
 * 小心处理重复执行带来的问题.例如WEB应用中,存在AppContext和WebContext两个Context
 * </p>
 * 
 * 
 */
public interface AnnotationHandler {
	/**
	 * <p>
	 * 处理使用了自定义annotation的所有class
	 * </p>
	 * 
	 * @param annotationConfigs key为annotation的全类名,value为使用了此annotation的class集合
	 * @throws Exception
	 */
	public void handle(Map<String, Set<BeanDefinition>> annotationConfigs) throws Exception;
}
