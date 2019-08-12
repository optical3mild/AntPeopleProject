<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>AntPeople 로그인</title>
	
	<!-- Tell the browser to be responsive to screen width -->
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	
	<%@ include file= "../common/_header_css_sum.jspf" %>
	
</head>
<body class="hold-transition login-page">
<div class="login-box">
  <div class="login-logo">
    <a href="../main/mainpage"><b>Ant</b>People</a>
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
        <input name="password" onkeyup="enterkey();" id="password" type="password" class="form-control" placeholder="Password">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        <span class="checkMessage" style="color:green; display:none"></span>
      </div>
      <div class="row">
        <div class="col-xs-4">
        </div>
        <!-- /.col -->
        <div class="col-xs-4">
          <button id="login" type="button" class="btn btn-primary btn-block btn-flat">로그인</button>
        </div>
      </div>
    </form>
    
    <a href="register" class="text-center">회원가입이 되어 있지 않으신가요?</a>

  </div>
  <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<!-- jQuery 3.3.1 : templet 기본버전-->
<script src="setfiles/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="setfiles/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<script>
function loginProcess() {
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
    		url : '${path}/user/logincheck',
    		method : 'post',
    		data : JSON.stringify(checkExist),
    		contentType: 'application/json',
    		//서버에서 보내줄 datatype.
			//dataType : ,
			error : function(response) {
				alert("존재하지 않는 이메일 입니다.");
			},
			success : function(response) {
				document.location.href = response;
				//성공 시 이메일 존재여부 판별.
				//존재 --> 이메일이 존재한다는 알림 띄움.
				//없음 --> 회원가입 폼 자동으로 전송.
			}
    	});
	}
}
	function enterkey() {
	    if (window.event.keyCode == 13) {
	    	loginProcess();
	    }
	}

  function getName(targetName){
    switch(targetName) {
      case "email" : return "이메일을"; break;
      case "password" : return "비밀번호를"; break;
    }
  };
  
  $(function () {
    $('#login').click(function(){
    	//버튼클릭시
    	// 1. 빈칸 존재여부 확인			--> 없을 시 해당 칸을 찾아 span으로 문구표시
    	// 2. ajax로 전체 전송				--> 이메일 존재여부, password일치여부 확인 후 리턴
    	// 3. 로그인 성공 시 				--> 페이지 리다이렉션
    	// 4. 로그인 실패 시				--> 리턴값으로 원인을 받아 span tag로 문구표시
    	
    	loginProcess();
    });
  });
</script>
</body>
</html>