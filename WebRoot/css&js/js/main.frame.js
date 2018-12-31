/**
 * 添加tab页面的方法
 * @param title
 * @param url
 * @param iframe
 */
var addTab = function(title, url, iframe) {
	if ($('#center').tabs('exists', title)) {
		$('#center').tabs('select', title);
	} else {
		var content = href = ''; // 声明 iframe 和 url加载的两个变量初始值
		if (iframe) {
			var content = '<iframe scrolling="auto" frameborder="0"  src="'
					+ url + '" style="width:100%;height:100%;"></iframe>';
		} else {
			href = url;
		}
		$('#center').tabs('add', {
			title : title,
			content : content,
			href : href,
			closable : true
		});
	}
}

/**
 * 操作选中的数据，执行简单的操作， 比如删除类、审核类、修改类等
 * @param data { table:'#xxx', url: 'xxx/xxx', text:'xx成功'}
 * @param obj 当前的元素的 this值
 */
var findSelected = function(obj, param){
	var ids = [];
	var title = $(obj).attr("title");
	var rows = $(param.table).datagrid('getSelections');
	if(rows.length == 0){
		$.messager.alert('系统提示','请至少选中一条数据!');
		return ;
	}
	for ( var i = 0; i < rows.length; i++) {
		ids.push(rows[i].id);
	}
	$.messager.confirm('系统提示', '确定要'+title+'这些信息吗？', function(r){
		if (r){
			$.post(param.url, {ids: ids.toString()}, function(data){
				if(data == 'true'){
					$.messager.alert('系统提示', param.text);
					$(param.table).datagrid("load", {});
				} else {
					$.messager.alert('系统提示','操作失败，请刷新页面后重新操作!',"error");
				}
			})
		} 
	});
}

/**
 * 列表中的按照条件查询
 * @param obj input条件框的上级目录
 */
var searchQuery = function(obj, table){
	var param = new Object();
	$(obj).find("input").each(function(){
		var value = $(this).val();
		if(value && value != undefined){
			param[$(this).attr("name")] = value;
		}
	})
	$(obj).find("select").each(function(){
		var value = $(this).val();
		if(value && value != undefined){
			param[$(this).attr("name")] = value;
		}
	})
	$(table).datagrid("load", param);
}

/**
 * 晴空查询数据，并刷新列表
 * @param obj input条件框的上级目录
 * @param table 列表的id号
 */
var clearQuery = function(obj, table){
	$(obj).find("input").val("");
	$(obj).find("select").val("");
	if(table && table != undefined){
		$(table).datagrid("load", {});
	}
}

/**
 * 日期格式化
 * @param format 例如 yyyy-MM-dd hh:MM:ss
 */
Date.prototype.format = function(format){ 
	var o = { 
		"M+" : this.getMonth()+1, //month 
		"d+" : this.getDate(), //day 
		"h+" : this.getHours(), //hour 
		"m+" : this.getMinutes(), //minute 
		"s+" : this.getSeconds(), //second 
		"q+" : Math.floor((this.getMonth()+3)/3), //quarter 
		"S" : this.getMilliseconds() //millisecond 
	} 
	if(/(y+)/.test(format)) { 
		format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	} 
	for(var k in o) { 
		if(new RegExp("("+ k +")").test(format)) { 
			format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
		} 
	} 
	return format; 
} 



