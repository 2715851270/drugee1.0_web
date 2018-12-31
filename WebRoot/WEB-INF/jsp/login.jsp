<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="../common/common.jsp"%>
<title>欢迎登陆药品耗材采购平台</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<style type="text/css">
.north {
	background: #E0ECFF;
	height: 60px;
	padding: 10px
}

.center {
	background-color: #CCCCCC;
	background-image: url("css&js/imgs/bg.png");
	background-size: 100% 100%;
	background-repeat: no-repeat;
	filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='images/bg.png',sizingMethod='scale' );
}

.footor {
	height: 40px;
	line-height: 40px;
	background: #E0ECFF;
	text-align: center;
}

.loginDiv {
	height: 231px;
	padding-right: 15%;
	margin-top: 14.5%;
}

.loginWindow {
	width: 300px;
	height: 200px;
	padding: 10px;
	background: #E0ECFF
}

fieldset {
	border: 0px;
	padding-top: 5px
}

p{
	margin: 0px; padding: 0px
}

label {
	display: block;
	width: 60px;
	float: left;
	text-align: right;
	height: 20px;
	line-height: 20px;
	padding:3px;
	margin: 0 5px
}

h2 {
	text-align: center;
}

input{
	height: 26px;
	margin-bottom: 5px;
}

#captcha {
	width: 80px;
	display: block;
	float: left;
	margin-right: 2px;
	margin-left: 0px
}

.easyui-linkbutton {
	height: 30px;
	padding: 0px 80px;
	display: block;
	text-align: center;
}
</style>

<body class="easyui-layout">
	<%--<div class="north" data-options="region:'north',border:false">
		<h1 style="font-size: 24px">中国电信股份有限公司山东分公司</h1>
	</div>
	--%><div class="center" data-options="region:'center'">
		<div class="easyui-layout loginDiv" data-options="fit: false">
			<div data-options="region:'east'" class="loginWindow">
				<form action="login.do?mt=login" method="post" onkeydown="commit()">
					<fieldset>
						<h2>用户登陆</h2>
						<p>
							<label for="usercode">账号</label> 
							<input id="usercode" type="text" name="usercode" />
						</p>
						<p>
							<label for="password">密码</label> 
							<input id="password" type="password" name="password" />
						</p>
						<p class="captcha">
							<label for="captcha">验证码</label> 
							<input id="captcha" type="text" name="captcha" /> 
							<img src="login.do?mt=captcha" onclick="changeCaptcha(this)" />
							<%-- <a href="javascript:void(0)" onclick="changeCaptcha()">看不清，换一张</a> --%>
						</p>
						<p>
						<center>
							<input type="button" class="easyui-linkbutton" value="登陆" onclick="login()"/>
						</center>
						<%--<a href="#"
							style="float: right; margin-right: 20px; margin-top: 10px">点击注册</a>
						<a href="#"
							style="float: right; margin-right: 20px; margin-top: 10px">忘记密码</a>
						--%></p>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
	<%--<div class="footor" data-options="region:'south',border:false">
		<command>本产品由济南宽豫信息技术有限公司制作</command>
	</div>--%>
	<script type="text/javascript">
		
		/** 验证码 */
		var changeCaptcha = function(obj){
			$(obj).attr("src", "login.do?mt=captcha&"+Math.random())
		}
		
		/** 回车提交 */
		var commit = function(){
			if(window.event.keyCode == 13){
				login();
			}
		}
		
		/** 登陆验证 */
		var login = function(){
			var form = $("form").first();
			$.post(form.attr("action"), form.serialize(), function(res){
				if(res == 'true'){
					window.location.href = "index.do";
				} else {
					$.messager.alert("系统提示",res,"info");
				}
			})
		}
	
	</script>
</body>
</html>
