package org.ufa.core.constant;

import org.ufa.core.annotation.ErrorCode;



/**
 * <P>ufa core模块的异常编号常量类</P>
 * @author Jonathan
 */
@ErrorCode
public interface ExceptionCode extends BaseConstant {
	/**
	 * 当必须初始化的对象没有被初始化,则会抛出此异常
	 */
	String	UNINITIALIZED_OBJECT_EXCEPTION_CODE					= UFA_EXCEPTION_CODE_PREFIX + "CORE_01";
	/**
	 * 当在异常类中或配置文件中使用的code前缀不符合规范时,会抛出此异常
	 */
	String	ILLEGAL_EXCEPTION_CODE_PREFIX_EXCEPTION_CODE		= UFA_EXCEPTION_CODE_PREFIX + "CORE_02";
	/**
	 * 通过反射创建AppUncheckedException时出错
	 */
	String	CREATE_SOOFA_APP_EXCEPTION_EXCEPTION_CODE			= UFA_EXCEPTION_CODE_PREFIX + "CORE_03";
	/**
	 * 在spring配置文件中重复配置了某个code前缀与异常类的映射时,会抛出此异常
	 * 
	 * @see com.natie.ufa.core.exception.ExceptionConfig.exceptionClassNameMappings
	 */
	String	REDUPLICATE_EXCEPTION_CODE_PREFIX_EXCEPTION_CODE	= UFA_EXCEPTION_CODE_PREFIX + "CORE_04";
	/**
	 * 在spring配置文件中重复配置了某个code与严重程度的映射时,会抛出此异常
	 * 
	 * @see com.natie.ufa.core.exception.ExceptionConfig.severityMappings
	 */
	String	REDUPLICATE_EXCEPTION_CODE_EXCEPTION_CODE			= UFA_EXCEPTION_CODE_PREFIX + "CORE_05";
	/**
	 * 在制定的base package下扫描使用了指定的annotation的类时出错会抛此异常
	 */
	String	SCAN_ALL_ANNOTATION_CONFIG_EXCEPTION_CODE			= UFA_EXCEPTION_CODE_PREFIX + "CORE_06";
	/**
	 * 在处理所有使用了@Constant注释的类时,出现了异常
	 */
	String	HANDLE_ALL_CONSTANT_ANNOTATION_EXCEPTION_CODE		= UFA_EXCEPTION_CODE_PREFIX + "CORE_07";
	/**
	 * ContextRefreshListener执行指定注释扫描并调用所有annotation处理器时出现了异常
	 */
	String	SCAN_APP_CONTEXT_EXCEPTION_CODE						= UFA_EXCEPTION_CODE_PREFIX + "CORE_08";
	
	/**
	 * TODO
	 */
	String JCAPTCHA_OUTPUT_EXCEPTION_CODE 						= UFA_EXCEPTION_CODE_PREFIX + "CORE_09";
	
	/**
	 * TODO
	 */
	String JCAPTCHA_SESSION_NULL_EXCEPTION_CODE					= UFA_EXCEPTION_CODE_PREFIX + "CORE_09";;
}
