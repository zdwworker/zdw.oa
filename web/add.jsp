<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>增加部门</title>
	</head>
	<body>
		<form action="<%=request.getContextPath()%>/dept/add" method="post">
			部门编号<input type="text" name="no" /><br>
			部门名称<input type="text" name="name" /><br>
			部门地址<input type="text" name="add" /><br>
			<input type="submit" value="确认"/>
			<input type="button" onclick="window.history.back()" value="取消" />
		</form>
	</body>
</html>
