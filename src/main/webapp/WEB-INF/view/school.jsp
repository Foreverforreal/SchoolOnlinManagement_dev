<%@ page language="java" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="resources/ui/themes/bootstrap/easyui.css">
	<link rel="stylesheet" type="text/css" href="resources/ui/themes/icon.css">
	<script src="resources/ui/jquery.min.js"></script>
	<script src="resources/ui/jquery.easyui.min.js"></script>
<title>新建学校</title>
</head>

<body>

	<div id="school-main" style="position:absolute;top:70%;left:40%;margin: -100px 0px 0px -100px;transform: scale(1.5);">
		<form id="newschool"  method="post" name="fileupload" enctype="multipart/form-data" >
			<table>
				<tr>
					<td>
						<input type="file"  style="border:gray solid 1px ;zoom:1.1" name="filepath" />	
						<div style="height: 10px"></div>
						<a class="filedmes">校名:</a>  <input id="schoolName" name="schoolName"  class="forminput" type="text">
						<a class="filedmes">后缀:</a>  <input id="schoolSymbol" name="schoolSymbol"  class="forminput" type="text" >
					</td>
					<td style=" vertical-align:center;">
						<img id="submitimg" alt="提交" src="resources/ui/image/submit.png" style="transform: scale(0.65);margin-top: auto;margin-bottom: auto">
					</td>
				</tr>
			</table>	
		 </form>
	</div>
	
	<div id="myShow" style="display:none;">
        	<img alt="loading" src="resources/ui/image/wait.gif" />
    </div>
    
	<script>
	$("#submitimg").click(function(){
		
		$.ajax({
		    url: '<%=request.getContextPath()%>/NewSchool',
		    type: 'POST',
		    cache: false,
		    data: new FormData($('#newschool')[0]),
		    idField:'id',
		    processData: false,
		    contentType: false,
		    beforeSend:function(XMLHttpRequest){
		    	$("#school-main").css({display:"none"});
		    	$("#myShow").css({display:"",position:"absolute",top:"50%",left:"50%",margin:"-60px 0px 0px -169px"})
		    }
		}).done(function(res) {
			if(res=='ok'){
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
						{field:'id',title:'id',width:30,align:'center',editor:'String'},
						{field:'major',title:'专业',width:100,align:'center',
							styler: function(value,row,index){
								if (value ==null){
									return 'background-color:#DA776F;';}}},
						{field:'level',title:'层级',width:60,align:'center',
							styler: function(value,row,index){
								if (value ==null){
									return 'background-color:#DA776F;';}
								else if (!value=='高起专'||value=='高起本'||value=='专升本'){
									return 'background-color:#2196F3;'; }
							}},
						{field:'name',title:'课程名称',width:120,align:'center',
							styler: function(value,row,index){
								if (value ==null){
									return 'background-color:#DA776F;';}}},
						{field:'term',title:'学期',width:60,align:'center',editor:'textbox',
							styler: function(value,row,index){
								if (value ==null){
									return 'background-color:#DA776F;';}}},
						{field:'url',title:'课件',width:80,align:'center',editor:'textbox'},
						{field:'enroll_year',title:'年份',width:80,align:'center',editor:'textbox',
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
						{field:'name',title:'专业',width:100,align:'center'},
						{field:'level',title:'层级',width:60,align:'center'},
			    		]]
				})
			
				var tab = $('#centernavigation').tabs('getSelected');
				$('#centernavigation').tabs('update', {
					tab:tab,
					options: {
						"title": '修改计划',
						"content":'<div id="tabDiv'+'">加载数据中,请稍候....</div>',
					}
				})
		}
			
		tab.panel('refresh','<%=request.getContextPath()%>/GoUpdateJxjh?type=new');
			
		}).fail(function(res) {
			alert("返回数据失败");				
		});
		
	});
	
	var editIndex = undefined;
	function endEditing(){
		if (editIndex == undefined){return true}
		if ($('#dategrid-north').datagrid('validateRow', editIndex)){
			var ed = $('#dategrid-north').datagrid('getEditor', {index:editIndex,field:'productid'});
			var productname = $(ed.target).combobox('getText');
			$('#dategrid-north').datagrid('getRows')[editIndex]['productname'] = productname;
			$('#dategrid-north').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickRow(index){
		alert(index);
		if (editIndex != index){
			if (endEditing()){
				$('#dategrid-north').datagrid('selectRow', index)
						.datagrid('beginEdit', index);
				editIndex = index;
			} else {
				$('#dategrid-north').datagrid('selectRow', editIndex);
			}
		}
	}
	
	
	</script>
</body>
</html>