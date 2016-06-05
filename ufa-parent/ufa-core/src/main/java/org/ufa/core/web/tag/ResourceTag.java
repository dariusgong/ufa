package org.ufa.core.web.tag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.ufa.core.web.tag.ResourceGroupTag;


/**
 * <P>
 * 动态压缩参数
 * </P>
 * 
 *   2010-9-1 下午12:55:04
 */
public class ResourceTag extends TagSupport {
	private static final long	serialVersionUID	= 487038747306073287L;
	/**
	 * 资源路径
	 */
	private String				path;

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public int doEndTag() throws JspTagException {
		Tag t = findAncestorWithClass(this, ResourceGroupTag.class);
		ResourceGroupTag parent = (ResourceGroupTag) t;
		parent.addParam(path);
		return EVAL_PAGE;
	}
}
