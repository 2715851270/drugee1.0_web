<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户管理的界面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>
	<table id="user_list" class="easyui-datagrid"
		data-options="url:'user.do?mt=table',method:'post',border:true,singleSelect:false,fit:true,fitColumns:true,pagination:true,rownumbers:true,toolbar:'#user_tab'">
		<thead>
			<tr>
				<%-- <th data-options="field:'id',checkbox:true"></th> --%>
				<th data-options="field:'usercode', align:'center'" width="80">用户账号</th>
				<th data-options="field:'imsi', align:'center'" width="80">IMSI码</th>
				<th data-options="field:'imei', align:'center'" width="100">IMEI码</th>
				<th data-options="field:'telphone', align:'center'" width="100">电话号码</th>
				<th data-options="field:'corpid', align:'center'" width="100">企业编号</th>
				<th data-options="field:'corpname', align:'center'" width="100">企业名称</th>
				<th data-options="field:'reason', align:'center'" width="100">变更原因</th>
				<th data-options="field:'type', align:'center', formatter:function(v){if(v == 1){return '药品企业'} else {return '耗材企业'}}" width="100">企业类型</th>
			</tr>
		</thead>
	</table>
	<!-- table的工具栏 -->
	<div id="user_tab" style="padding:5px;height:auto">
		<div>
			<label>账号:</label>
			<input name="usercode" class="easyui-validatebox" style="width: 120px">
			<label>电话:</label>
			<input name="telphone" class="easyui-validatebox" style="width: 120px">
			<label>企业编码:</label>
			<input name="corpid" class="easyui-validatebox" style="width: 120px">
			<label>企业名称:</label>
			<input name="corpName" class="easyui-validatebox" style="width: 120px">
			<label>企业类型:</label>
			<select name="type" class="easyui-validatebox">
				<option value="0">请选择</option>
				<option value="1">药品企业</option>
				<option value="2">耗材企业</option>
			</select>
			<a onclick="query(this)" class="easyui-linkbutton" iconCls="icon-search">查找</a> 
			<a onclick="cleanAll(this)" class="easyui-linkbutton" iconCls="icon-clear">清空</a>		
		</div>
		<%--<div style="margin-bottom:5px;">
			<a onclick="toPush(this)" title="重新推送" class="easyui-linkbutton" iconCls="icon-reload">重新推送</a>
		</div>
	--%></div>
	<script type="text/javascript">
		//重新推送信息到客户端
		var toPush = function(obj){
			var param = {
				table:'#user_list',
				url : 'msg.do?mt=toPush',
				text:'已进入重新推送队列，请耐心等待。。。。。!'
			}
			findSelected(obj, param);
		}
		
		//按照条件查询列表数据
		var query = function(obj){
			searchQuery($(obj).parent(), '#user_list')
		}
		//清空查询条件并刷新列表
		var cleanAll = function(obj){
			clearQuery($(obj).parent(), '#user_list');
		}
		
		//帮顶查询条件的回车查询事件
		$(function(){
			$('#user_tab').keydown(function(e){
				if(e.keyCode == 13){
					searchQuery(this, '#user_list')
				}
			});
		})
	</script>
</body>
</html>
