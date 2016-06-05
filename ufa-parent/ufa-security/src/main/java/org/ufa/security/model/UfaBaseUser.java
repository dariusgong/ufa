package org.ufa.security.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.ufa.core.model.BaseObject;


public class UfaBaseUser extends BaseObject implements UserDetails, CredentialsContainer {
	/**
	 * TODO
	 */
	private static final long serialVersionUID = -3672159312167522936L;
	private Long userId;
	private String password;
	private final String username = null;
	private final Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	private final boolean accountNonExpired = true;
	private final boolean accountNonLocked = true;
	private final boolean credentialsNonExpired = true;
	private final boolean enabled = true;

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the authorities
	 */
	public Set<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	/**
	 * @return the accountNonExpired
	 */
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	/**
	 * @return the accountNonLocked
	 */
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	/**
	 * @return the credentialsNonExpired
	 */
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public void eraseCredentials() {
		// TODO Auto-generated method stub

	}
}