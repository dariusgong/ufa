/**
 * 
 */
package org.ufa.mail.constant;

import org.ufa.core.annotation.ErrorCode;
import org.ufa.core.constant.BaseConstant;


/**
 * <P>
 * ufa support 模块的异常编号常量类
 * </P>

 */
@ErrorCode
public interface ExceptionCode extends BaseConstant {

	/**
	 * 发送基于BaseModel的email时出现异常,则使用此code
	 */
	String	SEND_BASE_MODEL_MAIL_EXCEPTION_CODE	= UFA_EXCEPTION_CODE_PREFIX + "MAIL_01";
	/**
	 * 发送基于Map的email时出现异常,则使用此code
	 */
	String	SEND_MAP_MAIL_EXCEPTION_CODE		= UFA_EXCEPTION_CODE_PREFIX + "MAIL_02";

}
