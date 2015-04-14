/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月8日 上午10:59:05
 * @version V1.0  
 */
package org.health.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.health.model.Answers;
import org.health.model.Reputation;
import org.health.model.ReputationStrategy;
import org.health.model.User;
import org.health.model.UserVote;
import org.health.util.KbbConstants;
import org.health.util.KbbUtils;
import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.dao.Cnd;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.service.EntityService;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月8日 上午10:59:05
 */
@IocBean(fields={"dao"})
public class AnswerService extends EntityService<Answers> {
	/**
	 * 对回答进行投票
	 * 
	 * @Description TODO
	 * @param answerId 
	 * @param voteType 投票类型
	 * @param userId 投票人
	 */
	@Aop(TransAop.READ_COMMITTED)
	public void voteAnswer(String answerId, String voteType, String userId){
		Answers a = dao().fetchLinks(dao().fetch(Answers.class, Cnd.where("answersId", "=", answerId)),"question");
		// 不能对自己的回答投票
		if(a.getUserId().equals(userId)){
			throw Lang.makeThrow("不能对自己的回答投票", new Object[0]);
		}
		// 检测是否已经投过票
		UserVote uvote = dao().fetch(UserVote.class, Cnd.where("userId", "=", userId).and("sourceId","=",answerId).and("sourceType", "=", KbbConstants.SourceType.ANSWERS.getValue()));
		if(uvote!=null) {
			throw Lang.makeThrow("您已经对答案投过票", new Object[0]);
		}
		// 获取声望策略
		Map<String, Integer> maps = getReputationStrategy();
		if(KbbConstants.VoteType_Add.equals(voteType)){
			// 赞同票
			Reputation repu = new Reputation();
			repu.setId(KbbUtils.generateID());
			repu.setReputationType(KbbConstants.Stragety_AnswerBeVoteAdd);
			repu.setSourceId(a.getQuestionId());
			repu.setSourceTitle(a.getQuestion().getTitle());
			repu.setSourceType(KbbConstants.SourceType.QUESTION.getValue());
			repu.setUserId(a.getUserId());
			repu.setValue(maps.get(KbbConstants.Stragety_AnswerBeVoteAdd));
			repu.setAnswersId(a.getAnswersId());
			dao().insert(repu);
			
			// 更新被赞同答案的用户声望数
			User u1 = dao().fetch(User.class, Cnd.where("userId", "=", a.getUserId()));
			u1.setReputationCount(u1.getReputationCount()+maps.get(KbbConstants.Stragety_AnswerBeVoteAdd));
			dao().update(u1);
			
			// 插入用户投票记录
			UserVote uv = new UserVote();
			uv.setId(KbbUtils.generateID());
			uv.setSourceId(a.getAnswersId());
			uv.setSourceType(KbbConstants.SourceType.ANSWERS.getValue());
			uv.setUserId(userId);
			uv.setVoteType(KbbConstants.VoteType_Add);
			dao().insert(uv);
			
			// 答案投票数增加
			a.setVoteCount(a.getVoteCount()+1);
			dao().update(a);
			
		} else if(KbbConstants.VoteType_Reduce.equals(voteType)){// 反对票
			// 被反对
			Reputation repu = new Reputation();
			repu.setId(KbbUtils.generateID());
			repu.setReputationType(KbbConstants.Stragety_AnswerBeVoteReduce);
			repu.setSourceId(a.getQuestionId());
			repu.setSourceTitle(a.getQuestion().getTitle());
			repu.setSourceType(KbbConstants.SourceType.QUESTION.getValue());
			repu.setUserId(a.getUserId());
			repu.setValue(maps.get(KbbConstants.Stragety_AnswerBeVoteReduce));
			repu.setAnswersId(a.getAnswersId());
			//更新被反对答案用户的声望数
			User u1 = dao().fetch(User.class, Cnd.where("userId", "=", a.getUserId()));
			u1.setReputationCount(u1.getReputationCount()+maps.get(KbbConstants.Stragety_AnswerBeVoteReduce));
			
			// 反对者
			Reputation repu2 = new Reputation();
			repu2.setId(KbbUtils.generateID());
			repu2.setReputationType(KbbConstants.Stragety_AnswerVoteReduce);
			repu2.setSourceId(a.getQuestionId());
			repu2.setSourceTitle(a.getQuestion().getTitle());
			repu2.setSourceType(KbbConstants.SourceType.QUESTION.getValue());
			repu2.setUserId(userId);
			repu2.setValue(maps.get(KbbConstants.Stragety_AnswerVoteReduce));
			repu2.setAnswersId(a.getAnswersId());
			
			// 更新反对者用户声望
			User u2 = dao().fetch(User.class, Cnd.where("userId", "=", userId));
			u2.setReputationCount(u2.getReputationCount()+maps.get(KbbConstants.Stragety_AnswerVoteReduce));
			
			dao().insert(Arrays.asList(repu,repu2));
			dao().update(Arrays.asList(u1,u2));
			
			// 插入用户投票记录
			UserVote uv = new UserVote();
			uv.setId(KbbUtils.generateID());
			uv.setSourceId(a.getAnswersId());
			uv.setSourceType(KbbConstants.SourceType.ANSWERS.getValue());
			uv.setUserId(userId);
			uv.setVoteType(KbbConstants.VoteType_Reduce);
			dao().insert(uv);
			
			// 答案投票数减少
			a.setVoteCount(a.getVoteCount()-1);
			dao().update(a);
		} else {
			throw Lang.makeThrow("未知的投票类型", new Object[0]);
		}
	}
	
	// 获取声望策略
	public Map<String, Integer> getReputationStrategy(){
		List<ReputationStrategy> strategies = dao().query(ReputationStrategy.class, null);
		Map<String, Integer> maps = new HashMap<String, Integer>();
		for(ReputationStrategy stra:strategies) {
			maps.put(stra.getStrategyName(), stra.getRelatedUserValue());
		}
		return maps;
	}
}
