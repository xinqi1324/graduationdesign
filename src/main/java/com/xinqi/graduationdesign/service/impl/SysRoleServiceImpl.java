package com.xinqi.graduationdesign.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xinqi.graduationdesign.entity.SysRolePermission;
import com.xinqi.graduationdesign.entity.SysUserRole;
import com.xinqi.graduationdesign.mapper.SysRolePermissionMapper;
import com.xinqi.graduationdesign.mapper.SysUserRoleMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.xinqi.graduationdesign.mapper.SysRoleMapper;
import com.xinqi.graduationdesign.entity.SysRole;
import com.xinqi.graduationdesign.service.ISysRoleService;
import com.xinqi.graduationdesign.service.support.BaseServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * SysRole 表数据服务层接口实现类
 *
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public void deleteByUserId(Long userId) {
        SysUserRole ur = new SysUserRole();
        ur.setUid(userId);
        userRoleMapper.deleteSelective(ur);
    }

    @Override
    @CacheEvict(value = "permissionCache",allEntries = true)
    public boolean updateRoleRight(Long roleId, String rights) {
        boolean rlt;
        try {
            //查询出本角色已经分配了的权限
            SysRolePermission rolePermission = new SysRolePermission();
            rolePermission.setRid(roleId);
            EntityWrapper<SysRolePermission> ew = new EntityWrapper<SysRolePermission>();
            ew.setEntity(rolePermission);
            List<SysRolePermission> roleRightList = sysRolePermissionMapper.selectList(ew);

            //如果存在权限，先进行删除
            if (roleRightList.size() > 0) {
                for (SysRolePermission rp :roleRightList) {
                    sysRolePermissionMapper.deleteSelective(rp);
                }
            }

            String[] rightIds = rights.split(",");
            if(StringUtils.isNotBlank(rights) && rightIds != null){
                //添加新分配的权限
                List<SysRolePermission> permissions = new ArrayList<SysRolePermission>();
                SysRolePermission e = null;
                for (String pid : rightIds) {
                    e = new SysRolePermission();
                    e.setPid(Long.valueOf(pid));
                    e.setRid(roleId);
                    permissions.add(e);
                }
                sysRolePermissionMapper.insertBatch(permissions);
            }
            rlt = true;
        } catch (Exception e) {
            rlt = false;
        }
        return rlt;
    }
}