package org.ufa.core.context;

import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.ufa.core.annotation.AnnotationHandler;
import org.ufa.core.constant.ExceptionCode;
import org.ufa.core.exception.UfaCoreException;
import org.ufa.core.model.PackageInfo;



/**
 * <P>
 * 在spring容器初始化完毕或者刷新完毕后,执行一些初始化操作.例如:加载所有常量注释类,所有异常编号注释类
 * </P>
 * <p>
 * 注意:在web项目中,可能重复执行此listener,因为在web.xml里一般会配置2个AppContext,一个由ContextLoaderListener
 * 初始化;另一个由DispatcherServlet初始化.每个Context初始化或刷新时,都会发布ContextRefreshedEvent,从而
 * 导致此listener重复执行,所有的AnnotationHandler也会被重复调用,此问题会影响启动速度,但是只要做好重复处理 的校验逻辑,对项目没有影响,有待改进.
 * </p>
 * 
 * 
 */
public class ContextRefreshListener implements ApplicationListener<ContextRefreshedEvent> {
	private ClasspathAnnotationDefinitionScanner	scanner;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context
	 * .ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();
		try {
			scan(applicationContext);
		} catch (Exception e) {
			throw new UfaCoreException(ExceptionCode.SCAN_APP_CONTEXT_EXCEPTION_CODE, null, "", e);
		}
	}

	/**
	 * <p>
	 * 执行扫描并调用所有annotation处理器
	 * </p>
	 * 
	 * @param applicationContext
	 * 
	 * @throws Exception
	 */
	private void scan(ApplicationContext applicationContext) throws Exception {
		Map<String, PackageInfo> basePackagesMap = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext,
				PackageInfo.class, true, false);
		String[] allBasePackages = null;
		String[] allAnnotationTypes = null;
		String[] basePackages = null;
		String[] annotationTypes = null;
		for (String key : basePackagesMap.keySet()) {
			PackageInfo packageInfo = basePackagesMap.get(key);
			basePackages = packageInfo.getBasePackages();
			if (basePackages != null) {
				allBasePackages = (String[]) ArrayUtils.addAll(allBasePackages, basePackages);
			}
			annotationTypes = packageInfo.getAnnotationTypes();
			if (annotationTypes != null) {
				allAnnotationTypes = (String[]) ArrayUtils.addAll(allAnnotationTypes, annotationTypes);
			}
		}
		if (allBasePackages != null && allAnnotationTypes != null) {
			try {
				scanner.findAllAnnotationConfig(allAnnotationTypes, allBasePackages);
				// ConstantTag.constants = allContants;
			} catch (Exception e) {
				throw new UfaCoreException(ExceptionCode.SCAN_ALL_ANNOTATION_CONFIG_EXCEPTION_CODE, null,
						"scan all annotation config error", e);
			}
			// 调用所有annotation处理器
			Map<String, AnnotationHandler> annotationHandlersMap = BeanFactoryUtils.beansOfTypeIncludingAncestors(
					applicationContext, AnnotationHandler.class, true, false);
			for (String beanName : annotationHandlersMap.keySet()) {
				AnnotationHandler handler = annotationHandlersMap.get(beanName);
				if (handler != null) {
					handler.handle(scanner.getAnnotationConfigs());
				}
			}

		}
	}

	/**
	 * @param scanner
	 *            the scanner to set
	 */
	public void setScanner(ClasspathAnnotationDefinitionScanner scanner) {
		this.scanner = scanner;
	}

}
