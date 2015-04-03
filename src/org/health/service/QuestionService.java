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
import java.util.List;

import org.health.common.page.Pagination;
import org.health.model.Question;
import org.health.util.KbbConstants;
import org.health.vo.AnswerVo;
import org.health.vo.QuestionDetailVo;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.impl.sql.callback.EntityCallback;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlContext;
import org.nutz.lang.Strings;
import org.nutz.service.EntityService;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月1日 下午2:27:54
 */
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
				rlt.setFocus(rs.getString("isFocus")==null?false:true);
				rlt.setFavorite(rs.getString("isFav")==null?false:true);
		        return rlt;
			}
		};
		
		Sql sql;
		if(Strings.isEmpty(userId)){
			sql = Sqls.create("select q.*, u.userName, u.reputationCount, u.imageUrl from tb_question q, tb_user u where q.questionId=@q1 and q.userId=u.userId");
			sql.params().set("q1", questionId);
		} else {
			// 查询指定问题的详细信息，提问人的详细信息，当前用户是否收藏了该问题，是否关注了该问题
			sql = Sqls.create("select q.*, u.userName, u.reputationCount, u.imageUrl, (select id from tb_user_focus uf where uf.sourceId=@q1  and uf.sourceType=@s1 and uf.userId=@u1) as isFocus, (select tuf.id from tb_user_favorites ufa, tb_user_fav tuf where tuf.sourceId=@s2 and ufa.userId=@u2 and tuf.sourceType=@s2 and tuf.favoritesId=ufa.id) as isFav from tb_question q, tb_user u where q.questionId=@q3 and q.userId=u.userId");
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
		return sql.getObject(QuestionDetailVo.class);
	}
	
//	public Pagination<AnswerVo> getAnswersByQuestion(String questionId, int pageNum, int pageSize) {
//		
//	}
	

}
