package org.ufa.core.web.constant;

import org.ufa.core.annotation.ErrorCode;
import org.ufa.core.constant.BaseConstant;



@ErrorCode
public interface ExceptionCode extends BaseConstant {

	/**
	 * 所有通过UfaAppExceptionHandlerInterceptor统一转换的UfaAppUncheckedException,均使用此code加上时间戳,例如:
	 * EC_UFA_EXCEPTION_01-198747382
	 */
	String	COMMON_WEB_UNCHECKED_EXCEPTION_CODE					= UFA_EXCEPTION_CODE_PREFIX + "WEB_BASE_01";
	/**
	 * 扫描所有使用了@UrlConstant或@Constant注释的类时出现异常
	 */
	String	SCAN_ALL_CONSTANT_EXCEPTION_CODE					= UFA_EXCEPTION_CODE_PREFIX + "WEB_BASE_02";
	/**
	 * 处理所有使用了@UrlConstant注释的类时出现异常
	 */
	String	HANDLE_ALL_URL_CONSTANT_ANNOTATION_EXCEPTION_CODE	= UFA_EXCEPTION_CODE_PREFIX + "WEB_BASE_03";
	/**
	 * 将国际化消息文件中的内容转换成json格式字符串时出现了异常
	 */
	String	GET_MESSAGE_SOURCE_JSON_STRING_EXCEPTION_CODE		= UFA_EXCEPTION_CODE_PREFIX + "WEB_BASE_04";

}
