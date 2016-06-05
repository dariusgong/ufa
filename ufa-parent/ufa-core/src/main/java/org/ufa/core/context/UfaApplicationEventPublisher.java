package org.ufa.core.context;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

public class UfaApplicationEventPublisher implements ApplicationEventPublisherAware {
	private ApplicationEventPublisher	ufaApplicationEventPublisher;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.ufaApplicationEventPublisher = applicationEventPublisher;
	}

	/**
	 * 发布异常事件
	 * 
	 * @param event
	 */
	public void publish(UfaApplicationEvent event) {
		ufaApplicationEventPublisher.publishEvent(event);
	}
}
