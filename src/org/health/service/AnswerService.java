/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月8日 上午10:59:05
 * @version V1.0  
 */
package org.health.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.health.model.Answers;
import org.health.model.AnswersComments;
import org.health.model.Question;
import org.health.model.Reputation;
import org.health.model.User;
import org.health.model.UserVote;
import org.health.util.KbbConstants;
import org.health.util.KbbUtils;
import org.health.util.ServiceUtils;
import org.health.vo.CommentsVo;
import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.impl.sql.callback.EntityCallback;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlContext;
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
	
	
	@Aop(TransAop.READ_COMMITTED)
	public int saveAnswerComments(AnswersComments comment){
		// 插入
		AnswersComments cmnt = dao().insert(comment);
		// 更新问题评论数
		dao().update(Answers.class, Chain.makeSpecial("commentCount", "+1"), Cnd.where("answersId", "=", comment.getAnswersId()));
		
		return cmnt==null?0:1;
	}
	
	/**
	 * 获取指定问题评论信息
	 * @Description TODO
	 * @param sourceId
	 * @param sourceType
	 * @return
	 */
	public List<CommentsVo> getComments(String answerId) {
		EntityCallback callback = new EntityCallback(){
			@Override
			protected List<CommentsVo> process(ResultSet rs, Entity<?> entity,
					SqlContext context) throws SQLException {
				List<CommentsVo> vos = new ArrayList<CommentsVo>();
				CommentsVo vo;
				while(rs.next()) {
					vo = new CommentsVo();
					vo.setAnswersComments((AnswersComments)entity.getObject(rs, context.getFieldMatcher(), null));
					vo.setUserName(rs.getString("userName"));
					vo.setReputation(rs.getInt("reputationCount"));
					vo.setImgUrl(rs.getString("imageUrl"));
					vos.add(vo);
				}
		        return vos;
			}
		};
		
		Sql sql = Sqls.create("SELECT u.userName, u.reputationCount, u.imageUrl, qc.* from tb_answers_comments qc, tb_user u where qc.answersId=@q1 and qc.userId=u.userId;");
		sql.params().set("q1", answerId);
		sql.setEntity(dao().getEntity(AnswersComments.class));
		sql.setCallback(callback);
		dao().execute(sql);
		return sql.getList(CommentsVo.class);
	}
	
	// 采纳答案
	@Aop(TransAop.READ_COMMITTED)
	public void adoptAnswer(String answerId, String userId, String act) throws Exception{
		// 先找出这个答案
		Answers ans = dao().fetchLinks(dao().fetch(Answers.class, Cnd.where("answersId", "=", answerId)),"question");
		if(Lang.isEmpty(ans)){
			throw Lang.makeThrow("操作失败，没有这个答案", new Object[0]);
		}
		// 判断操作人是不是该问题的发起人
		if(!ans.getQuestion().getUserId().equals(userId)){
			throw Lang.makeThrow("操作失败，采纳答案人不是该问题的发起人", new Object[0]);
		}
		
		if(KbbConstants.ActType_Add.equals(act)){ // 采纳答案
			// 该问题是不是已经被采纳过
			if(KbbConstants.Status.QuestionResolved.getValue()==ans.getQuestion().getQuestionStatus()){
				throw Lang.makeThrow("操作失败，该问题已解决", new Object[0]);
			}
			
			// 更新答案状态
			ans.setIsAdoption(true);
			dao().update(ans);
			// 更新问题状态
			Question q = ans.getQuestion();
			q.setQuestionStatus(KbbConstants.Status.QuestionResolved.getValue());
			dao().update(q);
			
			// 自己采纳自己的答案不增加声望
			if(!ans.getUserId().equals(userId)){
				// 声望
				Map<String, Integer> maps =  ServiceUtils.getReputationStrategy(dao());
				//采纳者声望记录
				Reputation repu = new Reputation();
				repu.setId(KbbUtils.generateID());
				repu.setReputationType(KbbConstants.Stragety_AnswerAdopt);
				repu.setSourceId(q.getQuestionId());
				repu.setSourceTitle(q.getTitle());
				repu.setSourceType(KbbConstants.SourceType.QUESTION.getValue());
				repu.setUserId(q.getUserId());
				repu.setValue(maps.get(KbbConstants.Stragety_AnswerAdopt));
				repu.setAnswersId(ans.getAnswersId());
				dao().insert(repu);
				// 采纳者声望值
				User u1 = dao().fetch(User.class, Cnd.where("userId", "=", q.getUserId()));
				u1.setReputationCount(u1.getReputationCount()+maps.get(KbbConstants.Stragety_AnswerAdopt));
				dao().update(u1);
				
				// 被采纳者（采纳答案的回答人）
				Reputation repu2 = new Reputation();
				repu2.setId(KbbUtils.generateID());
				repu2.setReputationType(KbbConstants.Stragety_AnswerBeAdopt);
				repu2.setSourceId(ans.getQuestionId());
				repu2.setSourceTitle(ans.getQuestion().getTitle());
				repu2.setSourceType(KbbConstants.SourceType.QUESTION.getValue());
				repu2.setUserId(ans.getUserId());
				repu2.setValue(maps.get(KbbConstants.Stragety_AnswerBeAdopt));
				repu2.setAnswersId(ans.getAnswersId());
				dao().insert(repu2);
				User u2 = dao().fetch(User.class, Cnd.where("userId", "=", ans.getUserId()));
				u2.setReputationCount(u2.getReputationCount()+maps.get(KbbConstants.Stragety_AnswerBeAdopt));
				dao().update(u2);
				
				// 给被采纳答案者更新标签积分
				ServiceUtils.updateUserTagsValue(u2.getUserId(), q.getTags(), maps.get(KbbConstants.Stragety_AnswerBeAdopt), dao());
			}
			
			
		} else if(KbbConstants.ActType_Reduce.equals(act)){ // 取消采纳
			// 该问题是不是已经被采纳过
			if(KbbConstants.Status.QuestionResolved.getValue()!=ans.getQuestion().getQuestionStatus()){
				throw Lang.makeThrow("操作失败，该问题还没有采纳答案", new Object[0]);
			}
			// 更新答案状态
			ans.setIsAdoption(false);
			dao().update(ans);
			// 更新问题状态
			Question q = ans.getQuestion();
			q.setQuestionStatus(KbbConstants.Status.QuestionOpen.getValue());
			dao().update(q);
			
			if(!ans.getUserId().equals(userId)){
				// 声望
				Map<String, Integer> maps =  ServiceUtils.getReputationStrategy(dao());
				
				//删除采纳者声望记录
				Sql sql = Sqls.create("delete from tb_reputation where reputationType=@rt and sourceId=@sid and userId=@uid and sourceType=@st");
				sql.params().set("rt", KbbConstants.Stragety_AnswerAdopt);
				sql.params().set("sid", q.getQuestionId());
				sql.params().set("uid",q.getUserId());
				sql.params().set("st", KbbConstants.SourceType.QUESTION.getValue());
				dao().execute(sql);
				// 更新采纳者声望值
				User u1 = dao().fetch(User.class, Cnd.where("userId", "=", q.getUserId()));
				u1.setReputationCount(u1.getReputationCount()-maps.get(KbbConstants.Stragety_AnswerAdopt));
				dao().update(u1);
				
				// 被采纳者（采纳答案的回答人）
				sql = Sqls.create("delete from tb_reputation where reputationType=@rt and sourceId=@sid and userId=@uid and sourceType=@st");
				sql.params().set("rt", KbbConstants.Stragety_AnswerBeAdopt);
				sql.params().set("sid", ans.getQuestionId());
				sql.params().set("uid",ans.getUserId());
				sql.params().set("st", KbbConstants.SourceType.QUESTION.getValue());
				dao().execute(sql);
				User u2 = dao().fetch(User.class, Cnd.where("userId", "=", ans.getUserId()));
				u2.setReputationCount(u2.getReputationCount()-maps.get(KbbConstants.Stragety_AnswerBeAdopt));
				dao().update(u2);
				
				// 给被采纳答案者更新标签积分
				ServiceUtils.updateUserTagsValue(u2.getUserId(), q.getTags(), -maps.get(KbbConstants.Stragety_AnswerBeAdopt), dao());
			}
			
		} 
		
	}
	
	/**
	 * 对回答进行投票
	 * 
	 * @Description TODO
	 * @param answerId 
	 * @param voteType 投票类型
	 * @param userId 投票人
	 */
	@Aop(TransAop.READ_COMMITTED)
	public void voteAnswer(String answerId, String voteType, String userId) throws Exception{
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
		Map<String, Integer> maps =  ServiceUtils.getReputationStrategy(dao());
		if(KbbConstants.ActType_Add.equals(voteType)){
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
			u1.setPraiseCount(u1.getPraiseCount()+1);
			dao().update(u1);
			
			// 插入用户投票记录
			UserVote uv = new UserVote();
			uv.setId(KbbUtils.generateID());
			uv.setSourceId(a.getAnswersId());
			uv.setSourceType(KbbConstants.SourceType.ANSWERS.getValue());
			uv.setUserId(userId);
			uv.setVoteType(KbbConstants.ActType_Add);
			dao().insert(uv);
			
			// 答案投票数增加
			a.setVoteAddCount(a.getVoteAddCount()+1);
			dao().update(a);
			
			// 更新标签积分
			ServiceUtils.updateUserTagsValue(u1.getUserId(), a.getQuestion().getTags(), maps.get(KbbConstants.Stragety_AnswerBeVoteAdd), dao());
			
		} else if(KbbConstants.ActType_Reduce.equals(voteType)){// 反对票
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
			// 更新标签积分
			ServiceUtils.updateUserTagsValue(u1.getUserId(), a.getQuestion().getTags(), maps.get(KbbConstants.Stragety_AnswerBeVoteReduce), dao());
			
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
			uv.setVoteType(KbbConstants.ActType_Reduce);
			dao().insert(uv);
			
			// 答案投票数减少
			a.setVoteReduceCount(a.getVoteReduceCount()+1);
			dao().update(a);
		} else {
			throw Lang.makeThrow("未知的投票类型", new Object[0]);
		}
	}
	
}
