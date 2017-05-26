package com.xinqi.graduationdesign.service.impl;

import com.xinqi.graduationdesign.common.enums.TaxType;
import com.xinqi.graduationdesign.entity.PsysEmp;
import com.xinqi.graduationdesign.entity.vo.EmpSalaryVO;
import org.springframework.stereotype.Service;

import com.xinqi.graduationdesign.mapper.PsysEmpSalaryMapper;
import com.xinqi.graduationdesign.entity.PsysEmpSalary;
import com.xinqi.graduationdesign.service.IPsysEmpSalaryService;
import com.xinqi.graduationdesign.service.support.BaseServiceImpl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * PsysEmpSalary 表数据服务层接口实现类
 *
 */
@Service
public class PsysEmpSalaryServiceImpl extends BaseServiceImpl<PsysEmpSalaryMapper, PsysEmpSalary> implements IPsysEmpSalaryService {
    public List<EmpSalaryVO> selectAllEmpSalaryVO(int pageCount,int pageSize){
        return baseMapper.selectAllEmpSalaryVO(pageCount, pageSize);
    }
    public int selectAllEmpSalaryVOCount(){
        return baseMapper.selectAllEmpSalaryVOCount();
    }
    public boolean insertEmpSalary(PsysEmpSalary empSalary, PsysEmp emp){
        if(empSalary.getId() != null){
            PsysEmpSalary oldEmpSalary = new PsysEmpSalary();
            oldEmpSalary.setIsNew(false);
            oldEmpSalary.setId(empSalary.getId());
            updateSelectiveById(oldEmpSalary);
        }
        empSalary.setIsNew(true);
        empSalary.setCreateDate(new Date());
        empSalary.setOperatorId(emp.getId());
        //计算总薪水
        BigDecimal baseSalary = empSalary.getBaseSalary();
        BigDecimal fullAttendenceSalary = empSalary.getFullAttendenceSalary();
        BigDecimal mealsSalary = empSalary.getMealsSalary();
        BigDecimal salary = baseSalary.add(fullAttendenceSalary).add(mealsSalary);
        DecimalFormat format = new DecimalFormat("0.00");
        BigDecimal tax = salary.multiply(new BigDecimal(TaxType.TAX.tax()));
        tax = new BigDecimal(format.format(tax));
        empSalary.setTax(tax);
        empSalary.setSalary(salary.subtract(tax));
        empSalary.setId(null);
        return insert(empSalary);
    }
}