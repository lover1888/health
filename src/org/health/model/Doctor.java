/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年3月21日 下午5:24:18
 * @version V1.0  
 */
package org.health.model;

import java.util.List;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年3月21日 下午5:24:18
 */
@Table("tb_doctor")
public class Doctor {
	
	@Id
	private long doctorID;
	
	private long departmentID;
	
	private long hospitalID;
	
	private List<Hospital> hospital;
	
	
	
	

}
