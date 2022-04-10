<%@ page import="com.zdw.dept.Dept" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>详细信息</title>
	</head>
	<body>
	<%
		List<Dept> deptList = (List)request.getAttribute("deptList");
		for (Dept dept:deptList){
	%>

		部门编号:<%=dept.getEmptno()%>  <br>
		部门名称:<%=dept.getEname()%> <br>
		部门地址:<%=dept.getLoc()%>  <br>
	<%
		}
	%>
		<input type="button" onclick="window.history.back()" value="返回" />
	</body>
</html>
