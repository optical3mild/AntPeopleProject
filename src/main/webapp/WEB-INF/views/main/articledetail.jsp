<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Article</title>
  
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
  <!-- AdminLTE Skins. Choose a skin from the css/skins folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="setfiles/dist/css/skins/skin-blue.css">

  <!-- Google Font -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
  
  
  
  <%@ include file= "../common/header.jsp" %>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <!-- _top.jspf -->
  <%@ include file="../common/_top.jspf" %>
  
  <!-- Left side column. contains the logo and sidebar -->
  <%@ include file="../common/_nav.jspf" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Common - Notice - ArticleDetail(글 상세보기)
        <small>Control panel</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="${path}/#"><i class="fa fa-dashboard"></i>Home</a></li>
        <li class="active">Main</li>
      </ol>
    </section>
<!-- ------------------------------------------------- -->    
    <!-- Main content -->
    <section class="content">
      <div class="row">
        
        <!-- right column -->
        <div class="col-md-12">
          <c:set var="article" value="${requestScope.detail}"/>
          <c:set var="category" value="${requestScope.category}"/>
          <!-- general form elements disabled -->
          <div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">[${category}] ${article.title}</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <form role="form" method="post">
                <!-- text input -->
                <div class="form-group">
                  <label style="display:inline">작성자:&nbsp;&nbsp;${article.user.name}</label>&nbsp;&nbsp;&nbsp;&nbsp;
                  <label style="display:inline">작성일:&nbsp;&nbsp;${article.updatedAt}</label>
                </div>
                
                <!-- textarea -->
                <div class="form-group">
                  <textarea class="form-control" rows="3" disabled style="background-color: white;">
                    ${article.description}
                  </textarea>
                </div>
                <c:if test="${category == '공지사항'}">
                <div class="box-footer" style="padding-left: 0; padding-right:0;">
                  <button type="button" class="btn btn-default" onclick="location.href='noticepage' ">목록으로</button>
                  <button type="button" class="btn btn-info pull-right" style="margin: 0 0 0 20px" onclick="location.href='location.href='deletenotice?id=${article.notice_id} ">삭제</button>
                  <button type="button" class="btn btn-info pull-right" onclick="location.href='updatenoticepage?id=${article.notice_id}' ">수정</button>
                </div>
                </c:if>
                <c:if test="${category == '자유게시판'}">
                <div class="box-footer" style="padding-left: 0; padding-right:0;">
                  <button type="button" class="btn btn-default" onclick="location.href='bbspage' ">목록으로</button>
                  <button type="button" class="btn btn-info pull-right" style="margin: 0 0 0 20px" onclick="location.href='deletebbs?id=${article.bbs_id}'">삭제</button>
                  <button type="button" class="btn btn-info pull-right" onclick="location.href='updatebbspage?id=${article.bbs_id}'">수정</button>
                </div>
                </c:if>
              </form>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!--/.col (right) -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
<!-- -------------------------------------------- -->    
  </div>
  <!-- /.content-wrapper -->
  <%@ include file = "../common/_bottom.jspf" %>

</div>
<!-- ./wrapper -->

<!-- jQuery 3.3.1 -->
<script src="setfiles/bower_components/jquery/dist/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="setfiles/bower_components/jquery-ui/jquery-ui.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="setfiles/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- AdminLTE App : navbar 관련-->
<script src="setfiles/dist/js/adminlte.min.js"></script>

<script>
//오류발생.. 주석내에 el이나 tag형식 사용하지 말것..
/*$(function() {
	//jstl의 변수를 javascript로 바로 사용 불가. ' c:out value="jstl변수명" '을 사용할 것.
	//바로 el로 받을경우 not defined(?) 같은 오류로 인식되지 않는다.
	var articleCategory = '<c:out value="${categoryOption}" />';
	console.log(articleCategory);
	$('.selection option').val(articleCategory).attr('selected', true);
	
	//:contains() --> ()안에 변수 삽입불가. 변수명을 문자열로 인식함.
	//$('.selection option:contains(Board)').attr('selected', true);
});*/

</script>

</body>
</html>