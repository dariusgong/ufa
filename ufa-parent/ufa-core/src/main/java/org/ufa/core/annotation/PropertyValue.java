/*******************************************************************************
 * Project   : ufa-core
 * Class Name: org.ufa.core.annotation.PropertyValue
 * Created By: Jonathan 
 * Created on: 2013-8-26 下午10:04:33
 * Copyright © 2011-2013 Natie All rights reserved.
 ******************************************************************************/
package org.ufa.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * <P>TODO</P>
 * @author Jonathan
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PropertyValue {
	String value() default "";
}
