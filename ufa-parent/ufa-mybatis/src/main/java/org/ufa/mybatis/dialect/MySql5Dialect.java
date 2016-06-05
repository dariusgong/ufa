package org.ufa.mybatis.dialect;

/**
 * <P> 支持mysql5的方言实现类 </P>
 * 
 */
public class MySql5Dialect extends Dialect {

	protected static final String SQL_END_DELIMITER = ";";

	public String getLimitString(String sql, boolean hasOffset) {
		return MySql5PageHepler.getLimitString(sql, -1, -1);
	}

	@Override
	public String getLimitString(String sql, int offset, int limit) {
		return MySql5PageHepler.getLimitString(sql, offset, limit);
	}

	public boolean supportsLimit() {
		return true;
	}
}
