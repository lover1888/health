/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月8日 下午2:41:08
 * @version V1.0  
 */
package org.health.web;

import javax.servlet.http.HttpServletRequest;

import org.health.common.page.Pagination;
import org.health.model.Question;
import org.health.service.QuestionService;
import org.health.vo.AnswerVo;
import org.health.vo.QuestionDetailVo;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月8日 下午2:41:08
 */
@IocBean
public class QuestionModule {
	Log log = Logs.getLog(MainModule.class);
	final int pageSize = 20;
	
	@Inject
	private QuestionService questionService;
	
	@At("/question/*")
	@Ok("jsp:jsp.question.questions")
	public void doQuestions(String type, int page, HttpServletRequest req) {
		if(Strings.isEmpty(type)){
			type = "newest";
		}
		if(page<=0){
			page = 1;
		}
		if("newest".equals(type)) {
			Pagination<Question> pgs = this.questionService.getQuestionByNews(page, pageSize);
			req.setAttribute("pagination", pgs);
		}else if("hottest".equals(type)) {
			Pagination<Question> pgs = this.questionService.getQuestionByHot(page, pageSize);
			req.setAttribute("pagination", pgs);
		}else if("unanswered".equals(type)) {
			Pagination<Question> pgs = this.questionService.getQuestionByUnanswered(page, pageSize);
			req.setAttribute("pagination", pgs);
		}
	}
	
	@At("/q/?")
	@Ok("jsp:jsp.question.detail")
	public void doQuestionDetail(String id, HttpServletRequest req) {
		QuestionDetailVo vo =  this.questionService.getQuestionDetail(id, null);
		req.setAttribute("detailVo", vo);
		
		Pagination<AnswerVo> ansPg = this.questionService.getAnswersByQuestion(id, 1, pageSize);
		req.setAttribute("ansPg", ansPg);
	}
	
	
	

}
