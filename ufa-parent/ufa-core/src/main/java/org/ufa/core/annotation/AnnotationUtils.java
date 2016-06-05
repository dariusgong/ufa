package org.ufa.core.annotation;

import java.lang.annotation.Annotation;
import java.util.Set;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.ufa.core.context.ClasspathAnnotationDefinitionScanner;



/**
 * <P>
 * 代理并扩展spring的AnnotationUtils工具类,为ufa提供统一的工具视图
 * </P>
 * 
 * @see org.springframework.core.annotation.AnnotationUtils
 *  
 */
public class AnnotationUtils extends org.springframework.core.annotation.AnnotationUtils {
	@SuppressWarnings("unchecked")
	public static Set<BeanDefinition> findAnnotationBeanDefinition(String[] basePackages, String annotationType)
			throws ClassNotFoundException {
		ClasspathAnnotationDefinitionScanner scanner = new ClasspathAnnotationDefinitionScanner();
		scanner.addIncludeFilter(new AnnotationTypeFilter((Class<? extends Annotation>) Class.forName(annotationType)));
		Set<BeanDefinition> candidates = null;
		for (String basePackage : basePackages) {
			if (candidates == null) {
				candidates = scanner.findCandidateComponents(basePackage);
			} else {
				for (BeanDefinition bd : scanner.findCandidateComponents(basePackage)) {
					candidates.add(bd);
				}
			}
		}
		return candidates;
	}
}
