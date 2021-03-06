
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/public/head.jspf"%>
<script type="text/javascript">
	$(function() {
		$('#dg').datagrid({
			//url地址改为请求productAction
			url : "product_queryJoinCategory.action",
			loadMsg : 'Loading......',
			queryParams : {
				name : ''
			}, //name参数，根据name实现商品查询
			//width:300,
			fitColumns : true,
			striped : true,
			nowrap : true,
			singleSelect : true,
			pagination : true,
			pageSize : 5,
			pageList : [ 5, 10, 15, 20 ],
			idField : 'id',
			toolbar : [ {
				iconCls : 'icon-add',
				text : '添加商品',
				handler : function() {
					parent.$("#win").window({ //因为<div>放在了aindex.jsp中，所以这里创建窗口要先调用parent  
						title : "添加商品",
						width : 350,
						height : 150,
						content : '<iframe src="send_product_save.action" frameborder="0" width="100%" height="100%"/>'
					});
				}
			}, '-', {
				iconCls : 'icon-edit',
				text : '更新商品',
				handler : function() {
						var rows = $("#dg").datagrid('getSelections');
						if(rows.length==0){
							$.message.show({
								title:'错误提示',
								msg:'至少要选择一条记录',
								timeout:2000,
								showType:'slide',
							});
						}else if(rows.length!=1){
							$.message.show({
								title:'错误提示',
								msg:'每次只能更新一条记录',
								timeout:2000,
								showType:'slide',
							});
						}else{
							parent.$('#win').window({
								title:'更新商品',
								width:350,
								height:250,
								content:'<iframe src="send_product_update.action" frameborder="0" width="100%" height="100%"/>'
							});
						}

				}
			}, '-', {
				iconCls : 'icon-remove',
				text : '删除商品',
				handler : function() {
					//判断是否有选中行记录，使用getSelections获取选中的所有行  
					var rows = $("#dg").datagrid("getSelections");
					//返回被选中的行，如果没有任何行被选中，则返回空数组  
					if (rows.length == 0) {
						//弹出提示信息  
						$.messager.show({ //语法类似于java中的静态方法，直接对象调用  
							title : '错误提示',
							msg : '至少要选择一条记录',
							timeout : 2000,
							showType : 'slide',
						});
					} else {
						//提示是否确认删除，如果确认则执行删除的逻辑  
						$.messager.confirm('删除的确认对话框', '您确定要删除此项吗？', function(r) {
							if (r) {
								//1. 从获取的记录中获取相应的的id,拼接id的值，然后发送后台1,2,3,4  
								var ids = "";
								for (var i = 0; i < rows.length; i++) {
									ids += rows[i].id + ",";
								}
								ids = ids.substr(0, ids.lastIndexOf(","));
								//2. 发送ajax请求  
								$.post("product_deleteByIds.action", {
									ids : ids
								}, function(result) {
									if (result == "true") {
										//将刚刚选中的记录删除，要不然会影响后面更新的操作  
										$("#dg").datagrid("uncheckAll");
										//刷新当前页，查询的时候我们用的是load，刷新第一页，reload是刷新当前页  
										$("#dg").datagrid("reload"); //不带参数默认为上面的queryParams  
									} else {
										$.messager.show({
											title : '删除异常',
											msg : '删除失败，请检查操作',
											timeout : 2000,
											showType : 'slide',
										});
									}
								}, "text");
							}
						});
					}
				}
			}, '-', {
				text : "<input id='ss' name='search'/>"
			} ],

			rowStyler : function(index, row) {
				console.info("index" + index + "," + row)
				if (index % 2 == 0) {
					return 'background-color:#fff;';
				} else {
					return 'background-color:#ff0;';
				}

			},
			frozenColumns : [ [
				{
					field : 'checkbox',
					checkbox : true
				},
				{
					field : 'id',
					title : '商品编号',
					width : 100
				} //这里的field字段要和json数据中的一样             
			] ],
			columns : [ [
				{
					field : 'name',
					title : '商品名称',
					width : 100, 
				},
				{
					field : 'price',
					title : '商品价格',
					width : 100, 
				},
				{
					field : 'remark',
					title : '简单描述',
					width : 100, 
				},
				{
					field : 'xremark',
					title : '详细描述',
					width : 100, 
				},
				{
					field : 'date',
					title : '上架时间',
					width : 100, 
				},
				{
					field : 'commend',
					title : '推荐商品',
					width : 100,
					formatter:function(value,row,index){
						if(value){
							return "<input type='checkbox' checked='checked' disabled='true'"; 
						}else{
							return "<input type='checkbox' disable='true'"; //不勾选
						}
					} 
				},
				{
					field : 'open',
					title : '有效商品',
					width : 100, 
					formatter:function(value,row,index){
						if(value){
							return "<input type='checkbox' checked='checked' disabled='true'"; 
						}else{
							return "<input type='checkbox' disable='true'"; //不勾选
						}
					}
				},
				{
					field : 'category.type',
					title : '所属商品类别',
					width : 200, 
					formatter : function(value, row, index) {
						if (row.category != null && row.category.type != null) {
							return row.category.type; 
						} else {
							return "此商品暂时未分类";
						}
					}
				}
			] ]
		});

		$('#ss').searchbox({
			searcher : function(value, name) {
				//查询操作
				$('#dg').datagrid('load', {
					name : value
				});
			},
			prompt : '请输入搜索关键字'
		});
	});
</script>
</head>

<body>
	<table id="dg"></table>
</body>
</html>
