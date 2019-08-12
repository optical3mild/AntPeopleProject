<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>세션 만료</title>
	
	<!-- Tell the browser to be responsive to screen width -->
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	
	<%@ include file= "../common/_header_css_sum.jspf" %>
</head>
<body class="hold-transition login-page">

<script type="text/javascript">
	var returnUrl = "login";
	alert("세션이 만료되었습니다. 다시 로그인 해주세요.");
	document.location.href = returnUrl;
</script>

<!-- jQuery 3.3.1 : templet 기본버전-->
<script src="setfiles/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="setfiles/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

</body>
</html>