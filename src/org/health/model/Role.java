/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月1日 上午9:23:12
 * @version V1.0  
 */
package org.health.model;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月1日 上午9:23:12
 */
@Table("tb_role")
public class Role {
	@Id
	private int id;
	private String roleName;
	private String roleDescribe;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName
	 *            the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the roleDescribe
	 */
	public String getRoleDescribe() {
		return roleDescribe;
	}

	/**
	 * @param roleDescribe
	 *            the roleDescribe to set
	 */
	public void setRoleDescribe(String roleDescribe) {
		this.roleDescribe = roleDescribe;
	}

}
