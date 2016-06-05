package org.ufa.mybatis.dialect;

/**
 * <P>
 * 模拟hibernate的数据库方言，用于支持分页等各个数据库不同的SQL，当前只支持oracle和mysql
 * </P>
 * 
 *  
 */
public abstract class Dialect {

	public static enum Type {
		MYSQL, ORACLE
	}

	public abstract String getLimitString(String sql, int skipResults, int maxResults);

}
