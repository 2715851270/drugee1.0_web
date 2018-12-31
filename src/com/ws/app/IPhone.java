package com.ws.app;

/**
 * 手机客户端调用的webservice接口
 * 统一返回的数据格式为 {res:xxx};
 * @author YINGFU
 * @version 1.0bate
 */
public interface IPhone {
	
	/**
	 * app手机客户端用户登陆功能接口
	 * @param usercode 用户名
	 * @param password 密码
	 * @return appResult
	 */
	public String systemLogin(String usercode, String password, String imsi);
	
	/**
	 * app手机客户端用户密码修改功能接口
	 * @param usercode  用户名
	 * @param oldPwd	原始密码
	 * @param newPwd	新密码
	 * @return appResult
	 */
	public String updatePassword(String usercode, String oldPwd, String newPwd, String imsi);
	
	/**
	 * app手机客户端消息推送功能接口
	 * @param usercode 用户名
	 * @param module   消息模块
	 * @return appResult
	 */
	public String toPushMsg(String usercode, String imsi);
	
	/**
	 * app客户端用户注册接口
	 * @param userJson 用户相关数据的json字符串
	 * @return appResult
	 */
	public String userRegister(String userJson);
	
	/**
	 * app客户端的设备更换，imsi是手机卡识别码， 根据手机卡识别码更改手机设备的imei
	 * @param imsi 手机卡识别码
	 * @param imei 手机设备识别码
	 * @param usercode 登陆账号
	 * @param password 登陆密码
	 * @param reason   变更原因
	 * @return 是否变更成功的提示
	 */
	public String deviceChange(String userJson);
	
	/**
	 * 根据用户当前的手机卡和用户名匹配，查询各个模块未读信息的数量
	 * @param usercode	用户名
	 * @param imsi		手机卡
	 * @return			推送数据 
	 */
	public String unReadCount(String usercode, String imsi);
	
	/**
	 * app手机用户阅读了某条数据
	 * @param usercode 	用户名
	 * @param imsi		手机卡
	 * @return 			阅读后的返回数据
	 */
	public String toReadMsg(int msgId, String usercode, String imsi);
	
	/**
	 * 查询某个模块下的全部消息数据
	 * @param usercode 	用户名
	 * @param imsi	   	手机卡
	 * @param module	模块
	 * @return 			模块内的全部消息记录
	 */
	public String moduleMsg(String usercode, String imsi, String module, int pageNumber, int pageSize);
	
	/**
	 * 查询消息模块下的全部未读消息
	 * @param usercode 	用户名
	 * @param imsi		手机卡
	 * @return 			全部的未读消息
	 */
	public String homeMsg(String usercode, String imsi);
	
	/**
	 * 获取服务器上最新app的版本号
	 * @return 当前服务器的版本号
	 */
	public String getVersion();
	

}
