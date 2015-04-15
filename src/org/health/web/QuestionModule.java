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
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.health.common.page.Pagination;
import org.health.model.Answers;
import org.health.model.Question;
import org.health.service.QuestionService;
import org.health.service.StrategyService;
import org.health.util.KbbConstants;
import org.health.util.KbbUtils;
import org.health.vo.AnswerVo;
import org.health.vo.QuestionDetailVo;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.ServerRedirectView;
import org.nutz.mvc.view.ViewWrapper;

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
	
	@Inject
	private StrategyService strategyService;

	// 问题列表
	@At("/question/*")
	@Ok("jsp:jsp.question.questions")
	public void doGetQuestions(String type, int page, HttpServletRequest req) {
		if (Strings.isEmpty(type)) {
			type = "newest";
		}
		if (page <= 0) {
			page = 1;
		}
		if ("newest".equals(type)) {
			Pagination<Question> pgs = this.questionService.getQuestionByNews(
					page, pageSize);
			req.setAttribute("pagination", pgs);
		} else if ("hottest".equals(type)) {
			Pagination<Question> pgs = this.questionService.getQuestionByHot(
					page, pageSize);
			req.setAttribute("pagination", pgs);
		} else if ("unanswered".equals(type)) {
			Pagination<Question> pgs = this.questionService
					.getQuestionByUnanswered(page, pageSize);
			req.setAttribute("pagination", pgs);
		}
	}

	
	@At("/question/ask")
	@Ok("jsp:jsp.question.ask")
	public void doAskQuestion() {
	}

	// 提交问题
	@At("/question/askAct")
	@Ok("redirect:/question")
	public void doAskQuestionAct(@Param("..") Question question) {
		question.setQuestionId(KbbUtils.generateID());
		String userId = KbbUtils.getSession(KbbConstants.SESSION_USER_ID);
		question.setUserId(userId);
		this.questionService.saveQuestion(question);
	}

	// 问题详情
	@At("/q/?")
	@Ok("jsp:jsp.question.detail")
	public void doGetQuestionDetail(String id, HttpServletRequest req) {
		QuestionDetailVo vo = this.questionService.getQuestionDetail(id, null);
		req.setAttribute("detailVo", vo);

		Pagination<AnswerVo> ansPg = this.questionService.getAnswersByQuestion(
				id, 1, pageSize);
		req.setAttribute("ansPg", ansPg);
	}

	// 回答问题
	@At("/q/answer")
	public View doQuestionAnswer(@Param("..") Answers answer) {
		String userId = KbbUtils.getSession(KbbConstants.SESSION_USER_ID);
		answer.setUserId(userId);
		this.questionService.answerQuestion(answer);
		
		return new ViewWrapper(new ServerRedirectView("/q/"
				+ answer.getQuestionId()), null);
	}
	
	// 问题投票（赞成，反对）用Ajax调用
	@At("/q/?/vote/?")
	@Ok("json")
	public String doVoteQuestion(String id, String type, HttpServletRequest req){
		String msg = null;
		// 检测投票是否有权限
		try {
			Subject subject = SecurityUtils.getSubject();
			if(KbbConstants.VoteType_Add.equals(type)){
				subject.checkPermission("question:vote:add");	
			} else if(KbbConstants.VoteType_Reduce.equals(type)){
				subject.checkPermission("question:vote:reduce");
			}
		} catch (AuthorizationException e) {
			msg = "您的声望值不够。";
			return msg;
		}
		
		try {
			this.questionService.voteQuestion(id, type, KbbUtils.getCurrentUserId());
			msg = "投票成功";
		} catch (Exception e) {
			msg = e.getMessage();
		}
		return msg;
	}
	
	@At("/q/?/focus")
	@Ok("json")
	public String doFocusQuestion(String questionId){
		String rtnMsg = null;
		
		
		return rtnMsg;
	}
	

}
