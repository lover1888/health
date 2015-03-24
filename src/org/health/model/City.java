/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年3月24日 上午10:25:49
 * @version V1.0  
 */
package org.health.model;

import java.util.List;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年3月24日 上午10:25:49
 */
@Table("tb_city")
public class City {

	@Id
	private long cityID;

	private long provinceID;

	private String cityName;

	private String zipCode;

	@One(target = Province.class, field = "provinceID")
	private Province province;

	@Many(target = District.class, field = "cityID")
	private List<District> districts;

	/**
	 * @return the cityID
	 */
	public long getCityID() {
		return cityID;
	}

	/**
	 * @param cityID
	 *            the cityID to set
	 */
	public void setCityID(long cityID) {
		this.cityID = cityID;
	}

	/**
	 * @return the provinceID
	 */
	public long getProvinceID() {
		return provinceID;
	}

	/**
	 * @param provinceID
	 *            the provinceID to set
	 */
	public void setProvinceID(long provinceID) {
		this.provinceID = provinceID;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName
	 *            the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the province
	 */
	public Province getProvince() {
		return province;
	}

	/**
	 * @param province
	 *            the province to set
	 */
	public void setProvince(Province province) {
		this.province = province;
	}

	/**
	 * @return the districts
	 */
	public List<District> getDistricts() {
		return districts;
	}

	/**
	 * @param districts
	 *            the districts to set
	 */
	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}

}
