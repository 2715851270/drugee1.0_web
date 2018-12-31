package com.ws.web.controller;

import com.ws.frame.web.Controller;
import com.ws.frame.web.ResultModel;
import com.ws.web.service.LogsService;

/**
 * 日志功能模块的controller
 * @author YINGFU
 * @version 1.0bate
 */
public class LogsController extends Controller {
	LogsService logsService = new LogsService();
	
	/**
	 * 默认的访问方法
	 */
	public void index(){
		showJsp("jsp/logs/table.jsp");
	}
	
	/**
	 * 按条件查询日志的列表数据
	 */
	public void table(){
		String time = getParam("time");
		String tag = getParam("tag");
		String info = getParam("info");
		int pageNumber = getInt("page");
		int pageSize = getInt("rows");
		ResultModel result = logsService.query(time, tag, info, pageNumber, pageSize);
		showJson(result);
	}

	
}
