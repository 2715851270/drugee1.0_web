<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>ɽ��ҩƷ�ĲĲɹ��ֻ��ͻ�������</title>
    <meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">
	<script type="text/javascript" src="css&js/js/jquery-1.8.0.min.js"></script>
    
<style type="text/css">
body {
	text-align: center;
	padding-top: 40%;
	font-weight: 14;
	color: #666;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	if((isAndroid() || isIos()) && isWechat()) {
		$("body").html("������Ͻǰ�ť��ѡ����������д򿪡�");
	} else {
		if(isAndroid()) {
			window.location = "/yq/sdyy.apk";//���ص�ַ�����������
		} else if(isIos()) {
			$("body").html("ƻ���ͻ�������У������ڴ���");
		} else {
			
		}
	}
});
//�Ƿ�Android
var isAndroid = function() {
	var u = navigator.userAgent, app = navigator.appVersion;
	//android�ն˻���uc�����
	return u.indexOf('Android') > -1 || u.indexOf('Linux') > -1;
}
//�Ƿ�IOS
var isIos = function() {
	var u = navigator.userAgent, app = navigator.appVersion;
	//ios�ն�
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/);
}
//�Ƿ�΢��
var isWechat = function() { 
	var ua = window.navigator.userAgent.toLowerCase(); 
	if(ua.match(/MicroMessenger/i) == 'micromessenger'){ 
		return true; 
	} else { 
		return false; 
	} 
} 
</script>
  </head>
  
  <body>
    	������...
  </body>
</html>
