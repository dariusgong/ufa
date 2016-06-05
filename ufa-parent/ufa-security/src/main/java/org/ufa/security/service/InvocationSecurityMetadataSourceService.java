/*******************************************************************************
 * Project Key : sample Create on 2012-6-8 上午1:13:11
 ******************************************************************************/
package org.ufa.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.ufa.core.service.BaseService;
import org.ufa.security.model.UfaBaseResource;
import org.ufa.security.model.UfaBaseRole;
import org.ufa.security.service.api.IResourceService;
import org.ufa.security.service.api.IRoleService;



/**
 * <P>初始化权限服务</P>
 * 
 * @author Jonathan
 */
public class InvocationSecurityMetadataSourceService extends BaseService implements FilterInvocationSecurityMetadataSource {

	private IRoleService roleService;

	private IResourceService resourceService;

	private String appId;
	
	private AntPathMatcher urlMatcher = new AntPathMatcher();
	
	private Map<String, Collection<ConfigAttribute>> resourceMap = null;
	
	
	public void loadResourceDefine() throws Exception {
		logger.info("进入InvocationSecurityMetadataSourceService.loadResourceDefine: 开始加载所有资源信息");
		this.resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		for (UfaBaseRole role : this.roleService.getAllRoles()) {
			logger.debug("角色名称{}",role.getRoleName());
			Collection<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();
			ConfigAttribute attribute = new SecurityConfig(role.getRoleName());
			attributes.add(attribute);
			List<? extends UfaBaseResource> resources = resourceService.findByRoleID(role.getRoleId(), appId);
			for (UfaBaseResource resource : resources) {
				Collection<ConfigAttribute> configAttribute=resourceMap.get(resource.getUrl());
				if(configAttribute==null){
					resourceMap.put(resource.getUrl(), attributes);	
				}else{
					configAttribute.add(attribute);
				}
				
			}
		}
	}

	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

		String url = ((FilterInvocation) object).getRequestUrl();
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			if (urlMatcher.match(resURL,url)) {
				Collection<ConfigAttribute> returnCollection = resourceMap.get(resURL);

				return returnCollection;
			}
		}

		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	/**
	 * @return the roleService
	 */
	public IRoleService getRoleService() {
		return roleService;
	}

	/**
	 * @param roleService the roleService to set
	 */
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * @return the resourceService
	 */
	public IResourceService getResourceService() {
		return resourceService;
	}

	/**
	 * @param resourceService the resourceService to set
	 */
	public void setResourceService(IResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
}
