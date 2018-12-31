package com.ws.web.controller;

import java.util.List;
import java.util.Map;

import com.jspsmart.upload.Request;
import com.ws.frame.web.Controller;
import com.ws.web.service.MenuService;
import com.ws.web.service.VersionService;

public class IndexController extends Controller{
	MenuService menuService = new MenuService();
	VersionService versionService = new VersionService();
	
	/**
	 * 进入主页面
	 */
	public void index(){
		List<Map<String, Object>> menus = menuService.query();
		setReqAttr("menus", menus);
		showJsp("jsp/index.jsp");
	}
	
	/**
	 * 密码修改部分
	 */
	public void pwd(){
		showJsp("jsp/user/pwd.jsp");
	}
	
	/**
	 * 打开文件上传页面
	 */
	public void upfile(){
		setReqAttr("version", VersionService.getVersion());
		showJsp("jsp/user/upfile.jsp");
	}
	
	/**
	 * 文件上传的操作
	 */
	public void fileUpload(){
		try {
			Request request = versionService.fileUpload(getServletConfig(), getRequest(), getResponse());
			boolean flag = versionService.updateVersion(request.getParameter("version"));
			if(flag){
				setReqAttr("result", "文件更新成功！");
			} else {
				setReqAttr("result", "文件更新失败！");
			}
		} catch (Exception e) {
			setReqAttr("result", "文件上传失败，请重新上传文件");
			e.printStackTrace();
		}
		setReqAttr("version", VersionService.getVersion());
		showJsp("jsp/user/upfile.jsp");
	}
	
	/**
	 * 文件下载
	 */
	public void download(){
		versionService.download(getServletConfig(), getRequest(), getResponse());
	}

}
