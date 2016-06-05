package org.ufa.mybatis.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.ApplicationContext;
import org.ufa.mybatis.stereotype.UfaMapper;
import org.ufa.util.UfaUtils;


/**
 * <P> 扩展mybaits原来的扫描器，使其能自动从多个配置文件中读取base package信息，并组装成一个以英文逗号分隔的字符串，作为basePackage</P>
 * 
 */
public class UfaMapperScannerConfigurer extends MapperScannerConfigurer {
	private ApplicationContext applicationContext;
	private String basePackage;

	@Override
	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
		super.setApplicationContext(applicationContext);
		setAnnotationClass(UfaMapper.class);
	}

	/*
	 * <p>注意：之所以增加basePackage属性并判断其是否为null，是为了支持多数据源配置。因为在配置多数据源时，会配置多个SqlSessionFactoryBean，
	 * 而每个UfaMapperScannerConfigurer都只能对应一个SqlSessionFactoryBean
	 * ，所以也需要配置多个UfaMapperScannerConfigurer
	 * 。而UfaMapperScannerConfigurer父类的doScan方法有个问题，其调用了super
	 * ，而之后的N次扫描，因为ClassPathBeanDefinitionScanner
	 * .checkCandidate方法的第一行判断，导致所有已经注册的bean，都会被再被获取。所以除了第一次之外
	 * ，后续所有的扫描都无法获得任何使用了@Mapper的bean。</p>
	 * <p>解决方法有多个：一种覆盖MapperScannerConfigurer.Scanner.doScan方法
	 * ，使其不使用父类的doScan方法，改为每次都能获取到使用了@Mapper的bean的方法
	 * 。另一种更简单一点的就是目前使用的方法，每个MapperScannerConfigurer都指定一个不重复也不互相包含的包，这样由于扫描的包不同，也可以解决这个问题。</p>
	 * @see org.mybatis.spring.mapper.MapperScannerConfigurer#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		if (basePackage == null) {
			String allBasePackages = UfaUtils.getAllBasePackagesString(applicationContext);
			super.setBasePackage(allBasePackages.toString());
		} else {
			super.setBasePackage(basePackage);
		}
		super.afterPropertiesSet();
	}

}
