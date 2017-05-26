package com.xinqi.graduationdesign.service;

import com.xinqi.graduationdesign.entity.SysRolePermission;
import com.baomidou.framework.service.ISuperService;

import java.util.List;

/**
 *
 * SysRolePermission 表数据服务层接口
 *
 */
public interface ISysRolePermissionService extends ISuperService<SysRolePermission> {
    /**
     * <p>
     * 判断是否存在角色对应的权限
     * </p>
     *
     * @param permissionId
     *            权限ID
     * @return
     */
    boolean existRolePermission(Long permissionId);

    /**
     * 根据角色ID获取对应的所有关联权限的ID
     * @param id
     * @return
     */
    List<Long> selecPermissionIdsByRoleId(Long id);

}