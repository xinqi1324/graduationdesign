package com.xinqi.graduationdesign.service;

import com.xinqi.graduationdesign.entity.PsysEmp;
import com.xinqi.graduationdesign.entity.PsysEmpSalary;
import com.baomidou.framework.service.ISuperService;
import com.xinqi.graduationdesign.entity.vo.EmpSalaryVO;

import java.util.List;

/**
 *
 * PsysEmpSalary 表数据服务层接口
 *
 */
public interface IPsysEmpSalaryService extends ISuperService<PsysEmpSalary> {
    List<EmpSalaryVO> selectAllEmpSalaryVO(int pageCount, int pageSize);
    int selectAllEmpSalaryVOCount();
    boolean insertEmpSalary(PsysEmpSalary empSalary, PsysEmp emp);
}