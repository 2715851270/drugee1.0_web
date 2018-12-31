package com.ws.app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ws.MessageImpl;

import com.google.gson.JsonObject;
import com.ws.app.entity.AppResult;
import com.ws.app.entity.User;
import com.ws.frame.db.DbUtils;
import com.ws.frame.utils.StrUtils;
import com.ws.frame.utils.WsUtils;
import com.ws.frame.web.Controller;
import com.ws.web.service.LogsService;
import com.ws.web.service.VersionService;

/**
 * 手机端接口调用的实现类，
 * @author YINGFU
 * @version 1.0bate
 */
public class PhoneImpl implements IPhone {
	public static SimpleDateFormat sdf = MessageImpl.sdf;
	//消息已读 IS_READ, 消息未读 UN_READ, 状态正常 STATE_OK 
	private final int IS_READ = 0, UN_READ = 1 , STATE_OK = 0;

	/**
	 * app客户端用户登陆
	 */
	@Override
	public String systemLogin(String usercode, String password , String imsi) {
		String sql = "select * from sys_user where usercode = ? and password = ? and imsi = ?";
		Map<String,Object> result = DbUtils.getInstance().find(sql, new Object[]{usercode, password, imsi});
		AppResult appResult = new AppResult();
		if(null == result || result.isEmpty()){
			appResult.setData("当前手机卡下的用户名或密码错误!");
		} else {
			result.remove("password");
			JsonObject param = new JsonObject();
			param.addProperty("IMEI", StrUtils.isNullOrEmpty(result.get("imei"))? "" : result.get("imei").toString());
			param.addProperty("IMSI", StrUtils.isNullOrEmpty(result.get("imsi"))? "" : result.get("imsi").toString());
			param.addProperty("DRUG_OR_MC", result.get("type").toString());
			try {
				//调用平台的webservice,并获得返回值， 只有在返回值为{2:用户正常}
				String wsResult = WsUtils.visit("MC_CheckUserStatus", new Object[]{param.toString()});
				HashMap<?, ?> resMap = Controller.gson.fromJson(wsResult, HashMap.class);
				Object key = resMap.keySet().toArray()[0];
				if("2".equals(key)){
					appResult.setResult(true);
					appResult.setData(result);
				} else {
					appResult.setData(resMap.get(key));
				}
			} catch (Exception e) {
				System.out.println("请保持平台与服务器之间的网络畅通。。。");
				appResult.setData("网络错误，请检查网络重新登陆！");
			}
		}
		LogsService.save(LogsService.TAG_APP, "用户名为【"+usercode+"】,手机卡为【"+imsi+"】的用户登陆了系统"+(appResult.getResult()?"登陆成功":"登陆失败"), sdf.format(new Date()));
		return Controller.gson.toJson(appResult);
	}

	/**
	 * app客户端密码修改
	 */
	@Override
	public String updatePassword(String usercode, String oldPwd, String newPwd, String imsi) {
		String sql = "update sys_user set password = ? where usercode = ? and password = ? and imsi = ?";
		int num = DbUtils.getInstance().execute(sql, new Object[]{newPwd, usercode, oldPwd, imsi});
		AppResult appResult = new AppResult();
		if(num >0){
			appResult.setResult(true);
		} else {
			appResult.setData("原密码输入错误，请重新输入！");
		}
		LogsService.save(LogsService.TAG_APP, "用户名为【"+usercode+"】,手机卡为【"+imsi+"】的用户修改了密码，"+(appResult.getResult()?"修改成功":"修改失败"),sdf.format(new Date()));
		return Controller.gson.toJson(appResult);
	}

	/**
	 * app客户端消息推送
	 */
	@Override
	public String toPushMsg(String usercode, String imsi) {
		StringBuilder sql = new StringBuilder(" and usercode = ? and imsi = ? order by time desc");
		Object[] params = new Object[]{usercode, imsi};
		List<Map<String, Object>> datas = DbUtils.getInstance().query("SELECT * FROM message WHERE pushed IS FALSE" + sql.toString(), params);
		AppResult appResult = new AppResult();
		appResult.setResult(true);
		appResult.setData(datas);
		if(null != datas && !datas.isEmpty()){
			DbUtils.getInstance().execute("update message set pushed = TRUE where 1 = 1" + sql.toString(), params);
			LogsService.save(LogsService.TAG_APP, "用户名为【"+usercode+"】,手机卡为【"+imsi+"】的用户推送了消息", sdf.format(new Date()));
		}
		return Controller.gson.toJson(appResult);
	}

