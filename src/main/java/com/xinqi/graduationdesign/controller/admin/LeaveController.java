package com.xinqi.graduationdesign.controller.admin;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xinqi.framework.controller.AjaxResult;
import com.xinqi.graduationdesign.common.enums.LeaveStatusType;
import com.xinqi.graduationdesign.common.enums.UserType;
import com.xinqi.graduationdesign.controller.BaseController;
import com.xinqi.graduationdesign.entity.PsysEmp;
import com.xinqi.graduationdesign.entity.PsysLeave;
import com.xinqi.graduationdesign.entity.SysUser;
import com.xinqi.graduationdesign.entity.vo.LeaveVO;
import com.xinqi.graduationdesign.service.IPsysDeptService;
import com.xinqi.graduationdesign.service.IPsysEmpService;
import com.xinqi.graduationdesign.service.IPsysLeaveService;
import com.xinqi.kisso.annotation.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2017/5/12.
 */
@Controller
@RequestMapping("/admin/leave/")
public class LeaveController extends BaseController{
    @Autowired
    private IPsysLeaveService leaveService;
    @Autowired
    private IPsysEmpService empService;
    @Autowired
    private IPsysDeptService deptService;
    @Permission("7002")
    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("userTypes",UserType.values());
        model.addAttribute("depts",deptService.selectList(null));
        model.addAttribute("leaveStatusTypes", LeaveStatusType.values());
        return "/psys/leave/list";
    }

    @ResponseBody
    @Permission("7002")
    @RequestMapping("/getLeaveList")
    public String getLeaveList(){
        Page<LeaveVO> page = getPage();
        SysUser user = userService.selectById(getCurrentUserId());
        //根据用户的角色去查询对应的员工
        List<LeaveVO> leaveVOS = leaveService.selectLeaveVOs(user,getEmp(), page);
        page.setRecords(leaveVOS);
        page.setTotal(leaveService.getLeaveVOCount(user,getEmp()));
        return jsonPage(page);
    }
    @Permission("7002")
    @RequestMapping("/showLeave")
    public String showLeave(Long id, Model model){
        LeaveVO leave = leaveService.selectLeaveVObyId(id);
        model.addAttribute("userTypes",UserType.values());
        model.addAttribute("depts",deptService.selectList(null));
        model.addAttribute("leaveStatusTypes", LeaveStatusType.values());
        model.addAttribute("leave",leave);
        return "/psys/leave/showLeave";
    }

    @ResponseBody
    @Permission("7002")
    @RequestMapping("/editLeaveStatus")
    public String editLeaveStatus(Long id,boolean status){
        PsysLeave leave = leaveService.selectById(id);
        leave.setOperatorDate(new Date());
        if(status){
            leave.setStatus(LeaveStatusType.AGREED.key());
        }else{
            leave.setStatus(LeaveStatusType.DISAGREED.key());
        }
        boolean flag = leaveService.updateSelectiveById(leave);
        return callback(new AjaxResult(flag));
    }
    @ResponseBody
    @Permission("7001")
    @RequestMapping("/cancel")
    public String cancel(Long id){
        PsysLeave leave = leaveService.selectById(id);
        leave.setOperatorDate(new Date());
        if(leave.getStatus() == LeaveStatusType.AGREED.key() || leave.getStatus() == LeaveStatusType.DISAGREED.key()){
            return callback(new AjaxResult(false,"该请假已审批！不能取消"));
        }
        if(leave.getStatus() == LeaveStatusType.CANCELED.key()){
            return callback(new AjaxResult(false,"请勿重复取消！"));
        }
        leave.setStatus(LeaveStatusType.CANCELED.key());
        boolean flag = leaveService.updateSelectiveById(leave);
        return callback(new AjaxResult(flag));
    }
    @Permission("7001")
    @RequestMapping("/leave")
    public String leave(Model model){
        model.addAttribute("leaveStatusTypes", LeaveStatusType.values());
        return "/psys/leave/userLeaveList";
    }

    @ResponseBody
    @Permission("7001")
    @RequestMapping("/getUserLeaveList")
    public String getUserLeaveList(){
        Page<PsysLeave> page = getPage();
        EntityWrapper<PsysLeave> entityWrapper = new EntityWrapper<PsysLeave>();
        entityWrapper.where("userId=" + getCurrentUserId())
                .and("status!=" + LeaveStatusType.CANCELED.key()).orderBy("createDate",true);
        return jsonPage(leaveService.selectPage(page,entityWrapper));
    }
    @Permission("7001")
    @RequestMapping("/insert")
    public String insert(){
        return "/psys/leave/addUserLeave";
    }

    @ResponseBody
    @Permission("7001")
    @RequestMapping("/insertLeave")
    public String insertLeave(String beginDate,String endDate,String reason){
        Date date = new Date();
        PsysLeave leave = new PsysLeave();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            leave.setBeginDate(simpleDateFormat.parse(beginDate));
            leave.setEndDate(simpleDateFormat.parse(endDate));
        } catch (ParseException e) {
            return callback(new AjaxResult(false,"日期格式错误！"));
        }
        PsysEmp emp = getEmp();
        leave.setUserId(getCurrentUserId());
        leave.setEmpId(emp.getId());
        leave.setDateCount((int) ((leave.getEndDate().getTime() - leave.getBeginDate().getTime()) / (1000*3600*24)) + 1);
        leave.setOperatorId(getCurrentUserId());
        leave.setReason(reason);
        leave.setCreateDate(date);
        leave.setDeptId(emp.getDeptId());
        leave.setStatus(LeaveStatusType.APPLYING.key());
        leave.setIsRead(false);
        boolean flag = leaveService.insert(leave);
        return callback(new AjaxResult(flag));
    }

}

