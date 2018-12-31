package com.ws.web.service;

import java.util.List;
import java.util.Map;

import com.ws.frame.db.DbUtils;

/**
 * 左侧功能树列表加载类，该类只负责加载后台系统的左侧功能树
 * @author YINGFU
 * @version 1.0bate
 */
public class MenuService {
	
	/**
	 * 查询全部的menu信息
	 * @return menus
	 */
	public List<Map<String, Object>> query(){
		List<Map<String, Object>> menus = DbUtils.getInstance().query("select * from sys_menu order by id desc", null);
		return menus;
	}

}
