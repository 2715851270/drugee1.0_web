package com.ws.web.controller;

import com.ws.frame.web.Controller;
import com.ws.frame.web.ResultModel;
import com.ws.web.service.MsgService;

public class MsgController extends Controller {
	MsgService msgService = new MsgService();
	
	/**
	 * 默认加载的方法
	 */
	public void index(){
		showJsp("jsp/msg/table.jsp");
	}
	
	/**
	 * 加载列表展示的页面数据
	 */
	public void table(){
		int page = getInt("page");
		int rows = getInt("rows");
		String title = getParam("title");
		String usercode = getParam("usercode");
		String module = getParam("module");
		ResultModel result = msgService.query(title, usercode, module, page, rows);
		showJson(result);
	}
	
	/**
	 * 消息推送的方法
	 */
	public void toPush(){
		String ids = getParam("ids");
		boolean flag = msgService.toPush(ids);
		showText(flag);
	}
	

}
