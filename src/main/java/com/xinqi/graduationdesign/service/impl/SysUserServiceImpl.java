package com.xinqi.graduationdesign.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xinqi.framework.annotations.Log;
import com.xinqi.graduationdesign.common.enums.StatusType;
import com.xinqi.graduationdesign.common.enums.UserType;
import com.xinqi.graduationdesign.entity.PsysEmp;
import com.xinqi.graduationdesign.entity.SysUserRole;
import com.xinqi.graduationdesign.service.IPsysEmpService;
import com.xinqi.graduationdesign.service.ISysRoleService;
import com.xinqi.graduationdesign.service.ISysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinqi.graduationdesign.mapper.SysUserMapper;
import com.xinqi.graduationdesign.entity.SysUser;
import com.xinqi.graduationdesign.service.ISysUserService;
import com.xinqi.graduationdesign.service.support.BaseServiceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * SysUser 表数据服务层接口实现类
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Autowired
    private ISysRoleService roleService;
    @Autowired
    private ISysUserRoleService userRoleService;
    @Autowired
    private IPsysEmpService empService;
    @Log("登录")
    @Override
    public SysUser selectByLoginName(String loginName) {
        SysUser user = new SysUser();
        user.setLoginName(loginName);
        return super.selectOne(user);
    }

    @Log("删除用户")
    @Override
    public void deleteUser(Long userId) {
        //删除用户角色，再删除用户
        roleService.deleteByUserId(userId);
        super.deleteById(userId);
        //然后将员工信息设置为已删除
        EntityWrapper<PsysEmp> entityWrapper = new EntityWrapper<PsysEmp>();
        entityWrapper.where("userId=" + userId);
        PsysEmp emp = empService.selectOne(entityWrapper);
        emp.setStatus(StatusType.DELETED.key());
        empService.updateSelectiveById(emp);
        //删除关联
        SysUserRole userRole = new SysUserRole();
        Map<String,Object> userRoleMap = new HashMap<String, Object>();
        userRoleMap.put("uId",userId);
        userRoleService.deleteByMap(userRoleMap);
    }

    @Log("添加用户")
    @Override
    public boolean insertSelective(SysUser entity) {
        System.err.println(" 覆盖父类方法 ");
        return super.insertSelective(entity);
    }

    public boolean insertOrUpdateUser(SysUser user) {
        user.setCrTime(new Date());
        user.setLastTime(user.getCrTime());
        boolean flag = false;
        if (user.getId() != null) {
            flag = updateSelectiveById(user);
            if (flag) {
                //找到关联角色信息，然后改变角色
                //该功能已删除
                Long roleId = user.getType();
                EntityWrapper<SysUserRole> entityWrapper = new EntityWrapper<SysUserRole>();
                entityWrapper.where("uid=" + user.getId());
                SysUserRole userRole = userRoleService.selectOne(entityWrapper);
                userRole.setRid(roleId);
                userRoleService.updateById(userRole);
            }
        } else {
            flag = insertSelective(user);
            if (flag) {
                //添加角色关联
                Long roleId = user.getType();
                SysUserRole userRole = new SysUserRole();
                userRole.setRid(roleId);
                userRole.setUid(user.getId());
                userRoleService.insertSelective(userRole);
                //如果添加的不是管理员，则添加一个条员工信息
                if(!user.getType().equals(UserType.ADMIN)){
                    PsysEmp emp = new PsysEmp();
                    emp.setName(user.getNickName());
                    emp.setUserId(user.getId());
                    emp.setWorkStartTime(user.getCrTime());
                    emp.setStatus(StatusType.NORMAL.key());
                    emp.setCreateDate(user.getCrTime());
                    emp.setLastDate(user.getCrTime());
                    empService.insert(emp);
                }
            }
        }
        return flag;
    }

}