<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/public/head.jspf"%>
<style type="text/css">
#menu {
	width: 60px;
	/*border:1px solid red;*/
}

#menu ul {
	list-style: none;
	padding: 0px;
	margin: 0px;
}

#menu ul li {
	border-bottom: 1px solid #fff;
}

#menu ul li a {
	/*先将a标签转换为块级元素，才能设置宽和内间距*/
	display: block;
	background-color: #00a6ac;
	color: #fff;
	padding: 5px;
	text-decoration: none;
}

#menu ul li a:hover {
	background-color: #008792;
}
</style>

<script type="text/javascript">
	$(function() {
		$("a[title]").click(function() {
			var text = $(this).text();
			var href = $(this).attr("title");
			if ($('#tt').tabs('exists', text)) {
				$('#tt').tabs('select', text);
			} else {
				$('#tt').tabs('add', {
					title : text,
					content : '<iframe title='+text+'  src='+href+' frameborder="0" width="100%" height="100%" />',
					closable : true
				});
			}
		});
	});
</script>
</head>

<body class="easyui-layout">

	<div data-options="region:'north',title:'欢迎来到易购后台管理',split:true"
		style="height:100px;"></div>
	<div data-options="region:'west',title:'系统管理',split:true"
		style="width:200px;">
		<div id="menu" class="easyui-accordion"
			data-options:"fit:true"
			style="width:300px;height:200px;">
				<div title="基本操作">   
        			<ul>
						<li><a href="#" title="send_category_query.action">类别管理</a>
						<li><a href="#" title="send_product_query.action">商品管理</a>
					</ul>
    			</div>   
		</div>
	</div>
	<div data-options="region:'center',title:'center title'"
		style="padding:5px;background:#eee;">
		<div id="tt" class="easyui-tabs" style="width:100%;height:270px;">

		</div>
	</div>
	<div id="win" data-options="collapsible:false,minimizable:false,maximizable:false,modal:true"></div>    
</body>
</html>
