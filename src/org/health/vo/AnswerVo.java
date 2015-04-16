/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月2日 下午5:29:41
 * @version V1.0  
 */
package org.health.vo;

import java.util.List;

import org.health.model.Answers;
import org.health.model.QuestionComments;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月2日 下午5:29:41
 */
public class AnswerVo extends BaseVo {

	private Answers answers;

	private List<QuestionComments> comments;

	/**
	 * @return the answers
	 */
	public Answers getAnswers() {
		return answers;
	}

	/**
	 * @param answers
	 *            the answers to set
	 */
	public void setAnswers(Answers answers) {
		this.answers = answers;
	}

	/**
	 * @return the comments
	 */
	public List<QuestionComments> getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(List<QuestionComments> comments) {
		this.comments = comments;
	}

}
