package com.xinqi.graduationdesign.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xinqi.graduationdesign.entity.PsysEmp;
import com.xinqi.graduationdesign.service.IPsysEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinqi.graduationdesign.mapper.PsysDeptMapper;
import com.xinqi.graduationdesign.entity.PsysDept;
import com.xinqi.graduationdesign.service.IPsysDeptService;
import com.xinqi.graduationdesign.service.support.BaseServiceImpl;

import java.util.List;

/**
 *
 * PsysDept 表数据服务层接口实现类
 *
 */
@Service
public class PsysDeptServiceImpl extends BaseServiceImpl<PsysDeptMapper, PsysDept> implements IPsysDeptService {
    @Autowired
    private IPsysEmpService empService;
    public boolean deleteDept(Long deptId) throws Exception{
        //删除前要查看该部门有没有员工，如果有员工则不允许删除
        EntityWrapper<PsysEmp> empEntityWrapper = new EntityWrapper<PsysEmp>();
        empEntityWrapper.where("deptId=" + deptId);
        List<PsysEmp> psysEmps = empService.selectList(empEntityWrapper);
        if(psysEmps.size() >= 1){
            throw new Exception("请先删除该部门下的所有员工！");
        }
        boolean flag = deleteById(deptId);
        return flag;
    }

}