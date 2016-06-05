package org.ufa.core.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.ufa.log.Logger;
import org.ufa.log.LoggerFactory;


/**
 * 扩展ResourceBundleMessageSource,以支持通过前缀通配来获取多个国际化消息文件
 * 
 */
public class UfaResourceBundleMessageSource extends ResourceBundleMessageSource implements UfaMessageSource {
	private static Logger logger = LoggerFactory.getLogger(UfaResourceBundleMessageSource.class);
	private static List<String> localeSuffixes;
	@SuppressWarnings("unused")
	private List<String> excludes;

	private Set<String> basenames;

	/**
	 * @return the basenames
	 */
	public Set<String> getBasenames() {
		return basenames;
	}

	static {
		Locale[] availableLocales = Locale.getAvailableLocales();
		localeSuffixes = new ArrayList<String>(availableLocales.length);
		for (Locale availableLocale : availableLocales) {
			localeSuffixes.add("_" + availableLocale.toString() + ".properties");
		}
	}

	/**
	 * 首先通过PathMatchingResourcePatternResolver在classpath下找所有匹配pattern的resource,然后取其文件名,去掉locale后缀部分,
	 * 作为basename提供给ResourceBundle.getBundle
	 * 
	 */
	@Required
	public void setLocationPatterns(String[] locationPatterns) {
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(getBundleClassLoader());
		try {
			Resource[] messageResources = null;
			String prefix = null;
			basenames = new HashSet<String>();
			String resourceName = null;
			int lastIndexOf = -1;
			for (String locationPattern : locationPatterns) {
				messageResources = resolver.getResources(locationPattern);
				lastIndexOf = locationPattern.lastIndexOf('/');
				if (lastIndexOf == -1) {
					prefix = "";
				} else {
					prefix = locationPattern.substring(locationPattern.indexOf(':') + 1, lastIndexOf + 1);
				}
				for (Resource resource : messageResources) {
					resourceName = prefix + (resource).getFilename();
					Boolean isIncluded=false;
					for (String localeSuffix : localeSuffixes) {
						if (resourceName.endsWith(localeSuffix)) {
							basenames.add(resourceName.substring(0, resourceName.lastIndexOf(localeSuffix)));
							isIncluded=true;
							break;
						}
					}
					if(!isIncluded && !basenames.contains(resourceName)){
						basenames.add(resourceName.substring(0, resourceName.lastIndexOf(".properties")));
					}
				}
			}
			setBasenames(basenames.toArray(new String[basenames.size()]));
		} catch (IOException e) {
			logger.error("Cann't get resources[locationPattern = " + locationPatterns + "].", e);
			throw new IllegalArgumentException("Cann't get resources[locationPattern = " + locationPatterns + "].", e);
		}
	}

	@Override
	public List<ResourceBundle> getResourceBundles(Locale locale) {
		List<ResourceBundle> rbs = new LinkedList<ResourceBundle>();
		for (String basename : basenames) {
			ResourceBundle bundle = getResourceBundle(basename, locale);
			if (bundle != null) {
				rbs.add(bundle);
			}
		}
		return rbs;
	}

}
