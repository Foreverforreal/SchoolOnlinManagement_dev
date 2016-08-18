<%@ page language="java" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="resources/ui/themes/bootstrap/easyui.css">
	<link rel="stylesheet" type="text/css" href="resources/ui/themes/icon.css">
	<script src="resources/ui/jquery.min.js"></script>
	<script src="resources/ui/jquery.easyui.min.js"></script>
<title></title>
</head>

<body>

	<div id="newteachingplan-main" style="position:absolute;top:70%;left:50%;margin: -100px 0px 0px -100px;transform: scale(1.5);">
		<form id="newteachingplan"  method="post" name="fileupload" enctype="multipart/form-data" >
						<input type="file"  style="border:gray solid 1px ;zoom:1.1" name="filepath" />
						<div style="height: 15px"></div>
						<a style="font-family: 微软雅黑;zoom:1.2">选择学校:</a>  
						<span style="position: relative;left: 10px;top: -2px" >
						<input id="schoolSymbol" class="easyui-combobox" name="schoolSymbol" style="width: 100px" data-options="textField:'value',url:'<%=request.getContextPath()%>/GetSchoolSymbol'" >
						</span>
						<a id="submiting"  class="easyui-linkbutton" style="position: relative;left: 40px;width:60px;height: 20px">提交</a>
		 </form>
	</div>
	
	<div id="myShow" style="display:none;">
        	<img alt="loading" src="resources/ui/image/wait.gif" />
    </div>
    
	<script>
	$("#submiting").click(function(){
				
		$.ajax({
		    url:'<%=request.getContextPath()%>/TeachingplanReplenish',
		    type: 'POST',
		    cache: false,
		    data: new FormData($('#newteachingplan')[0]),
		    processData: false,
		    contentType: false,
		    beforeSend:function(XMLHttpRequest){
		    	$("#newteachingplan-main").css({display:"none"});
		    	$("#myShow").css({display:"",position:"absolute",top:"50%",left:"50%",margin:"-60px 0px 0px -169px"})
		    }
		}).done(function(res) {
			
			$("#dategrid-north").datagrid({
				url: '<%=request.getContextPath()%>/GetBreakJxjh',
			 	fit:'ture',
			 	autoRowHeight:'false',
			 	fitColumns:'true',
			 	loadMsg:'正在获有问题的教学计划...',
			 	rowStyler:function(index,row){
			 		if (row.status=="repeat"){
			 			return 'background-color:#8BC34A;'
			 		}
			 	},
			    columns:[[
					{field:'id',title:'id',width:30,align:'center',editor:'String',editor:{type:'validatebox',options:{required:true}}},
					{field:'major',title:'专业',width:100,align:'center',editor:{type:'validatebox',options:{required:true}},
						styler: function(value,row,index){
							if (value ==null){
								return 'background-color:#DA776F;';}}},
					{field:'level',title:'层级',width:60,align:'center',editor:{type:'validatebox',options:{required:true}},
						styler: function(value,row,index){
							if (value ==null){
								return 'background-color:#DA776F;';}}},
					{field:'name',title:'课程名称',width:120,align:'center',editor:{type:'validatebox',options:{required:true}},
						styler: function(value,row,index){
							if (value ==null){
								return 'background-color:#DA776F;';}}},
					{field:'term',title:'学期',width:60,align:'center',editor:{type:'validatebox',options:{required:true}},
						styler: function(value,row,index){
							if (value ==null){
								return 'background-color:#DA776F;';}}},
					{field:'url',title:'课件',width:80,align:'center',editor:'String'},
					{field:'enroll_year',title:'年份',width:80,align:'center',editor:'String',
						styler: function(value,row,index){
							if (value ==null){
								return 'background-color:#DA776F;';}}},
					{field:'status',title:'状态',hidden:true},
			    ]],
			})
			
			$("#dategrid-east").datagrid({
		    url: '<%=request.getContextPath()%>/GetMappingGlobalMajor',
		 	fit:'ture',
		 	autoRowHeight:'false',
		 	fitColumns:'true',
		 	loadMsg:'正在获取没有global_major_id的专业...',
		 	sortName:'id',
		    columns:[[
					{field:'name',title:'专业',width:100,align:'left'},
					{field:'level',title:'层级',width:60,align:'center'},
		    		]]
			})
			
			var tab = $('#centernavigation').tabs('getSelected');
			$('#centernavigation').tabs('update', {
				tab:tab,
				options: {
					"title": '修改jxjh',
					"content":'<div id="tabDiv'+'">加载数据中,请稍候....</div>',
				}
			})
			tab.panel('refresh','<%=request.getContextPath()%>/GoUpdateJxjh?type=replenish');
		}).fail(function(res) {
			alert("返回数据失败");				
		});
		
	});
	</script>
</body>
</html>