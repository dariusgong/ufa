package org.ufa.core.context;

import org.springframework.context.ApplicationEvent;


/**
 * <P>
 * 所有ufa模块和基于ufa构造的应用中的事件基类,用于屏蔽对spring的依赖,同时便于从此类出发获取所有ufa事件
 * </P>
 * 
 *  
 */
public abstract class UfaApplicationEvent extends ApplicationEvent {
	private static final long	serialVersionUID	= -3362244931949128820L;
	/**
	 * 是否需要异步处理此事件?true表示是异步.默认为true
	 */
	private boolean				async				= true;

	/**
	 * Create a new ufa ApplicationEvent.
	 * 
	 * @param source
	 *            the component that published the event (never <code>null</code>)
	 */
	public UfaApplicationEvent(Object source) {
		super(source == null ? "" : source);
	}

	/**
	 * @return 是否需要异步处理此事件?true表示是异步.默认为true
	 */
	public boolean isAsync() {
		return async;
	}

	/**
	 * @param async
	 *            是否需要异步处理此事件?true表示是异步.默认为true
	 */
	public void setAsync(boolean async) {
		this.async = async;
	}

}
