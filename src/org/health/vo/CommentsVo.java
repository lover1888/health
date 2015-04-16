/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月16日 下午1:11:33
 * @version V1.0  
 */
package org.health.vo;

import org.health.model.AnswersComments;
import org.health.model.QuestionComments;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月16日 下午1:11:33
 */
public class CommentsVo extends BaseVo {

	private QuestionComments questionComments;

	private AnswersComments answersComments;

	/**
	 * @return the questionComments
	 */
	public QuestionComments getQuestionComments() {
		return questionComments;
	}

	/**
	 * @param questionComments
	 *            the questionComments to set
	 */
	public void setQuestionComments(QuestionComments questionComments) {
		this.questionComments = questionComments;
	}

	/**
	 * @return the answersComments
	 */
	public AnswersComments getAnswersComments() {
		return answersComments;
	}

	/**
	 * @param answersComments
	 *            the answersComments to set
	 */
	public void setAnswersComments(AnswersComments answersComments) {
		this.answersComments = answersComments;
	}

}
