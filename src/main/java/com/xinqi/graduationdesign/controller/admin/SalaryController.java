package com.xinqi.graduationdesign.controller.admin;

import com.baomidou.mybatisplus.plugins.Page;
import com.xinqi.framework.controller.AjaxResult;
import com.xinqi.graduationdesign.common.enums.UserType;
import com.xinqi.graduationdesign.controller.BaseController;
import com.xinqi.graduationdesign.entity.PsysEmpSalary;
import com.xinqi.graduationdesign.entity.vo.EmpSalaryVO;
import com.xinqi.graduationdesign.service.IPsysDeptService;
import com.xinqi.graduationdesign.service.IPsysEmpSalaryService;
import com.xinqi.kisso.annotation.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ASUS on 2017/5/13.
 */
@Controller
@RequestMapping("/admin/salary/")
public class SalaryController extends BaseController {
    @Autowired
    private IPsysEmpSalaryService empSalaryService;
    @Autowired
    private IPsysDeptService deptService;
    @Permission("8001")
    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("userTypes", UserType.values());
        model.addAttribute("depts",deptService.selectList(null));
        return "/psys/salary/list";
    }
    @ResponseBody
    @Permission("8001")
    @RequestMapping("/getList")
    public String getLeaveList(){
        Page<EmpSalaryVO> page = getPage();
        List<EmpSalaryVO> empSalaryVOS = empSalaryService.selectAllEmpSalaryVO(page.getCurrent() - 1, page.getSize());
        page.setRecords(empSalaryVOS);
        page.setTotal(empSalaryService.selectAllEmpSalaryVOCount());
        return jsonPage(page);
    }
    @Permission("8001")
    @RequestMapping("/edit")
    public String edit(Model model,Long empId,Long empSalaryId) {
        if(empSalaryId != null){
            PsysEmpSalary psysEmpSalary = empSalaryService.selectById(empSalaryId);
            model.addAttribute("empSalary",psysEmpSalary);
        }
        model.addAttribute("empId",empId);
        return "/psys/salary/edit";
    }
    @ResponseBody
    @Permission("8001")
    @RequestMapping("/insertEmpSalary")
    public String insertEmpSalary(Model model, PsysEmpSalary empSalary) {
        boolean flag = empSalaryService.insertEmpSalary(empSalary, getEmp());
        return callback(new AjaxResult(flag));
    }
}
