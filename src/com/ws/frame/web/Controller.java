package com.ws.frame.web;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.ws.frame.utils.StrUtils;

/**
 * controller的基类。
 * @author YINGFU
 * @version 1.0bate
 */
public class Controller{
	
	HttpServletRequest request;
	HttpServletResponse response;
	ServletConfig servletConfig;
	public static final Gson gson = new Gson();
	
	/**
	 * 显示Json字符串， bean类型数据自动转化
	 * @param bean
	 */
	public void showJson(Object bean){
		showText(gson.toJson(bean));
	}
	
	/**
	 * 显示文本
	 */
	public void showText(Object text){
		try {
			Writer writer = response.getWriter();
			writer.write(text.toString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 重定向到一个jsp页面
	 * @param path
	 */
	public void showJsp(String path){
		try {
			request.getRequestDispatcher("WEB-INF/"+path).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 页面跳转到一个jsp
	 */
	public void turnToJsp(String path){
		try {
			response.sendRedirect(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 数据初始化， 并被reques和response赋值
	 * @param request	
	 * @param response
	 */
	public void init(HttpServletRequest request, HttpServletResponse response, ServletConfig servletConfig) {
		this.request = request;
		this.response = response;
		this.servletConfig = servletConfig;
	}
	
	/**
	 * 获取字符串类型的参数的数据
	 * @param name
	 * @return string字符串
	 */
	public String getParam(String name){
		return request.getParameter(name);
	}
	
	/**
	 * 获取int类型的参数的数据
	 * @param name
	 * @return int 
	 */
	public int getInt(String name){
		String param = request.getParameter(name);
		return StrUtils.isNullOrEmpty(param) ? 0 : Integer.valueOf(param);
	}
	
	/**
	 * 获取数组类型的数据
	 * @param name
	 */
	public String[] getArray(String name){
		return request.getParameterValues(name);
	}
	
	/**
	 * 设置request属性
	 * @param key
	 * @param value
	 */
	public void setReqAttr(String key, Object value){
		request.setAttribute(key, value);
	}
	
	/**
	 * 获取request属性
	 * @param key
	 * @return
	 */
	public Object getReqAttr(String key){
		return request.getAttribute(key);
	}
	
	/**
	 * 设置session值
	 * @param key
	 * @param value
	 */
	public void setSession(String key, Object value){
		request.getSession(true).setAttribute(key, value);
	}
	
	/**
	 * 移除session值
	 * @param key
	 */
	public void removeSession(String key){
		request.getSession().removeAttribute(key);
	}
	
	/**
	 * 获取session值
	 * @param key
	 */
	public Object getSession(String key){
		return request.getSession().getAttribute(key);
	}
	
	/**
	 * 获取当前的session
	 */
	public HttpSession getSession(){
		return request.getSession();
	}
	
	/**
	 * 获取当前的httpServletRequest
	 */
	public HttpServletRequest getRequest(){
		return request;
	}
	
	/**
	 * 获取当前的httpServletResponse
	 */
	public HttpServletResponse getResponse(){
		return response;
	}
	
	/**
	 * 获取当前的servlet
	 */
	public ServletConfig getServletConfig(){
		return servletConfig;
	}
}
