package org.ufa.core.annotation;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.config.BeanDefinition;
import org.ufa.core.constant.ExceptionCode;
import org.ufa.core.exception.UfaCoreException;


/**
 * <P指定的java包基础路径下,找寻所有使用了@UrlConstant或@Constant注释的类.并将所有符合条件的类中的特定属性值放置到一个MAP中返回,例如URL常量类中以
 * '_URL'结尾的属性</P>
 * 
 * @see org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
 *  
 */
public class ClassPathConstantAnnotationHandler implements AnnotationHandler {
	private static final Logger			logger		= LoggerFactory.getLogger(ClassPathConstantAnnotationHandler.class);
	private static Map<String, String>	constants	= new HashMap<String, String>(50);

	@Override
	public void handle(Map<String, Set<BeanDefinition>> annotationConfigs) {
		Set<BeanDefinition> allConstantAnnotationConfigs = annotationConfigs.get(Constant.class.getName());
		for (BeanDefinition candidate : allConstantAnnotationConfigs) {
			try {
				findConstant(candidate);
			} catch (Exception e) {
				throw new UfaCoreException(ExceptionCode.HANDLE_ALL_CONSTANT_ANNOTATION_EXCEPTION_CODE, null,
						"handle all " + Constant.class.getName() + " error", e);
			}
		}

	}

	/**
	 * <p>
	 * 扫描非URL常量类
	 * </p>
	 * 
	 * @param constants
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 *  
	 * @throws ClassNotFoundException
	 */
	private void findConstant(BeanDefinition candidate) throws IllegalArgumentException, IllegalAccessException,
			ClassNotFoundException {
		String beanClassName = candidate.getBeanClassName();
		Class<?> clazz = Class.forName(beanClassName);
		String namespace = clazz.getAnnotation(Constant.class).namespace();
		Field[] fields = clazz.getFields();// 只取可访问的public属性
		String fieldName = null;
		String fieldValue = null;
		for (Field field : fields) {
			fieldName = field.getName();
			fieldValue = String.valueOf(field.get(null));
			constants.put(namespace + "." + fieldName, fieldValue);
			if (logger.isDebugEnabled()) {
				logger.debug("finded constant [{}={}] in {}", new Object[] { fieldName, fieldValue, beanClassName });
			}

		}
	}

	/**
	 * @return key为@Constant注释中的namespace值+常量类里的属性名,value为常量类里的属性值
	 */
	public static Map<String, String> getConstants() {
		return constants;
	}
}
