<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Main Page</title>
  
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
  <!-- pagination -->
  <link rel="stylesheet" href="setfiles/css/ant_fullcalendar1.0.3.css">
 
  <%@ include file= "../common/header.jsp" %>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <!-- _top.jspf -->
    <%@ include file="../common/_top.jspf" %>
<%--   <%@ include file="../common/_top.jspf" %> --%>
  
  <!-- Left side column. contains the logo and sidebar -->
  <%@ include file="../common/_nav.jspf" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        HOME
        <small>메인페이지</small>
      </h1>
      <!-- <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Dashboard</li>
      </ol> -->
    </section>

    <!-- Main content -->
    <section class="content">
    <c:set var = "todayStaffList" value ="${requestScope.todayStaffList}"/>
    <c:set var = "todayStaffCount" value = "${fn:length(todayStaffList)}"/>
      <!-- Small boxes (Stat box) -->
        <c:if test="${user.email != null }">
      <div class="row">
        <div class="col-lg-3 col-xs-6">
        
        <!-- 직원의 box -->
          <c:if test="${user.role.role == '직원'}">
          <!-- small box -->
          <c:set var="staffApply" value="${requestScope.staffApply}"/>
          <div class="small-box bg-aqua">
            <div class="inner">
              <h3>${staffApply}건</h3>

              <p>신청한 일정 수</p>
            </div>
            <div class="icon">
              <i class="fa  fa-calendar-plus-o"></i>
            </div>
            <a href="${path}/staff/requestwork" class="small-box-footer"> 근무 신청 페이지로 <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        <!-- ./col -->
        <div class="col-lg-3 col-xs-6">
        <!-- small box -->
        <c:set var="staffRefuseApply" value="${requestScope.staffRefuseApply}"/>
          <div class="small-box bg-red">
            <div class="inner">
              <h3>${staffRefuseApply}건</h3>

              <p>승인 거부된 일정 수</p>
            </div>
            <div class="icon">
              <i class="fa fa-calendar-times-o"></i>
            </div>
            <a href="${path}/staff/requestwork" class="small-box-footer"> 근무 신청 페이지로 <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        <!-- ./col -->
        <div class="col-lg-3 col-xs-6">
        </c:if>
        <!-- 직원의 box End -->
        
        
        <!-- 사장의 box -->
        <c:if test="${user.role.role == '사장'}">
          <!-- small box -->
          <div class="small-box bg-aqua">
            <div class="inner">
              <h3>53<sup style="font-size: 20px">%</sup></h3>

              <p>근무 일정 신청 수</p>
            </div>
            <div class="icon">
              <i class="fa fa-list-alt"></i>
            </div>
            <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        <!-- ./col -->
        <div class="col-lg-3 col-xs-6">
        <!-- small box -->
          <div class="small-box bg-red">
            <div class="inner">
              <h3>53<sup style="font-size: 20px">%</sup></h3>

              <p>금일 휴가자 수</p>
            </div>
            <div class="icon">
              <i class="fa fa-user-times"></i>
            </div>
            <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        <!-- ./col -->
        <div class="col-lg-3 col-xs-6">
        </c:if>
        <!-- 사장의 box End -->

        <!-- 공통 box -->
          <!-- small box -->
          <div class="small-box bg-yellow">
            <div class="inner">
              <h3>${todayStaffCount}명</h3>

              <p>금일 근무자</p>
            </div>
            <div class="icon">
              <i class="fa fa-users"></i>
            </div>
            <a href="${path}/main/staffinfo" class="small-box-footer">전체 직원 리스트 <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        <!-- ./col -->
        <div class="col-lg-3 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-green">
            <div class="inner">
              <h3>65</h3>

              <p>오늘의 할 일</p>
            </div>
            <div class="icon">
              <i class="fa fa-check-square-o"></i>
            </div>
            <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        <!-- ./col -->
      <!-- 공통 box End -->
      </div>
      </c:if>
      <!-- /.row -->
      
      
