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
@TableName("psys_emp_salary")
public class PsysEmpSalary implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId
	private Long id;

	/**  */
	private Long empId;

	/** 基本工资 */
	private BigDecimal baseSalary;

	/** 餐补 */
	private BigDecimal mealsSalary;

	/** 个人所得税 */
	private BigDecimal tax;

	/** 全勤奖 */
	private BigDecimal fullAttendenceSalary;

	/** 全部工资 */
	private BigDecimal salary;

	/**  */
	private Long operatorId;

	/**  */
	private Boolean isNew;

	/**  */
	private Date createDate;


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

	public BigDecimal getBaseSalary() {
		return this.baseSalary;
	}

	public void setBaseSalary(BigDecimal baseSalary) {
		this.baseSalary = baseSalary;
	}

	public BigDecimal getMealsSalary() {
		return this.mealsSalary;
	}

	public void setMealsSalary(BigDecimal mealsSalary) {
		this.mealsSalary = mealsSalary;
	}

	public BigDecimal getTax() {
		return this.tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getFullAttendenceSalary() {
		return this.fullAttendenceSalary;
	}

	public void setFullAttendenceSalary(BigDecimal fullAttendenceSalary) {
		this.fullAttendenceSalary = fullAttendenceSalary;
	}

	public BigDecimal getSalary() {
		return this.salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public Long getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	public Boolean getIsNew() {
		return this.isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
