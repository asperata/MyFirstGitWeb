<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<h3>登录成功，欢迎您：${sessionScope.myuser.realname }</h3>
<form action="findbyid" method="post" >
	请输入编号：<input type="text" value="2" name="userid" />
	<input type="submit" value="查询" />
</form>
</body>
</html>