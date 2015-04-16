/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月14日 下午5:04:00
 * @version V1.0  
 */
package org.health.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.health.model.AnswersComments;
import org.health.service.AnswerService;
import org.health.service.StrategyService;
import org.health.util.KbbConstants;
import org.health.util.KbbUtils;
import org.health.vo.CommentsVo;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.ServerRedirectView;
import org.nutz.mvc.view.ViewWrapper;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月14日 下午5:04:00
 */
@IocBean
public class AnswerModule {
	Log log = Logs.getLog(MainModule.class);
	final int pageSize = 20;

	@Inject
	private AnswerService answerService;
	
	@Inject
	private StrategyService strategyService;
	
	@At("/answer/?/comment/list")
	@Ok("json")
	public List<CommentsVo> doGetAnswersComments(String answersId){
		return this.answerService.getComments(answersId);
	}
	@At("/answer/comment")
	@Ok("json")
	public List<CommentsVo> doCommentAnswer(@Param("..") AnswersComments comment) {
		// 检测是否有权限
		try {
			Subject subject = SecurityUtils.getSubject();
			subject.checkPermission("answer:comment");	
		} catch (AuthorizationException e) {
			throw Lang.makeThrow("您没有该权限", new Object[0]);
		}
		comment.setUserId(KbbUtils.getCurrentUserId());
		this.answerService.saveAnswerComments(comment);
		return this.answerService.getComments(comment.getAnswersId());
//		return new ViewWrapper(new ServerRedirectView("/q/"
//				+ comment.getAnswersId()), null);
	}
	// 问题投票（赞成，反对）用Ajax调用
	@At("/answer/?/vote/?")
	@Ok("json")
	public String doVoteAnswer(String id, String type, HttpServletRequest req){
		String msg = null;
		// 检测投票是否有权限
		try {
			Subject subject = SecurityUtils.getSubject();
			if(KbbConstants.ActType_Add.equals(type)){
				subject.checkPermission("answer:vote:add");	
			} else if(KbbConstants.ActType_Reduce.equals(type)){
				subject.checkPermission("answer:vote:reduce");
			}
		} catch (AuthorizationException e) {
			msg = "您的声望值不够。";
			return msg;
		}
		
		try {
			this.answerService.voteAnswer(id, type, KbbUtils.getCurrentUserId());
			msg = "投票成功";
		} catch (Exception e) {
			msg = e.getMessage();
		}
		return msg;
	}
	
	@At("/answer/?/adopt/?")
	@Ok("json")
	public String doAdoptAnswer(String answerId, String type) {
		try {
			Subject subject = SecurityUtils.getSubject();
			subject.checkPermission("answer:adopt");	
		} catch (AuthorizationException e) {
			return "您的声望值不够。";
		}
		
		try {
			this.answerService.adoptAnswer(answerId, KbbUtils.getCurrentUserId(), type);
		} catch (Exception e) {
			return e.getMessage();
		}
		
		return "操作成功";
	}
}
