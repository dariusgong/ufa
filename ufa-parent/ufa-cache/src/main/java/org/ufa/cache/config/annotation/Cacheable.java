package org.ufa.cache.config.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



/**
 * <p>用于对方法声明使用cache,对方法使用此注释后，系统将 基于切面拦截方法的返回值，将返回值保存至用户所选的 缓存中。</p>
 * <p> 注意：使用此注释的方法，如果不设置KEY，则全部形参必须 实现toString()方法，否则将出现缓存无法命中的 情况。因为无KEY的情况下将方法返回值保存至缓存时，使用的key是
 * 基于方法名和所有参数的toString()值进行构造的。</p>
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Cacheable {

	String region();

	/**
	 * 明确指定缓存时使用的KEY,如果不设置，则默认为空白字符，会使用方法全名加上各参数的toString()生成KEY
	 */
	String key() default "";

}
