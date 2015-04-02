/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月2日 下午5:31:07
 * @version V1.0  
 */
package org.health.vo;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月2日 下午5:31:07
 */
public class BaseVo {

	private String userId;
	private String userName;
	private String imgUrl;
	private int reputation;

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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the reputation
	 */
	public int getReputation() {
		return reputation;
	}

	/**
	 * @param reputation
	 *            the reputation to set
	 */
	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

}
