/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月8日 下午3:22:48
 * @version V1.0  
 */
package org.health.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月8日 下午3:22:48
 */
@IocBean
public class UserModule {
	@At("/login")
	@Ok("jsp:jsp.login")
	public void doLogin(){
//		 Subject subject = SecurityUtils.getSubject();  
//		 ThreadContext.bind(subject);
//		 UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
//		 subject.login(token);
	}
	
	@At("/loginAct")
	@Ok("redirect:/question")
	public void doLoginAction(@Param("userName") String userName, @Param("password") String password){
		 Subject subject = SecurityUtils.getSubject();  
		 ThreadContext.bind(subject);
		 UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		 subject.login(token);
	}

}
