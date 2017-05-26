package com.xinqi.graduationdesign.mapper;

import com.xinqi.graduationdesign.entity.SysRolePermission;
import com.baomidou.mybatisplus.mapper.AutoMapper;

import java.util.List;

/**
 *
 * SysRolePermission 表数据库控制层接口
 *
 */
public interface SysRolePermissionMapper extends AutoMapper<SysRolePermission> {

    /**
     * 根据角色ID获取对应的所有关联权限的ID
     * @param id 角色Id
     * @return
     */
    List<Long> selecPermissionIdsByRoleId(Long id);

}