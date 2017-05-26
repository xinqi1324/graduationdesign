package com.xinqi.graduationdesign.mapper;

import com.xinqi.graduationdesign.entity.PsysLeave;
import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.xinqi.graduationdesign.entity.vo.LeaveVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * PsysLeave 表数据库控制层接口
 *
 */
public interface PsysLeaveMapper extends AutoMapper<PsysLeave> {
    List<LeaveVO> selectLeaveVOs(@Param("deptId")Long deptId,@Param("userId")Long userId,
                                 @Param("pageCount")int pageCount,@Param("pageSize")int pageSize,@Param("status")int status);
    int selectLeaveVOsCount(@Param("deptId")Long deptId,@Param("userId")Long userId,@Param("status")int status);
    LeaveVO selectLeaveVObyId(Long id);
}