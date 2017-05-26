package com.xinqi.graduationdesign.controller.sys;

import com.xinqi.kisso.annotation.Action;
import com.xinqi.kisso.annotation.Permission;
import com.xinqi.graduationdesign.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 李坤 on 2016/12/27.
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseController {
    /**
     * 地图
     */
    @Permission(action = Action.Skip)
    @RequestMapping("/echarts/map")
    public String map(Model model) {

        return "/admin/test/echarts/map";
    }

    /**
     * 发送
     */
    @Permission("1001")
    @RequestMapping("/mail/send")
    public String send(Model model, String email) {
        if (isPost()) {
            model.addAttribute("email", email);
            model.addAttribute("loginName", getSSOToken().getData());
            boolean rlt = mailHelper.sendMail(email, "SpringMVC 测试邮件！", "/test/mail/tplSend.html", model);
            String tipMsg = "发送邮件至【" + email + "】失败！！";
            if(rlt){
                tipMsg = "已成功发送邮件至【" + email + "】注意查收！！";
            }
            model.addAttribute("tipMsg", tipMsg);
        }
        return "/admin/test/mail/send";
    }
}
