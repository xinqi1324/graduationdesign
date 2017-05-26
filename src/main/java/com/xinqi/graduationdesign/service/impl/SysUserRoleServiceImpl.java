package com.xinqi.graduationdesign.service.impl;

import org.springframework.stereotype.Service;

import com.xinqi.graduationdesign.mapper.SysUserRoleMapper;
import com.xinqi.graduationdesign.entity.SysUserRole;
import com.xinqi.graduationdesign.service.ISysUserRoleService;
import com.xinqi.graduationdesign.service.support.BaseServiceImpl;

/**
 *
 * SysUserRole 表数据服务层接口实现类
 *
 */
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {
    @Override
    public boolean existRoleUser( Long roleId ) {
        SysUserRole ur = new SysUserRole();
        ur.setRid(roleId);
        int rlt = baseMapper.selectCount(ur);
        return rlt >= 1;
    }

}