/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月10日 上午9:22:34
 * @version V1.0  
 */
package org.health.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.health.model.Permissions;
import org.health.model.User;
import org.health.util.KbbConstants;
import org.health.util.KbbUtils;
import org.health.util.MailSender;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.impl.sql.callback.EntityCallback;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlContext;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.service.EntityService;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月10日 上午9:22:34
 */
@IocBean(fields = { "dao" })
public class UserService extends EntityService<User> {
	
	@Inject
	private MailSender mailSender;

	public User findUser(String userName){
		User user = dao().fetch(User.class, Cnd.where("userName", "=", userName));
		return user;
	}
	
	public void registerConfirm(String userId, int flag) {
		if(KbbConstants.Flag_Normal == flag){
			dao().update(User.class, Chain.make("flag", 0), Cnd.where("userId", "=", userId));
		} else if(KbbConstants.Flag_Delete == flag){
			dao().delete(User.class, userId);
		} 
	}
	
	public User register(User user,HttpServletRequest req){
		if(!Strings.isEmail(user.getEmail())){
			throw Lang.makeThrow("邮箱地址不合法", new Object[0]);
		}
		User u = findUser(user.getUserName());
		if(!Lang.isEmpty(u)){
			throw Lang.makeThrow("用户名已经被注册", new Object[0]);
		}
		
		user.setUserId(KbbUtils.generateID());
		user.setFlag(KbbConstants.Flag_UnActive);
		user.setBirthday(new Date());
		user.setSex(1);
		u = dao().insert(user);
		
		String url = "http://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/register/"+user.getUserId()+"/confirm/"+System.currentTimeMillis();
		// 发邮件
		String content = "看病吧<br>"
				+ "最专业的看病问答社区<hr><p>"
				+ user.getUserName()+"您好，感谢您注册看病吧问答社区<p>"
				+ "请在1小时内点击此链接以完成注册<br>"
				+ url;
		this.mailSender.sendHtml(user.getEmail(), "欢迎加入看病吧-问答社区", content);
		
		return u;
	}
	
	public User fetchUserInfo(String userName) {
//		User user = dao().fetchLinks(dao().fetch(User.class, Cnd.where("userName", "=", userName)),"tags");
		User user = dao().fetchLinks(dao().fetch(User.class, Cnd.where("userName", "=", userName)),"userTags");
		user = dao().fetchLinks(user, "reputations",Cnd.orderBy().desc("createDate"));
		
		user = dao().fetchLinks(user, "focusQuestions");
		return user;
	}
	
	public User fetchUserTags(String userName) {
		User user = dao().fetchLinks(dao().fetch(User.class, Cnd.where("userName", "=", userName)),"userTags");
		return user;
	}
	
	public User fetchUserRole(String userName){
		User user = dao().fetchLinks(dao().fetch(User.class, Cnd.where("userName", "=", userName)),"roles");
		return user;
	}
	
	public User fetchUserRoleAndPermissions(final String userName) {
		final User user = fetchUserRole(userName);
		if(Lang.isEmpty(user)){
			return null;
		}
		Sql sql = Sqls.create("select DISTINCT p.* from tb_user u, tb_user_role ur, tb_role_permissions rp, tb_permissions p  where u.userName=@uname and u.userId=ur.userId and ur.roleId = rp.roleId and p.id = rp.permissionId;");
		sql.params().set("uname", userName);
		sql.setEntity(dao().getEntity(Permissions.class));
		sql.setCallback(new EntityCallback(){
			@Override
			protected User process(ResultSet rs, Entity<?> entity,
					SqlContext context) throws SQLException {
					Set<Permissions> psets = new HashSet<Permissions>();
					while(rs.next()){
						psets.add((Permissions)entity.getObject(rs, context.getFieldMatcher(), null));
					}
					user.setPermissions(psets);
				return user;
			}
		});
		dao().execute(sql);
		return sql.getObject(User.class);
	}

}
