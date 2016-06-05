/*******************************************************************************
 * Project   : ufa-core
 * Class Name: org.ufa.core.model.BaseCommand
 * Created By: Jonathan 
 * Created on: 2014年11月25日 上午1:40:25
 * Copyright © 2010-2012 KFT Pay All rights reserved.
 ******************************************************************************/
package org.ufa.core.model;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;


/**
 * <P>Command基类</P>
 * @author Jonathan
 */
public class BaseCommand {

	@Override
	public String toString() {
		return new ReflectionToStringBuilder(this).toString();
	}
}
