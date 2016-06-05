package org.ufa.core.annotation;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.ufa.core.exception.UfaCoreException;
import org.ufa.core.web.constant.ExceptionCode;
import org.ufa.core.web.tag.UrlConstant;



/**
 * <P> 扫描所有@UrlConstant/@Constant注释所标注的类,将其中的所有url常量进行加载到缓存中,便于url常量标签进行检索 </P>
 * 
 * @see org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
 */
public class ClassPathUrlConstantAnnotationHandler implements AnnotationHandler {
	private static final Logger logger = LoggerFactory.getLogger(ClassPathUrlConstantAnnotationHandler.class);
	private static Map<String, String> constants = new HashMap<String, String>(50);

	@Override
	public void handle(Map<String, Set<BeanDefinition>> annotationConfigs) {
		Set<BeanDefinition> allConstantAnnotationConfigs = annotationConfigs.get(UrlConstant.class.getName());
		for (BeanDefinition candidate : allConstantAnnotationConfigs) {
			try {
				findUrlConstant(candidate);
			} catch (Exception e) {
				throw new UfaCoreException(ExceptionCode.HANDLE_ALL_URL_CONSTANT_ANNOTATION_EXCEPTION_CODE, null,
						"handle all " + UrlConstant.class.getName() + " error", e);
			}
		}

	}

	/**
	 * <p>
	 * 扫描URL常量
	 * </p>
	 * 
	 * @param candidate
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws ClassNotFoundException
	 *  
	 */
	private static void findUrlConstant(BeanDefinition candidate) throws IllegalArgumentException,
			IllegalAccessException, ClassNotFoundException {
		String beanClassName = candidate.getBeanClassName();
		Class<?> clazz = Class.forName(beanClassName);
		String namespace = clazz.getAnnotation(UrlConstant.class).namespace();
		Field[] fields = clazz.getFields();// 只取可访问的public属性
		String fieldName = null;
		String fieldValue = null;
		for (Field field : fields) {
			fieldName = field.getName();
			fieldValue = (String) field.get(null);// 注意:这里不使用String.valueOf(field.get(null));是因为URL常量不可能是除String之外的其他类型
			constants.put(namespace + "." + fieldName, fieldValue);
			if (logger.isDebugEnabled()) {
				logger.debug("finded url constant [{}={}] in {}", new Object[] { fieldName, fieldValue, beanClassName });
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
