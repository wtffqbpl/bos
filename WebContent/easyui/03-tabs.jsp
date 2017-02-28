<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>layout---页面布局</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
</head>
<body  class="easyui-layout">
<div title="xxx系统" data-options="region:'north'" style="height: 100px">背部区域</div>
<div title="主菜单" data-options="region:'west'" style="width: 200px">
<!-- 折叠面板效果 -->
	<div class="easyui-accordion" data-options="fit:true">
		<!-- 每个子div是其中的一个面板 -->

		<div title="面板一">
			<a class="easyui-linkbutton" onclick="doadd();">百度
				<script type="text/javascript">
<!--
					
//-->
				function doadd(){
						
						$("#tt").tabs("add",{
							title:'动态页面',
							closable:true,
							iconCls:'icon-edit',
							content:'<iframe src="page_base_staff.action" style="border: 0px;height: 100%;width: 100%;"></iframe>'
						});
				}
</script>
			</a>
		</div>	
		<div title="面板二"></div>	
		<div title="面板三"></div>	
	</div>
</div>
<div title="q" data-options="region:'center'">
<div id="tt" class="easyui-tabs" data-options="fit:true">
		<!-- 每个子div是其中的一个面板 -->

		<div data-options="closable:true,iconCls:'icon-add'" title="面板一"></div>	
		<div title="面板二"></div>	
		<div title="添加"></div>	
	</div>
</div>
<div title="q" data-options="region:'east'" style="width: 100px">东部区域</div>
<div title="q" data-options="region:'south'" style="height: 50px">南部区域</div>

</body>
</html>