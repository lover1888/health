/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月8日 下午2:41:08
 * @version V1.0  
 */
package org.health.web;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.health.common.page.Pagination;
import org.health.model.Answer;
import org.health.model.Question;
import org.health.model.QuestionComment;
import org.health.service.QuestionService;
import org.health.service.StrategyService;
import org.health.util.KbbConstants;
import org.health.util.KbbUtils;
import org.health.vo.AnswerVo;
import org.health.vo.CommentVo;
import org.health.vo.QuestionDetailVo;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.UploadAdaptor;
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
	@Ok("jsp:jsp.question.questions2")
	public void doGetQuestions(String type, int page, String msg, HttpServletRequest req) {
		if (Strings.isEmpty(type)) {
			type = "newest";
		}
		if(!Lang.isEmpty(msg)){
			log.error(msg);
			req.setAttribute("msg", KbbUtils.unescape(msg));	
		}
		
		req.setAttribute("type", type);
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
	
	@AdaptBy(type = UploadAdaptor.class, args = { "${app.root}/WEB-INF/tmp" })
	public void uploadFile(@Param("id") int id, @Param("file") File f){
		
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
		try {
			this.questionService.saveQuestion(question);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 问题详情
	@At("/q/?")
	@Ok("jsp:jsp.question.detail2")
	public void doGetQuestionDetail(String id, HttpServletRequest req) {
		String userId = KbbUtils.getCurrentUserId();
		QuestionDetailVo vo = this.questionService.getQuestionDetail(id, userId);
		req.setAttribute("detailVo", vo);

		Pagination<AnswerVo> ansPg = this.questionService.getAnswersByQuestion(
				id, 1, pageSize);
		req.setAttribute("ansPg", ansPg);
		
		req.setAttribute("currUserId", userId);
	}

	// 回答问题
	@At("/q/answer")
	@Fail("jsp:jsp.error")
	public View doAnswerQuestion(@Param("..") Answer answer, HttpServletRequest req) {
		String userId = KbbUtils.getSession(KbbConstants.SESSION_USER_ID);
		answer.setUserId(userId);
		try {
			this.questionService.answerQuestion(answer);
		} catch (Exception e) {
			req.setAttribute("errorMsg", e.getMessage());
			throw Lang.makeThrow(e.getMessage(), new Object[0]);
		}
		return new ViewWrapper(new ServerRedirectView("/q/"
				+ answer.getQuestionId()), null);
	}
	
	@At("/q/?/comment/list")
	@Ok("json")
	public List<CommentVo> doGetQuestionComments(String questionId){
		return this.questionService.getComments(questionId);
	}
	
	@At("/q/comment")
	@Fail("json")
	public View doCommentQuestion(@Param("..") QuestionComment comment) {
		// 检测是否有权限
		try {
			Subject subject = SecurityUtils.getSubject();
			subject.checkPermission("question:comment");	
		} catch (AuthorizationException e) {
			throw Lang.makeThrow("您没有该权限", new Object[0]);
		}
		comment.setUserId(KbbUtils.getCurrentUserId());
		this.questionService.saveQuestionComments(comment);
		
		return new ViewWrapper(new ServerRedirectView("/q/"
				+ comment.getQuestionId()), null);
	}
	
	// 问题投票（赞成，反对）用Ajax调用
	@At("/q/?/vote/?")
	@Ok("json")
	public String doVoteQuestion(String id, String type, HttpServletRequest req){
		String msg = null;
		// 检测投票是否有权限
		try {
			Subject subject = SecurityUtils.getSubject();
			if(KbbConstants.ActType_Add.equals(type)){
				subject.checkPermission("question:vote:add");	
			} else if(KbbConstants.ActType_Reduce.equals(type)){
				subject.checkPermission("question:vote:reduce");
			}
		} catch (AuthorizationException e) {
			msg = "您的声望值不够。";
			return msg;
		}
		
		try {
			this.questionService.voteQuestion(id, type, KbbUtils.getCurrentUserId());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			msg = e.getMessage();
			return msg;
		}
		return "投票成功";
	}
	
	@At("/q/?/focus/?")
	@Ok("json")
	public String doFocusQuestion(String questionId, String type){
		String rtnMsg = null;
		// 是否已经关注
		// 关注
		try {
			Subject subject = SecurityUtils.getSubject();
			subject.checkPermission("question:focus");
		} catch (AuthorizationException e) {
			rtnMsg = "您没有该权限";
			return rtnMsg;
		}
		try {
			this.questionService.focusQuestion(KbbUtils.getCurrentUserId(), questionId, type);
		} catch (Exception e) {
			rtnMsg = e.getMessage();
			log.error(e.getMessage(), e);
			return rtnMsg;
		}
		return "操作成功";
	}
	

}
