package com.xinqi.graduationdesign.controller.sys;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xinqi.framework.controller.AjaxResult;
import com.xinqi.graduationdesign.common.enums.UserType;
import com.xinqi.graduationdesign.controller.BaseController;
import com.xinqi.graduationdesign.entity.SysRole;
import com.xinqi.graduationdesign.entity.SysUserRole;
import com.xinqi.graduationdesign.service.ISysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xinqi.kisso.annotation.Action;
import com.xinqi.kisso.annotation.Permission;
import com.xinqi.kisso.common.encrypt.SaltEncoder;
import com.baomidou.mybatisplus.plugins.Page;
import com.xinqi.graduationdesign.entity.SysUser;
import com.xinqi.graduationdesign.service.ISysRoleService;
import com.xinqi.graduationdesign.service.ISysUserService;

import java.util.List;

/**
 * <p>
 * 用户管理相关操作
 * </p>
 *
 *
 * @Author Jack
 * @Date 2016/4/15 15:03
 */
@Controller
@RequestMapping("/sys/perm/user")
public class UserController extends BaseController {

	@Autowired
	private ISysUserService userService;

	@Autowired
	private ISysRoleService roleService;

	@Autowired
	private ISysUserRoleService userRoleService;

	@Permission("2001")
	@RequestMapping("/list")
	public String list(Model model) {
		SysRole sysRole = roleService.selectById(UserType.ADMIN.key());
		model.addAttribute("sysRole",sysRole);
		return "/admin/user/list";
	}

    @Permission("2001")
    @RequestMapping("/edit")
    public String edit(Model model, Long id ) {
    	if ( id != null ) {
			model.addAttribute("user", userService.selectById(id));
		}
		SysRole sysRole = roleService.selectById(UserType.ADMIN.key());
		model.addAttribute("sysRole",sysRole);
        return "/admin/user/edit";
    }
    
	@ResponseBody
	@Permission("2001")
	@RequestMapping("/editUser")
	public String editUser( SysUser user,Model model ) {
		boolean flag = false;
		if ( user != null ) {
			user.setPassword(SaltEncoder.md5SaltEncode(user.getLoginName(), user.getPassword()));
		 	flag = userService.insertOrUpdateUser(user);
		}
		return callback(new AjaxResult(flag));
	}

	@ResponseBody
	@Permission("2001")
	@RequestMapping("/getUserList")
	public String getUserList() {
		Page<SysUser> page = getPage();
		EntityWrapper<SysUser> entityWrapper = new EntityWrapper<SysUser>();
		entityWrapper.where("type=" + UserType.ADMIN.key());
		return jsonPage(userService.selectPage(page, entityWrapper));
	}

	@ResponseBody
	@Permission("2001")
	@RequestMapping("/delUser")
	public String delUser(Long id) {
		Long currentUserId = getCurrentUserId();
		if(id.equals(currentUserId)){
			return callback(new AjaxResult(false,"不能删除当前登录用户"));
		}
		userService.deleteUser(id);
		return callbackSuccess(null);
	}

	@ResponseBody
	@Permission("2001")
	@RequestMapping("/{userId}")
	public SysUser getUser(@PathVariable Long userId) {
		return userService.selectById(userId);
	}


	/**
	 * 设置头像
	 */
	@Permission(action = Action.Skip)
	@RequestMapping(value = "/setAvatar", method = RequestMethod.GET)
	public String setAvatar() {
		return "/admin/user/avatar";
	}
}
