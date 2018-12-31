package com.ws.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ws.frame.db.DbUtils;
import com.ws.frame.utils.StrUtils;
import com.ws.frame.web.ResultModel;

public class UserService {
	
	/**
	 *  type_system 系统管理员 ; type_drug 药品 ; type_hp 耗材
	 */
	public static final int TYPE_SYSTEM = 9, TYPE_DRUG = 1, TYPE_HP = 2;
		
	/**
	 * 按照条件查询用户的信息
	 * @param usercode	用户账号
	 * @param telphone	电话号码
	 * @param corpid	公司编码
	 * @param corpName	公司名称
	 * @param type		用户类别
	 * @param pageNumber 当前第几页
	 * @param pageSize	每页多少条
	 * @return 标准列表数据接口 total:xx. rows:[{}]
	 */
	public ResultModel query(String usercode, String telphone, String corpid, String corpName, int type, int pageNumber, int pageSize){
		StringBuilder sql = new StringBuilder(" from sys_user where type != 9 and isDel = 0 ");
		List<Object> params = new ArrayList<Object>();
		if(!StrUtils.isNullOrEmpty(usercode)){
			sql.append(" and usercode like ?");
			params.add("%"+usercode+"%");
		}
		if(!StrUtils.isNullOrEmpty(telphone)){
			sql.append(" and telphone like ?");
			params.add("%"+telphone+"%");
		}
		if(!StrUtils.isNullOrEmpty(corpid)){
			sql.append(" and corpid like ?");
			params.add("%"+corpid+"%");
		}
		if(!StrUtils.isNullOrEmpty(corpName)){
			sql.append(" and corpname like ?");
			params.add("%"+corpName+"%");
		}
		if(type != 0){
			sql.append(" and type = ?");
			params.add(type);
		}
		String totalSql = sql.toString();
		String querySql = sql.append(" limit ").append((pageNumber -1) * pageSize).append(",").append(pageSize).toString();
		List<Map<String, Object>> rows = DbUtils.getInstance().query("select *" + querySql, params.toArray());
		long total = DbUtils.getInstance().getTotal("select count(*)" + totalSql, params.toArray());
		return new ResultModel(total, rows);
	}
	
	/**
	 * 用户登陆系统时的验证
	 * @param usercode  用户名
	 * @param password	密码
	 * @param type		用户类别
	 * @return 用户的全部信息
	 */
	public Map<String, Object> systemLogin(String usercode, String password, int type){
		String sql = "select * from sys_user where usercode = ? and password = ? and isDel = 0 and type = ?";
		Object[] params = {usercode, StrUtils.MD5(password), type};
		Map<String, Object> data = DbUtils.getInstance().find(sql, params);
		return data;
	}

	/**
	 * 用户修改密码的功能
	 * @param oldPwd 原始密码
	 * @param newPwd 新密码
	 * @param usercode 操作的账号
	 * @return 返回密码修改中遇到的错误
	 */
	public String updatePassword(String usercode, String oldPwd, String newPwd, int type) {
		Map<String, Object> userMsg = systemLogin(usercode, oldPwd, type);
		if(null == userMsg || userMsg.isEmpty()){
			return "原始密码不正确，请重新输入！";
		} else{
			int res = DbUtils.getInstance().execute("update sys_user set password = ? where id = ?", new Object[]{StrUtils.MD5(newPwd), userMsg.get("id")});
			if(res > 0){
				return "true";
			} else {
				return "操作失败，请重新操作！";
			}
		}
	}
	
	/**
	 * 密码重置功能
	 * @param usercode
	 * @param imsi
	 */
	public boolean resetPassword(String usercode, String imsi){
		int num = DbUtils.getInstance().execute("update sys_user set password = ? where usercode = ? and imsi = ?", new Object[]{usercode, imsi});
		if(num > 0){
			return true;
		} else {
			return false;
		}
	}
	
	

}
