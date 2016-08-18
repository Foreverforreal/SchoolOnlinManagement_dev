<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="icon" href="resources/ui/image/head.png" type="image/x-icon">
	<link rel="stylesheet" type="text/css" href="resources/ui/themes/bootstrap/easyui.css">
	<link rel="stylesheet" type="text/css" href="resources/ui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="resources/ui/css/school.css">
	<script  src="resources/ui/jquery.min.js"></script>
	<script  src="resources/ui/jquery.easyui.min.js"></script>
<title>学校上线管理</title>
	
	<style type="text/css">
		#mainId {
			margin-left: auto;
			margin-right: auto;
			position:relative;
			top:30px;
		}

		.easyui-linkbutton {
			margin: auto;
			width: 100px;
		}
		div[class='panel combo-p'][style*='width: 100px'] {
			margin-left:22px;
			margin-top:50px;
			transform: scale(1.4);
		}
		div[class='panel combo-p'][style*='width: 173px'] {
			margin-left:45px;
			margin-top:60px;
			transform: scale(1.5);
		}

		.fun_button {
			margin-left: auto;
			margin-right: auto;
			margin-bottom: -1px;
			height:70px;
			border:1px solid #eaeaea;
			border-left:none;
		}
		.fun-img {
			margin-left: 10px;
		    height:50px;
			transform: scale(1);
			position:relative;
			top: 10px;
		}
		.fun_button:HOVER {
			border:1px solid #eaeaea;
			-webkit-box-shadow:3px 3px 10px #9edeff;
			box-shadow:3px 3px 10px #eaeaea ;
		}
		.fun_font{
			font:18px bold;
			font-family:Microsoft YaHei;
			position:relative;
			bottom: 10px;
		}
		body {
			opacity: 0.9;
			filter: alpha(opacity=80);  
			opacity: 0.9;
		}
		</style>
</head>
<body background="resources/ui/image/body.gif">

