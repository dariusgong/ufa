/*******************************************************************************
 * Project Key : core
 * Create on 2012-6-9 涓???5:11:38
 ******************************************************************************/
package org.ufa.security.service.api;

import java.util.List;

import org.ufa.security.model.UfaBaseResource;


/**
 * <P>TODO</P>
 * @author Jonathan
 */
public interface IResourceService {
	public List<? extends UfaBaseResource> findByRoleID(Long id, String appId);
}
