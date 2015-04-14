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
import java.util.HashSet;
import java.util.Set;

import org.health.model.Permissions;
import org.health.model.User;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.impl.sql.callback.EntityCallback;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlContext;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.service.EntityService;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月10日 上午9:22:34
 */
@IocBean(fields = { "dao" })
public class UserService extends EntityService<User> {

	public User findUser(String userName){
		User user = dao().fetch(User.class, Cnd.where("userName", "=", userName));
		return user;
	}
	
	public User fetchUserInfo(String userName) {
//		User user = dao().fetchLinks(dao().fetch(User.class, Cnd.where("userName", "=", userName)),"tags");
		User user = dao().fetchLinks(dao().fetch(User.class, Cnd.where("userName", "=", userName)),"userTags");
		user = dao().fetchLinks(user, "reputations",Cnd.orderBy().desc("createDate"));
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
