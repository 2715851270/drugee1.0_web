package ws;

public interface IMessage {
	
	/**
	 * 药企平台推送数据到本地数据库中
	 * @param message	推送的信息， 如果为“通知公告”及“办事指南”模块时，为药采网的链接地址
	 * @param message2	数组类型的账号信息
	 * @param message3	模块标识， 目前平台推送的为汉字
	 * @param message4	信息标题
	 * @return >0 数据保存成功   <=0 数据保存失败
	 */
	public int saveMessage(String message,String[] message2,String message3,String message4);
	
	/**
	 * 药企平台重置app用户的密码
	 * @param usercode  登陆用户名
	 * @param imsi	   	用户使用的手机卡imsi码
	 * @return true 表示重置成功， false表示重置失败
	 */
	public boolean resetPassword(String usercode, String imsi);
	

}