<!-- Main row -->
      <div class="row">
      
      	<!-- Left col (공지사항 부분) -->
        <section class="col-lg-6 connectedSortable">
        
         <div class="box">
            <div class="box-header">
              <h3 class="fa fa-bell"> 공지사항</h3>
			  <b class="pull-right" style="font-size:medium"><a href="${path}/main/noticepage">more..</a></b>
            </div>
            <!-- /.box-header -->
            <div class="box-body no-padding">
              <table class="table">
                <tr>
                  <th style="width: 20%">게시글 번호</th>
                  <th>제목</th>
                  <th style="width: 15%">작성자</th>
                </tr>
                <c:set var="noticeList" value="${requestScope.noticeList}"/>
				<c:forEach var="notice" items="${noticeList}">
                <tr>
                  <td>${notice.notice_id}</td>
                  <td><a href="detailnotice?id=${notice.notice_id}">${notice.title}</a></td>
                  <c:if test="${notice.user.store.store == '둔산점'}">
                  	<td><span class="badge bgDun">${notice.user.name}</span></td>
                  </c:if>
                  <c:if test="${notice.user.store.store == '월평점'}">
                  	<td><span class="badge bgWal">${notice.user.name}</span></td>
                  </c:if>
                  <c:if test="${notice.user.store.store == '관저점'}">
                  	<td><span class="badge bgGhwan">${notice.user.name}</span></td>
                  </c:if>
                  <c:if test="${notice.user.store.store == '갈마점'}">
                  	<td><span class="badge bgGal">${notice.user.name}</span></td>
                  </c:if>
                  <c:if test="${notice.user.store.store == '탄방점'}">
                  	<td><span class="badge bgTan">${notice.user.name}</span></td>
                  </c:if>
                </tr>
                </c:forEach>
              </table>
            </div>
            <!-- /.box-body -->
            
          </div>
          <!-- /.box -->
          
        </section>
        <!-- /.Left col -->
     
     	<!-- right col -->
     	<section class="col-lg-6 connectedSortable">
        
         <div class="box">
            <div class="box-header">
              <h3 class="fa fa-comments"> 자유게시판</h3>
              <b class="pull-right" style="font-size:medium"><a href="${path}/main/bbspage">more..</a></b>
            </div>
            <!-- /.box-header -->
            <div class="box-body no-padding">
              <table class="table">
                <tr>
                  <th style="width: 20%">게시글 번호</th>
                  <th>제목</th>
                  <th style="width: 15%">작성자</th>
                </tr>
                <c:set var="boardList" value="${requestScope.bbsList}"/>
				<c:forEach var="board" items="${boardList}">
                <tr>
                  <td>${board.bbs_id}</td>
                  <td><a href="detailbbs?id=${board.bbs_id}">${board.title}</a></td>
                  <c:if test="${notice.user.store.store == '둔산점'}">
                  	<td><span class="badge bgDun">${notice.user.name}</span></td>
                  </c:if>
                  <c:if test="${notice.user.store.store == '월평점'}">
                  	<td><span class="badge bgWal">${notice.user.name}</span></td>
                  </c:if>
                  <c:if test="${notice.user.store.store == '관저점'}">
                 	<td><span class="badge bgGhwan">${notice.user.name}</span></td>
                  </c:if>
                  <c:if test="${notice.user.store.store == '갈마점'}">
                    <td><span class="badge bgGal">${notice.user.name}</span></td>
                  </c:if>
                  <c:if test="${notice.user.store.store == '탄방점'}">
                  	<td><span class="badge bgTan">${notice.user.name}</span></td>
                  </c:if>
                </tr>
                </c:forEach>
              </table>
            </div>
            <!-- /.box-body -->
            
          </div>
          <!-- /.box -->
          
        </section>
        <!-- /.right col -->
      </div>
      <!-- /.row (main row) -->
      <c:if test="${user.email != null}">
      <!-- 2nd row -->
       <div class="row">
      	<!-- Left col -->
        <section class="col-lg-4 connectedSortable">
          
          <!-- USERS LIST -->
              <div class="box box-danger">
                <div class="box-header with-border">
                  <h3 class="fa fa-users"> 근무자 명단</h3>
                  <div class="box-tools pull-right">
                    <span class="label label-danger">${todayStaffCount} New Members</span>
                  </div>
                </div>
                <!-- /.box-header -->
                <div class="box-body no-padding">
                  <ul class="users-list clearfix">
	                  <c:if test="${todayStaffCount != 0}">
	                  	<c:forEach var="Staff" items="${todayStaffList}">
		                  	<li>
		                      <img src="dist/img/user1-128x128.jpg" alt="User Image">
		                      <a class="users-list-name" href="#">Alexander Pierce</a>
		                      <span class="users-list-date">Today</span>
		                    </li>   
	                  	</c:forEach>
	                  </c:if>
	                  <c:if test="${todayStaffCount == 0}">
	                  		오늘 출근하는 직원이 없습니다.
	                  </c:if>
                  </ul>
                  <!-- /.users-list -->
                </div>
                <!-- /.box-body -->
                <div class="box-footer text-center">
                  <a href="javascript:void(0)" class="uppercase">View All Users</a>
                </div>
                <!-- /.box-footer -->
              </div>
              <!--/.box -->
        
        </section>
        <!-- /.Left col -->
        
        <!-- middle col -->
        <section class="col-lg-4 connectedSortable">

          <!-- TO DO List -->
          <div class="box box-primary">
            <div class="box-header">
              <i class="ion ion-clipboard"></i>

              <h3 class="box-title">To Do List: 수신,확인기능</h3>

              <div class="box-tools pull-right">
                <ul class="pagination pagination-sm inline">
                  <li><a href="#">&laquo;</a></li>
                  <li><a href="#">1</a></li>
                  <li><a href="#">2</a></li>
                  <li><a href="#">3</a></li>
                  <li><a href="#">&raquo;</a></li>
                </ul>
              </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <!-- See dist/js/pages/dashboard.js to activate the todoList plugin -->
              <ul class="todo-list">
                <li>
                  <!-- drag handle -->
                  <span class="handle">
                        <i class="fa fa-ellipsis-v"></i>
                        <i class="fa fa-ellipsis-v"></i>
                      </span>
                  <!-- checkbox -->
                  <input type="checkbox" value="">
                  <!-- todo text -->
                  <span class="text">Design a nice theme</span>
                  <!-- Emphasis label -->
                  <small class="label label-danger"><i class="fa fa-clock-o"></i> 2 mins</small>
                  <!-- General tools such as edit or delete-->
                  <div class="tools">
                    <i class="fa fa-edit"></i>
                    <i class="fa fa-trash-o"></i>
                  </div>
                </li>
                <li>
                      <span class="handle">
                        <i class="fa fa-ellipsis-v"></i>
                        <i class="fa fa-ellipsis-v"></i>
                      </span>
                  <input type="checkbox" value="">
                  <span class="text">Make the theme responsive</span>
                  <small class="label label-info"><i class="fa fa-clock-o"></i> 4 hours</small>
                  <div class="tools">
                    <i class="fa fa-edit"></i>
                    <i class="fa fa-trash-o"></i>
                  </div>
                </li>
                <li>
                      <span class="handle">
                        <i class="fa fa-ellipsis-v"></i>
                        <i class="fa fa-ellipsis-v"></i>
                      </span>
                  <input type="checkbox" value="">
                  <span class="text">Let theme shine like a star</span>
                  <small class="label label-warning"><i class="fa fa-clock-o"></i> 1 day</small>
                  <div class="tools">
                    <i class="fa fa-edit"></i>
                    <i class="fa fa-trash-o"></i>
                  </div>
                </li>
                <li>
                      <span class="handle">
                        <i class="fa fa-ellipsis-v"></i>
                        <i class="fa fa-ellipsis-v"></i>
                      </span>
                  <input type="checkbox" value="">
                  <span class="text">Let theme shine like a star</span>
                  <small class="label label-success"><i class="fa fa-clock-o"></i> 3 days</small>
                  <div class="tools">
                    <i class="fa fa-edit"></i>
                    <i class="fa fa-trash-o"></i>
                  </div>
                </li>
                <li>
                      <span class="handle">
                        <i class="fa fa-ellipsis-v"></i>
                        <i class="fa fa-ellipsis-v"></i>
                      </span>
                  <input type="checkbox" value="">
                  <span class="text">Check your messages and notifications</span>
                  <small class="label label-primary"><i class="fa fa-clock-o"></i> 1 week</small>
                  <div class="tools">
                    <i class="fa fa-edit"></i>
                    <i class="fa fa-trash-o"></i>
                  </div>
                </li>
                <li>
                      <span class="handle">
                        <i class="fa fa-ellipsis-v"></i>
                        <i class="fa fa-ellipsis-v"></i>
                      </span>
                  <input type="checkbox" value="">
                  <span class="text">Let theme shine like a star</span>
                  <small class="label label-default"><i class="fa fa-clock-o"></i> 1 month</small>
                  <div class="tools">
                    <i class="fa fa-edit"></i>
                    <i class="fa fa-trash-o"></i>
                  </div>
                </li>
              </ul>
            </div>
            <!-- /.box-body -->
            <div class="box-footer clearfix no-border">
              <button type="button" class="btn btn-default pull-right"><i class="fa fa-plus"></i> Add item</button>
            </div>
          </div>
          <!-- /.box -->
          
        </section>
        <!-- /.middle col -->
        
        
        <!-- right col (We are only adding the ID to make the widgets sortable)-->
        <section class="col-lg-4 connectedSortable">
		  <!-- TO DO List -->
          <div class="box box-primary">
            <div class="box-header">
              <i class="ion ion-clipboard"></i>

              <h3 class="box-title">User TODO List : 작성,전달.</h3>

              <div class="box-tools pull-right">
                <ul class="pagination pagination-sm inline">
                  <li><a href="#">&laquo;</a></li>
                  <li><a href="#">1</a></li>
                  <li><a href="#">2</a></li>
                  <li><a href="#">3</a></li>
                  <li><a href="#">&raquo;</a></li>
                </ul>
              </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <!-- See dist/js/pages/dashboard.js to activate the todoList plugin -->
              <ul class="todo-list">
                <li>
                  <!-- drag handle -->
                  <span class="handle">
                        <i class="fa fa-ellipsis-v"></i>
                        <i class="fa fa-ellipsis-v"></i>
                      </span>
                  <!-- checkbox -->
                  <input type="checkbox" value="">
                  <!-- todo text -->
                  <span class="text">Design a nice theme</span>
                  <!-- Emphasis label -->
                  <small class="label label-danger"><i class="fa fa-clock-o"></i> 2 mins</small>
                  <!-- General tools such as edit or delete-->
                  <div class="tools">
                    <i class="fa fa-edit"></i>
                    <i class="fa fa-trash-o"></i>
                  </div>
                </li>
                <li>
                      <span class="handle">
                        <i class="fa fa-ellipsis-v"></i>
                        <i class="fa fa-ellipsis-v"></i>
                      </span>
                  <input type="checkbox" value="">
                  <span class="text">Make the theme responsive</span>
                  <small class="label label-info"><i class="fa fa-clock-o"></i> 4 hours</small>
                  <div class="tools">
                    <i class="fa fa-edit"></i>
                    <i class="fa fa-trash-o"></i>
                  </div>
                </li>
                <li>
                      <span class="handle">
                        <i class="fa fa-ellipsis-v"></i>
                        <i class="fa fa-ellipsis-v"></i>
                      </span>
                  <input type="checkbox" value="">
                  <span class="text">Let theme shine like a star</span>
                  <small class="label label-warning"><i class="fa fa-clock-o"></i> 1 day</small>
                  <div class="tools">
                    <i class="fa fa-edit"></i>
                    <i class="fa fa-trash-o"></i>
                  </div>
                </li>
                <li>
                      <span class="handle">
                        <i class="fa fa-ellipsis-v"></i>
                        <i class="fa fa-ellipsis-v"></i>
                      </span>
                  <input type="checkbox" value="">
                  <span class="text">Let theme shine like a star</span>
                  <small class="label label-success"><i class="fa fa-clock-o"></i> 3 days</small>
                  <div class="tools">
                    <i class="fa fa-edit"></i>
                    <i class="fa fa-trash-o"></i>
                  </div>
                </li>
                <li>
                      <span class="handle">
                        <i class="fa fa-ellipsis-v"></i>
                        <i class="fa fa-ellipsis-v"></i>
                      </span>
                  <input type="checkbox" value="">
                  <span class="text">Check your messages and notifications</span>
                  <small class="label label-primary"><i class="fa fa-clock-o"></i> 1 week</small>
                  <div class="tools">
                    <i class="fa fa-edit"></i>
                    <i class="fa fa-trash-o"></i>
                  </div>
                </li>
                <li>
                      <span class="handle">
                        <i class="fa fa-ellipsis-v"></i>
                        <i class="fa fa-ellipsis-v"></i>
                      </span>
                  <input type="checkbox" value="">
                  <span class="text">Let theme shine like a star</span>
                  <small class="label label-default"><i class="fa fa-clock-o"></i> 1 month</small>
                  <div class="tools">
                    <i class="fa fa-edit"></i>
                    <i class="fa fa-trash-o"></i>
                  </div>
                </li>
              </ul>
            </div>
            <!-- /.box-body -->
            <div class="box-footer clearfix no-border">
              <button type="button" class="btn btn-default pull-right"><i class="fa fa-plus"></i> Add item</button>
            </div>
          </div>
          <!-- /.box -->


        </section>
        <!-- right col -->
      </div>
      <!-- /.row (2nd row) -->
	</c:if>
    </section>
    <!-- /.content -->
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

