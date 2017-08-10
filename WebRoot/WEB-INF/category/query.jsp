<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/public/head.jspf"%>

<script type="text/javascript">
	$(function() {
		$('#dg').datagrid({
			url : 'datagrid_data1.json',
			loadMsg : 'loading....',
			queryParams : {
				type : ''
			},
			fitColumns : true,
			striped : true,
			nowrap : true,
			singleSelect : true,
			pagination : true,
			rowStyler : function(index, row) {
				console.info("index" + index + "," + row);
				if (index % 2 == 0) {
					return 'background-color:#fff';
				} else {
					return 'background-color:#cee4f2';
				}
			},
			frozenColumns : [ [
				{
					field : 'checkbox',
					checkbox : true
				},
				{
					field : 'code',
					title : '编号',
					width : 200
				}
			] ],
			columns : [ [
				{
					field : 'productname',
					title : '类别名称',
					width : 100
				},
				{
					field : 'price',
					title : '价格',
					width : 100,
					styler : function(value, row, index) {
						if (value < 20) {
							return 'color:red;';
						}
					}
				}
			] ]
		});

	});
</script>

</head>
<body>
	<table id="dg"></table>
</body>
</html>
