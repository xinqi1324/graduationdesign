package com.xinqi.graduationdesign.common;

import javax.servlet.http.HttpServletRequest;



import com.xinqi.framework.spring.SpringContextHolder;
import com.xinqi.kisso.SSOHelper;
import com.xinqi.kisso.Token;
import com.xinqi.graduationdesign.service.ISysPermissionService;


public class SSOPermissionTool {

	/**
	 * 按钮级别权限判断
	 */
	public boolean isActionable(String permission) {
		HttpServletRequest request = HttpHelper.getHttpServletRequest();
		Token token = SSOHelper.attrToken(request);
		if ( token == null ) {
			return false;
		}
		//数据库判断按钮权限是否合法，生产环境此处建议加缓存判断逻辑。
		ISysPermissionService psi = SpringContextHolder.getBean(ISysPermissionService.class);
		return psi.isActionable(token, permission);
	}
	
}
