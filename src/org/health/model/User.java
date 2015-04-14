/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年3月31日 下午5:02:36
 * @version V1.0  
 */
package org.health.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Readonly;
import org.nutz.dao.entity.annotation.Table;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年3月31日 下午5:02:36
 */

@Table("tb_user")
public class User {

	@Name
	private String userId;

	private String userName;

	private String sex;
	private Date birthday;
	private String city;
	private String address;
	private String signature;
	private String profession;
	private String degree;
	private String email;
	private String telphone;
	private String imageUrl;
	private int reputationCount;
	private int badgeCount;
	private int fansCount;
	private int praiseCount;
	private String introduce;
	private Date createDate;
	private Date updateDate;
	private boolean isDoctor;
	private String personName;
	private int flag;
	private String password;

	@ManyMany(target = Role.class, relation = "tb_user_role", from = "userId", to = "roleId")
	private List<Role> roles;

	@Readonly
	private Set<Permissions> permissions;

	@ManyMany(target = Tags.class, relation = "tb_user_tags", from = "userId", to = "tagName")
	private List<Tags> tags;

	@Many(target = UserTags.class, field = "userId")
	private List<UserTags> userTags;

	// 声望记录
	@Many(target = Reputation.class, field = "userId")
	private List<Reputation> reputations;
	
	//关注的问题
	@ManyMany(target = Question.class, relation="tb_focus_question", from="userId", to="questionId")
	private List<Question> focusQuestions;
	
	public List<Question> getFocusQuestions() {
		return focusQuestions;
	}

	public void setFocusQuestions(List<Question> focusQuestions) {
		this.focusQuestions = focusQuestions;
	}

	/**
	 * @return the reputations
	 */
	public List<Reputation> getReputations() {
		return reputations;
	}

	/**
	 * @param reputations
	 *            the reputations to set
	 */
	public void setReputations(List<Reputation> reputations) {
		this.reputations = reputations;
	}

	/**
	 * @return the userTags
	 */
	public List<UserTags> getUserTags() {
		return userTags;
	}

	/**
	 * @param userTags
	 *            the userTags to set
	 */
	public void setUserTags(List<UserTags> userTags) {
		this.userTags = userTags;
	}

	/**
	 * @return the tags
	 */
	public List<Tags> getTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(List<Tags> tags) {
		this.tags = tags;
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
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * @param signature
	 *            the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * @return the profession
	 */
	public String getProfession() {
		return profession;
	}

	/**
	 * @param profession
	 *            the profession to set
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}

	/**
	 * @return the degree
	 */
	public String getDegree() {
		return degree;
	}

	/**
	 * @param degree
	 *            the degree to set
	 */
	public void setDegree(String degree) {
		this.degree = degree;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telphone
	 */
	public String getTelphone() {
		return telphone;
	}

	/**
	 * @param telphone
	 *            the telphone to set
	 */
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl
	 *            the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * @return the reputationCount
	 */
	public int getReputationCount() {
		return reputationCount;
	}

	/**
	 * @param reputationCount
	 *            the reputationCount to set
	 */
	public void setReputationCount(int reputationCount) {
		this.reputationCount = reputationCount;
	}

	/**
	 * @return the badgeCount
	 */
	public int getBadgeCount() {
		return badgeCount;
	}

	/**
	 * @param badgeCount
	 *            the badgeCount to set
	 */
	public void setBadgeCount(int badgeCount) {
		this.badgeCount = badgeCount;
	}

	/**
	 * @return the fansCount
	 */
	public int getFansCount() {
		return fansCount;
	}

	/**
	 * @param fansCount
	 *            the fansCount to set
	 */
	public void setFansCount(int fansCount) {
		this.fansCount = fansCount;
	}

	/**
	 * @return the praiseCount
	 */
	public int getPraiseCount() {
		return praiseCount;
	}

	/**
	 * @param praiseCount
	 *            the praiseCount to set
	 */
	public void setPraiseCount(int praiseCount) {
		this.praiseCount = praiseCount;
	}

	/**
	 * @return the introduce
	 */
	public String getIntroduce() {
		return introduce;
	}

	/**
	 * @param introduce
	 *            the introduce to set
	 */
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
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
	 * @return the isDoctor
	 */
	public boolean isDoctor() {
		return isDoctor;
	}

	/**
	 * @param isDoctor
	 *            the isDoctor to set
	 */
	public void setDoctor(boolean isDoctor) {
		this.isDoctor = isDoctor;
	}

	/**
	 * @return the personName
	 */
	public String getPersonName() {
		return personName;
	}

	/**
	 * @param personName
	 *            the personName to set
	 */
	public void setPersonName(String personName) {
		this.personName = personName;
	}

	/**
	 * @return the flag
	 */
	public int getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(int flag) {
		this.flag = flag;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	/**
	 * @return the permissions
	 */
	public Set<Permissions> getPermissions() {
		return permissions;
	}

	/**
	 * @param permissions
	 *            the permissions to set
	 */
	public void setPermissions(Set<Permissions> permissions) {
		this.permissions = permissions;
	}

}
