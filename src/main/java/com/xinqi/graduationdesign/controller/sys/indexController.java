package com.xinqi.graduationdesign.controller.sys;

import com.xinqi.graduationdesign.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ASUS on 2017/5/8.
 */
@Controller
@RequestMapping("/")
public class indexController extends BaseController{
    @RequestMapping("/")
    public String root(Model model) {
        return redirectTo("/admin/index.html");
    }
}
