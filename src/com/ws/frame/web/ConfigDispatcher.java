package com.ws.frame.web;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ws.frame.utils.StrUtils;

/**
 * servlet分派器，处理访问的controller
 * @author YINGFU
 * @version 1.0bate
 */
@SuppressWarnings("serial")
public class ConfigDispatcher extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String controllerName = request.getRequestURI();
		String keys = controllerName.substring(request.getContextPath().length() + 1);
		Class<?> clazz = Config.getControllerMaps().get(keys);
		if(null != clazz){
			String mt = request.getParameter("mt");
			//默认的访问方法名为 index
			mt = StrUtils.isNullOrEmpty(mt) ? "index" : mt;
			try {
				Object object = clazz.newInstance();
				Method method = clazz.getMethod("init", HttpServletRequest.class, HttpServletResponse.class, javax.servlet.ServletConfig.class);
				method.invoke(object, request, response, this.getServletConfig());
				clazz.getDeclaredMethod(mt).invoke(object);
			} catch (Exception e) {
				e.printStackTrace();
				StrUtils.pageNotFound(response);
			}
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
