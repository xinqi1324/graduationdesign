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
@TableName("psys_emp")
public class PsysEmp implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**  */
    @TableId
    private Long id;

    /**  */
    private String name;

    /**
     * 0男 1女
     */
    private Boolean gender;

    /**  */
    private Long deptId;

    /**
     * 学历
     */
    private Integer education;

    /**
     * 状态 0正常 1冻结 2已删除
     */
    private Integer status;

    /**  */
    private Date birth;

    /**
     * 籍贯
     */
    private String origin;

    /**  */
    private String nation;

    /**  */
    private String idCardNumber;

    /**
     * 婚否 0否 1是
     */
    private Boolean marry;

    /**
     * 政治面貌
     */
    private String politics;

    /**  */
    private String blood;

    /**  */
    private Date workStartTime;

    /**  */
    private String address;


    /**  */
    private String phone;

    /**
     * 关联的账号id
     */
    private Long userId;

    private String email;

    private Date createDate;

    private Date lastDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }



    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getGender() {
        return this.gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Long getDeptId() {
        return this.deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Integer getEducation() {
        return this.education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getBirth() {
        return this.birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getNation() {
        return this.nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getIdCardNumber() {
        return this.idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public Boolean getMarry() {
        return this.marry;
    }

    public void setMarry(Boolean marry) {
        this.marry = marry;
    }

    public String getPolitics() {
        return this.politics;
    }

    public void setPolitics(String politics) {
        this.politics = politics;
    }

    public String getBlood() {
        return this.blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public Date getWorkStartTime() {
        return this.workStartTime;
    }

    public void setWorkStartTime(Date workStartTime) {
        this.workStartTime = workStartTime;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
