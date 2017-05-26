package com.xinqi.graduationdesign.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.xinqi.graduationdesign.common.enums.LeaveStatusType;
import com.xinqi.graduationdesign.common.enums.UserType;
import com.xinqi.graduationdesign.entity.PsysEmp;
import com.xinqi.graduationdesign.entity.PsysLeave;
import com.xinqi.graduationdesign.entity.SysUser;
import com.xinqi.graduationdesign.entity.vo.LeaveVO;
import com.xinqi.graduationdesign.mapper.PsysLeaveMapper;
import com.xinqi.graduationdesign.service.IPsysLeaveService;
import com.xinqi.graduationdesign.service.support.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * PsysLeave 表数据服务层接口实现类
 *
 */
@Service
public class PsysLeaveServiceImpl extends BaseServiceImpl<PsysLeaveMapper, PsysLeave> implements IPsysLeaveService {
    public List<LeaveVO> selectLeaveVOs(SysUser user, PsysEmp emp, Page<LeaveVO> page) {
        Long type = user.getType();
        if (type == UserType.DEPARTMENT_MANAGER.key() || type == UserType.HR.key()) {
            return baseMapper.selectLeaveVOs(emp.getDeptId(),user.getId(),page.getCurrent() - 1,page.getSize(), LeaveStatusType.CANCELED.key());
        } else if (type == UserType.GENERAL_MANAGER.key() || type == UserType.BOSS.key() || type == UserType.ADMIN.key()) {
            return baseMapper.selectLeaveVOs(null,user.getId(),page.getCurrent() - 1,page.getSize(),LeaveStatusType.CANCELED.key());
        }else {
            return null;
        }
    }
    public int getLeaveVOCount(SysUser user,PsysEmp emp){
        return baseMapper.selectLeaveVOsCount(emp.getDeptId(),user.getId(),LeaveStatusType.CANCELED.key());
    }

    public LeaveVO selectLeaveVObyId(Long id){
        return baseMapper.selectLeaveVObyId(id);
    }
}