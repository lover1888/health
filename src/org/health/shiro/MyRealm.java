/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月9日 下午6:09:23
 * @version V1.0  
 */
package org.health.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月9日 下午6:09:23
 */
public class MyRealm extends AuthorizingRealm {

//	 private UserService userService = new UserServiceImpl();  
	    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {  
	    	
	        String username = (String)principals.getPrimaryPrincipal();  
	        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//	        authorizationInfo.setRoles(userService.findRoles(username));  
//	        authorizationInfo.setStringPermissions(userService.findPermissions(username));  
	        return authorizationInfo;  
	    }  
	    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {  
//	        String username = (String)token.getPrincipal();  
//	        User user = userService.findByUsername(username);  
//	        if(user == null) {  
//	            throw new UnknownAccountException();//没找到帐号  
//	        }  
//	        if(Boolean.TRUE.equals(user.getLocked())) {  
//	            throw new LockedAccountException(); //帐号锁定  
//	        }  
//	        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现  
//	        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(  
//	                user.getUsername(), //用户名  
//	                user.getPassword(), //密码  
//	                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt  
//	                getName()  //realm name  
//	        );  
	        return null;//authenticationInfo;  
	    }  

}
