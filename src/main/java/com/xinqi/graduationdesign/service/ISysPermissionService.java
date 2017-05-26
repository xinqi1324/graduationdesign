package com.xinqi.graduationdesign.service;

import com.xinqi.kisso.Token;
import com.xinqi.graduationdesign.entity.SysPermission;
import com.baomidou.framework.service.ISuperService;
import com.xinqi.graduationdesign.entity.vo.MenuVO;

import java.util.List;

/**
 *
 * SysPermission 表数据服务层接口
 *
 */
public interface ISysPermissionService extends ISuperService<SysPermission> {

    List<MenuVO> selectMenuVOByUserId(Long userId );


    List<SysPermission> selectAllByUserId( Long userId );


    /**
     * <p>
     * 是否为可操作的权限
     * </p>
     * @param token
     * 				SSO 票据顶级父类
     * @param permission
     * 				操作权限编码
     * @return
     */
    boolean isActionable(Token token, String permission );

    boolean insertWithClearCache(SysPermission permission);

    boolean updateByIdWithClearCache(SysPermission permission);
}