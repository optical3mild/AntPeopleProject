<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Write</title>
  
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
        Common - Notice - Write Article(글 쓰기)
        <small>Control panel</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Dashboard</li>
      </ol>
    </section>
<!-- ------------------------------------------------- -->    
    <!-- Main content -->
    <section class="content">
      <div class="row">
        
        <!-- right column -->
        <div class="col-md-12">
          <c:set var="article" value="${requestScope.article}"/>
          <!-- general form elements disabled -->
          <div class="box box-info">
            <div class="box-header with-border">
            </div>
            <!-- /.box-header -->
            <div class="box-body">
            
              <!-- 새글작성 : order값으로 newArticle을 받으면 실행. -->
              <c:if test="${order == 'newArticle'}">
	              <form role="form" action="${path}/#">
	                <!-- text input -->
	                <div class="form-group">
	                  <label style="display:inline">작성자:&nbsp;&nbsp;${userName}</label>&nbsp;&nbsp;&nbsp;&nbsp;
	                  <label style="display:inline">작성일:&nbsp;&nbsp;${today}</label>
	                </div>
	                
	                <div class="form-group">
	                  <label>Title</label>
	                  <input type="text" class="form-control" name="title">
	                </div>
	
	                <!-- textarea -->
	                <div class="form-group">
	                  <label>The Body</label>
	                  <textarea class="form-control" rows="3" name="theBody"></textarea>
	                </div>
	
	                <div class="form-group">
	                  <label>Category</label>
	                  <select class="form-control" name="category">
	                    <option value="notice">Notice</option>
	                    <option value="board">Board</option>
	                  </select>
	                </div>
					<div class="box-footer" style="padding-left: 0; padding-right:0;">
	                  <button type="button" class="btn btn-default" onclick="location.href='${path}/#' ">목록으로</button>
	                  <button type="submit" class="btn btn-info pull-right">작성완료</button>
	                </div>
	                <!-- /.box-footer -->
	              </form>
              </c:if>
              
              <!-- 수정 -->
              <c:if test="${order == 'modifyArticle'}">
	              <form role="form" action="${path}/#">
	                <!-- text input -->
	                
	                <div class="form-group">
	                  <label style="display:inline">작성자:&nbsp;&nbsp;${article.userName}</label>&nbsp;&nbsp;&nbsp;&nbsp;
	                  <label style="display:inline">작성일:&nbsp;&nbsp;${article.date}</label>
	                </div>
	                
	                <div class="form-group">
	                  <label>Title</label>
	                  <input type="text" class="form-control" name="title" value="${article.title">
	                </div>
	
	                <!-- textarea -->
	                <div class="form-group">
	                  <label>The Body</label>
	                  <textarea class="form-control" rows="3" name="theBody">
	                  	${article.theBody }
	                  </textarea>
	                </div>
	
	                <div class="form-group">
	                  <c:set var="categoryOption" value="${article.category}" />
                      <label>Category</label>
                      <select class="form-control selection" disabled name="category">
	                    <option value="notice">Notice</option>
	                    <option value="board">Board</option>
	                  </select>
	                </div>
					<div class="box-footer" style="padding-left: 0; padding-right:0;">
	                  <button type="button" class="btn btn-default" onclick="location.href='${path}/#' ">목록으로</button>
	                  <button type="submit" class="btn btn-info pull-right">수정완료</button>
	                </div>
	                <!-- /.box-footer -->
	              </form>
              </c:if>
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
$(function() {
	var articleCategory = '<c:out value="${categoryOption}" />';
	console.log(articleCategory);
	$('.selection option').val(articleCategory).attr('selected', true);
});

</script>


</body>
</html>