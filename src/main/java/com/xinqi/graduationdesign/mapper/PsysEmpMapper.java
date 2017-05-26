package com.xinqi.graduationdesign.mapper;

import com.xinqi.graduationdesign.entity.PsysEmp;
import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.xinqi.graduationdesign.entity.vo.EmpVO;
import com.xinqi.graduationdesign.entity.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * PsysEmp 表数据库控制层接口
 *
 */
public interface PsysEmpMapper extends AutoMapper<PsysEmp> {
    List<UserVO> selectUserVObyId(@Param("deptId") Long deptId,@Param("pageCount")int pageCount,@Param("pageSize") int pageSize);
    List<EmpVO> selectEmpVOList(@Param("pageCount")int pageCount,@Param("pageSize") int pageSize);
    int selectEmpVOListCount();
    EmpVO selectEmpVO(Long empId);
}