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
	$(function(){
		
		//$.messager.alert("标题","内容","question");
		/* window.setTimeout(function(){
		$.messager.show({
			title:'提示',
			msg:'欢迎tom',
			timeout:3000,
			showType:'slide'
		});
		},3000);
		$.messager.confirm("提示信息","内容",function(res){
			alert(res);
		});*/
		$.messager.prompt("提示信息","内容",function(res){
			alert(res);
		});
	}); 
		
	
</script>
</head>
<body  >

</body>
</html>