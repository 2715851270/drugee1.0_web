package com.ws.web.controller;

import java.util.Map;

import com.ws.frame.utils.Captcha;
import com.ws.frame.web.Controller;
import com.ws.web.service.UserService;


public class LoginController extends Controller {
	UserService userService = new UserService();
	
	/**
	 * 默认是用的方法
	 */
	public void index(){
		removeSession("admin");
		showJsp("jsp/login.jsp");
	}
	
	/**
	 * 生成验证码的方法
	 */
	public void captcha(){
		Captcha captcha = new Captcha();
		captcha.buildCaptcha(getSession(), getResponse());
	}
	
	/**
	 * 登陆的方法
	 */
	public void login(){
		String usercode = getParam("usercode");
		String password = getParam("password");
		String captcha = getParam("captcha");
		if(captcha.equalsIgnoreCase(getSession(Captcha.SESSION_KEY).toString())){
			Map<String, Object> dataMap = userService.systemLogin(usercode, password, UserService.TYPE_SYSTEM);
			if(null == dataMap || dataMap.isEmpty()){
				showText("用户名或密码错误，请重新输入");
			} else {
				dataMap.remove("password");
				setSession("admin", dataMap);
				showText(true);
			}
		} else {
			showText("验证码输入有误，请重新输入");
		}
	}

}
