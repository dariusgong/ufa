package org.ufa.mybatis.binding;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.ufa.core.model.Page;


/**
 * <P>
 * 由于mybaits自带的MapperMethod没有足够的扩展点,导致覆盖其全部代码后加上自定义的分页支持
 * </P>
 * <P>
 * 注意:版本更新必须检查MapperMethod类的代码变化
 * </P>
 * 
 * @see org.apache.ibatis.binding.MapperMethod
 * 
 */
public class PaginationMapperMethod extends MapperMethod {
	private final SqlCommand command;
	private final UfaMethodSignature method;
	private boolean returnsPage;// 返回分页对象类型
	private Integer pageIndex;// 分页对象位于方法参数的第几位

	/**
	 * <p>
	 * Method for constructor
	 * </p>
	 * 
	 * @param mapperInterface
	 * @param method
	 * @param config
	 */
	public PaginationMapperMethod(Class<?> mapperInterface, Method method, Configuration config) {
		super(mapperInterface, method, config);
		this.command = new SqlCommand(config, mapperInterface, method);
		this.method = new UfaMethodSignature(config, method);
		setupMethodSignature(method);
	}

	/**
	 * <p>
	 * 增加分页支持,为了简化，不放在MethodSignature类中
	 * </p>
	 * 
	 * 
	 */
	private void setupMethodSignature(Method method) {
		// 增加分页支持
		if (Page.class.isAssignableFrom(method.getReturnType())) {
			this.returnsPage = true;
		}
		this.pageIndex = getUniqueParamIndex(method, Page.class);
	}

	/**
	 * <p>
	 * 完全复制了父类的相同方法，版本变化后必须关注代码是否变更
	 * </p>
	 * 
	 * @param method
	 * @param paramType
	 * @return
	 * 
	 */
	private Integer getUniqueParamIndex(Method method, Class<?> paramType) {
		Integer index = null;
		final Class<?>[] argTypes = method.getParameterTypes();
		for (int i = 0; i < argTypes.length; i++) {
			if (paramType.isAssignableFrom(argTypes[i])) {
				if (index == null) {
					index = i;
				} else {
					throw new BindingException(method.getName() + " cannot have multiple " + paramType.getSimpleName()
							+ " parameters");
				}
			}
		}
		return index;
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.ibatis.binding.MapperMethod#execute(org.apache.ibatis.session.SqlSession,
	 * java.lang.Object[])
	 * 
	 */
	@Override
	public Object execute(SqlSession sqlSession, Object[] args) {
		if (SqlCommandType.SELECT == command.getType()) {
			if (returnsPage) {
				return executeForPagination(sqlSession, args);
			}
		}
		return super.execute(sqlSession, args);
	}

	/**
	 * <p>
	 * 支持mapper的分页，注意其去实际行数时的逻辑为：不包含offset，包含offset+limit。即包括offset+1到offset+limit
	 * </p>
	 * 
	 * @param args
	 * @return
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object executeForPagination(SqlSession sqlSession, Object[] args) {
		Object param = method.convertArgsToSqlCommandParam(args);
		Page page = null;
		RowBounds rowBounds = null;
		if (pageIndex != null) {
			page = (Page) args[pageIndex];
			rowBounds = new RowBounds(page.getStartRow() - 1, page.getRowNum());
		} else if (method.hasRowBounds()) {// 除了支持参数中直接传入Page对象外,还要支持原生的传入RowBounds对象
			rowBounds = method.extractRowBounds(args);
			page = new Page();
			page.setNowPage((rowBounds.getOffset() / rowBounds.getLimit() + 1));
		} else {
			throw new BindingException("Invalid bound statement (not found RowBounds or Page in paramenters)");
		}
		// 支持默认就查询总数,并保存到page对象里,目前为了提高性能,默认不支持
		// Number result = (Number) sqlSession.selectOne(commandCountName, param);
		// long count = result.longValue();
		List result = sqlSession.selectList(command.getName(), param, rowBounds);
		// issue #510 Collections & arrays support
		// if (!method.getReturnType().isAssignableFrom(result.getClass())) {
		// if (method.getReturnType().isArray()) {
		// return convertToArray(result);
		// } else {
		// return convertToDeclaredCollection(sqlSession.getConfiguration(), result);
		// }
		// }
		page.setContentBulk(result);
		return page;
	}

	/**
	 * <P>
	 * 除了getParams方法被修改之外，其余方法完全复制父类，鄙视一下mybatis的可扩展性
	 * </P>
	 * <P>
	 * 注意：需要关注版本变化
	 * </P>
	 * 
	 * 
	 */
	public static class UfaMethodSignature extends MethodSignature {
		private final SortedMap<Integer, String> params;
		private final boolean hasNamedParameters;

		public UfaMethodSignature(Configuration arg0, Method method) throws BindingException {
			super(arg0, method);
			this.hasNamedParameters = hasNamedParams(method);
			this.params = Collections.unmodifiableSortedMap(getParams(method, this.hasNamedParameters));
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * org.apache.ibatis.binding.MapperMethod.MethodSignature#convertArgsToSqlCommandParam(java
		 * .lang.Object[])
		 * 
		 */
		@Override
		public Object convertArgsToSqlCommandParam(Object[] args) {
			final int paramCount = params.size();
			if (args == null || paramCount == 0) {
				return null;
			} else if (!hasNamedParameters && paramCount == 1) {
				return args[params.keySet().iterator().next()];
			} else {
				final Map<String, Object> param = new ParamMap<Object>();
				int i = 0;
				for (Map.Entry<Integer, String> entry : params.entrySet()) {
					param.put(entry.getValue(), args[entry.getKey()]);
					// issue #71, add param names as param1, param2...but ensure backward
					// compatibility
					final String genericParamName = "param" + String.valueOf(i + 1);
					if (!param.containsKey(genericParamName)) {
						param.put(genericParamName, args[entry.getKey()]);
					}
					i++;
				}
				return param;
			}
		}

		private SortedMap<Integer, String> getParams(Method method, boolean hasNamedParameters) {
			final SortedMap<Integer, String> params = new TreeMap<Integer, String>();
			final Class<?>[] argTypes = method.getParameterTypes();
			for (int i = 0; i < argTypes.length; i++) {
				// 增加分页支持
				if (!Page.class.isAssignableFrom(argTypes[i]) && !RowBounds.class.isAssignableFrom(argTypes[i])
						&& !ResultHandler.class.isAssignableFrom(argTypes[i])) {
					// 增加完毕
					String paramName = String.valueOf(params.size());
					if (hasNamedParameters) {
						paramName = getParamNameFromAnnotation(method, i, paramName);
					}
					params.put(i, paramName);
				}
			}
			return params;
		}

		private String getParamNameFromAnnotation(Method method, int i, String paramName) {
			final Object[] paramAnnos = method.getParameterAnnotations()[i];
			for (Object paramAnno : paramAnnos) {
				if (paramAnno instanceof Param) {
					paramName = ((Param) paramAnno).value();
				}
			}
			return paramName;
		}

		private boolean hasNamedParams(Method method) {
			boolean hasNamedParams = false;
			final Object[][] paramAnnos = method.getParameterAnnotations();
			for (Object[] paramAnno : paramAnnos) {
				for (Object aParamAnno : paramAnno) {
					if (aParamAnno instanceof Param) {
						hasNamedParams = true;
						break;
					}
				}
			}
			return hasNamedParams;
		}

	}
}
