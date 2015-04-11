/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月8日 上午9:54:58
 * @version V1.0  
 */
package org.health.util;

import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月8日 上午9:54:58
 */
public class KbbUtils {

	/**
	 * 生成唯一标识ID
	 * @Description TODO
	 * @return
	 */
	public static String generateID(){
		return UUID.randomUUID().toString();
	}
	
	/**
	 * 从Session中取指定的值
	 * @param key
	 * @return
	 */
	public static String getSession(String key){
		Subject subject = SecurityUtils.getSubject();
		String userId = (String)subject.getSession().getAttribute(key);
		return userId;
	}
	
	public static String getCurrentUserId(){
		return getSession(KbbConstants.SESSION_USER_ID);
	}
	
}
