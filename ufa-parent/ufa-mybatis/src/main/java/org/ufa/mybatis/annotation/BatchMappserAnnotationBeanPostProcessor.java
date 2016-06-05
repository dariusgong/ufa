package org.ufa.mybatis.annotation;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.ufa.log.Logger;
import org.ufa.log.LoggerFactory;
import org.ufa.mybatis.spring.UfaSqlSessionTemplate;
import org.ufa.util.ReflectionUtils;


/**
 * <P>
 * 扫描所有加在bean的field之上的@BatchMapper,找到后,将从spring
 * 容器中找到对应的mapper,在执行postProcessPropertyValues时注入给此field
 * </P>
 * 
 *  
 */
public class BatchMappserAnnotationBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter implements
		PriorityOrdered, BeanFactoryAware {
	private static final Logger logger = LoggerFactory.getLogger(BatchMappserAnnotationBeanPostProcessor.class);
	private ConfigurableListableBeanFactory beanFactory;
	private final Map<Class<?>, InjectionMetadata> injectionMetadataCache = new ConcurrentHashMap<Class<?>, InjectionMetadata>();
	private String batchExecutorSqlSessionBeanName;

	public void setBatchExecutorSqlSessionBeanName(String batchExecutorSqlSessionBeanName) {
		this.batchExecutorSqlSessionBeanName = batchExecutorSqlSessionBeanName;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.core.Ordered#getOrder()
	 *  
	 */
	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE + 100;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.beans.factory.BeanFactoryAware#setBeanFactory(org.springframework.beans
	 * .factory.BeanFactory)
	 *  
	 */
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		if (!(beanFactory instanceof ConfigurableListableBeanFactory)) {
			throw new IllegalArgumentException(
					"BatchMappserAnnotationBeanPostProcessor requires a ConfigurableListableBeanFactory");
		}
		this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
	}

	@Override
	public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean,
			String beanName) throws BeansException {

		InjectionMetadata metadata = findBatchMapperMetadata(bean.getClass());
		try {
			metadata.inject(bean, beanName, pvs);
		} catch (Throwable ex) {
			throw new BeanCreationException(beanName, "Injection of batchMapper dependencies failed", ex);
		}
		return pvs;
	}

	public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
		if (beanType != null) {
			InjectionMetadata metadata = findBatchMapperMetadata(beanType);
			metadata.checkConfigMembers(beanDefinition);
		}
	}

	private InjectionMetadata findBatchMapperMetadata(Class<?> clazz) {
		InjectionMetadata metadata = this.injectionMetadataCache.get(clazz);
		if (metadata == null) {
			synchronized (this.injectionMetadataCache) {
				metadata = this.injectionMetadataCache.get(clazz);
				if (metadata == null) {
					metadata = buildBatchMapperMetadata(clazz);
					this.injectionMetadataCache.put(clazz, metadata);
				}
			}
		}
		return metadata;
	}

	private InjectionMetadata buildBatchMapperMetadata(Class<?> clazz) {
		LinkedList<InjectionMetadata.InjectedElement> elements = new LinkedList<InjectionMetadata.InjectedElement>();
		Class<?> targetClass = clazz;

		do {
			for (Field field : targetClass.getDeclaredFields()) {
				Annotation annotation = field.getAnnotation(BatchMapper.class);
				if (annotation != null) {
					if (Modifier.isStatic(field.getModifiers())) {
						if (logger.isWarnEnabled()) {
							logger.warn("BatchMapper annotation is not supported on static fields: " + field);
						}
						continue;
					}
					elements.add(new BatchMapperFieldElement(field, true));
				}
			}
			targetClass = targetClass.getSuperclass();
		} while (targetClass != null && targetClass != Object.class);
		return new InjectionMetadata(clazz, elements);
	}

	public Object getBatchMapperBean(DependencyDescriptor descriptor) {
		UfaSqlSessionTemplate template = null;
		try {
			template = beanFactory.getBean(batchExecutorSqlSessionBeanName, UfaSqlSessionTemplate.class);
		} catch (NoSuchBeanDefinitionException e) {
			throw new BeanCreationException(
					"BatchMapper annotation requires at least one bean[name=batchExecutorSqlSession]");
		}
		// getMapper方法找不到对应的mapper,自己会抛异常,不用在判断是否为null
		Object mapper = template.getMapper(descriptor.getDependencyType());
		// ReflectionUtils.setField(field, bean, mapper);
		return mapper;
	}

	private class BatchMapperFieldElement extends InjectionMetadata.InjectedElement {

		private final boolean required;

		private volatile boolean cached = false;

		private volatile Object cachedFieldValue;

		public BatchMapperFieldElement(Field field, boolean required) {
			super(field, null);
			this.required = required;
		}

		@Override
		protected void inject(Object bean, String beanName, PropertyValues pvs) throws Throwable {
			Field field = (Field) this.member;
			try {
				Object value = null;
				if (this.cached) {
					value = this.cachedFieldValue;
				} else {
					DependencyDescriptor descriptor = new DependencyDescriptor(field, this.required);
					// Set<String> batchMapperBeanNames = new LinkedHashSet<String>(1);
					// TypeConverter typeConverter = beanFactory.getTypeConverter();
					// value = beanFactory.resolveDependency(descriptor, beanName,
					// batchMapperBeanNames, typeConverter);
					value = getBatchMapperBean(descriptor);
					synchronized (this) {
						if (!this.cached) {
							if (value != null) {
								this.cachedFieldValue = value;
							} else {
								this.cachedFieldValue = null;
							}
							this.cached = true;
						}
					}
				}
				if (value != null) {
					ReflectionUtils.makeAccessible(field);
					field.set(bean, value);
				} else {
					throw new BeanCreationException(
							"Could not autowire batchMapper field,because had not found any of candidates.");
				}
			} catch (Throwable ex) {
				throw new BeanCreationException("Could not autowire batchMapper field: " + field, ex);
			}
		}
	}

}