<%@ include file = "../common/_commonScriptList.jspf" %>
<script>
var board = {};
var lstCnt = 10;
board.boardList = {
        init : function(cmpnNo, lstCnt) {
            var page = 1;
            board.boardList.param.pageNumber = Number(page);
            board.boardList.param.cmpnNo = cmpnNo;
            board.boardList.param.pageSize = lstCnt;
            board.boardList.data();
        },
       data : function() {
            $.ajax({
                url : 'event/boardList',
                data : board.boardList.param,
                success : function(result) {
                    var boardList = result.boardList;
                    if(boardList.length != 0){
                        board.boardList.totalCount = boardList[0].totalCount; // 총 건수
                    };
                    drawPagination(lstCnt);
                    var markup ="";    // mark 로직 작성
                    $("#event_div").html(markup);
                },
                error : function() {
                    alert('게시판 조회 중 오류가 발생했습니다.');
                }
            });
        },
        param : {
            pageNumber : 1,
            pageSize : lstCnt
        },
        totalCount : 0
    };
 
//페이징을 설정하고 페이징 영역에 화면에 그리는 함수
function drawPagination(lstCnt){
    $("#boardPagingDiv").pagination({
       items: board.boardList.totalCount,
       currentPage : board.boardList.param.pageNumber,
       itemsOnPage: lstCnt, // 설정 안할 경우 10
       displayedPages : lstCnt, // 설정 안할 경우 10
       selectOnClick : false, // 페이징 버튼을 눌렀을 때 자동으로 페이징을 다시 그릴지 여부 (기본값은 true)
       onPageClick: function(currentPage){ // 페이징 버튼을 눌렀을 때 이벤트 바인딩
           searchBoardListPaging(currentPage); // 페이징 버튼을 눌렀을 때 다시 비동기로 데이터를 가져와 화면과 페이징을 그립니다.
       }
   });
}
 
// 페이징 번호 눌렀을때 함수
function searchBoardListPaging (page) {
    board.boardList.param.pageNumber = Number(page);
    board.boardList.data();
    drawPagination(lstCnt);
}

</script>
</body>
</html>