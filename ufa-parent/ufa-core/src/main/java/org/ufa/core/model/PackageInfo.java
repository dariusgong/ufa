package org.ufa.core.model;

/**
 * <P> 可用于存放多个java包信息</P>
 * 
 */
public class PackageInfo {
	private String[] basePackages;
	private String[] annotationTypes;

	/**
	 * @return the basePackages
	 */
	public String[] getBasePackages() {
		return basePackages;
	}

	/**
	 * @param basePackages the basePackages to set
	 */
	public void setBasePackages(String[] basePackages) {
		this.basePackages = basePackages;
	}

	/**
	 * @return the annotationTypes
	 */
	public String[] getAnnotationTypes() {
		return annotationTypes;
	}

	/**
	 * @param annotationTypes the annotationTypes to set
	 */
	public void setAnnotationTypes(String[] annotationTypes) {
		this.annotationTypes = annotationTypes;
	}

}
