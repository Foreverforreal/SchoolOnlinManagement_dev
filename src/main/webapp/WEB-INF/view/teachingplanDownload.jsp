<%@ page language="java" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="t_query" style="position:absolute;top:70%;left:45%;margin: -100px 0px 0px -100px;transform: scale(1.5);">
		<form id="query" >	
			<div style="position:absolute;left:30%;bottom:110%;">
				<a style="font-family: 微软雅黑;zoom:1.2">选择学校:</a>
				<span style="position: relative;left: 10px;top: -2px" >
				<input id="schoolSymbol" class="easyui-combobox" name='schoolSymbol' style="width: 100px" data-options="textField:'value',url:'<%=request.getContextPath()%>/GetSchoolSymbol'" >
				</span>
			</div>
			<div>
				专业<input  id="major" class="easyui-combobox" name="key"> 
				层级<input  id="level" class="easyui-combobox" name="value"> 
			</div>
			<div style="height: 15px"></div>
			<div>
				学期<input  id="term" class="easyui-combobox" name="count" > 
				年份<input  id="year" class="easyui-combobox" name="temp"  >
			</div>
			<br>
			
		 </form>
			<a id="download-button"  class="easyui-linkbutton" style="position: relative;width:60px;height: 20px">下载</a>
			<a id="clear"  class="easyui-linkbutton" style="position: relative;left:26px;width:60px;height: 20px">清  空</a>
			<a id="submit-teachingplan"  class="easyui-linkbutton" style="position: relative;left: 66px;width:60px;height: 20px">查询</a>
		 
		<script >
		  $(function(){
			  var school=$('#schoolSymbol').combobox({
				  url:'<%=request.getContextPath()%>/GetSchoolSymbol',
				  textField:'value',
				  onSelect:function(data){
					  var schoolSymbol=$('#schoolSymbol').combobox('getValue');
						 $.ajax({
							    url:'<%=request.getContextPath()%>/ChooseSchool?schoolSymbol='+schoolSymbol,
							    type:'get',
								}).done(function(res) {	
									if(res=="connect"){
										 var major = $('#major').combobox({
									            url:'<%=request.getContextPath()%>/GetTeachingplanMajor',
									            textField:'value',
									            onSelect:function(data){
									            	level.combobox({
									                    disabled:false,
									                    url:'<%=request.getContextPath()%>/GetTeachingplanLevel?major='+data.value,
									                    textField:'value'
									                	}).combobox('clear');
									            	year.combobox({
									                    disabled:false,
									                    url:'<%=request.getContextPath()%>/GetTeachingplanYear?majorId='+data.value,
									                    textField:'value'
									                	}).combobox('clear');
									            }
									        }).combobox('clear');
									        
									        var level = $('#level').combobox({
									            textField:'value'
									        });
									       $('#term').combobox({
									    	   	url:'<%=request.getContextPath()%>/GetTeachingplanTerm',
									            textField:'value'
									        });
									       var year = $('#year').combobox({
									            textField:'value'
									        });
									}
							}) 
				  }
			  })
			  
		  
		 $("#download-button").click(function(){
			 var schoolSymbol=$('#schoolSymbol').combobox('getValue');
			 $.ajax({
				    url: '<%=request.getContextPath()%>/downloadTeachingplanEbook?schoolSymbol='+schoolSymbol,
				    type: 'get',
				}).done(function(res) {	
					if(res){
						alert("教学计划已经下载完成")
					}
					
				})
			 
		 })
		 
		 $("#submit-teachingplan").click(function(){
			 var schoolSymbol=$('#schoolSymbol').combobox('getValue');
			 $.ajax({
				    url: '<%=request.getContextPath()%>/QueryTeachingplan',
				    data:$("#query").serialize(),
				    type: 'post',
				}).done(function(res) {	

					$("#dategrid-north").datagrid({
					data:res,
					singleSelect:'ture',
				 	fit:'ture',
				 	autoRowHeight:'false',
				 	fitColumns:'true',
				 	loadMsg:'加载教学计划......',
				 	sortName:'id',
				    columns:[[
							{field:'id',title:'id',width:20,align:'center'},
							{field:'major',title:'专业',width:100,align:'left'},
							{field:'level',title:'层级',width:30,align:'left'},
							{field:'name',title:'课程',width:120,align:'left'},
							{field:'term',title:'学期',width:15,align:'center'},
							{field:'url',title:'课件',width:200,align:'left'},
							{field:'enroll_year',title:'年份',width:30,align:'left'},
				    		]]
					})
				})
				})
				})
				
			$("#clear").click(function(){
				$("#major").combobox('clear');
				$("#level").combobox('clear');
				$("#term").combobox('clear');
				$("#year").combobox('clear');
		})
		 </script>
	</div>

</body>
</html>