<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Registration Page</title>
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
	
	
	<%@ include file="../common/header.jsp" %>
</head>
<body class="hold-transition register-page">
	<div class="register-box">
	  <div class="register-logo">
	    <a href="${path}/main/index"><b>Ant</b>People</a>
	  </div>
	
	  <div class="register-box-body">
	    <p class="login-box-msg">Register a new membership</p>
	
		<!-- 연결할 경로 -->
	    <!-- <form action="registercheck" method="post"> -->
	    <form>
	      <div class="form-group has-feedback">
	        <input type="text" id="userName" name="userName" class="form-control" placeholder="Your name">
	        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
	        <span class="checkMessage" style="color:green; display:none"></span>
	      </div>
	      
	      <div class="form-group has-feedback" style="height:34px">
	        <select name ="role" id="role" class="form-control" style="padding-left:7px;">
	          <option value ='0' selected>Position</option>
	          <c:set var="roleList" value="${requestScope.roleList}"/>
			  <c:forEach var="rName" items="${roleList}">
			    <option value="${rName.role_id}">${rName.role}</option>
			  </c:forEach>
	        </select>
	        <span class="checkMessage" style="color:green; display:none"></span>
	      </div>
	      
	      <div class="form-group has-feedback" style="height:34px">
	        <select name ="store" id="store" class="form-control" style="padding-left:7px">
	          <option value='0' selected>Place</option>
	          <c:set var="storeList" value="${requestScope.storeList}"/>
			  <c:forEach var="sName" items="${storeList}">
			    <option value="${sName.store_id}">${sName.store}</option>
			  </c:forEach>
	        </select>
	        <span class="checkMessage" style="color:green; display:none"></span>
	      </div>
	      
	      <div class="form-group has-feedback">
	        <input type="email" name="email" id="email" class="form-control" placeholder="Email">
	        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
	        <span class="checkMessage" style="color:green; display:none"></span>
	      </div>
	      
	      
	      <div class="form-group has-feedback">
	        <input type="password" name="password" id="password" class="form-control" placeholder="Password">
	        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
	        <span class="checkMessage" style="color:green; display:none"></span>
	      </div>
	      
	      <div class="form-group has-feedback">
	        <input type="password" name="password2" id="password2" class="form-control" placeholder="Retype password">
	        <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
	        <span class="checkMessage" style="color:green; display:none"></span>
	      </div>
	      
	      <div class="row">
	        <!-- 약관동의 항목
	        <div class="col-xs-8">
	          <div class="checkbox icheck">
	            <label>
	              <input type="checkbox"> I agree to the <a href="#">terms</a>
	            </label>
	          </div>
	        </div>
	         -->
	        <!-- /.col -->
	        <div class="col-xs-4">
	          <button id="register" type="button" class="btn btn-primary btn-block btn-flat">Register</button>
	        </div>
	        <!-- /.col -->
	      </div>
	    </form>
	
	    <a href="${path}/user/login" class="text-center">I already have a membership</a>
	  </div>
	  <!-- /.form-box -->
	</div>
	<!-- /.register-box -->
	
	<!-- jQuery 3 -->
	<script src="setfiles/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap 3.3.7 -->
	<script src="setfiles/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- iCheck -->
	<script src="setfiles/plugins/iCheck/icheck.min.js"></script>
	<script>
	  function getName(targetName){
		  switch(targetName) {
		  case "userName" : return "이름을"; break;
		  case "email" : return "이메일을"; break;
		  case "password" : return "비밀번호를"; break;
		  case "password2" : return "비밀번호를"; break;
		  case "role" : return "포지션을"; break;
		  case "store" : return "지점을"; break;
		  }
	  };
	  
	  $(function () {
	    $('input').iCheck({
	      checkboxClass: 'icheckbox_square-blue',
	      radioClass: 'iradio_square-blue',
	      increaseArea: '20%' /* optional */
	    });
	    
	    $('#register').click(function(){
	    	console.log($("#role option:selected").val());
	    	//버튼클릭시
	    	// 1. 빈칸 존재여부 확인			--> 없을 시 해당 칸을 찾아 span으로 문구표시
	    	// 2. 비밀번호 일치여부 확인		--> 불일치 시 두번째 칸 아래 span으로 문구표시
	    	// 3. ajax로 이메일 존재여부 확인	--> 이메일 존재 시 이메일 칸 아래 span으로 문구표시
	    	// 4. 전부 충족 시 					--> 자동으로 폼 내용 전송
	    	
	    	//input란 기입여부 확인
	    	var blankCheck = 0;
	    	$('input').each(function() {
	    		var attrName = $(this).attr("name");
    			var typeSelection = getName(attrName);
    			console.log(typeSelection)
	    		if(typeof $(this).val() == "undefined" || $(this).val() == null || $(this).val() == ""){
	    			$(this).siblings(".checkMessage").text(typeSelection + " 입력하세요.").css({'color':'red', 'display':'unset'});
	    		} else if( attrName == "password2") {
	    			// password2가 채워져 있음 --> 일치여부 확인
	    			//  불일치 --> 일치하지 않습니다.
	    			//  일치 --> 일치합니다.
	    			var passwd1 = $("#password").val();
	     	    	var passwd2 = $("#password2").val();
	    			console.log("패스워드2")
	    			console.log(passwd2)
	    			if(passwd1 != passwd2) {
	    				console.log("불일치")
	    				$(this).siblings(".checkMessage").text("비밀번호가 일치하지 않습니다.").css({'color':'red', 'display':'unset'});
	    			} else {
	    				console.log("일치")
	    				$(this).siblings(".checkMessage").text("비밀번호가 일치합니다.").css({'color':'green', 'display':'unset'});
	    			}
	    		} else {
	    			blankCheck++;
	    		}
	    	})
	    	
	    	$('select').each(function() {
	    		var attrName = $(this).attr("name");
    			var typeSelection = getName(attrName);
    			var valueCheck = $(this).children("option:selected").val();
	    		if(valueCheck == 0) {
	    			console.log(typeSelection);
	    			$(this).parent().css({'margin-bottom':'35px'})
	    			$(this).siblings(".checkMessage").text(typeSelection+" 선택하세요.").css({'color':'red', 'display':'unset'});
	    		} else {
	    			blankCheck++;
	    		}
	    	})
	    	
	    	//모두 기입된것을 확인. --> 전송
	    	if(blankCheck = '5') {
	    		var checkExist = { 
	    			email : $("#email").val(),
	    			password : $("#password").val(),
	    			name : $("#userName").val(),
	    			role : $("#role option:selected").val(),
	    			store : $("#store option:selected").val(),
	    		};
		    	//console.log(checkExist);
		    	//if(typeof checkExist.email == "undefined" || checkExist.email == null || checkExist.email == ""){
		    	//	alert('이메일을 입력해주세요.');
		    	//} else {
		    		$.ajax({
			    		url : 'registercheck',
			    		method : 'post',
			    		data : JSON.stringify(checkExist),
			    		contentType: 'application/json',
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
		    	//}
	    	}
	    });
	  });
	</script>
</body>
</html>
