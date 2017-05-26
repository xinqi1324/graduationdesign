package com.xinqi.graduationdesign.entity.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by ASUS on 2017/5/13.
 */
public class EmpSalaryVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long empId;
    private String name;
    private BigDecimal baseSalary;
    private BigDecimal mealsSalary;
    private BigDecimal tax;
    private BigDecimal fullAttendenceSalary;
    private BigDecimal salary;
    private BigDecimal deptId;
    private Long operatorId;
    private Boolean isNew;
    private Date createDate;
    private int type;

    public BigDecimal getDeptId() {
        return deptId;
    }

    public void setDeptId(BigDecimal deptId) {
        this.deptId = deptId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(BigDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }

    public BigDecimal getMealsSalary() {
        return mealsSalary;
    }

    public void setMealsSalary(BigDecimal mealsSalary) {
        this.mealsSalary = mealsSalary;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getFullAttendenceSalary() {
        return fullAttendenceSalary;
    }

    public void setFullAttendenceSalary(BigDecimal fullAttendenceSalary) {
        this.fullAttendenceSalary = fullAttendenceSalary;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public Boolean getNew() {
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
