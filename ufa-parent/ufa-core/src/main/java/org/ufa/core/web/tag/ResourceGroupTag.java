package org.ufa.core.web.tag;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import org.ufa.core.web.tag.ResourceGroupTag;



/**
 * <P>
 * 动态打包标签,依赖SPRING
 * </P>

 */
public class ResourceGroupTag extends RequestContextAwareTag {
	private static final long		serialVersionUID				= 3064008559048195363L;
	private static final Logger	log								= LoggerFactory.getLogger(ResourceGroupTag.class);
	private static final String		RESOURCES_SERVLET_URL_PATTERN	= "/resources";
	private static final String		JAVASCRIPT_SCR_PREFIX			= "<script type='text/javascript' src='";
	private static final String		JAVASCRIPT_SCR_SUFFIX			= "'></script>";
	private static final String		CSS_HREF_PREFIX					= "<link  type='text/css' rel='stylesheet' href='";
	private static final String		CSS_HREF_SUFFIX					= "'/>";
	/**
	 * 当前是否为调式模式.true表示是调试模式.调试模式不需要合并js,便于定位具体错误的js
	 */
	private static Boolean			isDebugModel=true;

	private final List<String>		resourcesGroup;

	private String					type;

	public ResourceGroupTag() {
		super();
		resourcesGroup = new ArrayList<String>();
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int doEndTag() throws JspTagException {
		final StringBuilder sb = new StringBuilder();
		String actualType = type.toLowerCase().trim();
		if ("js".equals(actualType)) {
			wrapJsContent(sb);
		} else if ("css".equals(actualType)) {
			wrapCssContent(sb);
		} else {
			JspTagException jspTagException = new JspTagException("使用了不支持的资源类型,当前只支持'js'或者'css'");
			log.error("使用了不支持的资源类型,当前只支持'js'或者'css'");
			throw jspTagException;
		}
		try {
			this.pageContext.getOut().write(sb.toString());
		} catch (Exception e) {
			String message=e.getMessage();
			log.error(message);
			throw new JspTagException(message);
		}
		return EVAL_PAGE;
	}

	private StringBuilder wrapJsContent(StringBuilder sb) {
		if (resourcesGroup != null) {
			String contextPath = ((HttpServletRequest) pageContext.getRequest()).getContextPath();
			final int length = resourcesGroup.size();
			if (isDebugModel) {// 调式模式
				for (int i = 0; i < length; i++) {
					sb.append(JAVASCRIPT_SCR_PREFIX);
					sb.append(contextPath);
					sb.append(resourcesGroup.get(i));
					sb.append(JAVASCRIPT_SCR_SUFFIX);
				}
			} else {// 生产环境模式
				sb.append(JAVASCRIPT_SCR_PREFIX);
				sb.append(contextPath);
				sb.append(RESOURCES_SERVLET_URL_PATTERN);
				for (int i = 0; i < length; i++) {
					if (i == 0) {
						sb.append(resourcesGroup.get(i));
						if (length > 1) {
							sb.append("?appended=");
						}
					} else {
						sb.append(resourcesGroup.get(i));
						if (length > 2 && i != length - 1) {
							sb.append(",");
						}
					}
				}
				sb.append(JAVASCRIPT_SCR_SUFFIX);
			}
		}
		return sb;
	}

	private StringBuilder wrapCssContent(StringBuilder sb) {
		if (resourcesGroup != null) {
			String contextPath = ((HttpServletRequest) pageContext.getRequest()).getContextPath();
			final int length = resourcesGroup.size();
			if (isDebugModel) {// 调式模式
				for (int i = 0; i < length; i++) {
					sb.append(CSS_HREF_PREFIX);
					sb.append(contextPath);
					sb.append(resourcesGroup.get(i));
					sb.append(CSS_HREF_SUFFIX);
				}
			} else {// 生产环境模式
				sb.append(CSS_HREF_PREFIX);
				sb.append(contextPath);
				sb.append(RESOURCES_SERVLET_URL_PATTERN);
				for (int i = 0; i < length; i++) {
					if (i == 0) {
						sb.append(resourcesGroup.get(i));
						if (length > 1) {
							sb.append("?appended=");
						}
					} else {
						sb.append(resourcesGroup.get(i));
						if (length > 2 && i != length - 1) {
							sb.append(",");
						}
					}
				}
				sb.append(CSS_HREF_SUFFIX);
			}
		}
		return sb;
	}

	public void addParam(String arg) {
		resourcesGroup.add(arg);
	}


	@Override
	protected int doStartTagInternal() throws Exception {
		resourcesGroup.clear();
		return EVAL_BODY_INCLUDE;
	}
}
