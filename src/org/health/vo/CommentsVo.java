/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月16日 下午1:11:33
 * @version V1.0  
 */
package org.health.vo;

import org.health.model.AnswerComment;
import org.health.model.QuestionComment;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月16日 下午1:11:33
 */
public class CommentsVo extends BaseVo {

	private QuestionComment questionComments;

	private AnswerComment answerComment;

	/**
	 * @return the questionComments
	 */
	public QuestionComment getQuestionComments() {
		return questionComments;
	}

	/**
	 * @param questionComments
	 *            the questionComments to set
	 */
	public void setQuestionComments(QuestionComment questionComments) {
		this.questionComments = questionComments;
	}

	public AnswerComment getAnswerComment() {
		return answerComment;
	}

	public void setAnswerComment(AnswerComment answerComment) {
		this.answerComment = answerComment;
	}


}
