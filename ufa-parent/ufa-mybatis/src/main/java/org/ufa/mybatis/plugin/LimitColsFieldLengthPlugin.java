package org.ufa.mybatis.plugin;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;


/**
 * <P>设置生成的java model类中对应数据库remark字段的长度大小</P>
 */
public class LimitColsFieldLengthPlugin extends PluginAdapter {

	/**
	 *默认为字段名称为remark,note的字段 ,多个字段以','分割;
	 */
	private String colsName = "remark,note";

	/**
	 * 需要处理的对应数据库表，多个表以 ','分割；
	 */
	private String tblsName = "t_fund_trans";

	/* (non-Javadoc)
	 * @see org.mybatis.generator.api.Plugin#validate(java.util.List)
	 */
	@Override
	public boolean validate(List<String> warnings) {
		// TODO Auto-generated method stub
		colsName = this.properties.getProperty("colsName");
		tblsName = this.properties.getProperty("tblsName");
		boolean valid = stringHasValue(colsName) || stringHasValue(tblsName);
		if (!valid) {
			warnings.add(getString("ValidationError.18", //$NON-NLS-1$
					"LimitColsFieldLengthPlugin", //$NON-NLS-1$
					"colsName or tblsName")); //$NON-NLS-1$
		}
		return valid;
	}

	@Override
	public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable,
			Plugin.ModelClassType modelClassType) {
		String colName = introspectedColumn.getActualColumnName();
		colName = colName.toLowerCase();
		colsName = colsName.toLowerCase();

		String tblName = introspectedTable.getFullyQualifiedTable().getIntrospectedTableName();
		tblName = tblName.toLowerCase();
		tblsName = tblsName.toLowerCase();

		if (tblsName.contains(tblName) && colsName.contains(colName) && introspectedColumn.isStringColumn()) {
			Parameter parameter = method.getParameters().get(0);
			String paraName = parameter.getName();
			int length = introspectedColumn.getLength();
			Collection<String> lines = new ArrayList<String>();
			lines.add("if(" + paraName + "!=null &&" + paraName + ".length() > " + length + "){");
			lines.add(paraName + "=" + paraName + ".substring(0," + length + ");");
			lines.add("}");
			method.addBodyLines(0,lines);
		}
		return true;
	}

}
