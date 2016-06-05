package org.ufa.mybatis.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * <P>
 * 用于注入采用批量模式执行的mybatis mapper.使用此注解时,不能再使用spring的@Autowired
 * </P>
 * 
 *  
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BatchMapper {

}
