/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月8日 上午10:59:05
 * @version V1.0  
 */
package org.health.service;

import java.sql.Connection;

import org.health.model.Answers;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.EntityService;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月8日 上午10:59:05
 */
@IocBean(fields={"dao"})
public class AnswerService extends EntityService<Answers> {
	
	public boolean saveAnswer(final Answers answer) {
		
		Trans.exec(Connection.TRANSACTION_READ_COMMITTED, new Atom(){
			@Override
			public void run() {
				//插入数据到回答表
				dao().insert(answer);
				//更新问题表中的回答数
				Sql sql = Sqls.create("update tb_question q set q.answersCount=q.answersCount+1 where q.questionId=@qid");
				sql.params().set("qid", answer.getQuestionId());
				dao().execute(sql);
				//插入声望记录表（根据打分策略，为回答人和提问人增加声望记录）
				
				//如果是回答自己的问题不加分
				
				//为关注该问题的人增加通知消息
				
			}});
		
		
		return false;
	}
	
}
