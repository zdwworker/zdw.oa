<%@ page import="java.util.List" %>
<%@ page import="com.zdw.dept.Dept" %>
<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>修改部门</title>
	</head>
	<body>
		<form action="<%=request.getContextPath()%>/dept/alter" method="post">
			<%
				List<Dept> deptList = (List)request.getAttribute("deptList");
				for(Dept dept:deptList){
			%>

			部门编号<input type="text" name="no" value="<%=dept.getEmptno()%>"/><br>
			部门名称<input type="text" name="name" value="<%=dept.getEname()%>"/><br>
			部门地址<input type="text" name="add" value="<%=dept.getLoc()%>/"><br>
			<%
				}
			%>
			<input type="submit" value="确认"/>
			<input type="button" onclick="window.history.back()" value="取消" />
		</form>
	</body>
</html>
