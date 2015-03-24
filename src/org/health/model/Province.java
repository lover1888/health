/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年3月24日 上午10:14:51
 * @version V1.0  
 */
package org.health.model;

import java.util.List;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.Table;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年3月24日 上午10:14:51
 */
@Table("tb_province")
public class Province {
	
	@Id
	private long provinceID;
	
	private String provinceName;
	
	@Many(target=City.class, field = "provinceID")
	private List<City> cities;

	/**  
	 * @return the provinceID  
	 */
	public long getProvinceID() {
		return provinceID;
	}

	/**  
	 * @param provinceID the provinceID to set  
	 */
	public void setProvinceID(long provinceID) {
		this.provinceID = provinceID;
	}

	/**  
	 * @return the provinceName  
	 */
	public String getProvinceName() {
		return provinceName;
	}

	/**  
	 * @param provinceName the provinceName to set  
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	/**  
	 * @return the cities  
	 */
	public List<City> getCities() {
		return cities;
	}

	/**  
	 * @param cities the cities to set  
	 */
	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	
	

}
