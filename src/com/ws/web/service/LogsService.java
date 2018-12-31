package com.ws.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ws.frame.db.DbUtils;
import com.ws.frame.utils.StrUtils;
import com.ws.frame.web.ResultModel;

public class LogsService {
	public static final String TAG_SYS = "平台推送记录", TAG_APP = "用户使用记录";

	/**
	 * 按条件查询系统操作日志的信息
	 * @param time 日志时间
	 * @param tag  日志标识
	 * @param info 日志内容
	 * @param pageNumber	当前页
	 * @param pageSize		每页几条数据
	 * @return 统一标准接口， total:xx, rows:[{}]
	 */
	public ResultModel query(String time, String tag, String info, int pageNumber,
			int pageSize) {
		StringBuilder sql = new StringBuilder(" from sys_logs where 1 = 1 ");
		List<Object> params = new ArrayList<Object>();
		if(!StrUtils.isNullOrEmpty(time)){
			sql.append(" and TIME like ?");
			params.add("%"+time+"%");
		}
		if(!StrUtils.isNullOrEmpty(tag)){
			sql.append(" and TAG like ?");
			params.add("%"+tag+"%");
		}
		if(!StrUtils.isNullOrEmpty(info)){
			sql.append(" and INFO like ?");
			params.add("%"+info+"%");
		}
		String totalSql = sql.toString();
		sql.append(" order by TIME DESC ");
		String querySql = sql.append(" LIMIT ").append((pageNumber - 1) * pageSize).append(",").append(pageSize).toString();
		List<Map<String, Object>> rows = DbUtils.getInstance().query("select *" + querySql, params.toArray());
		long total = DbUtils.getInstance().getTotal("select count(*) " + totalSql, params.toArray());
		return new ResultModel(total, rows);
	}
	
	/**
	 * 单条日志保存
	 * @param tag	日志标签
	 * @param info	日志内容
	 */
	public static void save(String tag, String info, String time){
		String sql  = "insert into sys_logs(id ,TAG, INFO, TIME) values(REPLACE(UUID(), '-',''), ?,?,?)";
		Object[] params = {tag, info, time};
		DbUtils.getInstance().execute(sql, params);
	}
	
	/**
	 * 批量保存log日志
	 * @param params [0]日志标签， [1] 日志内容 [3] 日志时间
	 */
	public static void addBatch(List<Object[]> params){
		String sql  = "insert into sys_logs(id, TAG, INFO, TIME) values(REPLACE(UUID(), '-',''), ?,?,?)";
		DbUtils.getInstance().executeBatch(sql, params);
	}
	
	

}
