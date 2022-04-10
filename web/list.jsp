<%@ page import="java.util.List" %>
<%@ page import="com.zdw.dept.Dept" %>
<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>部门列表</title>
	</head>
	<body>
		<script type="text/javascript">
			window.onload=function(){
					document.getElementById('a1').onclick=function(){
						alert('确定删除吗？');
					}
			}
		</script>
		<h1 align="center">部门列表</h1>
		<hr >
		<table border="1" align="center" width="50%">
			<tr>
				<td>序号</td>
				<td>部门编号</td>
				<td>部门名称</td>
				<td>操作</td>
			</tr>

<%
	List<Dept> list = (List)request.getAttribute("list");
	int i=0;
	for (Dept dept:list){



	/*List<Dept> deptList = (List<Dept>)request.getAttribute("list");
		// 循环遍历
		int i = 0;
		for(Dept dept:deptList){*/
%>
			<tr>
				<td><%=++i%></td>
				<td> <%=dept.getEmptno()%></td>
				<td><%=dept.getEname()%></td>
				<td>
					<a href="<%=request.getContextPath()%>/dept/detail?deptno=<%=dept.getEmptno()%>">详细</a>
					<a href="<%=request.getContextPath()%>/dept/del?deptno=<%=dept.getEmptno()%>" id='a1'>删除</a>
					<a href="<%=request.getContextPath()%>/dept/modify?deptno=<%=dept.getEmptno()%>">修改</a>
				</td>
			</tr>
<%
	}
%>

		</table>
		<hr >
		<a href="<%=request.getContextPath()%>/add.jsp">增加部门</a>
	</body>
</html>
