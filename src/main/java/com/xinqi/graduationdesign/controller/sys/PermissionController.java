package com.xinqi.graduationdesign.controller.sys;


import com.baomidou.mybatisplus.plugins.Page;
import com.xinqi.framework.controller.AjaxResult;
import com.xinqi.graduationdesign.controller.BaseController;
import com.xinqi.graduationdesign.entity.SysPermission;
import com.xinqi.graduationdesign.service.ISysPermissionService;
import com.xinqi.graduationdesign.service.ISysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 角色管理相关操作
 * </p>
 *
 *
 * @Author likun
 * @Date 2016-04-15
 */
@Controller
@RequestMapping("/sys/perm/permission")
public class PermissionController extends BaseController {

	@Autowired
	private ISysPermissionService permissionService;

	@Autowired
	private ISysRolePermissionService rolePermissionService;

	@com.xinqi.kisso.annotation.Permission("2003")
	@RequestMapping("/list")
	public String list(Model model) {
		return "/admin/permission/list";
	}

	@ResponseBody
	@com.xinqi.kisso.annotation.Permission("2003")
	@RequestMapping("/getPermissionList")
	public String getPermissionList(Model model) {
		Page<SysPermission> page = getPage();
		return jsonPage(permissionService.selectPage(page, null));
	}

	@com.xinqi.kisso.annotation.Permission("2003")
	@RequestMapping("/edit")
	public String edit(Model model,Long id) {
		if ( id != null ) {
			SysPermission permission = permissionService.selectById(id);
			model.addAttribute("permission",permission);
		}
		//父节点信息
		List<SysPermission> permissions = permissionService.selectList(null);
		model.addAttribute("permissions",permissions);
		return "/admin/permission/edit";
	}

	/**
	 * 跳转到添加子权限页面
	 * @param model
	 * @param id
	 * @return
	 */
	@com.xinqi.kisso.annotation.Permission("2003")
	@RequestMapping("/addChild")
	public String addChild(Model model,Long id) {
		if ( id != null ) {
			SysPermission parent = permissionService.selectById(id);
			model.addAttribute("parent",parent);
		}
		return "/admin/permission/edit";
	}

	@ResponseBody
	@com.xinqi.kisso.annotation.Permission("2003")
	@RequestMapping("/editPerm")
	public String editPerm( SysPermission perm) {
		boolean rlt = false;
		String msg = "";
		//判定是添加还是修改
		if (perm.getId() == null) {
			//添加权限
			rlt = permissionService.insertWithClearCache(perm);
		}else {
			rlt = permissionService.updateByIdWithClearCache(perm);
		}
		return callback(new AjaxResult(rlt,msg));
	}

	@ResponseBody
	@com.xinqi.kisso.annotation.Permission("2003")
	@RequestMapping("/delete")
	public String delete(Long id) {
		String msg = "";
		boolean rlt;
		boolean exist = rolePermissionService.existRolePermission(id);
		if (exist) {
			rlt = false;
			msg = "删除失败,需要删除的菜单存在关联角色！";
		}else {
			rlt = permissionService.deleteById(id);
		}
		return callback(new AjaxResult(rlt,msg));
	}
}
