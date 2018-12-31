<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>密码修改界面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">
</style>
</head>
<body>
	<div class="easyui-panel" title="" style="width:100%; height: 100%">
		<div style="padding:10px 60px 20px 60px">
	    <form action="user.do?mt=changePwd" method="post">
	    	<input type="hidden" name="usercode" value="${sessionScope.admin.usercode }"/>
	    	<table id="pwdForm" cellpadding="5">
	    		<tr>
	    			<td>原始密码:</td>
	    			<td><input class="easyui-textbox" type="text" name="oldPwd" data-options="required:true"/></td>
	    		</tr>
	    		<tr>
	    			<td>新密码:</td>
	    			<td><input class="easyui-textbox" type="text" name="newPwd" data-options="required:true"/></td>
	    		</tr>
	    		<tr>
	    			<td>再次确认:</td>
	    			<td><input class="easyui-textbox" type="text" name="again" data-options="required:true"/></td>
	    		</tr>
	    	</table>
	    </form>
	    <div style="text-align:center;padding:5px; width: 200px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="margin-right: 30px; padding:0px 10px">确认修改</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="padding:0px 10px">清空数据</a>
	    </div>
	    </div>
	</div>
	<script>
		function submitForm(){
			var flag = true;
			var form = $("#pwdForm");
			if("${sessionScope.admin.usercode}" == ""){
				$.messager.alert("系统提示", "登陆超时，请重新登陆后操作！", "error")
				setInterval(function(){
					window.location.reload(true);
				}, 2000)
				return;
			}
			form.find("input[type=hidden]").each(function(){
				if($(this).val() == ""){
					flag = false;
				}
			})
			if(!flag){
				$.messager.alert("系统提示", "尚有必填项没有填写完整！", "error")
				return;
			}
			flag = $("input[name=oldPwd]").val() == $("input[name=newPwd]").val();
			if(flag){
				$.messager.alert("系统提示", "新密码不能跟原密码一致！", "error");
				return;
			}
			flag = $("input[name=newPwd]").val() == $("input[name=again]").val();
			if(!flag){
				$.messager.alert("系统提示", "两次输入的新密码不一致！", "error");
				return;
			}
			$.post(form.attr("action"), form.serialize(), function(res){
				if(res == "true"){
					$.messager.alert("系统提示", "密码修改成功！");
				} else {
					$.messager.alert("系统提示", res, "error");
				}
			})
		}
		
		function clearForm(){
			$('#pwdForm').form('clear');
		}
	</script>
</body>
</html>