<div id="mainId" class="easyui-layout" style="width: 1400px; height: 900px;">
	
	
		<div id='top' data-options="region:'north',iconCls:'icon-help',title:'数据',split:true" style="height: 442px;">
			<table id="dategrid-north"></table>
		</div>
		
		<div id='bottom'data-options="region:'south',title:'状态统计',split:true,collapsed:true" style="height: 200px;">
			<table id="dategrid-south"></table>
			<table id="progressbar" style="position: absolute;bottom: 0;height: 20px;border-collapse:collapse;"></table>
		</div>
		
		
		<div data-options="region:'east',iconCls:'icon-reload',title:'数据',split:true,tools:[{iconCls:'icon-reload',handler:function(){$('#dategrid-east').datagrid('reload')}}]" style="width: 200px;">
			<table id="dategrid-east"></table>
		</div>
		
		
		<div data-options="region:'west',title:'操作目录'" style="width: 150px;">
			<div id="fuction" class="easyui-accordion" data-options="fit:true" >
			
				<div id='schoolonline' title="学校上线" data-options="iconCls:'icon-reload'" style="overflow: auto;">
					<div class="fun_button" id="proschool">
						<img src="resources/ui/image/pro.png" class="fun-img">  
						<a class="fun_font"  >Pro学校</a>
					</div>
					<div class="fun_button" id="devschool">
						<img src="resources/ui/image/dev.png" class="fun-img"> 
						<a class="fun_font" >Dev学校</a>
					</div>
					<div class="fun_button" >
						<img src="resources/ui/image/d2p.png" class="fun-img"> 
						<a class="fun_font" >学校复制</a>
					</div>
					<div class="fun_button" id="locschool">
						<img src="resources/ui/image/local.png" class="fun-img">
						<a class="fun_font">LocalDB</a>
					</div>
				</div>
				
				<div title="信息补充" data-options="iconCls:'icon-reload'" style="overflow: auto;">
					<div class="fun_button" id="studentreplenish">
						<img src="resources/ui/image/addstudent.png" class="fun-img">
						<a class="fun_font" >补充学生</a>
					</div>
					<div class="fun_button" id="teachingplanreplenish">
						<img src="resources/ui/image/addteachingplan.png" class="fun-img">
						<a class="fun_font" >补充计划</a>
					</div>
					<div class="fun_button" id="teachingplancopy">
						<img src="resources/ui/image/teachingplancopy.png" class="fun-img">
						<a class="fun_font" >复制计划</a>
					</div>
				</div>
				
				<div title="数据查询" data-options="iconCls:'icon-reload'" style="overflow: auto;">
					<div class="fun_button" id="query_student">
						<img src="resources/ui/image/find_student.png" class="fun-img">
						<a class="fun_font" >学生查询</a>
					</div>
					
					<div class="fun_button" id="query_admin">
						<img src="resources/ui/image/find_student.png" class="fun-img">
						<a class="fun_font" >管理员查询</a>
					</div>
					
					<div class="fun_button" id="query_teachingplan">
						<img src="resources/ui/image/find_teachignplan.png" class="fun-img">
						<a class="fun_font" >计划查询</a>
					</div>
				</div>
			</div>
		</div>
		
		
		<div id='center' data-options="region:'center',border:false" style="padding: 5px;">
			<div id='centernavigation' class="easyui-tabs" data-options="fit:true,border:false,plain:true"></div>	
		</div>
		
		</div>


	<script>
		$(".easyui-linkbutton").attr("data-options","iconCls:'icon-add',iconAlign:'left'");
		
		$("#studentreplenish").bind('click',function(){
		var flag=$("#centernavigation").tabs("exists",'补充学生')
		if(!flag){
			$("#centernavigation").tabs('add',{
			"title":'补充学生',
			"href":"<%=request.getContextPath()%>/Xuesheng",
			"closable":true
			})
		}
	}) 
		$("#proschool").bind('click',function(){
		var flag=$("#centernavigation").tabs("exists",'产品学校')
		if(!flag){
			$("#centernavigation").tabs('add',{
			"title":'产品学校',
			"href":"<%=request.getContextPath()%>/School?env=pro",
			"closable":true
			})
		}
	}) 
		$("#devschool").bind('click',function(){
		var flag=$("#centernavigation").tabs("exists",'测试学校')
		if(!flag){
			$("#centernavigation").tabs('add',{
			"title":'测试学校',
			"href":"<%=request.getContextPath()%>/School?env=dev",
			"closable":true
			})
		}
	}) 
		$("#locschool").bind('click',function(){
		var flag=$("#centernavigation").tabs("exists",'本地学校')
		if(!flag){
			$("#centernavigation").tabs('add',{
			"title":'本地学校',
			"href":"<%=request.getContextPath()%>/School?env=loc",
			"closable":true
			})
		}
	}) 
	
		
		$("#teachingplanreplenish").bind('click',function(){
		var flag=$("#centernavigation").tabs("exists",'补充计划')
		if(!flag){
			$("#centernavigation").tabs('add',{
			"title":'补充计划',
			"href":"<%=request.getContextPath()%>/teachingplan",
			"closable":true
			})
		}
	}) 
	
		$("#query_teachingplan").bind('click',function(){
		var flag=$("#centernavigation").tabs("exists",'计划查询')
		if(!flag){
			$("#centernavigation").tabs('add',{
			"title":'计划查询',
			"href":"<%=request.getContextPath()%>/GoQueryTeachingplan",
			"closable":true
			})
		}
	}) 

		$("#query_student").bind('click',function(){
		var flag=$("#centernavigation").tabs("exists",'学生查询')
		if(!flag){
			$("#centernavigation").tabs('add',{
			"title":'学生查询',

			"closable":true
			})
		}
	}) 
	
		$("#query_admin").bind('click',function(){
		var flag=$("#centernavigation").tabs("exists",'管理员查询')
		if(!flag){
			$("#centernavigation").tabs('add',{
			"title":'管理员查询',
			"href":"<%=request.getContextPath()%>/temp111",
			"closable":true
			})
		}
	}) 
		
	
	</script>
	

</body>
</html>