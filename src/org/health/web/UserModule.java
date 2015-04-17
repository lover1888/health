/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月8日 下午3:22:48
 * @version V1.0  
 */
package org.health.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.health.model.User;
import org.health.service.UserService;
import org.health.util.KbbConstants;
import org.health.util.MailSender;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月8日 下午3:22:48
 */
@IocBean
public class UserModule {
	
	@Inject
	private UserService userService; 
	
	@Inject
	private MailSender mailSender;
	
	@At("/login")
	@Ok("jsp:jsp.login")
	public void doLogin(){
	}
	
	@At("/register")
	public void doRegister(@Param("..") User user, HttpServletRequest req){
		this.userService.register(user, req);
	}
	@At("/register/?/confirm/?")
	public void doRegisterConfirm(String userId, long time){
		long be = System.currentTimeMillis() - time;
		if(be>3600000){
			this.userService.registerConfirm(userId, KbbConstants.Flag_Delete);
			throw Lang.makeThrow("激活已经过期，请重新注册。", new Object[0]);
		} else {
			this.userService.registerConfirm(userId, KbbConstants.Flag_Normal);
		}
	}
	
	@At("/logout")
	@Ok("redirect:/question")
	public void doLogout(){
		Subject subject = SecurityUtils.getSubject(); 
		subject.logout();
	}
	
	@At("/loginAct")
	@Ok("redirect:/question")
	@Fail("jsp:jsp.login")
	public void doLoginAction(@Param("userName") String userName, @Param("password") String password, HttpServletRequest req){
		 Subject subject = SecurityUtils.getSubject();  
		 ThreadContext.bind(subject);
		 UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		 try {
			subject.login(token);
			User u = this.userService.findUser(userName);
			subject.getSession().setAttribute(KbbConstants.SESSION_USER_ID, u.getUserId());
			
//			this.mailSender.sendText("hengfei.jin@kaihuahealth.com", "登录", "["+userName+"]登录了。");
		 }catch(UnknownAccountException e){
			 req.setAttribute("errmsg", "帐号不存在");
			 throw Lang.makeThrow("帐号不存在", new Object[0]);
		 }catch(IncorrectCredentialsException e){
			  req.setAttribute("errmsg", "密码错误");
			 throw Lang.makeThrow("密码错误", new Object[0]);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		} 
	}
	
	@At("/u/?")
	@Ok("jsp:jsp.user.user")
	public void doUserMain(String userName, HttpServletRequest req) {
		User user = this.userService.fetchUserInfo(userName);
		req.setAttribute("user", user);
	}

}
