package com.xinqi.graduationdesign.mapper;

import com.xinqi.graduationdesign.entity.PsysEmpSalary;
import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.xinqi.graduationdesign.entity.vo.EmpSalaryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * PsysEmpSalary 表数据库控制层接口
 *
 */
public interface PsysEmpSalaryMapper extends AutoMapper<PsysEmpSalary> {
    List<EmpSalaryVO> selectAllEmpSalaryVO(@Param("pageCount") int pageCount, @Param("pageSize") int pageSize);
    int selectAllEmpSalaryVOCount();
}