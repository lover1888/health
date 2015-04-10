/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月8日 下午2:41:08
 * @version V1.0  
 */
package org.health.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.health.common.page.Pagination;
import org.health.model.Question;
import org.health.service.QuestionService;
import org.health.util.KbbConstants;
import org.health.util.KbbUtils;
import org.health.vo.AnswerVo;
import org.health.vo.QuestionDetailVo;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

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
	public void doGetQuestions(String type, int page, HttpServletRequest req) {
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
	public void doGetQuestionDetail(String id, HttpServletRequest req) {
		QuestionDetailVo vo =  this.questionService.getQuestionDetail(id, null);
		req.setAttribute("detailVo", vo);
		
		Pagination<AnswerVo> ansPg = this.questionService.getAnswersByQuestion(id, 1, pageSize);
		req.setAttribute("ansPg", ansPg);
	}
	
	@At("/question/ask")
	@Ok("jsp:jsp.question.ask")
	public void doAskQuestion(){
		
	}
	
	@At("/question/askAct")
	@Ok("redirect:/question")
	public void doAskQuestionAct(@Param("..") Question question){
		question.setQuestionId(KbbUtils.generateID());
		Subject subject = SecurityUtils.getSubject();
		String userId = (String)subject.getSession().getAttribute(KbbConstants.SESSION_USER_ID);
		question.setUserId(userId);
		this.questionService.saveQuestion(question);
	}
	
}
