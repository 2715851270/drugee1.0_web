<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>文件上传页</title>
<%@include file="../../common/common.jsp"%>
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
	    <form id="upfile" action="index.do?mt=fileUpload" method="post" enctype="multipart/form-data" onsubmit="return submitForm()">
	    	<input type="hidden" name="usercode" value="${sessionScope.admin.usercode }"/>
	    	<table cellpadding="5">
	    		<tr>
	    			<td>当前版本号:</td>
	    			<td><input readonly="readonly" class="easyui-textbox" type="text" data-options="required:true" value="${requestScope.version }"/></td>
	    		</tr>
	    		<tr class="clearInput">
	    			<td>更新版本号:</td>
	    			<td><input class="easyui-textbox" type="text" name="version" data-options="required:true"/></td>
	    		</tr>
	    		<tr class="clearInput">
	    			<td>更新apk文件:</td>
	    			<td><input type="file" name="file" data-options="required:true" accept=".apk"/></td>
	    		</tr>
	    	</table>
	    	
	    	 <div style="text-align:center;padding:5px; width: 200px">
		    	<input type="submit" class="easyui-linkbutton" style="margin-right: 30px; padding:4px 10px" value="确认更新"/>
		    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="padding:0px 10px">清空数据</a>
		    </div>
	    </form>
	    </div>
	</div>
	<script>
		$(function(){
			var result = "${requestScope.result}";
			if(result && result != undefined){
				$.messager.alert("系统提示", result);
			}
		})
	
		function submitForm(){
			var flag = true;
			var form = $("#upfile");
			if("${sessionScope.admin.usercode}" == ""){
				$.messager.alert("系统提示", "登陆超时，请重新登陆后操作！", "error")
				setInterval(function(){
					window.location.reload(true);
				}, 2000)
				return false;
			}
			
			var version = $(form).find("input[name=version]").val();
			var apkfile = $(form).find("input[name=file]").val();
			if(version == "" || apkfile == ""){
				$.messager.alert("系统提示", "更新版本号和更新apk文件不能为空", "error")
				return false;
			}
			return true;
		}
		
		function clearForm(){
			$('.clearInput').form('clear');
		}
	</script>
</body>
</html>
