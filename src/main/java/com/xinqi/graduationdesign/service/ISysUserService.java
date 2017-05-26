package com.xinqi.graduationdesign.service;

import com.xinqi.graduationdesign.entity.SysUser;
import com.baomidou.framework.service.ISuperService;

/**
 *
 * SysUser 表数据服务层接口
 *
 */
public interface ISysUserService extends ISuperService<SysUser> {
    SysUser selectByLoginName(String loginName);

    void deleteUser(Long userId);

    boolean insertOrUpdateUser(SysUser user);
}