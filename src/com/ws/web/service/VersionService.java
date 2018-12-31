package com.ws.web.service;

import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.ws.frame.db.DbUtils;
import com.ws.frame.utils.StrUtils;

public class VersionService {
	private static String version ; 
	{
		if(StrUtils.isNullOrEmpty(version)){
			version = getVersion();
		}
	}
	
	/**
	 * 获取当前系统的版本号
	 */
	public static String getVersion(){
		if(StrUtils.isNullOrEmpty(version)){
			Map<String, Object> resMap = DbUtils.getInstance().find("select * from sys_version", null);
			if(null == resMap || resMap.isEmpty() || null == resMap.get("version")){
				version = "";
			} else {
				version = resMap.get("version").toString();
			}
		}
		return version;
	}
	
	/**
	 * 实现文件上传的功能, 并返回上传的参数
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	public com.jspsmart.upload.Request fileUpload(ServletConfig servletConfig ,HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		//实例化对象
		SmartUpload su=new SmartUpload();
		//初始化
		su.initialize(servletConfig,request,response);
		//设定上传限制
		//1，限制每个文件上传最大字节长度
		//su.setMaxFileSize(200*1000);//200K
		//设定允许上传的文件（通过扩展名限制），允许上传doc，txt，jpg，bmp，gif文件
		su.setAllowedFilesList("apk");
		//执行上传
		su.upload();
		//取得传来的文件和参数
		//参数
		//String fdesc=su.getRequest().getParameter("file");
		//文件
		File f=su.getFiles().getFile(0);
		//文件扩展名
		//String extname=f.getFileExt();
		//获得唯一文件名，扩展名保留
		//String newfile="sdyy."+extname;
		String newfile = "sdyy.apk";
		//保存到webroot下的upload目录中
		f.saveAs("/"+newfile,File.SAVEAS_VIRTUAL);
		return su.getRequest();
	}

	/**
	 * 更新数据库的文件内容
	 * @param version
	 */
	public boolean updateVersion(String version) {
		int num = DbUtils.getInstance().execute("update sys_version set version = ?", new Object[]{version});
		if(num > 0){
			VersionService.version = version;
			return true;
		} else{
			return false;
		}
	}
	
	/**
	 * 文件下载
	 * @param servletConfig
	 * @param request
	 * @param response
	 */
	public void download(ServletConfig servletConfig ,HttpServletRequest request, HttpServletResponse response){
		try{
			request.setCharacterEncoding("utf-8");
			//实例化对象
			SmartUpload su=new SmartUpload();
			//初始化
			su.initialize(servletConfig,request,response);
			//禁止浏览器打开
			su.setContentDisposition(null);
			su.downloadFile("/sdyy.apk");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
