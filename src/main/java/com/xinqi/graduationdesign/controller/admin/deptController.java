package com.xinqi.graduationdesign.controller.admin;

import com.baomidou.mybatisplus.plugins.Page;
import com.xinqi.framework.controller.AjaxResult;
import com.xinqi.graduationdesign.controller.BaseController;
import com.xinqi.graduationdesign.entity.PsysDept;
import com.xinqi.graduationdesign.service.IPsysDeptService;
import com.xinqi.kisso.annotation.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by ASUS on 2017/5/11.
 */
@Controller
@RequestMapping("/admin/dept/")
public class deptController extends BaseController{
    @Autowired
    private IPsysDeptService deptService;

    @Permission("6001")
    @RequestMapping("/list")
    public String list(Model model) {
        return "/psys/dept/list";
    }

    @ResponseBody
    @Permission("6001")
    @RequestMapping("/getDeptList")
    public String getDeptList(Model model) {
        Page<PsysDept> page = getPage();
        return jsonPage(deptService.selectPage(page, null));
    }

    @Permission("6001")
    @RequestMapping("/edit")
    public String edit(Model model,Long id){
        PsysDept psysDept = deptService.selectById(id);
        model.addAttribute("dept",psysDept);
        return "/psys/dept/edit";
    }

    @ResponseBody
    @Permission("6001")
    @RequestMapping("/editDept")
    public String editDept(Model model,PsysDept dept){
        boolean flag = false;
        Date date = new Date();
        if(dept.getId() != null){
            dept.setLastDate(date);
            flag = deptService.updateSelectiveById(dept);
        }else{
            dept.setCreateDate(date);
            dept.setLastDate(date);
            flag = deptService.insert(dept);
        }
        return callback(new AjaxResult(flag));
    }

    @ResponseBody
    @Permission("6001")
    @RequestMapping("/delete")
    public String delete(Long id){
        boolean flag = false;
        try {
            flag = deptService.deleteDept(id);
        } catch (Exception e) {
            return callbackFail(e.getMessage());
        }
        return callback(new AjaxResult(flag));
    }
}
