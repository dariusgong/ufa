package org.ufa.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * <P> 用于标识非URL常量类 </P>
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Constant {
	/**
	 * <p>
	 * 用于区分不同模块,项目的命名空间,一般是一个前缀
	 * </p>
	 */
	String namespace();
}
