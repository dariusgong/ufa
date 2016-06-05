package org.ufa.mybatis.plugin;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.internal.util.JavaBeansUtil;


public class RenameFileNamePlugin extends PluginAdapter {

	private Pattern pattern;
	private String searchString;
	private String replaceString;
	private String prefix = "";
	private String suffix ="_SqlMap.xml";

	/*
	 * (non-Javadoc)
	 * @see org.mybatis.generator.api.Plugin#validate(java.util.List)
	 */
	@Override
	public boolean validate(List<String> warnings) {
		// TODO Auto-generated method stub
		searchString = properties.getProperty("searchString"); //$NON-NLS-1$
		replaceString = properties.getProperty("replaceString") == null ? "" : properties.getProperty("replaceString"); //$NON-NLS-1$
		prefix = properties.getProperty("prefix") == null ? "" : properties.getProperty("prefix"); //$NON-NLS-1$
		suffix = properties.getProperty("suffix") == null ? "" : properties.getProperty("suffix"); //$NON-NLS-1$

		boolean valid = stringHasValue(searchString);

		if (valid) {
			pattern = Pattern.compile(searchString);
		} else {
			if (!stringHasValue(searchString)) {
				warnings.add(getString("ValidationError.18", //$NON-NLS-1$
						"RenameSqlMapFileNamePlugin", //$NON-NLS-1$
						"searchString")); //$NON-NLS-1$
			}
			if (!stringHasValue(replaceString)) {
				warnings.add(getString("ValidationError.18", //$NON-NLS-1$
						"RenameSqlMapFileNamePlugin", //$NON-NLS-1$
						"replaceString")); //$NON-NLS-1$
			}
		}

		return valid;
	}

	@Override
	public void initialized(IntrospectedTable introspectedTable) {
		//rename the sqlmap file name
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String tableName = table.getIntrospectedTableName();
		Matcher matcher = pattern.matcher(tableName);
		String genTableName = matcher.replaceAll(replaceString);
		genTableName = prefix + JavaBeansUtil.getCamelCaseString(genTableName, true)+ suffix;
		introspectedTable.setMyBatis3XmlMapperFileName(genTableName);

	}

}
