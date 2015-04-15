/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月1日 下午2:27:54
 * @version V1.0  
 */
package org.health.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.health.common.page.Pagination;
import org.health.model.Answers;
import org.health.model.Comments;
import org.health.model.Question;
import org.health.model.Reputation;
import org.health.model.ReputationStrategy;
import org.health.model.Tags;
import org.health.model.User;
import org.health.model.UserTags;
import org.health.model.UserVote;
import org.health.util.KbbConstants;
import org.health.util.KbbUtils;
import org.health.vo.AnswerVo;
import org.health.vo.QuestionDetailVo;
import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.impl.sql.callback.EntityCallback;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlContext;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.service.EntityService;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月1日 下午2:27:54
 */
@IocBean(fields = { "dao" })
public class QuestionService extends EntityService<Question> {
	
	public List<Question> list(){
		return query(null, null);
	}
	
	/**
	 * 获取最新的问题列表
	 * @Description TODO
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Pagination<Question> getQuestionByNews(int pageNum, int pageSize) {
		Pager pg = new Pager().setPageNumber(pageNum).setPageSize(pageSize);
		return new Pagination<>(pageNum, pageSize, super.count(), super.query(Cnd.orderBy().desc("createDate"), pg));
	}
	
	/**
	 * 获取最热的问题列表
	 * @Description TODO
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Pagination<Question> getQuestionByHot(int pageNum, int pageSize) {
		Pager pg = new Pager().setPageNumber(pageNum).setPageSize(pageSize);
		List<Question> list = query(Cnd.where("answersCount", ">", 0).or("voteCount",">",0), pg);
		return new Pagination<Question>(pageNum, pageSize, super.count(), list);
	}
	
	/**
	 * 获取未回答的问题列表
	 * @Description TODO
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Pagination<Question> getQuestionByUnanswered(int pageNum, int pageSize) {
		Pager pg = new Pager().setPageNumber(pageNum).setPageSize(pageSize);
		List<Question> list = query(Cnd.where("answersCount", "<=", 0).desc("createDate"), pg);
		return new Pagination<Question>(pageNum, pageSize, super.count(), list);
	}
	
	/**
	 * 获取问题详细信息
	 * @Description TODO
	 * @param questionId
	 * @return
	 */
	public QuestionDetailVo getQuestionDetail(String questionId, String userId) {
		EntityCallback callback = new EntityCallback(){
			@Override
			protected QuestionDetailVo process(ResultSet rs, Entity<?> entity,
					SqlContext context) throws SQLException {
				
				QuestionDetailVo rlt = new QuestionDetailVo();
				if (null != rs && rs.next()) {
		            rlt.setQuestion((Question)entity.getObject(rs, context.getFieldMatcher(), null));
				}
				rlt.setUserName(rs.getString("userName"));
				rlt.setReputation(rs.getInt("reputationCount"));
				rlt.setImgUrl(rs.getString("imageUrl"));
//				try {
//					rlt.setFocus(rs.getString("isFocus")==null?false:true);
//					rlt.setFavorite(rs.getString("isFav")==null?false:true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
		        return rlt;
			}
		};
		
		Sql sql;
		if(Strings.isEmpty(userId)){
			sql = Sqls.create("select q.*, u.userName, u.reputationCount, u.imageUrl from tb_question q, tb_user u where q.questionId=@q1 and q.userId=u.userId");
			sql.setEntity(dao().getEntity(Question.class));
			sql.params().set("q1", questionId);
		} else {
			// 查询指定问题的详细信息，提问人的详细信息，当前用户是否收藏了该问题，是否关注了该问题
			sql = Sqls.create("select q.*, u.userName, u.reputationCount, u.imageUrl, (select id from tb_user_focus uf where uf.sourceId=@q1  and uf.sourceType=@s1 and uf.userId=@u1) as isFocus, (select tuf.id from tb_user_favorites ufa, tb_user_fav tuf where tuf.sourceId=@s2 and ufa.userId=@u2 and tuf.sourceType=@s2 and tuf.favoritesId=ufa.id) as isFav from tb_question q, tb_user u where q.questionId=@q3 and q.userId=u.userId");
			sql.setEntity(dao().getEntity(Question.class));
			sql.params().set("q1", questionId);
			sql.params().set("s1", KbbConstants.SourceType.QUESTION.getValue());
			sql.params().set("u1", userId);
			sql.params().set("q2", questionId);
			sql.params().set("s2", KbbConstants.SourceType.QUESTION.getValue());
			sql.params().set("u2", userId);
			sql.params().set("q3", questionId);
		}
		sql.setCallback(callback);
		dao().execute(sql);
		
		// 问题浏览数增加
		// TODO
		
		return sql.getObject(QuestionDetailVo.class);
	}
	
