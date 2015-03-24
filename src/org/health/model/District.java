/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年3月24日 上午10:34:50
 * @version V1.0  
 */
package org.health.model;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年3月24日 上午10:34:50
 */
@Table("tb_district")
public class District {
	
	@Id
	private long districtID;
	
	private String districtName;
	
	private long cityID;
	
	@One(target=City.class, field="cityID")
	private City city;

	/**  
	 * @return the districtID  
	 */
	public long getDistrictID() {
		return districtID;
	}

	/**  
	 * @param districtID the districtID to set  
	 */
	public void setDistrictID(long districtID) {
		this.districtID = districtID;
	}

	/**  
	 * @return the districtName  
	 */
	public String getDistrictName() {
		return districtName;
	}

	/**  
	 * @param districtName the districtName to set  
	 */
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	/**  
	 * @return the cityID  
	 */
	public long getCityID() {
		return cityID;
	}

	/**  
	 * @param cityID the cityID to set  
	 */
	public void setCityID(long cityID) {
		this.cityID = cityID;
	}

	/**  
	 * @return the city  
	 */
	public City getCity() {
		return city;
	}

	/**  
	 * @param city the city to set  
	 */
	public void setCity(City city) {
		this.city = city;
	}
	
	

}
