
package org.ufa.core.constant;

/**
 * <P> 所有UFA常量类的父类,定义了在UFA中统一使用的一些常量 </P> <p> 异常code命名规范为:UFA_EXCEPTION_CODE_PREFIX + module
 * name + number 例如:EC_UFA_CACHE_02 </p>
 * 
 *  
 */
public interface BaseConstant {
	/**
	 * UFA自身所有异常code的统一前缀,需要跟具体模块名+编号,例如:EC_UFA_SECURITY_01,EC_UFA_LOG_01
	 * 注意:此前缀只供UFA自身使用,基于UFA开发的应用不需要使用此前缀
	 */
	String UFA_EXCEPTION_CODE_PREFIX = "EC_UFA_";

	/**
	 * 通用的异常KEY分隔符
	 */
	String COMMON_EXCEPTION_CODE_SEPARATOR = "-";

	/**
	 * 业务不正常状态编号的前缀，例如：BASC_CPP_01,表示资金处理平台的一个业务非正常状态编号01
	 */
	String BIZ_ABNORMAL_STATE_CODE_PREFIX = "BASC_";

	String DEFAULT_KEY_PREFIX = "default.";
	String HIDE_KEY_PREFIX = ".";
	String GROUP_KEY = "group";
	String INTERFACE_KEY = "interface";
	String VERSION_KEY = "version";

}
