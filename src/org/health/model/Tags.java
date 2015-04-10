/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月1日 上午9:05:39
 * @version V1.0  
 */
package org.health.model;

import java.util.Date;

import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月1日 上午9:05:39
 */
@Table("tb_tags")
public class Tags {
	@Name
	private String tagName;
	private String tagType;
	private Date createDate = new Date();
	private Date updateDate = new Date();
	private String tagDescribe;
	private int focusCount;


	/**
	 * @return the tagName
	 */
	public String getTagName() {
		return tagName;
	}

	/**
	 * @param tagName
	 *            the tagName to set
	 */
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	/**
	 * @return the tagType
	 */
	public String getTagType() {
		return tagType;
	}

	/**
	 * @param tagType
	 *            the tagType to set
	 */
	public void setTagType(String tagType) {
		this.tagType = tagType;
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

	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate
	 *            the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the tagDescribe
	 */
	public String getTagDescribe() {
		return tagDescribe;
	}

	/**
	 * @param tagDescribe
	 *            the tagDescribe to set
	 */
	public void setTagDescribe(String tagDescribe) {
		this.tagDescribe = tagDescribe;
	}

	/**
	 * @return the focusCount
	 */
	public int getFocusCount() {
		return focusCount;
	}

	/**
	 * @param focusCount
	 *            the focusCount to set
	 */
	public void setFocusCount(int focusCount) {
		this.focusCount = focusCount;
	}

}
