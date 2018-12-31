package com.ws.frame.utils;

import java.net.URL;

import org.codehaus.xfire.client.Client;

import com.google.gson.JsonObject;

/**
 * 客户端发起WebService访问的方法，通过该方法，可以访问其他网络的WebService，并获取返回的数据
 * @author YINGFU
 * @version 1.0bate
 */
public class WsUtils {
//	private final static String URL_SPAC = "http://123.232.20.41:8050/WebServices/AppServicesMain.asmx?WSDL";
	private final static String URL_SPAC = "http://10.10.11.40:8050/WebServices/AppServicesMain.asmx?WSDL";
	

	/**
	 * 发起webserivce访问， 有几个参数，按顺序传递参数
	 * @param method		访问的webservice方法名
	 * @param params		参数，按照顺序传值
	 * @return				返回字符串类别的数据
	 * @throws Exception	如果抛出异常表示访问失败
	 */
	public static String visit(String method, Object[] params) throws Exception{
		Client client = new Client(new URL(URL_SPAC));
		client.setTimeout(5000);	//访问超过5秒钟超时
		//设置访问的方法和参数
		Object[] results = client.invoke(method, params);  
		return results[0].toString();
	}
	
	
	
	public static void main(String[] args) {
		JsonObject param = new JsonObject();
		param.addProperty("IMEI", "A0000043A54C3A");
		param.addProperty("IMSI", "460030136411364");
		param.addProperty("DRUG_OR_MC", 2);
		try {
			String wsResult = WsUtils.visit("MC_CheckUserStatus", new Object[]{param.toString()});
			System.out.println(wsResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
