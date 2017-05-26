package com.xinqi.graduationdesign.controller.sys;

import com.xinqi.graduationdesign.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xinqi.kisso.annotation.Action;
import com.xinqi.kisso.annotation.Permission;

/**
 * <p>
 * 监控
 * </p>
 * 
 * @author likun
 * @Date 2016-04-21
 */
@Controller
@RequestMapping("/sys/monitor")
public class MonitorController extends BaseController {

	/**
	 * 实时监控
	 */
	@Permission(action = Action.Skip)
	@RequestMapping("/realTime")
	public String realTime(Model model) {
		
		return "/admin/monitor/realTime";
	}

}
