<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>山东药品耗材采购手机客户端下载</title>
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
		$("body").html("点击右上角按钮，选择“在浏览器中打开”");
	} else {
		if(isAndroid()) {
			window.location = "/yq/sdyy.apk";//下载地址在这里。。。。
		} else if(isIos()) {
			$("body").html("苹果客户端审核中，敬请期待！");
		} else {
			
		}
	}
});
//是否Android
var isAndroid = function() {
	var u = navigator.userAgent, app = navigator.appVersion;
	//android终端或者uc浏览器
	return u.indexOf('Android') > -1 || u.indexOf('Linux') > -1;
}
//是否IOS
var isIos = function() {
	var u = navigator.userAgent, app = navigator.appVersion;
	//ios终端
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/);
}
//是否微信
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
    	加载中...
  </body>
</html>
