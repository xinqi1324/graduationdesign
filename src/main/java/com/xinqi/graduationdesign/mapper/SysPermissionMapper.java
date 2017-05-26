package com.xinqi.graduationdesign.mapper;

import com.xinqi.graduationdesign.entity.SysPermission;
import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.xinqi.graduationdesign.entity.vo.MenuVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * SysPermission 表数据库控制层接口
 *
 */
public interface SysPermissionMapper extends AutoMapper<SysPermission> {

    List<MenuVO> selectMenuByUserId(@Param("userId") Long userId, @Param("pid") Long pid);

    List<SysPermission> selectAllByUserId(@Param("userId") Long userId);


}