	public Pagination<AnswerVo> getAnswersByQuestion(String questionId, int pageNum, int pageSize) {
		EntityCallback callback = new EntityCallback(){
			@Override
			protected List<AnswerVo> process(ResultSet rs, Entity<?> entity,
					SqlContext context) throws SQLException {
				List<AnswerVo> vos = new ArrayList<AnswerVo>();
				AnswerVo vo;
				while(rs.next()) {
					vo = new AnswerVo();
					vo.setAnswers((Answers)entity.getObject(rs, context.getFieldMatcher(), null));
					vo.setUserName(rs.getString("userName"));
					vo.setReputation(rs.getInt("reputationCount"));
					vo.setImgUrl(rs.getString("imageUrl"));
					vos.add(vo);
				}
		        return vos;
			}
		};
		
		Sql sql = Sqls.create("select a.*,u.userName, u.reputationCount, u.imageUrl from tb_answers a, tb_user u where a.questionId=@q1 and a.userId=u.userId");
		sql.params().set("q1", questionId);
		sql.setEntity(dao().getEntity(Answers.class));
		sql.setCallback(callback);
		dao().execute(sql);
		return new Pagination<AnswerVo>(pageNum, pageSize, 0, sql.getList(AnswerVo.class));
	}
	
	/**
	 * 获取评论信息
	 * @Description TODO
	 * @param sourceId
	 * @param sourceType
	 * @return
	 */
	public List<Comments> getComments(String sourceId, String sourceType) {
		return dao().query(Comments.class, Cnd.where("sourceId", "=", sourceId).and("sourceType","=",sourceType));
	}
	
	/**
	 * 提问题
	 * @Description TODO
	 * @param question
	 * @return
	 */
	@Aop(TransAop.READ_COMMITTED)
	public boolean saveQuestion(Question question) {
		// 保存问题
		question.setQuestionId(KbbUtils.generateID());
		Question q = dao().insert(question);
		// 更新标签
		String[] tags = question.getTags().split(",");
		StringBuilder sb = new StringBuilder();
		for(String tag:tags){
			sb.append("\"").append(tag).append("\"").append(",");
		}
		String tmp = sb.toString();
		tmp = tmp.substring(0, tmp.length()-1);
		List<Tags> list = dao().query(Tags.class, Cnd.where("tagName", "in", tmp));
		Set<String> tsets = new HashSet<String>();
		for(Tags tag:list){
			tsets.add(tag.getTagName());
		}
		list = new ArrayList<Tags>();
		for(String t:tags){
			if(!tsets.contains(t)){
				Tags tag = new Tags();
				tag.setTagName(t);
				list.add(tag);
			} 
		}
		User u = new User();
		u.setUserId(question.getUserId());
		u.setTags(list);
		// 添加新创建的标签以及用户关联标签表
		dao().insertLinks(u, "tags");
		
		// 查找所有的用户已关联标签
		u = dao().fetchLinks(u, "userTags");
		List<UserTags> uts = u.getUserTags();
		List<UserTags> unUts = new ArrayList<UserTags>();
		UserTags utmp;
		for(String tag:tags){
			boolean isFlag = false;
			for(UserTags ut:uts){
				if(ut.getTagName().equals(tag)){
					isFlag = true;
					break;
				}
			}
			if(!isFlag) {
				// 如果没有关联标签则需要关联
				utmp = new UserTags();
				utmp.setUserId(u.getUserId());
				utmp.setTagName(tag);
				unUts.add(utmp);
			}
		}
		u.setUserTags(unUts);
		dao().insertLinks(u, "userTags");
		
		// 插入用户关注表
		u.setFocusQuestions(Arrays.asList(q));
		dao().insertRelation(u, "focusQuestions");
		// 更新问题表中的关注数
		q.setFocusCount(q.getFocusCount()+1);
		dao().update(q);
		return q!=null;
	}
	
