<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>StaffList</title>
  
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
  
  <!-- DataTables -->
  <link rel="stylesheet" href="setfiles/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
  
  
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
        Common - Staff List (직원목록)
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
        <div class="col-xs-12">
          <div class="box box-info">
            <div class="box-header">
              <h3 class="box-title">Data Table With Full Features</h3>
              <hr style="margin-bottom: 0 ; border: 0.5px solid lightgrey">
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="staffListTable" class="table table-bordered table-striped">
				<colgroup>
				  <col style="width: 10%">
				  <col style="width: 15%">
				  <col style="width: 15%">
				  <col style="width: 20%">
				  <col style="width: 20%">
				  <col style="width: 20%">
				</colgroup>
                <thead>
                  <tr>
                    <th>번호</th>
                    <th>이름</th>
                    <th>직급</th>
                    <th>소속</th>
                    <th>연락처</th>
                    <th>이메일</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                  	<td>001</td>
                  	<td><a href="#">알바1</a></td>
                  	<td>알바</td>
                  	<td>점포1</td>
                  	<td>000-0000-0000</td>
                  	<td>test1_al-ba@gmail.com</td>
                  </tr>
                
               	  <c:set var="staffList" value="${requestScope.staffList}"/>
				  <c:forEach var="staff" items="${staffList}">
					<tr>
					  <td style="text-align: center;">${staff.number}</td>
					  <td style="text-align: center;">
					    <a href="${path}/#">${staff.name}</a>
					  </td>
					  <td style="text-align: center;">${staff.position}</td>
					  <td style="text-align: center;">${staff.group}</td>
					  <td style="text-align: center;">${staff.phoneNumber}</td>
					  <td style="text-align: center;">${staff.eMail}</td>
					</tr>
				  </c:forEach>
				  
                </tbody>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
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

<!-- DataTables -->
	<!-- 상하단 검색기능, 페이지 넘김기능 -->
<script src="setfiles/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
	<!-- table검색창등 미세설정.. -->
<script src="setfiles/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>

<script>
  $(function () {
    $('#boardTable').DataTable()
  })
</script>

</body>
</html>