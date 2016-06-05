package org.ufa.core.web.tag;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.tags.RequestContextAwareTag;
import org.ufa.core.annotation.ClassPathUrlConstantAnnotationHandler;
import org.ufa.log.Logger;
import org.ufa.log.LoggerFactory;


/**
 * <p>
 * 使用方法: &lt;soofa:url namespace="psp" fieldName="PASSWORD_MODIFY_PAYMENT_INDEX_URL"/&gt; <br/>
 * 常量类里面自动添加上下文
 * </p>
 * 
 * @author (tao)
 * @version 0.0.1
 */
public class UrlTag extends RequestContextAwareTag {

	private static final long serialVersionUID = 2886210253750159152L;
	private static Logger log = LoggerFactory.getLogger(UrlTag.class);
	private String namespace;
	private String fieldName;

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
	 * @author tao
	 * @see org.springframework.web.servlet.tags.RequestContextAwareTag#doStartTagInternal()
	 */
	@Override
	protected int doStartTagInternal() throws Exception {
		String key = namespace + "." + fieldName;
		String constant = ClassPathUrlConstantAnnotationHandler.getConstants().get(key);
		if (StringUtils.isNotBlank(constant)) {
			pageContext.getOut().print(getRequestContext().getContextPath() + constant);
		} else {
			log.warn("find a blank url constant [key = {}]", key);
		}
		return SKIP_BODY;
	}
}
