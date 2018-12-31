<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<%--<link rel="stylesheet" type="text/css" href="css&js/css/main.css">
--%><link rel="stylesheet" type="text/css" href="css&js/css/easyui.css">
<link rel="stylesheet" type="text/css" href="css&js/css/icon.css">
<script type="text/javascript" src="css&js/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="css&js/js/jquery.easyui.min.js"></script>
<!--[if lte IE 7]>
	<script type="text/javascript" src="css&js/js/json2.js"></script>
<![endif]-->
<script type="text/javascript" src="css&js/js/main.frame.js"></script>
