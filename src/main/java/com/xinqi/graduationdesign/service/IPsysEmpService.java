package com.xinqi.graduationdesign.service;

import com.xinqi.graduationdesign.entity.PsysEmp;
import com.baomidou.framework.service.ISuperService;
import com.xinqi.graduationdesign.entity.SysUser;
import com.xinqi.graduationdesign.entity.vo.EmpVO;
import com.xinqi.graduationdesign.entity.vo.UserVO;

import java.util.List;

/**
 *
 * PsysEmp 表数据服务层接口
 *
 */
public interface IPsysEmpService extends ISuperService<PsysEmp> {
    boolean insertEmp(PsysEmp emp, SysUser user);
    boolean freezeEmp(PsysEmp emp);
    List<UserVO> selectUserVOById(Long deptId,int pageCount,int pageSize);
    PsysEmp selectEmpByUserId(Long userId);
    List<EmpVO> selectEmpVOList(int pageCount, int pageSize);
    int selectEmpVOListCount();
    EmpVO selectEmpVO(Long empId);
    boolean updateEmpVO(EmpVO empVO);
}