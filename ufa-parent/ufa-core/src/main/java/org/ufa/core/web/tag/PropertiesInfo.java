
package org.ufa.core.web.tag;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.ufa.core.web.constant.TagConstant;


/**
 * <P>动态注入来自properties文件里的信息</P>
 *  
 */
//@Component(TagConstant.PROPERTIES_INFO_BEAN_NAME)
public class PropertiesInfo {
	@Value("${" + TagConstant.DEBUG_MODEL_KEY + "}")
	private Boolean debugModel;

	@Value("${" + TagConstant.MAVEN_VERSION_KEY + "}")
	private String version;

	/**
	 * @return the debugModel
	 */
	public Boolean isDebugModel() {
		return debugModel;
	}

	/**
	 * @param debugModel the debugModel to set
	 */
	public void setDebugModel(Boolean debugModel) {
		this.debugModel = debugModel;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

}
