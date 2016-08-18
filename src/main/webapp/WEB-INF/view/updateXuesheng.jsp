<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改学生信息</title>
	<link rel="stylesheet" type="text/css" href="resources/ui/themes/bootstrap/easyui.css">
	<link rel="stylesheet" type="text/css" href="resources/ui/themes/icon.css">
	<script src="resources/ui/jquery.min.js"></script>
	<script src="resources/ui/jquery.easyui.min.js"></script>
</head>
<body>
	<div id="updatexuesheng-main" style="position:absolute;top:70%;left:40%;margin: -100px 0px 0px -100px;transform: scale(1.5);">
		<form id="modifyxuesheng" enctype="application/x-www-form-urlencoded">
			<div>
			          专业
				<input  id="oldMajor" class="easyui-combobox" name="oldMajor" data-options="textField:'value',url:'<%=request.getContextPath()%>/getDistinctMajor'"> 
				<input  id="newMajor" class="easyui-combobox" name="newMajor" > 
			</div>
			<div style="height: 15px"></div>
			<div>
			          层级
				<input  id="oldLevel" class="easyui-combobox" name="oldLevel" data-options="textField:'value',url:'<%=request.getContextPath()%>/getDistinctLevel'"> 
				<input  id="newLevel" class="easyui-combobox" name="newLevel"  />
			</div>
			<div style="height: 15px"></div>
			<div>
				<a id="modify"  class="easyui-linkbutton" style="width:60px">修   改</a>
				<a id="clear"  class="easyui-linkbutton" style="position: relative;left:13%;width:60px">清  空</a>
				<a id="download"  class="easyui-linkbutton" style="position: relative;left:23%;width:60px">下  载</a>
				<a id="submit"  class="easyui-linkbutton" style="position: relative;left:33%;width:60px">提   交</a>
			</div>
		</form>
	</div>
	
	 <script>
		 $(function(){
		        var major = $('#newMajor').combobox({
		            url:'<%=request.getContextPath()%>/getMajor',
		            textField:'value',
		            onSelect:function(data){
		            	level.combobox({
		                    disabled:false,
		                    url:'<%=request.getContextPath()%>/getLevel?majorname='+data.value,
		                    textField:'value'
		                	}).combobox('clear');
		            }
		        }).combobox('clear');
		        
		        var level = $('#newLevel').combobox({
		            textField:'value'
		        });
		        
		        $("#dategrid-east").datagrid({
					url: '<%=request.getContextPath()%>/GetExistMajorLevel',
				 	fit:'ture',
				 	autoRowHeight:'false',
				 	fitColumns:'true',
				 	loadMsg:'该校所有专业...',
				 	sortName:'id',
				    columns:[[
						{field:'key',title:'已有专业',width:140,align:'left'},
						{field:'value',title:'层级',width:60,align:'center'},
				   		]]
					})
				});
	 
		$("#modify").click(function(){
			$.ajax({
			    url: '<%=request.getContextPath()%>/UpdateXueSheng',
			    type: 'GET',
			    cache: false,
			    data: $('#modifyxuesheng').serialize(),
			    processData: false,
			}).done(function(res) {
				$('#oldMajor').combobox('reload');
				$('#oldLevel').combobox('reload'); 
				$('#dategrid-north').datagrid('reload');
				
			}).fail(function(res) {
				alert("修改失败");				
			});
		});
		

		$("#clear").click(function(){
			$("#oldMajor").combobox('clear');
			$("#newMajor").combobox('clear');
			$("#oldLevel").combobox('clear');
			$("#newLevel").combobox('clear');
		})
		
		$("#download").click(function(){
			window.location.href='<%=request.getContextPath()%>/DownloanNoMappingXuesheng';
		})
		
		$("#submit").click(function(){
			$.ajax({
			    url: '<%=request.getContextPath()%>/StudentOK',
			    type: 'GET',
			    beforeSend:function(XMLHttpRequest){
			    	$("#updatexuesheng-main").css({display:"none"});
			    	$("#myShow").css({display:"",position:"absolute",top:"50%",left:"50%",margin:"-60px 0px 0px -169px"})
			    }
			}).done(function(res) {
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
				if(res=='ok'){
					var tab = $('#centernavigation').tabs('getSelected');
						$('#centernavigation').tabs('update', {
							tab: tab,
							options: {
								"title": '复制教学计划',
								"content":'<div id="tabDiv'+'">加载数据中,请稍候....</div>',
							}
						})
						tab.panel('refresh','<%=request.getContextPath()%>/GoCopyTeachingplan');
				}
			}).fail(function(res) {
				alert("提交学生失败");
			});
			
		});
	 </script>
	 
	  <div id="myShow" style="display:none;">
        	<img alt="loading" src="resources/ui/image/wait.gif" />
      </div>
	
</body>
</html>