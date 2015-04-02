/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月1日 下午2:27:54
 * @version V1.0  
 */
package org.health.service;

import java.util.List;

import org.health.common.page.Pagination;
import org.health.model.Question;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
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
	public Question getQuestionDetail(String questionId) {
		
		String sql = "select * from tb_question as q, tb_user as u where q.questionId=";
		
		
		return null;
	}

}
