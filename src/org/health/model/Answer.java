/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月1日 上午9:00:25
 * @version V1.0  
 */
package org.health.model;

import java.util.Date;
import java.util.List;

import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Readonly;
import org.nutz.dao.entity.annotation.Table;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月1日 上午9:00:25
 */
@Table("tb_answer")
public class Answer {
	@Name
	private String answerId;
	private String questionId;

	@One(target = Question.class, field = "questionId")
	private Question question;

	private String userId;
	
	private String content;
	private Date createDate = new Date();
	private Date updateDate = new Date();
	@Readonly
	private int voteCount;
	private int voteAddCount;
	private int voteReduceCount;
	private int commentCount;
	private boolean isAdoption;
	private boolean isIgnore;
	private boolean isComplaints;
	private int flag;
	
	@Many(target=AnswerComment.class, field="answerId")
	private List<AnswerComment> answerComments;
	
	public List<AnswerComment> getAnswerComments() {
		return answerComments;
	}

	public void setAnswerComments(List<AnswerComment> answerComments) {
		this.answerComments = answerComments;
	}

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


	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
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
	 * @return the isAdoption
	 */
	public boolean getIsAdoption() {
		return isAdoption;
	}

	/**
	 * @param isAdoption
	 *            the isAdoption to set
	 */
	public void setIsAdoption(boolean isAdoption) {
		this.isAdoption = isAdoption;
	}

	/**
	 * @return the isIgnore
	 */
	public boolean isIgnore() {
		return isIgnore;
	}

	/**
	 * @param isIgnore
	 *            the isIgnore to set
	 */
	public void setIgnore(boolean isIgnore) {
		this.isIgnore = isIgnore;
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
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

}
