<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>login</title>
<%@ include file="include/header.jsp" %>
</head>
<body>
<%@ include file="include/menu.jsp" %>
<hr>
<form action="${path}/check" method="post">
	<div>
		<label>ID</label>
		<input name="id" type="text" id="id" value="admin" >
	</div>
	<div>
		<label>Password</label>
		<input name="password" type="password" id="password" value="welcome" >
	</div>
	<button type="submit" value="submit">로그인</button>
</form>

</body>
</html>
