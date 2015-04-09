/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月8日 下午2:41:08
 * @version V1.0  
 */
package org.health.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.health.common.page.Pagination;
import org.health.model.Question;
import org.health.service.QuestionService;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月8日 下午2:41:08
 */
@IocBean
public class QuestionModule {
	Log log = Logs.getLog(QuestionModule.class);
	final int pageSize = 20;
	@Inject
	private QuestionService questionService;
	
	@At("/question/*")
	@Ok("jsp:jsp.question.questions")
	public void doQuestionList(String type, int page, HttpServletRequest req){
		if(!Strings.isEmpty(type)) {
			if(page<=0){
				page = 1;
			}
			if("newest".equals(type)) {
				Pagination<Question> pgs = this.questionService.getQuestionByNews(page, pageSize);
				req.setAttribute("pgs", pgs);
				
			} else if("hottest".equals(type)) {
				
			} else if("unanswered".equals(type)) {
				
			}
		}
		
		
	}
	
	@At("/hello")
    @Ok("jsp:jsp.hello")
    public String doHello() {
		Pagination<Question> page = this.questionService.getQuestionByNews(1, 20);
		System.out.println(page.getList().size());
		log.debug("hello nutz.");
        return "Hello Nutz ^_^";
    }
	
	@At("/say")
	@Ok("jsp:jsp.sayhello")
//	@Fail("jsp:jsp.error")
	@Fail("http:500")
	public void doSayHello(HttpServletRequest req) {
		Map<String, String> values = new HashMap<String, String>();
		values.put("name", "jjj");
		values.put("age", "12");
		req.setAttribute("map", values);
		req.setAttribute("name", "jhf");
	}

}
