package com.ws.web.controller;

import com.ws.frame.web.Controller;
import com.ws.frame.web.ResultModel;
import com.ws.web.service.UserService;

public class UserController extends Controller {
	UserService userService = new UserService();
	
	/**
	 * 默认加载的方法
	 */
	public void index(){
		showJsp("jsp/user/table.jsp");
	}
	
	/**
	 * 加载用户的列表信息
	 */
	public void table(){
		String usercode = getParam("usercode");
		String telphone = getParam("telphone");
		String corpid = getParam("corpid");
		String corpName = getParam("corpName");
		int type = getInt("type");
		int pageNumber = getInt("page");
		int pageSize = getInt("rows");
		ResultModel result = userService.query(usercode, telphone, corpid, corpName, type, pageNumber, pageSize);
		showJson(result);
	}
	
	/**
	 * 密码修改的功能
	 */
	public void changePwd(){
		String oldPwd = getParam("oldPwd");
		String newPwd = getParam("newPwd");
		String usercode = getParam("usercode");
		String result = userService.updatePassword(usercode, oldPwd, newPwd, UserService.TYPE_SYSTEM);
		showText(result);
	}

}
