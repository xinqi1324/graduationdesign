package com.xinqi.graduationdesign.controller.sys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xinqi.framework.controller.AjaxResult;
import com.xinqi.graduationdesign.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xinqi.kisso.annotation.Permission;
import com.baomidou.mybatisplus.plugins.Page;
import com.xinqi.graduationdesign.entity.SysRole;
import com.xinqi.graduationdesign.service.ISysPermissionService;
import com.xinqi.graduationdesign.service.ISysRolePermissionService;
import com.xinqi.graduationdesign.service.ISysRoleService;
import com.xinqi.graduationdesign.service.ISysUserRoleService;

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
@RequestMapping("/sys/perm/role")
public class RoleController extends BaseController {

	@Autowired
	private ISysRoleService roleService;
	
	@Autowired
	private ISysPermissionService permissionService;
	
	@Autowired
	private ISysRolePermissionService rolePermissionService;

	@Autowired
	private ISysUserRoleService userRoleService;


	@Permission("2002")
	@RequestMapping("/list")
	public String list( Model model ) {
		return "/admin/role/list";
	}


	@ResponseBody
	@Permission("2002")
	@RequestMapping("/getRoleList")
	public String getUserList() {
		Page<SysRole> page = getPage();
		return jsonPage(roleService.selectPage(page, null));
	}


	@ResponseBody
	@Permission("2003")
	@RequestMapping("/delete")
	public String delete(Long id ) {
		boolean rlt;
		String msg="";
		boolean exist = userRoleService.existRoleUser(id);
		if ( exist ) {
			rlt = false;
			msg = "删除失败，该角色存在关联的用户！";
		}else {
			rlt = roleService.deleteById(id);
		}
		return callback(new AjaxResult(rlt,msg));
	}


	@Permission("2002")
	@RequestMapping("/edit")
	public String edit( Model model, Long id ) {
		if ( id != null ) {
			model.addAttribute("role", roleService.selectById(id));
		}
		return "/admin/role/edit";
	}


	@ResponseBody
	@Permission("2002")
	@RequestMapping("/editRole")
	public String editRole( SysRole role ) {
		boolean rlt = false;
		if ( role != null ) {
			if ( role.getId() != null ) {
				rlt = roleService.updateById(role);
			} else {
				rlt = roleService.insertSelective(role);
			}
		}
		return callback(new AjaxResult(rlt,null));
	}
	
	/**
	 * 跳转到权限分配页面
	 * @param model
	 * @param id
	 * @return
	 */
	@Permission("2003")
	@RequestMapping("/assigning")
	public String assigning( Model model, Long id ) {
		model.addAttribute("roleId", id);
		return "/admin/role/assigning";
	}
	
	/**
	 * 获取权限树
	 * @param model
	 * @param roleId
	 * @return
	 */
	@Permission("2003")
	@RequestMapping("/right")
	@ResponseBody
	public String right( Model model,
			@RequestParam(value="roleId",required = true)  Long roleId ) {
		List<com.xinqi.graduationdesign.entity.SysPermission> list = permissionService.selectList(null);
		List<Long> roleRightList = rolePermissionService.selecPermissionIdsByRoleId(roleId);
		List<Map<String,String>> rightList = new ArrayList<Map<String,String>>();
		for(com.xinqi.graduationdesign.entity.SysPermission r : list){
			Map<String,String> map = new HashMap<String,String>();
			map.put("id", r.getId().toString());
			map.put("pId", r.getPid().toString());
			map.put("name", r.getTitle());
			//默认展开树
			map.put( "open", "true");
			//如果角色已有该权限，则默认选中
			if(roleRightList.contains(r.getId())){
				map.put( "checked", "true");
			}
			rightList.add(map);
		}
		return JSONObject.toJSONString(rightList);
	}
	
	/**
	 * 更新权限列表
	 * @param rights
	 * @return
	 * @throws IOException 
	 */
	@Permission("2003")
	@RequestMapping("updateRoleRight")
	@ResponseBody
	public String updateRoleRight(HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value="roleId",required = false) Long roleId,
			@RequestParam(value="rights",required = false) String rights)  throws Exception{
		boolean rlt = roleService.updateRoleRight(roleId,rights);
		return callback(new AjaxResult(rlt,null));
	}
}
