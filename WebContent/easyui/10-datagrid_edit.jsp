<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>layout---消息提示</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
			
	
</script>
</head>
<body  >
	
	<h3>方式三:通过js代码动态创建datagrid</h3>
	<table id="grid">
	</table>
	<script type="text/javascript">
		$(function(){
			var index;//全局行索引
			$("#grid").datagrid({
				columns:[[
					{field:'id',title:'编号',checkbox:true},
					{field:'name',title:'姓名',editor:{
						type:"validatebox",
						options:{}
					}},
					{field:'age',title:'年龄',editor:{
						type:"validatebox",
						options:{}
					}}
				]],
				url:'/bos/json/data.json',
				onAfterEdit:function(rowIndex,rowData,changes){
					
				},
				toolbar: [
					{text:'保存',iconCls:"icon-save",handler:function(){
						$("#grid").datagrid("endEdit",index);
					}},
					{text:'添加',iconCls:"icon-add",handler:function(){
						$("#grid").datagrid("insertRow",{
							index:0,
							row:{}
						});
						index=0;
						$("#grid").datagrid("beginEdit",0);
					}},
					{text:'删除',iconCls:"icon-remove",
						handler:function(){
							var row =$("#grid").datagrid("getSelected");
							index=$("#grid").datagrid("getRowIndex",row);
							$("#grid").datagrid("deleteRow",index);
						
					}},
					{text:'修改',iconCls:"icon:edit",handler:function(){
						var row =$("#grid").datagrid("getSelected");
						index=$("#grid").datagrid("getRowIndex",row);
						$("#grid").datagrid("beginEdit",index);
					}},
				],
				pagination:true,
				pageList:[3,5,7],
				singleSelect:true
			});
		});
	</script>
</body>
</html>