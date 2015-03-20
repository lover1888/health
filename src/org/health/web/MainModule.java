/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年3月18日 下午5:06:09
 * @version V1.0  
 */
package org.health.web;

import java.util.HashMap;
import java.util.Map;

import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年3月18日 下午5:06:09
 */
public class MainModule {
	Log log = Logs.getLog(MainModule.class);
	
	@At("/hello")
    @Ok("jsp:jsp.hello")
    public String doHello() {
		
		log.debug("hello nutz.");
        return "Hello Nutz ^_^";
    }
	
	@At("/say")
	@Ok("jsp:jsp.sayhello")
//	@Fail("jsp:jsp.error")
	public Map doSayHello() {
		Map<String, String> values = new HashMap<String, String>();
		values.put("name", "jjj");
		values.put("age", "12");
		
		return values;
	}

}
