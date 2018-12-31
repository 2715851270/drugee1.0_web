<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>推送消息的界面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>
	<table id="msg_list" class="easyui-datagrid"
		data-options="url:'msg.do?mt=table',method:'post',border:true,singleSelect:false,fit:true,fitColumns:true,pagination:true,rownumbers:true,toolbar:'#msg_tab'">
		<thead>
			<tr>
				<th data-options="field:'id',checkbox:true"></th>
				<th data-options="field:'usercode', align:'center'" width="80">推送用户</th>
				<th data-options="field:'title', align:'center'" width="80">消息标题</th>
				<th data-options="field:'data', align:'center'" width="100">推送数据</th>
				<th data-options="field:'module', align:'center'" width="100">模块名称</th>
				<th data-options="field:'pushed', align:'center', formatter:function(v){return v? '是' : '否'}" width="100">已经推送</th>
				<th data-options="field:'time', align:'center'" width="100">推送时间</th>
				<th data-options="field:'isDel', align:'center', formatter:function(v){return v==0 ? '已读' : '未读'}" width="100">消息状态</th>
			</tr>
		</thead>
	</table>
	<!-- table的工具栏 -->
	<div id="msg_tab" style="padding:5px;height:auto">
		<div>
			<label>推送用户:</label>
			<input name="usercode" class="easyui-validatebox">
			<label>消息标题:</label>
			<input name="title" class="easyui-validatebox">
			<label>模块名称:</label>
			<input name="module" class="easyui-validatebox">
			<a onclick="query(this)" class="easyui-linkbutton" iconCls="icon-search">查找</a> 
			<a onclick="cleanAll(this)" class="easyui-linkbutton" iconCls="icon-clear">清空</a>		
		</div>
		<div style="margin-bottom:5px;">
			<a onclick="toPush(this)" title="重新推送" class="easyui-linkbutton" iconCls="icon-reload">重新推送</a>
		</div>
	</div>
	<script type="text/javascript">
		//重新推送信息到客户端
		var toPush = function(obj){
			var param = {
				table:'#msg_list',
				url : 'msg.do?mt=toPush',
				text:'已进入重新推送队列，请耐心等待。。。。。!'
			}
			findSelected(obj, param);
		}
		
		//按照条件查询列表数据
		var query = function(obj){
			searchQuery($(obj).parent(), '#msg_list')
		}
		//清空查询条件并刷新列表
		var cleanAll = function(obj){
			clearQuery($(obj).parent(), '#msg_list');
		}
		
		//帮顶查询条件的回车查询事件
		$(function(){
			$('#msg_tab').keydown(function(e){
				if(e.keyCode == 13){
					searchQuery(this, '#msg_list')
				}
			});
		})
	</script>
</body>
</html>
