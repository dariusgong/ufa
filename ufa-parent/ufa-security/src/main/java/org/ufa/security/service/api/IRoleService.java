/*******************************************************************************
 * Project Key : core
 * Create on 2012-6-9 涓???5:11:04
 ******************************************************************************/
package org.ufa.security.service.api;

import java.util.List;

import org.ufa.security.model.UfaBaseRole;


/**
 * <P>TODO</P>
 * @author Jonathan
 */
public interface IRoleService {
	public List<? extends UfaBaseRole> getAllRoles();
}
