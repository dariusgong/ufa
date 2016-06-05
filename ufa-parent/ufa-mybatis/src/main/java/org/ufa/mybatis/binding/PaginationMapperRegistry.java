package org.ufa.mybatis.binding;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.builder.annotation.MapperAnnotationBuilder;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;


/**
 * <P>
 * 扩展原MapperRegistry,替换其中的方法支持分页.由于knownMappers无法替换，只能覆盖所有使用了knownMappers的方法
 * </P>
 * <P>
 * 注意:此类的getMapper方法完全覆盖了父类的方法,当版本升级时,必须检查父类代码是否变更
 * </P>
 * 
 * @since mybaits-3.2.2
 * @see org.apache.ibatis.binding.MapperRegistry
 * 
 */
public class PaginationMapperRegistry extends MapperRegistry {
	private final Configuration config;
	private final Map<Class<?>, UfaMapperProxyFactory<?>> knownMappers = new HashMap<Class<?>, UfaMapperProxyFactory<?>>();

	/**
	 * <p>
	 * Method for constructor
	 * </p>
	 * 
	 * @param config
	 */
	public PaginationMapperRegistry(Configuration config) {
		super(config);
		this.config = config;
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.ibatis.binding.MapperRegistry#getMapper(java.lang.Class,
	 * org.apache.ibatis.session.SqlSession)
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
		final UfaMapperProxyFactory<T> mapperProxyFactory = (UfaMapperProxyFactory<T>) knownMappers.get(type);
		if (mapperProxyFactory == null)
			throw new BindingException("Type " + type + " is not known to the MapperRegistry.");
		try {
			return mapperProxyFactory.newInstance(sqlSession);
		} catch (Exception e) {
			throw new BindingException("Error getting mapper instance. Cause: " + e, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.ibatis.binding.MapperRegistry#addMapper(java.lang.Class)
	 * 
	 */
	@Override
	public <T> void addMapper(Class<T> type) {
		if (type.isInterface()) {
			if (hasMapper(type)) {
				throw new BindingException("Type " + type + " is already known to the MapperRegistry.");
			}
			boolean loadCompleted = false;
			try {
				knownMappers.put(type, new UfaMapperProxyFactory<T>(type));
				// It's important that the type is added before the parser is run
				// otherwise the binding may automatically be attempted by the
				// mapper parser. If the type is already known, it won't try.
				MapperAnnotationBuilder parser = new MapperAnnotationBuilder(config, type);
				parser.parse();
				loadCompleted = true;
			} finally {
				if (!loadCompleted) {
					knownMappers.remove(type);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.ibatis.binding.MapperRegistry#hasMapper(java.lang.Class)
	 * 
	 */
	@Override
	public <T> boolean hasMapper(Class<T> type) {
		return knownMappers.containsKey(type);
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.ibatis.binding.MapperRegistry#getMappers()
	 * 
	 */
	@Override
	public Collection<Class<?>> getMappers() {
		return Collections.unmodifiableCollection(knownMappers.keySet());
	}

}
