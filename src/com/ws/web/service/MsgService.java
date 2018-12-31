package com.ws.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ws.frame.db.DbUtils;
import com.ws.frame.utils.StrUtils;
import com.ws.frame.web.ResultModel;

public class MsgService {

	/**
	 * 按条件加载列表显示的数据
	 * @param title		信息标题
	 * @param usercode	推送用户
	 * @param module	信息模块
	 * @param pageNumber 第几页
	 * @param pageSize	每页多少条记录
	 * @return 标准数据接口，total:xxx , rows:[{}]
	 */
	public ResultModel query(String title, String usercode, String module, int pageNumber, int pageSize) {
		StringBuilder sql = new StringBuilder(" from message where 1 = 1");
		List<Object> params = new ArrayList<Object>();
		if(!StrUtils.isNullOrEmpty(title)){
			sql.append(" and title like ?");
			params.add("%"+title+"%");
		}
		if(!StrUtils.isNullOrEmpty(usercode)){
			sql.append(" and usercode like ?");
			params.add("%"+usercode+"%");
		}
		if(!StrUtils.isNullOrEmpty(module)){
			sql.append(" and module like ?");
			params.add("%"+module+"%");
		}
		String totalSql = sql.toString();
		String querySql = sql.append(" limit ").append((pageNumber-1)*pageSize).append(",").append(pageSize).toString();
		List<Map<String, Object>> rows = DbUtils.getInstance().query("select *" + querySql, params.toArray());
		long total = DbUtils.getInstance().getTotal("select count(*)" + totalSql, params.toArray());
		return new ResultModel(total, rows);
	}

	/**
	 * 消息推送的方法
	 * @param ids
	 */
	public boolean toPush(String ids) {
		String[] params = ids.split(",");
		int result = DbUtils.getInstance().execute("update message set pushed = false, isDel = 2 where id in("+ids.replaceAll("\\w+", "?")+")", params);
		if(result > 0){
			return true;
		} else {
			return false;
		}
	}

}
