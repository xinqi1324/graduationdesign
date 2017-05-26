package com.xinqi.graduationdesign.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 
 *
 */
@TableName("psys_salary")
public class PsysSalary implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId
	private Long id;

	/** 加班费 */
	private BigDecimal overtimeSalary;

	/** 应发工资 */
	private BigDecimal grossSalary;

	/** 实发工资 */
	private BigDecimal actualSalary;

	/** 所得税 */
	private BigDecimal tax;

	/**  */
	private Date createDate;

	/**  */
	private Long operatorId;

	/** 工作天数 */
	private Integer workDayCount;

	/** 迟到天数 */
	private Integer lateDayCount;

	/** 旷工天数 */
	private Integer absenteeismDayCount;

	/** 请假天数 */
	private Integer leaveDayCount;

	/** 本月工作天数 */
	private Integer mouthWorkDay;

	/**  */
	private Integer status;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getOvertimeSalary() {
		return this.overtimeSalary;
	}

	public void setOvertimeSalary(BigDecimal overtimeSalary) {
		this.overtimeSalary = overtimeSalary;
	}

	public BigDecimal getGrossSalary() {
		return this.grossSalary;
	}

	public void setGrossSalary(BigDecimal grossSalary) {
		this.grossSalary = grossSalary;
	}

	public BigDecimal getActualSalary() {
		return this.actualSalary;
	}

	public void setActualSalary(BigDecimal actualSalary) {
		this.actualSalary = actualSalary;
	}

	public BigDecimal getTax() {
		return this.tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	public Integer getWorkDayCount() {
		return this.workDayCount;
	}

	public void setWorkDayCount(Integer workDayCount) {
		this.workDayCount = workDayCount;
	}

	public Integer getLateDayCount() {
		return this.lateDayCount;
	}

	public void setLateDayCount(Integer lateDayCount) {
		this.lateDayCount = lateDayCount;
	}

	public Integer getAbsenteeismDayCount() {
		return this.absenteeismDayCount;
	}

	public void setAbsenteeismDayCount(Integer absenteeismDayCount) {
		this.absenteeismDayCount = absenteeismDayCount;
	}

	public Integer getLeaveDayCount() {
		return this.leaveDayCount;
	}

	public void setLeaveDayCount(Integer leaveDayCount) {
		this.leaveDayCount = leaveDayCount;
	}

	public Integer getMouthWorkDay() {
		return this.mouthWorkDay;
	}

	public void setMouthWorkDay(Integer mouthWorkDay) {
		this.mouthWorkDay = mouthWorkDay;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
