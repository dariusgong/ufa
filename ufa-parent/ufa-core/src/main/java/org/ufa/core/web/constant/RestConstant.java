/*******************************************************************************
 * Project   : ufa-core
 * Class Name: org.ufa.core.web.constant.RESTConstant
 * Created By: Jonathan 
 * Created on: 2014-6-10 下午11:32:18
 * Copyright © 2008-2014 NATIE All rights reserved.
 ******************************************************************************/
package org.ufa.core.web.constant;

import org.ufa.core.annotation.Constant;

/**
 * RESTFUL使用到的常量
 *
 */
@Constant(namespace = ProjectInfo.NAMESPACE)
public class RestConstant {
	/**
	 * 当RESTFUL ULR中传递空值时，使用此变量值代替
	 */
	public static final String	EMPTY_PATH_VARIABLE	= "rest_empty";
}