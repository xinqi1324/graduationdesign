package com.xinqi.graduationdesign.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.xinqi.graduationdesign.entity.PsysEmp;
import com.xinqi.graduationdesign.entity.PsysLeave;
import com.baomidou.framework.service.ISuperService;
import com.xinqi.graduationdesign.entity.SysUser;
import com.xinqi.graduationdesign.entity.vo.LeaveVO;

import java.util.List;

/**
 *
 * PsysLeave 表数据服务层接口
 *
 */
public interface IPsysLeaveService extends ISuperService<PsysLeave> {
    List<LeaveVO> selectLeaveVOs(SysUser user, PsysEmp emp, Page<LeaveVO> page);
    int getLeaveVOCount(SysUser user,PsysEmp emp);
    LeaveVO selectLeaveVObyId(Long id);
}