/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月1日 上午9:19:18
 * @version V1.0  
 */
package org.health.model;

import java.util.Date;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月1日 上午9:19:18
 */
@Table("tb_user_favorites")
public class Favorites {

	@Id
	private String id;
	private String userId;
	private String favoritesName;
	private String favoritesType;
	private Date createDate;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the favoritesName
	 */
	public String getFavoritesName() {
		return favoritesName;
	}

	/**
	 * @param favoritesName
	 *            the favoritesName to set
	 */
	public void setFavoritesName(String favoritesName) {
		this.favoritesName = favoritesName;
	}

	/**
	 * @return the favoritesType
	 */
	public String getFavoritesType() {
		return favoritesType;
	}

	/**
	 * @param favoritesType
	 *            the favoritesType to set
	 */
	public void setFavoritesType(String favoritesType) {
		this.favoritesType = favoritesType;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
