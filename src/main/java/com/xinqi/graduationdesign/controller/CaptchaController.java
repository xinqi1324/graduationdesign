package com.xinqi.graduationdesign.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xinqi.kisso.annotation.Action;
import com.xinqi.kisso.annotation.Login;
import com.xinqi.kisso.annotation.Permission;
import com.xinqi.graduationdesign.common.MyCaptcha;

/**
 * <p>
 * 验证码服务
 * </p>
 * 
 * @author likun
 * @Date 2016-01-06
 */
@Controller
@RequestMapping("/captcha")
public class CaptchaController extends BaseController {

	/**
	 * 生成图片
	 */
	@ResponseBody
	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping("/image")
	public void image(String ctoken) {
		try {
			MyCaptcha.getInstance().generate(request, response.getOutputStream(), ctoken);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
