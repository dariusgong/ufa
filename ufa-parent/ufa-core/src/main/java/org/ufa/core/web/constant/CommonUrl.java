package org.ufa.core.web.constant;

/**
 * 所有URL常量类通用的属性.
 * <p>
 * 注意:<br>
 * 1.所有URL常量field的name必须以'_URL'结尾<br>
 * 2.所有URL前缀常量field的name,必须以'_URL_PREFIX'结尾
 * </p>
 *  
 *
 */
public interface CommonUrl {
	//===============================HTTP Method=========================================
	String METHOD_INDEX = "/index";
	String METHOD_NEW = "/new";
	String METHOD_EDIT = "/edit";
	String METHOD_SEARCH = "/search";
	String METHOD_DELETE = "/delete";
	/**
	 * 进入显示指定资源页面的统一前缀
	 */
	String METHOD_SHOW = "/show";
}
