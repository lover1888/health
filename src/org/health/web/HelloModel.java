/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月8日 下午4:33:31
 * @version V1.0  
 */
package org.health.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.health.model.Question;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月8日 下午4:33:31
 */
@IocBean
@At("/study")
public class HelloModel {
	
	@At("/c")
	@Ok("jsp:jsp.jstl.c")
	public List<Question> doTestList(HttpServletRequest req){
		// list 
		List<Question> qs = new ArrayList<Question>();
		Question q = new Question();
		q.setQuestionId("1");
		q.setTitle("abc");
		q.setContent("fjkdlas");
		qs.add(q);
		q = new Question();
		q.setQuestionId("2");
		q.setTitle("fds");
		q.setContent("uipouiop");
		qs.add(q);
		
		// map
		Map<String, Question> maps = new HashMap<String, Question>();
		q = new Question();
		q.setQuestionId("3");
		maps.put("3", q);
		q = new Question();
		q.setQuestionId("4");
		maps.put("4", q);
		req.setAttribute("map", maps);
		
		// forTokens
		String str="jjj,28,男";
		req.setAttribute("token", str);
		
		String varstr = "varstr 是有值的";
		req.setAttribute("varstr", varstr);
		
		return qs;
	}
	
}
