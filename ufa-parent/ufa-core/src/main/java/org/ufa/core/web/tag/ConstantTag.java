package org.ufa.core.web.tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.tags.RequestContextAwareTag;
import org.ufa.core.annotation.ClassPathConstantAnnotationHandler;
import org.ufa.core.annotation.ClassPathUrlConstantAnnotationHandler;

import org.ufa.core.web.tag.ConstantTag;



/**
 * <p>
 * 从初始化好的MAP中取指定的常量值.执行初始化动作的类见see
 * </p>
 *  
 * 
 */
public class ConstantTag extends RequestContextAwareTag {
	private static final long	serialVersionUID	= -2238887901550526263L;
	private static Logger		log					= LoggerFactory.getLogger(ConstantTag.class);
	private String				namespace;
	private String				fieldName;

	/**
	 * @param namespace
	 *            命名空间,用于区分不同模块或项目
	 */
	public void setNamespace(String namespace) {
		this.namespace = namespace.trim();
	}

	/**
	 * @param fieldName
	 *            常量类中的属性名
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName.trim();
	}

	/*
	 * (non-Javadoc)
	 *  
	 * @see org.springframework.web.servlet.tags.RequestContextAwareTag#doStartTagInternal()
	 */
	@Override
	protected int doStartTagInternal() throws Exception {
		String key = namespace + "." + fieldName;
		String constant = ClassPathConstantAnnotationHandler.getConstants().get(key);
		if (constant == null) {
			constant = ClassPathUrlConstantAnnotationHandler.getConstants().get(key);
			if (constant == null) {
				log.warn("find a null constant [key = {}]", key);
			}
		}
		pageContext.getOut().print(constant);
		return SKIP_BODY;
	}
}
