<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Log in</title>
	
	<!-- Tell the browser to be responsive to screen width -->
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	
	<!-- Bootstrap 3.3.7 -->
	<link rel="stylesheet" href="setfiles/bower_components/bootstrap/dist/css/bootstrap.min.css">
	<!-- Font Awesome -->
	<link rel="stylesheet" href="setfiles/bower_components/font-awesome/css/font-awesome.min.css">
	<!-- Ionicons -->
	<link rel="stylesheet" href="setfiles/bower_components/Ionicons/css/ionicons.min.css">
	<!-- Theme style -->
	<link rel="stylesheet" href="setfiles/dist/css/AdminLTE.min.css">
	<!-- iCheck -->
	<link rel="stylesheet" href="setfiles/plugins/iCheck/square/blue.css">
	
	<!-- Google Font -->
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
	
	<!-- jquery 하단에 존재. 공통파일 header로 묶을 것.-->
	<%@ include file="../common/header.jsp" %>
</head>
<body class="hold-transition login-page">
<div class="login-box">
  <div class="login-logo">
    <a href="../main/guestmain"><b>Ant</b>People</a>
  </div>
  <!-- /.login-logo -->
  <div class="login-box-body">
    <p class="login-box-msg">${messege}</p>

    <!-- <form action="logincheck" method="post"> -->
    <form>
      <div class="form-group has-feedback">
        <input name="email" id="email" type="text" class="form-control" placeholder="Email">
        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
        <span class="checkMessage" style="color:green; display:none"></span>
      </div>
      <div class="form-group has-feedback">
        <input name="password" id="password" type="password" class="form-control" placeholder="Password">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        <span class="checkMessage" style="color:green; display:none"></span>
      </div>
      <div class="row">
        <div class="col-xs-8">
          <div class="checkbox icheck">
            <label>
              <input type="checkbox"> Remember Me
            </label>
          </div>
        </div>
        <!-- /.col -->
        <div class="col-xs-4">
          <button id="login" type="submit" class="btn btn-primary btn-block btn-flat">로그인</button>
        </div>
        <!-- /.col -->
      </div>
    </form>
    
    <a href="#">I forgot my password</a><br>
    <a href="register" class="text-center">Register a new membership</a>

  </div>
  <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<!-- jQuery 3.3.1 : templet 기본버전-->
<script src="setfiles/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="setfiles/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="setfiles/plugins/iCheck/icheck.min.js"></script>
<script>
  function getName(targetName){
    switch(targetName) {
      case "email" : return "이메일을"; break;
      case "password" : return "비밀번호를"; break;
    }
  };
  
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' /* optional */
    });
    
    $('#login').click(function(){
    	//버튼클릭시
    	// 1. 빈칸 존재여부 확인			--> 없을 시 해당 칸을 찾아 span으로 문구표시
    	// 2. ajax로 전체 전송				--> 이메일 존재여부, password일치여부 확인 후 리턴
    	// 3. 로그인 성공 시 				--> 페이지 리다이렉션
    	// 4. 로그인 실패 시				--> 리턴값으로 원인을 받아 span tag로 문구표시
    	
    	//input란 기입여부 확인
    	var blankCheck = 0;
    	$('input[class="form-control"]').each(function() {
    		var attrName = $(this).attr("name");
			var typeSelection = getName(attrName);
			console.log(typeSelection)
    		if(typeof $(this).val() == "undefined" || $(this).val() == null || $(this).val() == ""){
    			$(this).siblings(".checkMessage").text(typeSelection + " 입력하세요.").css({'color':'red', 'display':'unset'});
    		}  else {
    			blankCheck++;
    		}
    	})
    	
    	//모두 기입된것을 확인. --> 전송
    	if(blankCheck = '2') {
    		var checkExist = { 
    			email : $("#email").val(),
    			password : $("#password").val(),
    		};
    		$.ajax({
	    		url : 'logincheck',
	    		method : 'post',
	    		data : JSON.stringify(checkExist),
	    		contentType: 'application/json',
	    		//서버에서 보내줄 datatype.
				//dataType : ,
				error : function(response) {
					alert("통신실패, response: " + response);
				},
				success : function(response) {
					alert("통신성공, response: " + response);
					//성공 시 이메일 존재여부 판별.
					//존재 --> 이메일이 존재한다는 알림 띄움.
					//없음 --> 회원가입 폼 자동으로 전송.
				}
	    	});
    	}
    });
    
    
  });
</script>
</body>
</html>