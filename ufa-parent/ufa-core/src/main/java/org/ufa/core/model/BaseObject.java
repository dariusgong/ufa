package org.ufa.core.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;


public class BaseObject implements java.io.Serializable {
	
	private static final long serialVersionUID = -5097958527961076055L;

	@Override
	public boolean equals(final Object other) {
		return EqualsBuilder.reflectionEquals(this, other);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return new ReflectionToStringBuilder(this).toString();
	}
}
