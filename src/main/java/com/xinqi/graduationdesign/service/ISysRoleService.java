package com.xinqi.graduationdesign.service;

import com.xinqi.graduationdesign.entity.SysRole;
import com.baomidou.framework.service.ISuperService;

/**
 *
 * SysRole 表数据服务层接口
 *
 */
public interface ISysRoleService extends ISuperService<SysRole> {
    void deleteByUserId(Long userId);

    boolean updateRoleRight(Long roleId,String rights);
}