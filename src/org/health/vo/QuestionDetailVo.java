/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月2日 下午5:29:16
 * @version V1.0  
 */
package org.health.vo;

import java.util.List;

import org.health.model.Answers;
import org.health.model.QuestionComments;
import org.health.model.Question;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月2日 下午5:29:16
 */
public class QuestionDetailVo extends BaseVo {
	
	private boolean isFocus;
	private boolean isFavorite;

	private Question question;
	
	private List<QuestionComments> comments;
	
	private List<Answers> answers;

	/**  
	 * @return the isFocus  
	 */
	public boolean getIsFocus() {
		return isFocus;
	}

	/**  
	 * @param isFocus the isFocus to set  
	 */
	public void setFocus(boolean isFocus) {
		this.isFocus = isFocus;
	}

	/**  
	 * @return the isFavorite  
	 */
	public boolean getIsFavorite() {
		return isFavorite;
	}

	/**  
	 * @param isFavorite the isFavorite to set  
	 */
	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	/**  
	 * @return the question  
	 */
	public Question getQuestion() {
		return question;
	}

	/**  
	 * @param question the question to set  
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**  
	 * @return the comments  
	 */
	public List<QuestionComments> getComments() {
		return comments;
	}

	/**  
	 * @param comments the comments to set  
	 */
	public void setComments(List<QuestionComments> comments) {
		this.comments = comments;
	}

	/**  
	 * @return the answers  
	 */
	public List<Answers> getAnswers() {
		return answers;
	}

	/**  
	 * @param answers the answers to set  
	 */
	public void setAnswers(List<Answers> answers) {
		this.answers = answers;
	}
	
	
	
}
