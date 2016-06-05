
package org.ufa.mybatis.binding;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.binding.MapperProxy;
import org.apache.ibatis.session.SqlSession;
import org.ufa.core.model.Page;


/**
 * <P>
 * 从mybatis 3.2.2开始MapperProxy的构造函数由私有改为公有了,所以可以直接继承之,添加支持分页的代码
 * </P>
 * <P>
 * 注意:版本更新必须检查MapperProxy类的代码变化
 * </P>
 * 
 * @see org.apache.ibatis.binding.MapperProxy
 * 
 */
public class PaginationMapperProxy<T> extends MapperProxy<T> {
	private static final long serialVersionUID = 7933548678087850966L;
	private final SqlSession sqlSession;
	private final Class<T> mapperInterface;
	private final Map<Method, MapperMethod> methodCache;

	/**
	 * <p>
	 * Method for constructor
	 * </p>
	 * 
	 * @param sqlSession
	 * @param mapperInterface
	 * @param methodCache
	 */
	public PaginationMapperProxy(SqlSession sqlSession, Class<T> mapperInterface, Map<Method, MapperMethod> methodCache) {
		super(sqlSession, mapperInterface, methodCache);
		this.sqlSession = sqlSession;
		this.mapperInterface = mapperInterface;
		this.methodCache = methodCache;
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.ibatis.binding.MapperProxy#invoke(java.lang.Object, java.lang.reflect.Method,
	 * java.lang.Object[])
	 * 
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (Object.class.equals(method.getDeclaringClass())) {
			return method.invoke(this, args);
		}
		final MapperMethod mapperMethod = cachedMapperMethod(method);
		return mapperMethod.execute(sqlSession, args);
	}

	private MapperMethod cachedMapperMethod(Method method) {
		MapperMethod mapperMethod = methodCache.get(method);
		if (mapperMethod == null) {
			// 加入分页支持
			Object result = null;
			if (Page.class.isAssignableFrom(method.getReturnType())) {
				// 分页处理
				mapperMethod = new PaginationMapperMethod(mapperInterface, method, sqlSession.getConfiguration());
			} else {
				mapperMethod = new MapperMethod(mapperInterface, method, sqlSession.getConfiguration());
			}
			// 结束分页支持
			methodCache.put(method, mapperMethod);
		}
		return mapperMethod;
	}

}
