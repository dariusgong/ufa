package org.ufa.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.ufa.core.model.PackageInfo;


/**
 * <P>与ufa开发平台本身相关的 工具类</P>
 * 
 * 
 */
public class UfaUtils {

	/**
	 * <p>扫描配置的所有org.ufa.core.model.PackageInfo类，从中获取basePackages属性，拼成一个以','分隔的字符串. <br/></p>
	 * 
	 * @return 例如："org.ufa.cip, org.ufa.cpp,com.lycheepay.cif"
	 * 
	 */
	public static String getAllBasePackagesString(ApplicationContext applicationContext) {

		StringBuilder allBasePackages = new StringBuilder();
		getBasePackages(applicationContext, allBasePackages);
		String string = allBasePackages.toString();
		if (string != null && string.endsWith(",")) {
			string = string.substring(0, string.length() - 1);
		}
		return string;
	}

	/**
	 * <p>递归找到所有配置的basepackage,拼接成以','分隔的字符串返回 </p>
	 */
	private static void getBasePackages(ApplicationContext applicationContext, StringBuilder allBasePackages) {
		Map<String, PackageInfo> basePackagesMap = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext,
				PackageInfo.class, true, false);
		String[] basePackages = null;
		for (String key : basePackagesMap.keySet()) {
			PackageInfo packageInfo = basePackagesMap.get(key);
			basePackages = packageInfo.getBasePackages();
			if (basePackages != null && basePackages.length > 0) {
				for (String basePackage : basePackages) {
					if (StringUtils.isNotBlank(basePackage)) {
						allBasePackages.append(basePackage);
						allBasePackages.append(",");
					}
				}
			}
		}
		if (applicationContext.getParent() != null) {
			getBasePackages(applicationContext.getParent(), allBasePackages);
		}
	}

	/**
	 * <p>扫描配置的所有org.ufa.core.model.PackageInfo类，从中获取所有basePackages属性值,以Set形式返回</p>
	 * 
	 * 
	 */
	public static Set<String> getAllBasePackagesList(ApplicationContext applicationContext) {
		Map<String, Boolean> allBasePackagesMap = new HashMap<String, Boolean>();
		findBasePackages(applicationContext, allBasePackagesMap);
		Set<String> keySet = allBasePackagesMap.keySet();
		return keySet;
	}

	/**
	 * <p>递归找到所有配置的basepackage,填充到map中</p>
	 */
	private static void findBasePackages(ApplicationContext applicationContext, Map<String, Boolean> allBasePackagesMap) {
		Map<String, PackageInfo> basePackagesMap = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext,
				PackageInfo.class, true, false);
		String[] basePackages = null;
		for (String key : basePackagesMap.keySet()) {
			PackageInfo packageInfo = basePackagesMap.get(key);
			basePackages = packageInfo.getBasePackages();
			if (basePackages != null && basePackages.length > 0) {
				for (String basePackage : basePackages) {
					if (StringUtils.isNotBlank(basePackage)) {
						allBasePackagesMap.put(basePackage, true);
					}
				}
			}
		}
		if (applicationContext.getParent() != null) {
			findBasePackages(applicationContext.getParent(), allBasePackagesMap);
		}
	}

	public static <T> Set<T> findAllBeans(ApplicationContext applicationContext, Map<T, Boolean> container,
			Class<T> type) {
		findBeans(applicationContext, container, type);
		return container.keySet();
	}

	private static <T> void findBeans(ApplicationContext applicationContext, Map<T, Boolean> container, Class<T> type) {
		Map<String, T> beansMap = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, type, true, false);
		T bean = null;
		for (String key : beansMap.keySet()) {
			bean = beansMap.get(key);
			if (bean != null) {
				container.put(bean, true);
			}
		}
		if (applicationContext.getParent() != null) {
			findBeans(applicationContext.getParent(), container, type);
		}
	}
}
