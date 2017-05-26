package com.xinqi.graduationdesign.controller.sys;

import com.xinqi.framework.controller.SuperController;
import com.xinqi.graduationdesign.common.enums.StatusType;
import com.xinqi.kisso.SSOConfig;
import com.xinqi.kisso.SSOHelper;
import com.xinqi.kisso.SSOToken;
import com.xinqi.kisso.common.encrypt.SaltEncoder;
import com.xinqi.kisso.common.util.RandomUtil;
import com.xinqi.kisso.web.waf.request.WafRequestWrapper;
import com.xinqi.graduationdesign.common.MyCaptcha;
import com.xinqi.graduationdesign.entity.SysUser;
import com.xinqi.graduationdesign.service.ISysPermissionService;
import com.xinqi.graduationdesign.service.ISysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xinqi.kisso.annotation.Action;
import com.xinqi.kisso.annotation.Login;
import com.xinqi.kisso.annotation.Permission;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 首页
 * </p>
 * 
 * @author likun
 * @Date 2016-04-13
 */
@Controller
@RequestMapping("/admin")
public class AdminIndexController extends SuperController {
	//锁定用户标记
	private static final String LOCKSCREEN_USER_FLAG = "LockscreenUserFlag";

	@Autowired
	protected ISysUserService userService;

	@Autowired
	private ISysPermissionService permissionService;

	/**
	 * 首页
	 */
	@Permission(action = Action.Skip)
	@RequestMapping("/")
	public String root(Model model) {
		return "/index";
	}

	/**
	 * 首页
	 */
	@Permission(action = Action.Skip)
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("currentUser", userService.selectById(getCurrentUserId()));
		model.addAttribute("menuList", permissionService.selectMenuVOByUserId(getCurrentUserId()));
		return "/admin/index";
	}

	/**
	 * 登录
	 */
	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping("/login")
	public String login(Model model) {
		if (isPost()) {
			String errorMsg = "用户名或密码错误";
			/**
			 * 过滤 XSS SQL 注入
			 */
			WafRequestWrapper wr = new WafRequestWrapper(request);
			String ctoken = wr.getParameter("ctoken");
			String captcha = wr.getParameter("captcha");
			if (StringUtils.isNotBlank(ctoken)
					&& StringUtils.isNotBlank(captcha)
					&& MyCaptcha.getInstance().verification(wr, ctoken, captcha)) {
				String loginName = wr.getParameter("loginName");
				String password = wr.getParameter("password");
				/**
				 * <p>
				 * 用户存在，签名合法，登录成功
				 * <br>
				 * 进入后台
				 * </p>
				 */
				SysUser user = userService.selectByLoginName(loginName);
				//账号冻结
				if(user.getStatus() == StatusType.FROZEN.key()){
					errorMsg = "账号已被冻结！请联系管理员";
					model.addAttribute("errorMsg", errorMsg);
					model.addAttribute(MyCaptcha.CAPTCHA_TOKEN, RandomUtil.get32UUID());
					return "admin/login";
				}
				if (user != null && SaltEncoder.md5SaltValid(loginName, user.getPassword(), password)) {
					SSOToken st = new SSOToken(request);
					st.setId(user.getId());
					st.setData(loginName);
					//TODO SSOToken 中getObject方法无法获取
					st.setApp(user.getNickName());
					// 记住密码，设置 cookie 时长 1 周 = 604800 秒
					String rememberMe = wr.getParameter("rememberMe");
					if ("on".equals(rememberMe)) {
						request.setAttribute(SSOConfig.SSO_COOKIE_MAXAGE, 604800);
					}

					SSOHelper.setSSOCookie(request, response, st, true);
					return redirectTo("/admin/index.html");
				}
			} else {
				errorMsg = "验证码错误";
			}
			model.addAttribute("errorMsg", errorMsg);
		}
		model.addAttribute(MyCaptcha.CAPTCHA_TOKEN, RandomUtil.get32UUID());
		return "admin/login";
	}

	
	/**
	 * 退出
	 */
	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping("/logout")
	public String logout(Model model) {
		SSOHelper.clearLogin(request, response);
		return redirectTo("/admin/login.html");
	}

	/**
	 * 锁定
	 */
	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping("/lockscreen")
	public String lockscreen(Model model, String password) {
		HttpSession session = request.getSession();
		String loginName = (String) session.getAttribute(LOCKSCREEN_USER_FLAG);
		if (StringUtils.isBlank(loginName)) {
			SSOToken st = SSOHelper.getToken(request);
			if (st == null) {
				/* 未登录 */
				return redirectTo("/admin/login.html");
			}
			loginName = st.getData();
			session.setAttribute(LOCKSCREEN_USER_FLAG, loginName);;
			SSOHelper.clearLogin(request, response);
		} else if (StringUtils.isNotBlank(password) && isPost()) {
			/*
			 * 锁定页面登录
			 */
			SysUser user = userService.selectByLoginName(loginName);
			if (user != null && SaltEncoder.md5SaltValid(loginName, user.getPassword(), password)) {
				/*
				 * 登录成功，进入后台
				 */
				SSOToken st = new SSOToken(request);
				st.setId(user.getId());
				st.setData(loginName);
				SSOHelper.setSSOCookie(request, response, st, true);
				return redirectTo("/admin/index.html");
			}
		}

		model.addAttribute("loginName", loginName);
		return "admin/lockscreen";
	}

}
