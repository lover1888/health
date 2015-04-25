/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月9日 下午6:09:23
 * @version V1.0  
 */
package org.health.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.health.model.Permissions;
import org.health.model.Role;
import org.health.model.User;
import org.health.service.UserService;
import org.health.util.KbbConstants;
import org.nutz.lang.Lang;
import org.nutz.mvc.Mvcs;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月9日 下午6:09:23
 */
public class MyRealm extends AuthorizingRealm {

	UserService getUserService() {
		return Mvcs.getIoc().get(UserService.class);
	}

	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String userName = (String) principals.getPrimaryPrincipal();
		UserService us = getUserService();
		User u = us.fetchUserRoleAndPermissions(userName);
		List<Role> roles = u.getRoles();
		Set<String> roleset = new HashSet<String>();
		if (!Lang.isEmpty(roles)) {
			for (Role r : roles) {
				roleset.add(r.getRoleName());
			}
		}
		Set<Permissions> sets = u.getPermissions();
		Set<String> psets = new HashSet<String>();
		if (!Lang.isEmpty(sets)) {
			for (Permissions p : sets) {
				if (u.getReputationCount() >= p.getNeedReputation()) {
					psets.add(p.getPermissionName());
				}
			}
		}
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(roleset);
		authorizationInfo.setStringPermissions(psets);
		return authorizationInfo;
	}

	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		User u = getUserService().findUser(userName);
		if (u == null) {
			throw new UnknownAccountException("帐号未注册.");// 没找到帐号
		}
		if(KbbConstants.Flag_Normal==u.getFlag()){
			throw new UnknownAccountException("帐号未激活.");
		}
		
		// //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现
		// SimpleAuthenticationInfo authenticationInfo = new
		// SimpleAuthenticationInfo(
		// user.getUsername(), //用户名
		// user.getPassword(), //密码
		// ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
		// getName() //realm name
		// );
		
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				u.getUserName(), u.getPassword(), getName());
		
		return authenticationInfo;
	}

}
