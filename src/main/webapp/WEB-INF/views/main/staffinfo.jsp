<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>점포별 직원 정보</title>
  
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
 
  <%@ include file= "../common/_header_css_sum.jspf" %>

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
        직원정보
        <small>Staff List</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="${path}/main/mainpage"><i class="fa fa-home"></i>Home</a></li>
        <li class="active">직원 정보</li>
      </ol>
    </section>
<!-- ------------------------------------------------- -->    
   <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box box-info">
            <div class="box-body">
              <table id="staffListTable" class="table table-bordered table-striped">
				<colgroup>
				  <col style="width: 33%">
				  <col style="width: 33%">
				  <col style="width: 33%">
				</colgroup>
                <thead>
                  <tr>
                    <th style="text-align: center;"><i class="fa fa-tags"></i> 이름</th>
                    <th style="text-align: center;"><i class="fa fa-street-view"></i> 직급</th>
                    <th style="text-align: center;"><i class="fa fa-building"></i> 소속</th>
                  </tr>
                </thead>
                <tbody>
               	  <c:set var="staffList" value="${requestScope.userList}"/>
				  <c:forEach var="staff" items="${staffList}">
					<tr>
					  <td style="text-align: center;">
					    <a href="${path}/#">${staff.name}</a>
					  </td>
					  <td style="text-align: center;">${staff.role.role}</td>
					  <td style="text-align: center;">${staff.store.store}</td>
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

<%@ include file = "../common/_commonScriptList.jspf" %>

<%@ include file = "../common/_datatableScriptList.jspf" %>

<script>
  $(function () {
    $('#boardTable').DataTable()
  })
</script>

</body>
</html>