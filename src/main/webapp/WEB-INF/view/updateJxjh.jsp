<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改教学计划</title>
	<link rel="stylesheet" type="text/css" href="resources/ui/themes/bootstrap/easyui.css">
	<link rel="stylesheet" type="text/css" href="resources/ui/themes/icon.css">
	<script src="resources/ui/jquery.min.js"></script>
	<script src="resources/ui/jquery.easyui.min.js"></script>
</head>
<body>
	<div id="updatejxjh-main" style="position:absolute;top:60%;left:40%;margin: -100px 0px 0px -100px;transform: scale(1.5);">
		 <form id=modifyjxjh>
			<div>
			          专业
				<input  id="oldMajor" class="easyui-combobox" name="oldMajor" data-options="textField:'value',url:'<%=request.getContextPath()%>/GetJxjhMajor'"> 
				<input  id="newMajor" class="easyui-combobox" name="newMajor" data-options="textField:'value',url:'<%=request.getContextPath()%>/GetGlobalMajor'"> 
			</div>
			<div style="height: 15px"></div>
			<div>
			          层级
				<input  id="oldLevel" class="easyui-combobox" name="oldLevel" data-options="textField:'value',url:'<%=request.getContextPath()%>/GetJxjhLevel'"> 
				<input  id="newLevel" class="easyui-combobox" name="newLevel" data-options="textField:'value',data: [{value: '高起专'},{value: '高起本'},{value: '专升本'}]" />
			</div>
			<div style="height: 15px"></div>
			<div>
			         课程
				<input  id="oldCourse" class="easyui-combobox" name="oldCourse" data-options="textField:'value',url:'<%=request.getContextPath()%>/GetJxjhCourse'"> 
				<input  id="newCourse" class="easyui-combobox" name="newCourse"  />
			</div>
			<div style="height: 15px"></div>
			<div>
			        年份
				<input  id="oldYear" class="easyui-combobox" name="oldYear" data-options="textField:'value',url:'<%=request.getContextPath()%>/GetJxjhYear'"> 
				<input  id="newYear" class="easyui-combobox" name="newYear" data-options="textField:'value',data:[{value: '2013'},{value: '2014'},{value: '2015'},{value: '2016'}]" />
			</div>
			<div style="height: 15px"></div>
			<div>
				<a id="modify"  class="easyui-linkbutton" style="width:60px">修   改</a>
				<a id="submit"  class="easyui-linkbutton" style="position: relative;left:70%;width:60px">提   交</a>
			</div>
		</form>
	</div>
	
	<script>
	$("#modify").click(function(){
		$.ajax({
		    url: '<%=request.getContextPath()%>/UpdateJxjh',
		    type: 'GET',
		    cache: false,
		    data: $('#modifyjxjh').serialize(),
		    processData: false,
		}).done(function(res) {
					$('#oldMajor').combobox('reload');
					$('#oldLevel').combobox('reload'); 
					$('#oldCourse').combobox('reload'); 
					$('#oldYear').combobox('reload'); 
					$('#dategrid-north').datagrid('reload');
					$('#dategrid-east').datagrid('reload');
		
		}).fail(function(res) {
			alert("修改失败");	
		});
	});
	

	
	
	$("#submit").click(function(){
		var type="${requestScope.to}";
		if(type=="new"){
			$.ajax({
			    url: '<%=request.getContextPath()%>/TeachingplanOk',
			    type: 'GET',
			}).done(function(res) {
				alert("提交成功");
			}).fail(function(res) {
				alert("提交失败");
			});
		}
		if(type=="replenish"){
			$('#dategrid-north').datagrid({  
			    url:'/demo/user/getUsers',  
			    queryParams:{  
			        id:'001',  
			        state:'ok'  
			    }  
			}); 
			
			
			$.ajax({
			    url: '<%=request.getContextPath()%>/AddTeachingplan',
			    type: 'GET',
			}).done(function(res) {
				if( !jQuery.isEmptyObject(res)){
					alert("上传失败,这些教学计划已经存在,无法上传");
					$("#dategrid-north").datagrid({
						data:res,
					 	fit:'ture',
					 	autoRowHeight:'false',
					 	fitColumns:'true',
					 	loadMsg:'加载已经上传过的教学计划...',
					 	sortName:'id',
					    columns:[[
							{field:'key',title:'专业',width:30,align:'center'},
							{field:'value',title:'层次',width:100,align:'center'},
							{field:'temp',title:'课程',width:60,align:'left'},
							{field:'count',title:'学期',width:100,align:'center'}
					    ]]
					})	
					
				}else{
					$('#mainId').layout('expand','south');
					
					
					$("#dategrid-south").datagrid({
						url: '<%=request.getContextPath()%>/NoMappingTeachingPlanXueSheng',
					 	fit:'ture',
					 	autoRowHeight:'false',
					 	fitColumns:'true',
					 	loadMsg:'正在获取没有找到教学计划的学生...',
					 	sortName:'id',
					    columns:[[
							{field:'id',title:'id',width:30,align:'center',hidden:true},
							{field:'key',title:'专业',width:30,align:'center'},
							{field:'value',title:'层次',width:100,align:'center'},
							{field:'temp',title:'年级',width:60,align:'center'},
							{field:'count',title:'人数',width:100,align:'center'}
					    ]]
					})	
					
					$.ajax({
					    url: '<%=request.getContextPath()%>/GetTeachingplanCourseInsertCount',
					    type: 'GET',
					}).done(function(res) {
						
						alert("提交成功,共计导入教学计划"+res+"条")
					});
					
				}
				
				
			}).fail(function(res) {
				alert("提交失败");
			});
		}
	});
    </script>
</body>
</html>