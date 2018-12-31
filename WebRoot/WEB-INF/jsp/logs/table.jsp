<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>日志管理的页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>
	<table id="logs_list" class="easyui-datagrid"
		data-options="url:'logs.do?mt=table',method:'post',border:true,singleSelect:false,fit:true,fitColumns:true,pagination:true,rownumbers:true,toolbar:'#logs_tab'">
		<thead>
			<tr>
				<%--<th data-options="field:'id',checkbox:true"></th>--%>
				<th data-options="field:'TAG', align:'center'" width="60">日志标识</th>
				<th data-options="field:'INFO', align:'left'" width="200">日志内容</th>
				<th data-options="field:'TIME', align:'center'" width="60">日志时间</th>
			</tr>
		</thead>
	</table>
	<!-- table的工具栏 -->
	<div id="logs_tab" style="padding:5px;height:auto">
		<div>
			<label>日志标识:</label>
			<input name="tag" class="easyui-validatebox">
			<label>日志内容:</label>
			<input name="info" class="easyui-validatebox">
			<label>日志时间:</label>
			<input name="time" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser">
			<a onclick="query(this)" class="easyui-linkbutton" iconCls="icon-search">查找</a> 
			<a onclick="cleanAll(this)" class="easyui-linkbutton" iconCls="icon-clear">清空</a>		
		</div>
	</div>
	<script type="text/javascript">
		//按照条件查询列表数据
		var query = function(obj){
			searchQuery($(obj).parent(), '#logs_list')
		}
		//清空查询条件并刷新列表
		var cleanAll = function(obj){
			clearQuery($(obj).parent(), '#logs_list');
		}
		
		//帮顶查询条件的回车查询事件
		$(function(){
			$('#logs_tab').keydown(function(e){
				if(e.keyCode == 13){
					searchQuery(this, '#logs_list')
				}
			});
		})
		
		function myformatter(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
		}
		function myparser(s){
			if (!s) return new Date();
			var ss = (s.split('-'));
			var y = parseInt(ss[0],10);
			var m = parseInt(ss[1],10);
			var d = parseInt(ss[2],10);
			if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
				return new Date(y,m-1,d);
			} else {
				return new Date();
			}
		}
	</script>
</body>
</html>
