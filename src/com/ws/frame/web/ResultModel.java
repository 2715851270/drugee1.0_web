package com.ws.frame.web;

import java.util.List;
import java.util.Map;

/**
 * 统一返回接口，用于实现列表查询时的数据显示
 * 主要是用来统一兼容jquery-easyui的列表查询方法
 * @author YINGFU
 * @version 1.0bate
 */
public class ResultModel {
	
	public long total;
	public List<Map<String, Object>> rows;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<Map<String, Object>> getRows() {
		return rows;
	}
	public void setRows(List<Map<String, Object>> rows) {
		this.rows = rows;
	}
	public ResultModel() {
		super();
	}
	public ResultModel(long total, List<Map<String, Object>> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	
	

}
