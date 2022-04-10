<%--
  Created by IntelliJ IDEA.
  User: 云胡不喜
  Date: 2022/3/26
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>


<form action="<%=request.getContextPath()%>/dept/login" method="post">
    <input type="text" name="account"><br>
    <input type="password" name="password"><br>
    <input type="checkbox" name="f" value="1">十天内免登录<br>
    <input type="submit" value="login"><br>
</form>
</body>
</html>
