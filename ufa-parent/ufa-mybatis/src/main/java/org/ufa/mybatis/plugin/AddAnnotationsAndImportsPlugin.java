package org.ufa.mybatis.plugin;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;


/**
 * add import statement and annotation in Mapper interface java file
 */
public class AddAnnotationsAndImportsPlugin extends PluginAdapter {

	private String imports = "";
	private String annotations = "";

	/*
	 * (non-Javadoc)
	 * @see org.mybatis.generator.api.Plugin#validate(java.util.List)
	 */
	@Override
	public boolean validate(List<String> warnings) {

		imports = this.properties.getProperty("imports");
		annotations = this.properties.getProperty("annotations");
		boolean valid = stringHasValue(imports) || stringHasValue(annotations);

		if (!valid) {
			warnings.add(getString("ValidationError.18", //$NON-NLS-1$
					"AddAnnotationPlugin", //$NON-NLS-1$
					"imports or annotations")); //$NON-NLS-1$
		}

		return valid;
	}

	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		// add import statement in Mapper interface file
		if (stringHasValue(imports)) {
			String[] imp = imports.split(",");
			for (String s : imp) {
				FullyQualifiedJavaType type = new FullyQualifiedJavaType(s);
				interfaze.addImportedType(type);
			}
		}
		// interfaze.addFormattedAnnotations(new StringBuilder("@SoofaMapper"), 0);
		// add annotation in Mapper interface file
		if (stringHasValue(annotations)) {
			String[] ann = annotations.split(",");
			for (String s : ann) {
				interfaze.addAnnotation(s);
				// topLevelClass.addAnnotation(s);
			}
		}

		return true;
	}

}