	/**
	 * app用户注册功能
	 */
	@Override
	public String userRegister(String userJson) {
		User user = Controller.gson.fromJson(userJson, User.class);
		AppResult appResult = new AppResult();
		Map<String, Object> userMap = DbUtils.getInstance().find("select * from sys_user where usercode = ?", new Object[]{user.getUsercode()});
		if(null != userMap && !userMap.isEmpty()){
			appResult.setData("当前用户名已存在，请重新注册！");
		} else{
			try {
				//webservice接口的参数对应为  string IMEI, string IMSI, string ORG_CODE, string ORG_NAME, string USERNAME, string MOBILE, string DRUG_OR_MC
				Object[] params = {user.getImei(), user.getImsi(), user.getCorpid(), user.getCorpname(), user.getUsercode(), user.getTelphone(), user.getType()};
				String result = WsUtils.visit("UserRegister", params);
				HashMap<?, ?> resMap = Controller.gson.fromJson("{" + result + "}", HashMap.class);
				Object key = resMap.keySet().toArray()[0];
				if("1".equals(key)){
					String sql = "insert into sys_user(usercode, password, imsi, imei, telphone, corpid, corpname, reason, type, isDel) values (?,?,?,?,?,?,?,?,?,?)";
					params = new Object[]{user.getUsercode(), user.getPassword(), user.getImsi(), user.getImei(), user.getTelphone(), user.getCorpid(), user.getCorpname(),user.getReason(), user.getType(), STATE_OK};
					int num = DbUtils.getInstance().execute(sql, params);
					if(num > 0){
						appResult.setResult(true);
					} else {
						appResult.setData("用户注册失败，请重新注册！");
					}
				} else {
					appResult.setData(resMap.get(key));
				}
			} catch (Exception e) {
				System.out.println("请保持平台与服务器之间的网络畅通。。。");
				appResult.setData("网络访问错误，请检查网络重新操作！");
			}
		}
		LogsService.save(LogsService.TAG_APP, "手机卡为【"+user.getImsi()+"】,的用户注册了用户名为【"+user.getUsercode()+"】的账户, " +(appResult.getResult()? "注册成功" : "注册失败"), sdf.format(new Date()));
		return Controller.gson.toJson(appResult);
	}

	/**
	 * 用户app设备变更
	 */
	@Override
	public String deviceChange(String userJson) {
		User user = Controller.gson.fromJson(userJson, User.class);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("IMEI", user.getImei());
		jsonObject.addProperty("IMSI", user.getImsi());
		AppResult appResult = new AppResult();
		try {
			String wsResult = WsUtils.visit("MC_ChangeUserIMEI", new Object[]{jsonObject.toString()});
			HashMap<?, ?> resMap = Controller.gson.fromJson(wsResult, HashMap.class);
			Object key = resMap.keySet().toArray()[0];
			if("1".equals(key)){	// 当{"1":"IMEI变更成功！"} 时设备，药企平台的内设备变更成功
				String sql = "update sys_user set imei = ?, reason = ? where imsi = ? and usercode = ? and password = ?";
				int num = DbUtils.getInstance().execute(sql, new Object[]{user.getImei(), user.getReason(), user.getImsi(), user.getUsercode(), user.getPassword()});
				if(num > 0){
					appResult.setResult(true);
				} else {
					appResult.setData("设备变更失败，请重新操作！");
				}
			} else {
				appResult.setData(resMap.get(key));
			}
		} catch (Exception e) {
			System.out.println("请保持平台与服务器之间的网络畅通。。。");
			appResult.setData("网络错误，请检查网络重新操作！");
		}
		LogsService.save(LogsService.TAG_APP, "手机卡为【"+ user.getImsi() +"】,的用户【"+user.getUsercode()+"】变更了新设备【"+user.getImei()+"】，"+(appResult.getResult()?"变更成功":"变更失败"), sdf.format(new Date()));
		return Controller.gson.toJson(appResult);
	}

