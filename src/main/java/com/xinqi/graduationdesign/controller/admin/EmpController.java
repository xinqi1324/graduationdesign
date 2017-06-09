package com.xinqi.graduationdesign.controller.admin;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xinqi.framework.controller.AjaxResult;
import com.xinqi.graduationdesign.common.enums.EducationType;
import com.xinqi.graduationdesign.common.enums.StatusType;
import com.xinqi.graduationdesign.common.enums.UserType;
import com.xinqi.graduationdesign.controller.BaseController;
import com.xinqi.graduationdesign.entity.PsysDept;
import com.xinqi.graduationdesign.entity.PsysEmp;
import com.xinqi.graduationdesign.entity.SysRole;
import com.xinqi.graduationdesign.entity.SysUser;
import com.xinqi.graduationdesign.entity.vo.EmpVO;
import com.xinqi.graduationdesign.service.IPsysDeptService;
import com.xinqi.graduationdesign.service.IPsysEmpService;
import com.xinqi.graduationdesign.service.ISysRoleService;
import com.xinqi.kisso.annotation.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2017/5/8.
 */
@Controller
@RequestMapping("/admin/emp/")
public class EmpController extends BaseController{
    @Autowired
    private IPsysEmpService empService;
    @Autowired
    private ISysRoleService roleService;
    @Autowired
    private IPsysDeptService deptService;
    @Permission("5001")
    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("sysRoles",roleService.selectList(null));
      return "/psys/emp/list";
    }

    @ResponseBody
    @Permission("5001")
    @RequestMapping("/getEmpList")
    public String getEmpList(Model model) {
        Page<PsysEmp> page = getPage();
        Long currentUserId = getCurrentUserId();
        SysUser user = userService.selectById(currentUserId);
        //查找当前用户对应的emp
        EntityWrapper<PsysEmp> empEntityWrapper = new EntityWrapper<PsysEmp>();
        empEntityWrapper.where("userId=" + currentUserId);
        PsysEmp emp = empService.selectOne(empEntityWrapper);
        Long deptId = emp.getDeptId();
        Long type = user.getType();
        empEntityWrapper = new EntityWrapper<PsysEmp>();
        if(type == UserType.DEPARTMENT_MANAGER.key()){
            empEntityWrapper.where("deptId=" + deptId);
        }else if (type ==UserType.HR.key() || type == UserType.GENERAL_MANAGER.key() || type == UserType.ADMIN.key()){

        }
        empEntityWrapper.where("status!=" + StatusType.DELETED.key());
        return jsonPage(empService.selectPage(page,empEntityWrapper));

    }

    @Permission("5001")
    @RequestMapping("/insert")
    public String insert(Model model, Long id ) {
        //角色列表
        EntityWrapper<SysRole> roleEntityWrapper = new EntityWrapper<SysRole>();
        //判断当前用户的角色，如果是部门经理只能添加自己部门
        Long currentUserId = getCurrentUserId();
        SysUser user = userService.selectById(currentUserId);
        Long type = user.getType();
        EntityWrapper<PsysEmp> empEntityWrapper = new EntityWrapper<PsysEmp>();
        empEntityWrapper.where("userId=" + currentUserId);
        PsysEmp emp = empService.selectOne(empEntityWrapper);
        List<PsysDept> depts = new ArrayList<PsysDept>();
        //查询当前账户所能添加的部门
        if(type == UserType.DEPARTMENT_MANAGER.key()){
            depts.add(deptService.selectById(emp.getDeptId()));
            roleEntityWrapper.where("id=" + UserType.EMPLOYEE.key());
        }else if(type == UserType.HR.key() ){
            depts.add(deptService.selectById(emp.getDeptId()));
            roleEntityWrapper.where("id=" + UserType.EMPLOYEE.key());
        }else if(type == UserType.GENERAL_MANAGER.key()){
            depts = deptService.selectList(null);
            roleEntityWrapper.where("id=" + UserType.EMPLOYEE.key()).and("id=" +UserType.DEPARTMENT_MANAGER.key());
        }else if(type == UserType.ADMIN.key()){
            depts = deptService.selectList(null);
            roleEntityWrapper.where("id!=" + UserType.ADMIN.key());
        }
        List<SysRole> sysRoles = roleService.selectList(roleEntityWrapper);
        model.addAttribute("sysRoles",sysRoles);
        model.addAttribute("depts",depts);
        //学历列表
        EducationType[] educationTypes = EducationType.values();
        model.addAttribute("educationTypes",educationTypes);
        return "/psys/emp/insert";
    }
    @ResponseBody
    @Permission("5001")
    @RequestMapping("/insertEmp")
    public String insertEmp(PsysEmp emp,SysUser user) {
        boolean flag = false;
        flag = empService.insertEmp(emp,user);
        return callback(new AjaxResult(flag));
    }
    //冻结员工
    @ResponseBody
    @Permission("5001")
    @RequestMapping("/freezeEmp")
    public String freezeEmp(Long id) {
        Long currentUserId = getCurrentUserId();
        PsysEmp emp =empService.selectById(id);
        if(emp.getUserId().equals(currentUserId)){
            return callbackFail("不能冻结当前登录用户");
        }else{
            boolean flag = empService.freezeEmp(emp);
            return callbackSuccess(new AjaxResult(flag));
        }
    }

    @Permission("5002")
    @RequestMapping("/editList")
    public String editList(Model model){
        UserType[] userTypes = UserType.values();
        model.addAttribute("userTypes",userTypes);
        return "/psys/emp/editList";
    }

    @ResponseBody
    @Permission("5002")
    @RequestMapping("/getEditList")
    public String editList(Model model,Long empId){
        Page<EmpVO> page = getPage();
        List<EmpVO> empVOS = empService.selectEmpVOList(page.getCurrent() - 1, page.getSize());
        page.setRecords(empVOS);
        page.setTotal(empService.selectEmpVOListCount());
        return jsonPage(page);
    }

    @Permission("5002")
    @RequestMapping("/editRoleDept")
    public String editRoleDept(Model model,Long id){
        EmpVO empVO = empService.selectEmpVO(id);
        EntityWrapper<SysRole> roleEntityWrapper = new EntityWrapper<SysRole>();
        roleEntityWrapper.where("id != 1");
        List<SysRole> sysRoles = roleService.selectList(roleEntityWrapper);
        List<PsysDept> depts = deptService.selectList(null);
        model.addAttribute("emp",empVO);
        model.addAttribute("sysRoles",sysRoles);
        model.addAttribute("depts",depts);
        return "/psys/emp/editRoleDept";
    }
    @ResponseBody
    @Permission("5002")
    @RequestMapping("/doEditRoleDept")
    public String doEditRoleDept(Model model,EmpVO empVO){
        boolean flag = empService.updateEmpVO(empVO);
        return callback(new AjaxResult(flag));
    }

}
