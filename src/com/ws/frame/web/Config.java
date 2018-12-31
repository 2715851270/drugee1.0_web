package com.ws.frame.web;

import java.util.HashMap;
import java.util.Map;

import com.ws.web.controller.IndexController;
import com.ws.web.controller.LoginController;
import com.ws.web.controller.LogsController;
import com.ws.web.controller.MsgController;
import com.ws.web.controller.UserController;

/**
 * controller配置类，用于配置项目中使用到的controller
 * @author YINGFU
 * @version 1.0bate
 */
public class Config {
	public static Map<String, Class<? extends Controller>> controllerMaps = new HashMap<String, Class<? extends Controller>>();

	public static void setControllerMaps(
			Map<String, Class<? extends Controller>> controllerMaps) {
		if(null == Config.controllerMaps || Config.controllerMaps.isEmpty()){
			controllerMaps.put("index.do", IndexController.class);
			controllerMaps.put("login.do", LoginController.class);
			controllerMaps.put("msg.do", MsgController.class);
			controllerMaps.put("user.do", UserController.class);
			controllerMaps.put("logs.do", LogsController.class);
			Config.controllerMaps = controllerMaps;
		}
	}

	public static Map<String, Class<? extends Controller>> getControllerMaps() {
		setControllerMaps(controllerMaps);
		return controllerMaps;
	}
	
	
	
	
	
}
