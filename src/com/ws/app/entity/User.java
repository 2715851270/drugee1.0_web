package com.ws.app.entity;

/**
 * 用户的基本信息实体类
 * @author YINGFU
 * @version 1.0bate
 */
public class User {

	private String usercode;	//用户名
	private String password;	//密码
	private String telphone;	//电话号码
	private String imsi;		//电话IMSI码
	private String imei;		//电话IMEI码
	private String corpid;		//企业编号
	private String corpname;	//企业名称
	private String reason;		//申请原因
	private Integer type; 		//企业类型
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getCorpid() {
		return corpid;
	}
	public void setCorpid(String corpid) {
		this.corpid = corpid;
	}
	public String getCorpname() {
		return corpname;
	}
	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
