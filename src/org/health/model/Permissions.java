/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月1日 上午9:25:00
 * @version V1.0  
 */
package org.health.model;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月1日 上午9:25:00
 */
@Table("tb_permissions")
public class Permissions {
	@Id
	private int id;
	private String permissionName;
	private String permissionDescribe;
	private int needReputation;
	private boolean isOnlySelf;

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
	 * @return the permissionName
	 */
	public String getPermissionName() {
		return permissionName;
	}

	/**
	 * @param permissionName
	 *            the permissionName to set
	 */
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	/**
	 * @return the permissionDescribe
	 */
	public String getPermissionDescribe() {
		return permissionDescribe;
	}

	/**
	 * @param permissionDescribe
	 *            the permissionDescribe to set
	 */
	public void setPermissionDescribe(String permissionDescribe) {
		this.permissionDescribe = permissionDescribe;
	}

	/**
	 * @return the needReputation
	 */
	public int getNeedReputation() {
		return needReputation;
	}

	/**
	 * @param needReputation
	 *            the needReputation to set
	 */
	public void setNeedReputation(int needReputation) {
		this.needReputation = needReputation;
	}

	/**
	 * @return the isOnlySelf
	 */
	public boolean isOnlySelf() {
		return isOnlySelf;
	}

	/**
	 * @param isOnlySelf
	 *            the isOnlySelf to set
	 */
	public void setOnlySelf(boolean isOnlySelf) {
		this.isOnlySelf = isOnlySelf;
	}

}
