package org.ufa.mybatis.interceptor;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.RowBounds;
import org.ufa.util.ReflectionUtils;
import org.ufa.log.Logger;
import org.ufa.log.LoggerFactory;
import org.ufa.mybatis.dialect.Dialect;


/**
 * <P>支持mybatis的分页方言</P>
 * 
 *  
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PaginationInterceptor implements Interceptor {
	private final static Logger log = LoggerFactory.getLogger(PaginationInterceptor.class);
	// 因为3.2.2版本中不在存在3.1版本中的forObject(Object object)方法，所以添加下面2个属性
	public static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
	public static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
	private Dialect dialect;

	/**
	 * @param dialect the dialect to set
	 */
	public void setDialect(Dialect dialect) {
		this.dialect = dialect;
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin.Invocation)
	 *  
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = getStatementHandler(invocation);
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY,
				DEFAULT_OBJECT_WRAPPER_FACTORY);
		RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("rowBounds");
		if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
			return invocation.proceed();
		}
		String originalSql = (String) metaStatementHandler.getValue("boundSql.sql");
		metaStatementHandler.setValue("boundSql.sql",
				dialect.getLimitString(originalSql, rowBounds.getOffset(), rowBounds.getLimit()));
		// 因为StatementHandler和FastResultSetHandler是同时生成，而且都接引用了RowBounds参数，在FastResultSetHandler还是分页形式存在，如果不修正，会导致mybatis用游标移动后取数据
		// 强制不允许游标分页
		metaStatementHandler.setValue("rowBounds.offset", RowBounds.NO_ROW_OFFSET);
		metaStatementHandler.setValue("rowBounds.limit", RowBounds.NO_ROW_LIMIT);
		if (log.isDebugEnabled()) {
			log.debug("生成分页SQL : {}", statementHandler.getBoundSql().getSql());
		}
		return invocation.proceed();
	}

	/**
	 * <p>取出StatementHandler,如果是RoutingStatementHandler,则从其"delegate"属性中取出</p>
	 * 
	 * @param invocation
	 * @return
	 *  
	 */
	protected StatementHandler getStatementHandler(Invocation invocation) {
		StatementHandler statement = (StatementHandler) invocation.getTarget();
		if (statement instanceof RoutingStatementHandler) {// 从delegate取出具体的StatementHandler
			statement = (StatementHandler) ReflectionUtils.getFieldValue(statement, "delegate");
		}
		return statement;
	}

	protected boolean hasBounds(RowBounds rowBounds) {
		return (rowBounds != null && rowBounds.getLimit() > 0 && rowBounds.getLimit() < RowBounds.NO_ROW_LIMIT);
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.ibatis.plugin.Interceptor#plugin(java.lang.Object)
	 *  
	 */
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.ibatis.plugin.Interceptor#setProperties(java.util.Properties)
	 *  
	 */
	@Override
	public void setProperties(Properties properties) {
		// do nothing

	}

}
