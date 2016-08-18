<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="icon" href="resources/ui/image/head.png" type="image/x-icon">
	<link rel="stylesheet" type="text/css" href="resources/ui/themes/bootstrap/easyui.css">
	<link rel="stylesheet" type="text/css" href="resources/ui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="resources/ui/css/school.css">
	<script type="text/javascript" src="resources/ui/jquery.min.js"></script>
	<script type="text/javascript" src="resources/ui/jquery.easyui.min.js"></script>
<title>教学计划补充</title>
</head>
<body>
	<div style="position:absolute;top:40%;left:30%;transform: scale(1.5);">
	<form action="">
		<table id="chooseCopyTeachingplan" style="font-family: 微软雅黑">
			<tr>
				<th>专业id</th>
				<th>专业</th>
				<th>层级</th>
				<th>年份</th>
				<th style="text-align: center">已有的教学计划</th>
			</tr>
		</table>
		<a id="refresh-button"  class="easyui-linkbutton" style="position: relative;left: 80px;width:60px;height: 20px">刷新</a>
		<a id="submit-button"  class="easyui-linkbutton" style="position: relative;left: 280px;width:60px;height: 20px">提交</a>
	</form>
	<script type="text/javascript">
			var rows=$('#dategrid-south').datagrid('getData').rows;
			var rownumber=rows.length;
			var table=$("#chooseCopyTeachingplan");
			
			var url="<%=request.getContextPath()%>/GetTeachingplan?majorid=";
			
			for(var i=0;i<rownumber;i++){
				var majorid="<td id='majorid'>"+rows[i].id+"</td>"
				var major="<td id='major'>"+rows[i].key+"</td>"
				var level="<td id='level'>"+rows[i].value+"</td>"
				var enroll_year="<td id='enroll_year'>"+rows[i].temp+"</td>"
				var options="<td width:210px;><input class='easyui-combobox' style='width: 250px;'data-options=\"textField:'value',url:'"+url+rows[i].id+"'\"></td>"
				var tr="<tr>"+majorid+major+level+enroll_year+options+"</tr>";
				table.append(tr)
			}
			
			$("#refresh-button").click(function(){
				$("tr").each(function(i){
					for (var j = 0; j < 5; j++) {
					alert($("this.td:eq(j)"));
					}
				})
			})
			
	
	</script>
	</div>

</body>
</html>