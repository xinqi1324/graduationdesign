package com.xinqi.graduationdesign.entity;

import java.io.Serializable;
import java.util.Date;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 
 *
 */
@TableName("psys_attendance")
public class PsysAttendance implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId
	private Long id;

	/**  */
	private Long empId;

	/**  */
	private Date beginTime;

	/**  */
	private Date endTime;

	/**  */
	private Date createTime;

	/**  */
	private Boolean isLate;

	/**  */
	private Boolean isWork;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEmpId() {
		return this.empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public Date getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Boolean getIsLate() {
		return this.isLate;
	}

	public void setIsLate(Boolean isLate) {
		this.isLate = isLate;
	}

	public Boolean getIsWork() {
		return this.isWork;
	}

	public void setIsWork(Boolean isWork) {
		this.isWork = isWork;
	}

}
