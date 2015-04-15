/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月1日 上午8:56:00
 * @version V1.0  
 */
package org.health.model;

import java.util.Date;
import java.util.List;

import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Readonly;
import org.nutz.dao.entity.annotation.Table;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月1日 上午8:56:00
 */
@Table("tb_question")
public class Question {

	@Name
	private String questionId;

	private String title;
	private String tags;
	private String content;
	private String userId;
	private Date createDate = new Date();
	private Date updateDate = new Date();
	private int viewCount;
	private int answersCount;
	@Readonly
	private int voteCount;
	private int voteAddCount;
	private int voteReduceCount;
	private int favoriteCount;
	private int focusCount;
	private int commentCount;
	private boolean isComplaints;
	private int flag;
	private int questionStatus;

	private String lastActiveInfo;

	@Many(target = Answers.class, field = "questionId")
	private List<Answers> answers;

	
	
	public int getVoteAddCount() {
		return voteAddCount;
	}

	public void setVoteAddCount(int voteAddCount) {
		this.voteAddCount = voteAddCount;
	}

	public int getVoteReduceCount() {
		return voteReduceCount;
	}

	public void setVoteReduceCount(int voteReduceCount) {
		this.voteReduceCount = voteReduceCount;
	}

	/**
	 * @return the questionId
	 */
	public String getQuestionId() {
		return questionId;
	}

	/**
	 * @param questionId
	 *            the questionId to set
	 */
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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
	 * @return the viewCount
	 */
	public int getViewCount() {
		return viewCount;
	}

	/**
	 * @param viewCount
	 *            the viewCount to set
	 */
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	/**
	 * @return the answersCount
	 */
	public int getAnswersCount() {
		return answersCount;
	}

	/**
	 * @param answersCount
	 *            the answersCount to set
	 */
	public void setAnswersCount(int answersCount) {
		this.answersCount = answersCount;
	}

	/**
	 * @return the voteCount
	 */
	public int getVoteCount() {
		return voteCount = this.voteAddCount-this.voteReduceCount;
	}

	/**
	 * @param voteCount
	 *            the voteCount to set
	 */
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	/**
	 * @return the favoriteCount
	 */
	public int getFavoriteCount() {
		return favoriteCount;
	}

	/**
	 * @param favoriteCount
	 *            the favoriteCount to set
	 */
	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
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

	/**
	 * @return the commentCount
	 */
	public int getCommentCount() {
		return commentCount;
	}

	/**
	 * @param commentCount
	 *            the commentCount to set
	 */
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	/**
	 * @return the isComplaints
	 */
	public boolean isComplaints() {
		return isComplaints;
	}

	/**
	 * @param isComplaints
	 *            the isComplaints to set
	 */
	public void setComplaints(boolean isComplaints) {
		this.isComplaints = isComplaints;
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
	 * @return the answers
	 */
	public List<Answers> getAnswers() {
		return answers;
	}

	/**
	 * @param answers
	 *            the answers to set
	 */
	public void setAnswers(List<Answers> answers) {
		this.answers = answers;
	}

	/**
	 * @return the lastActiveInfo
	 */
	public String getLastActiveInfo() {
		return lastActiveInfo;
	}

	/**
	 * @param lastActiveInfo
	 *            the lastActiveInfo to set
	 */
	public void setLastActiveInfo(String lastActiveInfo) {
		this.lastActiveInfo = lastActiveInfo;
	}

	/**
	 * @return the questionStatus
	 */
	public int getQuestionStatus() {
		return questionStatus;
	}

	/**
	 * @param questionStatus
	 *            the questionStatus to set
	 */
	public void setQuestionStatus(int questionStatus) {
		this.questionStatus = questionStatus;
	}

}
