/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年3月24日 下午3:09:24
 * @version V1.0  
 */
package org.health.model;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年3月24日 下午3:09:24
 */
@Table("tb_hospital")
public class Hospital {

	@Id
	private long hospitalID;

	private long districtID;

	@One(target = District.class, field = "districtID")
	private District district;

	private String hospitalName;

	private String address;

	private String telphone;

	private String webURL;

	private String introduce;

	// 医院等级（三级甲等、三级医院、二级医院、一级医院）
	private String hospitalLevel;

	// 医院类型（综合医院、中医医院、儿科医院、眼科医院、妇产科医院、对外专科、口腔医院）
	private String hospitalType;

	/**
	 * @return the hospitalID
	 */
	public long getHospitalID() {
		return hospitalID;
	}

	/**
	 * @param hospitalID
	 *            the hospitalID to set
	 */
	public void setHospitalID(long hospitalID) {
		this.hospitalID = hospitalID;
	}

	/**
	 * @return the districtID
	 */
	public long getDistrictID() {
		return districtID;
	}

	/**
	 * @param districtID
	 *            the districtID to set
	 */
	public void setDistrictID(long districtID) {
		this.districtID = districtID;
	}

	/**
	 * @return the district
	 */
	public District getDistrict() {
		return district;
	}

	/**
	 * @param district
	 *            the district to set
	 */
	public void setDistrict(District district) {
		this.district = district;
	}

	/**
	 * @return the hospitalName
	 */
	public String getHospitalName() {
		return hospitalName;
	}

	/**
	 * @param hospitalName
	 *            the hospitalName to set
	 */
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the telphone
	 */
	public String getTelphone() {
		return telphone;
	}

	/**
	 * @param telphone
	 *            the telphone to set
	 */
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	/**
	 * @return the webURL
	 */
	public String getWebURL() {
		return webURL;
	}

	/**
	 * @param webURL
	 *            the webURL to set
	 */
	public void setWebURL(String webURL) {
		this.webURL = webURL;
	}

	/**
	 * @return the introduce
	 */
	public String getIntroduce() {
		return introduce;
	}

	/**
	 * @param introduce
	 *            the introduce to set
	 */
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	/**
	 * @return the hospitalLevel
	 */
	public String getHospitalLevel() {
		return hospitalLevel;
	}

	/**
	 * @param hospitalLevel
	 *            the hospitalLevel to set
	 */
	public void setHospitalLevel(String hospitalLevel) {
		this.hospitalLevel = hospitalLevel;
	}

	/**
	 * @return the hospitalType
	 */
	public String getHospitalType() {
		return hospitalType;
	}

	/**
	 * @param hospitalType
	 *            the hospitalType to set
	 */
	public void setHospitalType(String hospitalType) {
		this.hospitalType = hospitalType;
	}

}
