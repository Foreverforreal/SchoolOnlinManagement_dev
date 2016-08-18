<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加学生</title>
	<link rel="stylesheet" type="text/css" href="resources/ui/themes/bootstrap/easyui.css">
	<link rel="stylesheet" type="text/css" href="resources/ui/themes/icon.css">
	<script src="resources/ui/jquery.min.js"></script>
	<script src="resources/ui/jquery.easyui.min.js"></script>

</head>
<body >
	<div id="xuesheng-main" style="position:absolute;top:80%;left:50%;margin: -100px 0px 0px -100px;transform: scale(1.5);">
		<form id="newxuesheng"  method="post" name="filepath" enctype="multipart/form-data" >	
		
		 	<input type="file"  style="border:gray solid 1px ;zoom:1.1" name="filepath" />
		 	
			<div style="height: 15px"></div>
			
			<a style="font-family: 微软雅黑;zoom:1.2">选择学校:</a>
			<span style="position: relative;left: 10px;top: -2px" >
			<input id="schoolSymbol" class="easyui-combobox" name="schoolSymbol" style="width: 100px" data-options="textField:'value',url:'<%=request.getContextPath()%>/GetSchoolSymbol'" >
			</span>
			
			<a id="submit-button"  class="easyui-linkbutton" style="position: relative;left: 40px;width:60px;height: 20px">提交</a>
		 </form>
		 
	</div>
		 <script>
			$("#submit-button").click(function(){
				
			
				$.ajax({
				    url: '<%=request.getContextPath()%>/xueshengCheck',
				    type: 'POST',
				    cache: false,
				    data: new FormData($('#newxuesheng')[0]),
				    processData: false,
				    contentType: false,
				    beforeSend:function(XMLHttpRequest){
				    	
				    	$("#xuesheng-main").css({display:"none"});
				    	$("#myShow").css({display:"",position:"absolute",top:"50%",left:"50%",margin:"-60px 0px 0px -169px"})
				    }
				}).done(function(res) {	
					
					$("#dategrid-north").datagrid({
						url: '<%=request.getContextPath()%>/GetNoMappingXueSheng',
					 	fit:'ture',
					 	autoRowHeight:'false',
					 	fitColumns:'true',
					 	loadMsg:'正在获取没有匹配到专业的学生...',
					 	sortName:'id',
					    columns:[[
							{field:'id',title:'id',width:30,align:'center'},
							{field:'major',title:'专业',width:100,align:'center'},
							{field:'level',title:'层级',width:60,align:'center'},
							{field:'center',title:'中心',width:100,align:'center'},
							{field:'user_name',title:'账号',width:120,align:'center'},
							{field:'password',title:'密码',width:100,align:'center'},
							{field:'display_name',title:'姓名',width:60,align:'center'},
							{field:'gender',title:'性别',width:30,align:'center'},
							{field:'student_number',title:'学号',width:80,align:'center'},
							{field:'identity_card',title:'身份证号',width:120,align:'center'},
							{field:'enroll_year',title:'入学年份',width:60,align:'center'},
							{field:'phone_num',title:'手机',width:80,align:'center'},
							{field:'address',title:'地址',width:80,align:'center'},
					    ]]
				})
					
					var tab = $('#centernavigation').tabs('getSelected');
					$('#centernavigation').tabs('update', {
						tab: tab,
						options: {
							"title": '修改学生信息',
							"content":'<div id="tabDiv'+'">加载数据中,请稍候....</div>',
						}
					})
					tab.panel('refresh','<%=request.getContextPath()%>/GoUpdateXuesheng');
					
				}).fail(function(res) {
							alert("返回数据失败");				
				});
				
				
			});
		 </script>
		 <div id="myShow" style="display:none;">
        	<img alt="loading" src="resources/ui/image/wait.gif" />
    	 </div>
</body>
</html>