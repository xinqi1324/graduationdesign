package com.xinqi.graduationdesign.service;

import com.xinqi.graduationdesign.entity.SysUserRole;
import com.baomidou.framework.service.ISuperService;

/**
 *
 * SysUserRole 表数据服务层接口
 *
 */
public interface ISysUserRoleService extends ISuperService<SysUserRole> {

    /**
     * <p>
     * 判断是否存在角色对应的用户
     * </p>
     *
     * @param roleId
     *            角色ID
     * @return
     */
    boolean existRoleUser(Long roleId);
}