package com.xinqi.graduationdesign.controller.sys;

import com.xinqi.framework.controller.AjaxResult;
import com.xinqi.graduationdesign.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xinqi.kisso.annotation.Permission;
import com.baomidou.mybatisplus.plugins.Page;
import com.xinqi.graduationdesign.entity.SysLog;
import com.xinqi.graduationdesign.service.ISysLogService;

/**
 * <p>
 * 操作日志
 * </p>
 *
 *
 * @Author likun
 * @Date 2016-05-09
 */
@Controller
@RequestMapping("/sys/log")
public class LogController extends BaseController {

	@Autowired
	private ISysLogService sysLogService;

	@Permission("4001")
	@RequestMapping("/list")
	public String list(Model model) {
		return "/admin/log/list";
	}

	@ResponseBody
	@Permission("4001")
	@RequestMapping("/getLogList")
	public String getUserList() {
		Page<SysLog> page = getPage();
		return jsonPage(sysLogService.selectPage(page, null));
	}

	@ResponseBody
	@Permission("4001")
	@RequestMapping("/delete")
	public String delUser(Long id) {
		boolean rlt = sysLogService.deleteById(id);
		return callback(new AjaxResult(rlt,null));
	}
}
