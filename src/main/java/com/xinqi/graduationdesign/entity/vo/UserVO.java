package com.xinqi.graduationdesign.entity.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ASUS on 2017/5/11.
 */
public class UserVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;
    private Long empId;
    private String empName;
    private int type;
    private int status;
    private Date lastDate;
    private Date wordStartTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public Date getWordStartTime() {
        return wordStartTime;
    }

    public void setWordStartTime(Date wordStartTime) {
        this.wordStartTime = wordStartTime;
    }
}
