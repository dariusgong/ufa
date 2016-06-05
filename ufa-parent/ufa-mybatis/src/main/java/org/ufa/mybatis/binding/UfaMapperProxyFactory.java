package org.ufa.mybatis.binding;

import java.lang.reflect.Proxy;

import org.apache.ibatis.binding.MapperProxyFactory;
import org.apache.ibatis.session.SqlSession;


/**
 * <P>
 * 使用PaginationMapperProxy替换MapperProxy
 * </P>
 * 注意：源码变更后要对比是否有变化
 * 
 * @since mybatis-3.2.2
 * 
 */
public class UfaMapperProxyFactory<T> extends MapperProxyFactory<T> {

	/**
	 * <p>
	 * Method for constructor
	 * </p>
	 * 
	 * @param mapperInterface
	 */
	public UfaMapperProxyFactory(Class<T> mapperInterface) {
		super(mapperInterface);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.apache.ibatis.binding.MapperProxyFactory#newInstance(org.apache.ibatis.session.SqlSession
	 * )
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T newInstance(SqlSession sqlSession) {
		final PaginationMapperProxy<T> mapperProxy = new PaginationMapperProxy<T>(sqlSession, getMapperInterface(),
				getMethodCache());
		return (T) Proxy.newProxyInstance(getMapperInterface().getClassLoader(), new Class[] { getMapperInterface() },
				mapperProxy);
	}

}
