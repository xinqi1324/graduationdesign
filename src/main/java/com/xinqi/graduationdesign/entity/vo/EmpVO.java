package com.xinqi.graduationdesign.entity.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ASUS on 2017/5/14.
 */
public class EmpVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long empId;
    private String empName;
    private String deptName;
    private int empStatus;
    private Date lastDate;
    private Long type;
    private Long deptId;

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(int empStatus) {
        this.empStatus = empStatus;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
}
