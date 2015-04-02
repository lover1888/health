/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月1日 上午9:09:14
 * @version V1.0  
 */
package org.health.model;

import org.nutz.dao.entity.annotation.Table;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月1日 上午9:09:14
 */

@Table("tb_user_tags")
public class UserTags {

	private String id;
	private String userId;
	private String tagId;
	private int value; 
	
}
