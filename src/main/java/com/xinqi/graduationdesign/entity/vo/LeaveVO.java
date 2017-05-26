package com.xinqi.graduationdesign.entity.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ASUS on 2017/5/12.
 */
public class LeaveVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long empId;
    private Long deptId;
    private int status;
    private Date beginDate;
    private Date endDate;
    private Date createDate;
    private Date operatorDate;
    private int dateCount;
    private String name;
    private int type;

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

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getOperatorDate() {
        return operatorDate;
    }

    public void setOperatorDate(Date operatorDate) {
        this.operatorDate = operatorDate;
    }

    public int getDateCount() {
        return dateCount;
    }

    public void setDateCount(int dateCount) {
        this.dateCount = dateCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
