package com.xinqi.graduationdesign.service.impl;

import com.xinqi.kisso.SSOAuthorization;
import com.xinqi.kisso.Token;
import com.xinqi.graduationdesign.entity.vo.MenuVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.xinqi.graduationdesign.mapper.SysPermissionMapper;
import com.xinqi.graduationdesign.entity.SysPermission;
import com.xinqi.graduationdesign.service.ISysPermissionService;
import com.xinqi.graduationdesign.service.support.BaseServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * SysPermission 表数据服务层接口实现类
 *
 */
@Service
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService, SSOAuthorization {

    @Cacheable(value = "permissionCache", key = "#userId")
    @Override
    public List<MenuVO> selectMenuVOByUserId(Long userId) {
        List<MenuVO> perList = baseMapper.selectMenuByUserId(userId, 0L);
        if (perList == null || perList.isEmpty()) {
            return null;
        }
        List<MenuVO> mvList = new ArrayList<MenuVO>();
        for (MenuVO mv : perList) {
            mv.setMvList(baseMapper.selectMenuByUserId(userId, mv.getId()));
            mvList.add(mv);
        }
        return mvList;
    }

    @Override
    public boolean isPermitted(Token token, String permission) {
        /**
         *
         * 菜单级别、权限验证，生产环境建议加上缓存处理。
         *
         */
        if (StringUtils.isNotBlank(permission)) {
            List<SysPermission> pl = this.selectAllByUserId(token.getId());
            if (pl != null) {
                for (SysPermission p : pl) {
                    if (permission.equals(p.getPermCode())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Cacheable(value = "permissionCache", key = "#userId")
    @Override
    public List<SysPermission> selectAllByUserId(Long userId) {
        return baseMapper.selectAllByUserId(userId);
    }

    @Override
    public boolean isActionable( Token token, String permission ) {
        /**
         *
         * 按钮级别、权限验证，生产环境建议加上缓存处理。
         * <br>
         * 演示  admin 返回 true
         *
         */
        System.err.println(" isActionable =" + permission);
        if ( token.getId() == 1L ) {
            return true;
        }
        return false;
    }

    @CacheEvict(value = "permissionCache",allEntries = true)
    public boolean insertWithClearCache(SysPermission permission){
        return super.retBool(super.baseMapper.insert(permission));
    }

    @CacheEvict(value = "permissionCache",allEntries = true)
    public boolean updateByIdWithClearCache(SysPermission permission){
        return super.retBool(super.baseMapper.updateById(permission));
    }
}