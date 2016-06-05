package org.ufa.core.web.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.js.ajax.AjaxUrlBasedViewResolver;
import org.ufa.core.exception.UfaCoreException;

/**
 * <P>支持在每个项目中自定义View实现类,以便能覆盖默认的View实现类</P>
 * 
 */
public class UfaDefaultViewResolver extends AjaxUrlBasedViewResolver {
	private UfaView coverView = null;

	@Override
	protected void initApplicationContext(ApplicationContext context) {
		super.initApplicationContext(context);
		loadAllCustomViews(context);
		if (coverView != null) {
			setViewClass(coverView.getClass());
		}
	}

	/**
	 * <p>从配置中间中加载所有自定义的SoofaView,其中只允许有一个是覆盖UrlBasedViewResolver.viewClass</p>
	 * 
	 * @param applicationContext
	 * @return
	 */
	private List<UfaView> loadAllCustomViews(ApplicationContext applicationContext) {
		Map<String, UfaView> ufaViewsMap = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext,
				UfaView.class, true, false);
		List<UfaView> views = new ArrayList<UfaView>(ufaViewsMap.size());
		for (String key : ufaViewsMap.keySet()) {
			UfaView ufaView = ufaViewsMap.get(key);
			if (ufaView.coverViewClass()) {
				if (coverView == null) {
					coverView = ufaView;
				} else {
					throw new UfaCoreException(
							"More than a SoofaView need to cover the default UrlBasedViewResolver.viewClass.");
				}
			} else {
				views.add(ufaView);
			}
		}
		return views;
	}
}
