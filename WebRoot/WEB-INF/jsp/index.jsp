<%@page import="ws.MessageImpl"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>欢迎进入后台主页面</title>
<%@include file="../common/common.jsp"%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- <link rel="stylesheet" type="text/css" href="styles.css">-->
<style type="text/css">
.tip { width: 100%; text-align: right; margin-top: 5px}
a { color: #e83119; -webkit-transition: color .4s ease-out;}
a:hover { color: #0a99ae }
.sys_menu ul { padding: 0; margin: 0; border: #ccc 1px dotted;}
.sys_menu li {height: 30px; line-height: 30px; list-style: none; border-bottom: #ccc 1px dotted; text-align: center;}
</style>
<script type="text/javascript">
	$(function(){
		//设置当前时间的定时器
		$("#timer").text(new Date().format("yyyy-MM-dd hh:mm:ss"))
		window.setInterval(function(){
			$("#timer").text(new Date().format("yyyy-MM-dd hh:mm:ss"))
		},1000);
	})
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false"
		style="height:60px;background:#E0ECFF;padding:10px">
		<div class="tip">欢迎[${sessionScope.admin.usercode }]进入系统 当前时间[<span id="timer"></span>]</div>
		<div class="tip">
			<a href="<%=basePath%>login.do">退出系统</a>
		</div>
	</div>
	<div data-options="region:'west',split:true,title:'功能菜单栏'"
		style="width:250px;">
		<div class="easyui-accordion" style="height: 100%">
			<!-- 加载左侧的菜单数据 -->
			<c:forEach var="menu" items="${requestScope.menus}">
				<c:if test="${menu.parent == null }">
					<div title="${menu.name }" iconCls="icon-more" style="overflow:auto;padding-left: 5px; padding-right: 5px;">
						<div class="sys_menu">
							<ul>
								<c:forEach var="child" items="${requestScope.menus}">
									<c:if test="${child.parent == menu.id}">
										<li onclick="addTab('${child.name}','${child.link }', ${child.target })">${child.name }</li>
									</c:if>
								</c:forEach>
							</ul>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>
	<%-- 
		<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">
			<!-- 右侧小功能菜单区域 -->
		</div>
		--%>
	<div data-options="region:'south',border:false"
		style="height:40px;background:#E0ECFF;text-align: center; padding: 10px;">
		<label>本产品由中国电信集团系统集成公司山东分公司制作</label>
	</div>
	<div data-options="region:'center',title:''">
		<div data-options="fit:true,fitColumns:true" class="easyui-tabs"
			id="center"
			style="widows: inherit; width: 100%; height: 544px; position: absolute;">
			<div title="平台首页">
				<img alt="首页事例图" src="css&js/imgs/bg.png" width="100%"
					height="99.5%">
			</div>
		</div>
	</div>
</body>
</html>