	/**
	 * 回答问题
	 */
	@Aop(TransAop.READ_COMMITTED)
	public boolean answerQuestion(Answers answers) {
		/*
			1插入数据到回答表
			2更新问题表中的回答数
			3插入回答问题声望记录\问题被回答声望记录
			如果是回答自己的问题不加分
			回答问题后会自动关注该问题
			为关注该问题的人增加通知消息
		 */
		//插入数据到回答表
		answers.setAnswersId(KbbUtils.generateID());
		Answers ans = dao().insert(answers);
		ans = dao().fetchLinks(ans, "question");
		
		//更新问题表中的回答数 
		Question question = ans.getQuestion();
		question.setAnswersCount(question.getAnswersCount()+1);
		dao().update(question);
		
		// 获取声望策略
		Map<String, Integer> maps = getReputationStrategy();
		
		// 声望不记录自问自答
		if(!ans.getQuestion().getUserId().equals(ans.getUserId())){
			List<Reputation> repus = new ArrayList<Reputation>();
			// 回答问题声望记录
			Reputation repu = new Reputation();
			repu.setId(KbbUtils.generateID());
			repu.setReputationType(KbbConstants.Stragety_AnswerQuestion);
			repu.setSourceId(ans.getQuestionId());
			repu.setSourceTitle(ans.getQuestion().getTitle());
			repu.setSourceType(KbbConstants.SourceType.QUESTION.getValue());
			repu.setUserId(ans.getUserId());
			repu.setValue(maps.get(KbbConstants.Stragety_AnswerQuestion));	
			repu.setAnswersId(ans.getAnswersId());
			repus.add(repu);
		
			// 问题被回答声望记录
			Reputation repu2 = new Reputation();
			repu2.setId(KbbUtils.generateID());
			repu2.setReputationType(KbbConstants.Stragety_QuestionBeAnswer);
			repu2.setSourceId(ans.getQuestionId());
			repu2.setSourceTitle(ans.getQuestion().getTitle());
			repu2.setSourceType(KbbConstants.SourceType.QUESTION.getValue());
			repu2.setUserId(ans.getQuestion().getUserId());
			repu2.setValue(maps.get(KbbConstants.Stragety_QuestionBeAnswer));	
			repu2.setAnswersId(ans.getAnswersId());
			repus.add(repu2);
			// 插入声望记录
			dao().insert(repus);
			
			// 更新用户声望记录，自问自答的不累积声望值
			// 问题被回答人的声望
			User u1 = dao().fetch(User.class, Cnd.where("userId", "=", ans.getQuestion().getUserId()));
			u1.setReputationCount(u1.getReputationCount()+maps.get(KbbConstants.Stragety_QuestionBeAnswer));
			// 回答问题人的声望
			User u2 = dao().fetch(User.class, Cnd.where("userId", "=", ans.getUserId()));
			u2.setReputationCount(u2.getReputationCount()+maps.get(KbbConstants.Stragety_AnswerQuestion));
			dao().update(Arrays.asList(u1,u2));
		}
		
		// 回答问题标签积分更新：
		// 自问自答问题标签积分不累积
		// 回答问题标签积分只记录第一次
		if(!ans.getQuestion().getUserId().equals(ans.getUserId())){
			int count = dao().count(Answers.class, Cnd.where("userId", "=", ans.getUserId()).and("questionId","=",ans.getQuestionId()));
			if(count<2){
				User u = dao().fetchLinks(dao().fetch(User.class, Cnd.where("userId", "=", answers.getUserId())), "userTags");
				String[] tags = question.getTags().split(",");
				List<UserTags> uts = u.getUserTags();
				List<UserTags> unUts = new ArrayList<UserTags>();
				List<UserTags> updateTags = new ArrayList<UserTags>();
				UserTags utmp;
				for(String tag:tags){
					UserTags tagFlag = null;
					for(UserTags ut:uts){
						if(ut.getTagName().equals(tag)){
							tagFlag = ut;
							break;
						}
					}
					if(tagFlag==null) {
						// 如果没有关联标签则需要关联
						utmp = new UserTags();
						utmp.setUserId(u.getUserId());
						utmp.setTagName(tag);
						utmp.setValue(1);
						unUts.add(utmp);
					} else {// 如果已关联标签则需要标签积分增加
						tagFlag.setValue(tagFlag.getValue()+1);
						updateTags.add(tagFlag);
					}
				}
				if(unUts.size()>0){
					u.setUserTags(unUts);
					dao().insertLinks(u, "userTags");
				}
				if(updateTags.size()>0){
					u.setUserTags(updateTags);
					dao().updateLinks(u, "userTags");
				}
			}
		}
		// 插入关注表
		User u = new User();
		u.setUserId(ans.getUserId());
		u = dao().fetchLinks(u, "focusQuestions", Cnd.where("userId", "=", ans.getUserId()).and("questionId", "=", ans.getQuestionId()));
		if(u.getFocusQuestions().size()<1){
			u.setFocusQuestions(Arrays.asList(ans.getQuestion()));
			dao().insertRelation(u, "focusQuestions");
		}
		// 问题的关注数增加
		question.setFocusCount(question.getFocusCount()+1);
		dao().update(question);
		
		return true;
	}
	
