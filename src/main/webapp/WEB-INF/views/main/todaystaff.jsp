<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Today Staff</title>
  
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
        Common - Today Staff (현재 날짜의 근무자 목록)
        <small>Control panel</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="${path}/main/mainpage"><i class="fa fa-home"></i>Home</a></li>
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
                
               	  <c:set var="todayStaffList" value="${requestScope.todayStaffList}"/>
				  <c:forEach var="todayStaff" items="${todayStaffList}">
					<tr>
					  <td style="text-align: center;">${todayStaff.number}</td>
					  <td style="text-align: center;">
					    <a href="${path}/#">${todayStaff.name}</a>
					  </td>
					  <td style="text-align: center;">${todayStaff.position}</td>
					  <td style="text-align: center;">${todayStaff.group}</td>
					  <td style="text-align: center;">${todayStaff.phoneNumber}</td>
					  <td style="text-align: center;">${todayStaff.eMail}</td>
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

</body>
</html>