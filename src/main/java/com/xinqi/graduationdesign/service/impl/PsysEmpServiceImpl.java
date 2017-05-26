package com.xinqi.graduationdesign.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xinqi.graduationdesign.common.enums.StatusType;
import com.xinqi.graduationdesign.entity.SysUser;
import com.xinqi.graduationdesign.entity.SysUserRole;
import com.xinqi.graduationdesign.entity.vo.EmpVO;
import com.xinqi.graduationdesign.entity.vo.UserVO;
import com.xinqi.graduationdesign.service.ISysUserRoleService;
import com.xinqi.graduationdesign.service.ISysUserService;
import com.xinqi.kisso.common.encrypt.SaltEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinqi.graduationdesign.mapper.PsysEmpMapper;
import com.xinqi.graduationdesign.entity.PsysEmp;
import com.xinqi.graduationdesign.service.IPsysEmpService;
import com.xinqi.graduationdesign.service.support.BaseServiceImpl;

import java.util.Date;
import java.util.List;

/**
 *
 * PsysEmp 表数据服务层接口实现类
 *
 */
@Service
public class PsysEmpServiceImpl extends BaseServiceImpl<PsysEmpMapper, PsysEmp> implements IPsysEmpService {
    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysUserRoleService userRoleService;
    public boolean insertEmp(PsysEmp emp, SysUser user){
        Date date = new Date();
        emp.setCreateDate(date);
        emp.setLastDate(date);
        emp.setStatus(StatusType.NORMAL.key());

        user.setCrTime(date);
        user.setLastTime(date);
        user.setStatus(StatusType.NORMAL.key());
        user.setNickName(emp.getName());
        //设置密码
        user.setPassword(SaltEncoder.md5SaltEncode(user.getLoginName(), user.getPassword()));
        boolean flag = userService.insertSelective(user);
        if(flag){
            emp.setUserId(user.getId());
            insertSelective(emp);
            Long roleId = user.getType();
            SysUserRole userRole = new SysUserRole();
            userRole.setRid(roleId);
            userRole.setUid(user.getId());
            flag = userRoleService.insertSelective(userRole);
        }
        return flag;
    }
    //冻结账户 根据当前账户的状态 冻结或解除冻结
     public boolean freezeEmp(PsysEmp emp){
         SysUser user = userService.selectById(emp.getUserId());
         if(emp.getStatus() == StatusType.NORMAL.key()){
             emp.setStatus(StatusType.FROZEN.key());
             user.setStatus(StatusType.FROZEN.key());
         }else if(emp.getStatus() == StatusType.FROZEN.key()){
             emp.setStatus(StatusType.NORMAL.key());
             user.setStatus(StatusType.NORMAL.key());
         }
         boolean flag = updateSelectiveById(emp);
         if(flag){
             flag = userService.updateSelectiveById(user);
         }
         return flag;
     }

     public List<UserVO> selectUserVOById(Long deptId,int pageCount,int pageSize){
         return baseMapper.selectUserVObyId(deptId,pageCount,pageSize);
     }
     public PsysEmp selectEmpByUserId(Long userId){
         EntityWrapper<PsysEmp> entityWrapper = new EntityWrapper<PsysEmp>();
         entityWrapper.where("userId=" + userId);
         return selectOne(entityWrapper);
     }
     public List<EmpVO> selectEmpVOList(int pageCount,int pageSize){
         return baseMapper.selectEmpVOList(pageCount,pageSize);
     }
     public int selectEmpVOListCount(){
         return baseMapper.selectEmpVOListCount();
     }
    public EmpVO selectEmpVO(Long empId){
         return baseMapper.selectEmpVO(empId);
    }
    public boolean updateEmpVO(EmpVO empVO){
        PsysEmp emp = selectById(empVO.getEmpId());
        emp.setDeptId(empVO.getDeptId());
        emp.setLastDate(new Date());
        SysUser user = userService.selectById(emp.getUserId());
        user.setLastTime(new Date());
        user.setType(empVO.getType());
        if(updateSelectiveById(emp)){
            return userService.updateSelectiveById(user);
        }else{
            return false;
        }
    }

}