	/**
	 * 用户未读信息的数量， 如果某个模块数量为0 ，则这个模块不进入数据列表中
	 */
	@Override
	public String unReadCount(String usercode, String imsi) {
		String sql = "SELECT module ,COUNT(*) as count FROM message WHERE isDel = ? and usercode = ? and imsi = ?  GROUP BY module";
		List<Map<String, Object>> datas = DbUtils.getInstance().query(sql, new Object[]{UN_READ, usercode, imsi});
		AppResult appResult = new AppResult();
		appResult.setResult(true);
		appResult.setData(datas);
		LogsService.save(LogsService.TAG_APP, "手机卡为【"+ imsi +"】,用户名为【"+usercode+"】的用户加载了未读消息数量", sdf.format(new Date()));
		return Controller.gson.toJson(appResult);
	}

	/**
	 * 用户阅读了消息
	 */
	@Override
	public String toReadMsg(int msgId, String usercode, String imsi) {
		int num = DbUtils.getInstance().execute("update message set isDel = ? where id = ?", new Object[]{IS_READ, msgId});
		AppResult appResult = new AppResult();
		if(num > 0){
			appResult.setResult(true);
		}
		LogsService.save(LogsService.TAG_APP, "手机卡为【"+ imsi +"】,用户名为【"+usercode+"】的用户阅读了Id号为【"+msgId+"】的消息", sdf.format(new Date()));
		return Controller.gson.toJson(appResult);
	}

	/**
	 * 加载某个模块内的该用户的数据信息
	 */
	@Override
	public String moduleMsg(String usercode, String imsi, String module, int pageNumber, int pageSize) {
		StringBuilder sql = new StringBuilder(" from message where usercode = ? and imsi = ? and module = ? order by time desc");
		sql.append(" LIMIT ").append((pageNumber - 1) * pageSize).append(",").append(pageSize);
		Object[] params = {usercode, imsi, module};
		List<Map<String, Object>> datas = DbUtils.getInstance().query("select *" + sql.toString(), params);
		AppResult appResult = new AppResult();
		appResult.setResult(true);
		appResult.setData(datas);
		LogsService.save(LogsService.TAG_APP, "手机卡为【"+ imsi +"】,用户名为【"+usercode+"】的用户查阅了【"+module+"】模块下的消息", sdf.format(new Date()));
		return Controller.gson.toJson(appResult);
	}

	/**
	 * 查询所有的未读的信息
	 */
	@Override
	public String homeMsg(String usercode, String imsi) {
		Object[] params = {usercode, imsi, usercode, imsi};
		String sql = "SELECT * FROM ((SELECT *, 'identify' FROM message WHERE 1 = 2) " +
						"UNION ALL " +
						"(SELECT *, 'NOTICE' FROM message WHERE module IN ('通知公告','图片新闻','办事指南') AND usercode = ? AND imsi = ? LIMIT 0, 3) " +
						"UNION ALL " + 
						"(SELECT *, 'MESSAGE' FROM message WHERE module NOT IN ('通知公告','图片新闻','办事指南') AND usercode = ? AND imsi = ? LIMIT 0, 20)) " + 
						"AS message " +
						"ORDER BY TIME DESC, isDel DESC ";
		List<Map<String, Object>> datas = DbUtils.getInstance().query(sql, params);
		AppResult appResult = new AppResult();
		appResult.setResult(true);
		appResult.setData(datas);
		LogsService.save(LogsService.TAG_APP, "手机卡为【"+ imsi +"】,用户名为【"+usercode+"】的用户进入了APP主页，并查询了数据", sdf.format(new Date()));
		return Controller.gson.toJson(appResult);
	}

	/**
	 * 获取当前服务器上的版本号
	 */
	@Override
	public String getVersion() {
		return VersionService.getVersion();
	}
	
}