	/**
	 * 对问题进行投票
	 * 
	 * @Description TODO
	 * @param questionId 
	 * @param voteType
	 * @param userId 投票用户Id
	 */
	@Aop(TransAop.READ_COMMITTED)
	public void voteQuestion(String questionId, String voteType, String userId){
		Question q = dao().fetch(Question.class, Cnd.where("questionId", "=", questionId));
		// 不能对自己的问题投票
		if(q.getUserId().equals(userId)){
			throw Lang.makeThrow("不能对自己的问题投票", new Object[0]);
		}
		// 检测是否已经投过票
		UserVote uvote = dao().fetch(UserVote.class, Cnd.where("userId", "=", userId).and("sourceId","=",questionId).and("sourceType", "=", KbbConstants.SourceType.QUESTION.getValue()));
		if(uvote!=null) {
			throw Lang.makeThrow("您已经对问题投过票", new Object[0]);
		}
		// 获取声望策略
		Map<String, Integer> maps = getReputationStrategy();
		if(KbbConstants.VoteType_Add.equals(voteType)){
			// 赞同票
			Reputation repu = new Reputation();
			repu.setId(KbbUtils.generateID());
			repu.setReputationType(KbbConstants.Stragety_QuestionBeVoteAdd);
			repu.setSourceId(q.getQuestionId());
			repu.setSourceTitle(q.getTitle());
			repu.setSourceType(KbbConstants.SourceType.QUESTION.getValue());
			repu.setUserId(q.getUserId());
			repu.setValue(maps.get(KbbConstants.Stragety_QuestionBeVoteAdd));
			dao().insert(repu);
			
			// 更新用户声望数
			User u1 = dao().fetch(User.class, Cnd.where("userId", "=", q.getUserId()));
			u1.setReputationCount(u1.getReputationCount()+maps.get(KbbConstants.Stragety_QuestionBeVoteAdd));
			dao().update(u1);
			
			// 插入用户投票记录
			UserVote uv = new UserVote();
			uv.setId(KbbUtils.generateID());
			uv.setSourceId(questionId);
			uv.setSourceType(KbbConstants.SourceType.QUESTION.getValue());
			uv.setUserId(userId);
			uv.setVoteType(KbbConstants.VoteType_Add);
			dao().insert(uv);
			
			// 问题投票数增加
			q.setVoteCount(q.getVoteCount()+1);
			dao().update(q);
			
		} else if(KbbConstants.VoteType_Reduce.equals(voteType)){// 反对票
			// 被反对
			Reputation repu = new Reputation();
			repu.setId(KbbUtils.generateID());
			repu.setReputationType(KbbConstants.Stragety_QuestionBeVoteReduce);
			repu.setSourceId(q.getQuestionId());
			repu.setSourceTitle(q.getTitle());
			repu.setSourceType(KbbConstants.SourceType.QUESTION.getValue());
			repu.setUserId(q.getUserId());
			repu.setValue(maps.get(KbbConstants.Stragety_QuestionBeVoteReduce));
			//用户声望数
			User u1 = dao().fetch(User.class, Cnd.where("userId", "=", q.getUserId()));
			u1.setReputationCount(u1.getReputationCount()+maps.get(KbbConstants.Stragety_QuestionBeVoteReduce));
			
			// 反对者
			Reputation repu2 = new Reputation();
			repu2.setId(KbbUtils.generateID());
			repu2.setReputationType(KbbConstants.Stragety_QuestionVoteReduce);
			repu2.setSourceId(q.getQuestionId());
			repu2.setSourceTitle(q.getTitle());
			repu2.setSourceType(KbbConstants.SourceType.QUESTION.getValue());
			repu2.setUserId(userId);
			repu2.setValue(maps.get(KbbConstants.Stragety_QuestionVoteReduce));
			// 更新用户声望
			User u2 = dao().fetch(User.class, Cnd.where("userId", "=", userId));
			u2.setReputationCount(u2.getReputationCount()+maps.get(KbbConstants.Stragety_QuestionVoteReduce));
			
			dao().insert(Arrays.asList(repu,repu2));
			dao().update(Arrays.asList(u1,u2));
			
			// 插入用户投票记录
			UserVote uv = new UserVote();
			uv.setId(KbbUtils.generateID());
			uv.setSourceId(questionId);
			uv.setSourceType(KbbConstants.SourceType.QUESTION.getValue());
			uv.setUserId(userId);
			uv.setVoteType(KbbConstants.VoteType_Reduce);
			dao().insert(uv);
			
			// 问题投票数减少
			q.setVoteCount(q.getVoteCount()-1);
			dao().update(q);
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
