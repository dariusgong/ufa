package org.ufa.core.context;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import com.natie.ufa.util.AnnotationUtils;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.AnnotationMetadata;
import org.ufa.core.annotation.AnnotationUtils;



/**
 * <P>
 * 扫描所有自定义的注释所标注的类,
 * </P>
 * 
 * @see org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
 */
public class ClasspathAnnotationDefinitionScanner extends ClassPathScanningCandidateComponentProvider {
	private static final Logger logger = LoggerFactory.getLogger(ClasspathAnnotationDefinitionScanner.class);
	private Map<String, Set<BeanDefinition>> annotationConfigs;

	/**
	 * <p>
	 * Method for constructor
	 * </p>
	 * 
	 * @param useDefaultFilters
	 */
	public ClasspathAnnotationDefinitionScanner() {
		super(false);// 不需要加载默认的过滤器
	}

	/**
	 * @return key是在xml配置文件中配置的annotationTypes,value为所有使用了此annotation的BeanDefinition
	 */
	public Map<String, Set<BeanDefinition>> getAnnotationConfigs() {
		return annotationConfigs;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider#
	 * isCandidateComponent(org.springframework.beans.factory.annotation.AnnotatedBeanDefinition)
	 */
	@Override
	protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		// url常量类必须是interface类
		AnnotationMetadata metadata = beanDefinition.getMetadata();
		return (metadata.isInterface() || metadata.isAbstract() || metadata.isConcrete() || metadata.isFinal())
				&& metadata.isIndependent();
	}

	/**
	 * <p>
	 * 从指定的java包基础路径下,找寻所有使用了指定注释的类.并将所有符合条件bean保存到map中
	 * </p>
	 * 
	 * @param allAnnotationTypes 需要扫描的注释类型
	 * @param basePackages java包名.例如:com.isofstone.ebiz
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * 
	 */
	public void findAllAnnotationConfig(String[] allAnnotationTypes, String... basePackages)
			throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		Map<String, Set<BeanDefinition>> tmpAnnotationConfigs = new HashMap<String, Set<BeanDefinition>>(
				allAnnotationTypes.length);

		for (String annotationType : allAnnotationTypes) {
			if (logger.isDebugEnabled()) {
				logger.debug("Found a annotation type[{}] to filter.", annotationType);
			}
			Set<BeanDefinition> candidates = AnnotationUtils.findAnnotationBeanDefinition(basePackages, annotationType);
			if (candidates != null) {
				tmpAnnotationConfigs.put(annotationType, candidates);
			}
		}
		annotationConfigs = Collections.unmodifiableMap(tmpAnnotationConfigs);
	}

}
