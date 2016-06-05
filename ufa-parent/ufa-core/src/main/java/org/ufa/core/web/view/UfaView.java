package org.ufa.core.web.view;

import org.springframework.web.servlet.View;


/**
 * <P>用于标识自定义的spring mvc view实现类</P>
 * 
 * @see org.UfaDefaultViewResolver.web.base.view.SoofaDefaultViewResolver
 */
public interface UfaView extends View {

	/**
	 * <p>指定此View实现类是否覆盖SoofaDefaultViewResolver中的viewClass</p>
	 * 
	 * @return true 表示覆盖
	 */
	boolean coverViewClass();
}
