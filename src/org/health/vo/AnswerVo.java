/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月2日 下午5:29:41
 * @version V1.0  
 */
package org.health.vo;

import java.util.List;

import org.health.model.Answer;
import org.health.model.QuestionComment;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月2日 下午5:29:41
 */
public class AnswerVo extends BaseVo {

	private Answer answer;

	private List<QuestionComment> comments;

	

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	/**
	 * @return the comments
	 */
	public List<QuestionComment> getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(List<QuestionComment> comments) {
		this.comments = comments;
	}

}
