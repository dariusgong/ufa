package org.ufa.cache.config.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 此注释用于在方法级别声明当前方法会造成数据库的那些表变更 从而使缓存系统中，相关表对应的缓存条目作废
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface EvictCache {


	String region();

	/**
	 * 明确指定所要刷新的缓存条目使用的KEY,可以多个
	 */
	String[] key();

	/**
	 * true表示key只是前缀,匹配所有以此KEY开头的缓存条目;false表示KEY不只是前缀,是完整的KEY,可以直接根据此KEY查找缓存条目
	 */
	boolean isPrefix();

}
