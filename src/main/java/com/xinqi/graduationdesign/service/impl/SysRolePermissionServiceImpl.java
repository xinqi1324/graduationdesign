package com.xinqi.graduationdesign.service.impl;

import com.xinqi.framework.annotations.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinqi.graduationdesign.mapper.SysRolePermissionMapper;
import com.xinqi.graduationdesign.entity.SysRolePermission;
import com.xinqi.graduationdesign.service.ISysRolePermissionService;
import com.xinqi.graduationdesign.service.support.BaseServiceImpl;

import java.util.List;

/**
 *
 * SysRolePermission 表数据服务层接口实现类
 *
 */
@Service
public class SysRolePermissionServiceImpl extends BaseServiceImpl<SysRolePermissionMapper, SysRolePermission> implements ISysRolePermissionService {


    @Autowired
    private SysRolePermissionMapper rolePermissionMapper;

    @Log("菜单查询")
    @Override
    public boolean existRolePermission(Long permissionId) {
        SysRolePermission rp = new SysRolePermission();
        rp.setPid(permissionId);
        int rlt = baseMapper.selectCount(rp);
        return rlt >= 1;
    }

    @Log("角色关联菜单查询")
    @Override
    public List<Long> selecPermissionIdsByRoleId(Long id) {
        return rolePermissionMapper.selecPermissionIdsByRoleId(id);
    }
}