/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年3月24日 下午4:47:45
 * @version V1.0  
 */
package org.health.model;

import java.util.List;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年3月24日 下午4:47:45
 */
@Table("tb_department")
public class Department {

	@Id
	private long departmentID;

	private long hospitalID;

	@One(target = Hospital.class, field = "hospitalID")
	private Hospital hospital;

	private String departmentName;

	private String parentTag;

	private String departmentLocation;
	
	@ManyMany(target=Doctor.class, relation="tb_department_doctor", from="departmentID", to="doctorID")
	private List<Doctor> doctors;

	/**
	 * @return the departmentID
	 */
	public long getDepartmentID() {
		return departmentID;
	}

	/**
	 * @param departmentID
	 *            the departmentID to set
	 */
	public void setDepartmentID(long departmentID) {
		this.departmentID = departmentID;
	}

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
	 * @return the hospital
	 */
	public Hospital getHospital() {
		return hospital;
	}

	/**
	 * @param hospital
	 *            the hospital to set
	 */
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName
	 *            the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * @return the departmentLocation
	 */
	public String getDepartmentLocation() {
		return departmentLocation;
	}

	/**
	 * @param departmentLocation
	 *            the departmentLocation to set
	 */
	public void setDepartmentLocation(String departmentLocation) {
		this.departmentLocation = departmentLocation;
	}

	/**
	 * @return the parentTag
	 */
	public String getParentTag() {
		return parentTag;
	}

	/**
	 * @param parentTag
	 *            the parentTag to set
	 */
	public void setParentTag(String parentTag) {
		this.parentTag = parentTag;
	}

}
