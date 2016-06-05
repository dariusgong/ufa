/*******************************************************************************
 * Project Key : sample Create on 2012-6-7
 ******************************************************************************/
package org.ufa.security.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * <P>
 * TODO
 * </P>
 * 
 * @author Jonathan
 */
public class SecurityContext {

	public static String getUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		} else {
			return principal.toString();
		}
	}
}
