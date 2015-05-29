/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年5月6日 下午1:15:55
 * @version V1.0  
 */
package org.health.web;

import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年5月6日 下午1:15:55
 */
@At("/wx")
public class WechatModule {
	@At("/doctor")
	@Ok("jsp:jsp.wechat.doctor")
	public void doctorMain(){
		
	}
	
	@At("/users")
	@Ok("jsp:jsp.wechat.userlist")
	public void userList(){
		
	}
	
	@At("/user/info")
	@Ok("jsp:jsp.wechat.userinfo")
	public void userInfo(){
		
	}
	
	@At("/user/chat")
	@Ok("jsp:jsp.wechat.chat")
	public void chat(){
		
		
	}
	
}
