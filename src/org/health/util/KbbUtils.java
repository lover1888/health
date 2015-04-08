/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月8日 上午9:54:58
 * @version V1.0  
 */
package org.health.util;

import java.util.UUID;

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
	
}
