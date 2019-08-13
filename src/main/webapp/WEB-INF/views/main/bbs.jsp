<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Board</title>
  
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

  <%@ include file= "../common/_header_css_sum.jspf" %>
  
  <!-- table text overflow 설정 -->
  <style>
	  td, th {
	  	text-overflow: ellipsis;
	  }
  </style>
  
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
    	    자유게시판
        <small>Board</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="${path}/main/mainpage"><i class="fa fa-home"></i>Home</a></li>
        <li class="active">자유게시판</li>
      </ol>
    </section>
<!-- ------------------------------------------------- -->    
   <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box box-info">
          	<!-- 
            <div class="box-header">
              <h3 class="box-title"></h3>
              <c:if test="${user.email == null}">
              	<div class="pull-right">
              	  글작성은 로그인 후에 가능 합니다.
              	</div>
              </c:if>
              <c:if test="${user.role.role == '사장'||user.role.role == '직원'}">
              	<button type="button" class="btn btn-info pull-right" onclick="location.href='insertbbspage' ">글쓰기</button>
              </c:if>
              <hr style="margin-bottom: 0 ; border: 0.5px solid lightgrey">
            </div>
             -->
            <!-- /.box-header -->
            <div class="box-body">
              <table id="boardTable" class="table table-bordered table-striped">
				<colgroup>
				  <col style="width: 10%">
				  <col style="width: 64%">
				  <col style="width: 13%">
				  <col style="width: 13%">
				</colgroup>
                <thead>
                  <tr>
                    <th>글번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성날짜</th>
                  </tr>
                </thead>
                <tbody>
               	  <c:set var="boardList" value="${requestScope.bbsList}"/>
				  <c:forEach var="board" items="${boardList}">
					<tr>
					  <td style="text-align: center;">${board.bbs_id}</td>
			          <%--Title 클릭 시 해당 글 링크로 넘어감. 서블릿 요청필요.--%>
					  <td>
					    <a href="detailbbs?id=${board.bbs_id}">${board.title}</a>
					  </td>
					  <td style="text-align: center;">${board.user.name}</td>
					  <td style="text-align: center;">
					    <fmt:parseDate value="${board.updatedAt}" var="dateFmt" pattern="yyyy-MM-dd'T'HH:mm"/>
					    <fmt:formatDate value="${dateFmt}" pattern="yyyy-MM-dd HH:mm"/>
					  </td>
					</tr>
				  </c:forEach>
				  
                </tbody>
              </table>
              <c:if test="${user.email == null}">
              	<div class="pull-right" style=" position: absolute; bottom: 27px; right: 15px;">
              	  글작성은 로그인 후에 가능 합니다.
              	 </div>
              </c:if>
              <c:if test="${user.role.role == '사장'||user.role.role == '직원'}">
              	<div style=" position: absolute; bottom: 15px; right: 10px;">
              	  <button type="button" class="btn btn-info pull-right" onclick="location.href='insertbbspage' ">글쓰기</button>
              	</div>
              </c:if>
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
	$(document).ready(function() {
		$('#boardTable').DataTable({
			"order": [0,'desc'],
			"language" : {
				"loadingRecords" : "로딩중...",
				"processing" : "처리중...",
				"zeroRecords" : "검색된 데이터가 없습니다.",
				"emptyTable" : "데이터가 없습니다.",
				"lengthMenu" : " _MENU_ 개씩 보기",
				"search" : "검색:",
				"info" : "_START_ - _END_ (총 _TOTAL_ 건)",
				"infoEmpty" : "0건",
				"infoFiltered" : "(전체 _MAX_ 건 중 검색결과)",
				"pagingType" : "full_numbers",
				"paginate" : {
					"first" : "첫 페이지",
					"last" : "마지막 페이지",
					"next" : "다음",
					"previous" : "이전"
				}
			}
		});
	});
</script>
</body>
</html>