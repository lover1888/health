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
import org.health.model.Comments;
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
	
	private List<Comments> comments;
	
	private List<Answers> answers;
	
}
