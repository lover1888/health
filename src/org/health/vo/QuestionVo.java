/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月2日 下午5:28:23
 * @version V1.0  
 */
package org.health.vo;

import org.health.model.Question;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月2日 下午5:28:23
 */
public class QuestionVo extends BaseVo {
	
	private Question question;

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
	
}
