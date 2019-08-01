<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Request Work</title>
  
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="setfiles/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="setfiles/bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="setfiles/bower_components/Ionicons/css/ionicons.min.css">
  <!-- fullCalendar -->
  <link rel="stylesheet" href="setfiles/bower_components/fullcalendar/dist/fullcalendar.min.css">
  <link rel="stylesheet" href="setfiles/bower_components/fullcalendar/dist/fullcalendar.print.min.css" media="print">
  <!-- Theme style -->
  <link rel="stylesheet" href="setfiles/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="setfiles/dist/css/skins/skin-blue.css">

  <!-- Google Font -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
  
  <!-- external eventbar style -->
  <link rel="stylesheet" href="setfiles/css/ant_fullcalendar1.0.3.css?ver=1">
  
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
        Staff - Request Work
        <small>Control panel</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="${path}/main/mainpage"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Calendar</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-md-3">

          <div class="box box-solid">
            <div class="box-header with-border">
              <h4 class="box-title">Draggable Events</h4>
            </div>
             
            <div class="box-body">
              <!-- the events -->
              <div id="external-events">
              </div>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /. box -->
          <div class="box box-solid">
            <div class="box-header with-border">
              <h3 class="box-title">Create Event</h3>
            </div>
            <div class="box-body">

              <div class="input-group" style="width:100%">
                <div>
                  <label for="newE-startT">시작시간</label>
                  <input class="form-control timeForm forColor panel" name='start-insert' type="time" id="newE-startT" value="00:00">
                </div>
                <br><br>
                <div>
                  <label for="newE-endT">종료시간</label>
                  <input class="form-control timeForm" name='end-insert' type="time" id="newE-endT" value="00:00">
                </div>
                <br><br>
                <hr class="divArea">
                <button id="add-new-event" type="button" class="btn btn-primary btn-flat colorButton pull-right">Add</button>
                <!-- /btn-group -->
              </div>
              <!-- /input-group -->
            </div>
          </div>

          <div class="box box-solid">
            <div class="box-body">
                <label for="beforeSubmit">
                  <input type="checkbox" id="beforeSubmit">
                  일정을 등록합니다.
                </label>
                <button id="submitPlan" type="button" class="btn btn-primary btn-flat pull-right">작성완료</button>
            </div>
            <!-- /.box-body -->
          </div>

        </div>
        <!-- /.col -->
        <div class="col-md-9">
          <div class="box box-primary">
            <div class="box-body no-padding">
              <!-- THE CALENDAR -->
              <div id="calendar"></div>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /. box -->
        </div>
        <!-- /.col -->

      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <%@ include file = "../common/_bottom.jspf" %>

</div>
<!-- ./wrapper -->

<!-- Input Modal Form  -->
<div class="modal fade" id="selectPopup" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">×</span>
        </button>
        <h4 class="modal-title">이벤트생성창</h4>
      </div>
      <div class="modal-body" id="inputTableForm">
        <div class="input-group" style="width:100%">
          <div>
            <label for="insModal-S">시작시간</label>
            <input class="form-control timeForm forColor in-modal" name='insModal-S' type="time" id="modal-startT" value="00:00">
          </div>
          <br><br>
          <div>
            <label for="insModal-E">종료시간</label>
            <input class="form-control timeForm" name='insModal-S' type="time" id="modal-endT" value="00:00">
          </div>
          <br><br>
          <div>
            <label for="insModal-P">인원</label>
            <input class="form-control timeForm" name='insModal-P' type="number" id="modal-numP" value="0">
          </div>
          <!-- /btn-group -->
        </div>
      </div>
      <div class="modal-footer">
        <!-- 이벤트 생성, 창닫기 -->
        <button id="makeEvent" class="btn btn-primary btn-flat pull-right colorButton" type="button" data-dismiss="modal">확인</button>
        <button id="closeEventModal" class="btn btn-primary btn-flat pull-left" type="button" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">취소</span>
        </button>
      </div>
    </div>
  </div>
</div>

<!-- jQuery 3 -->
<script src="setfiles/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="setfiles/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="setfiles/bower_components/jquery-ui/jquery-ui.min.js"></script>
<!-- Slimscroll -->
<script src="setfiles/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="setfiles/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App : navbar 관련-->
<script src="setfiles/dist/js/adminlte.min.js"></script>

<%@ include file = "../common/_commonScriptList.jspf" %>

<!-- AntPeople FullCalendar function -->
<script src="setfiles/js/ant_fullcalendar1.0.3.js?ver=1"></script>

<!-- fullCalendar -->
<script src="setfiles/bower_components/moment/moment.js"></script>
<script src="setfiles/bower_components/fullcalendar/dist/fullcalendar.min.js"></script>
<!-- Page specific script -->
<script>
// 페이지 로드 시 몇월을 표시해 줄 건지, 해당월의 운영계획과 함께 넘겨준다.
// --> 달력화면을 해당월로 이동하고, 이벤트를 표시한다.
// 이벤트를 클릭할 때, ajax통신이 일어나고 신청한 정보를 보내주며
// 현재 남은 자리수와 신청성공여부를 수신받는다.

// **신청 시 보낼 정보형식
// --> 신청시 ajax 통신 event당 1번
/*
{
   user : { user_id : staffId },
   schedule_id : objId
},
*/

//로그인한 staff의 id
var userId = "${user.user_id}";
//var userId = 'testUser';

//calendar 초기 로드값.
var selectedMonth; //캘린더에 연결된 변수.
//var getInitialMonth = '1907'; //테스트
var getInitialMonth = ${monthIndex};
//값의 존재여부 검사. --> 존재하여야 함.
if(getInitialMonth > 0) {
  var selectedMonth = convertToInitialDateInfo(getInitialMonth);
}
//받은 날짜정보를 Date객체로.
function convertToInitialDateInfo(val) {
  var yearP = parseInt("20"+val.slice(0,2));
  var monthP = parseInt(val.slice(2));
  var newDateObj = new Date(yearP,monthP,1);
  return newDateObj;
}

//DayObj, TimeObj : 프로토타입 객체.
var startDay = new DayObj();
var endDay = new DayObj();
var startTime = new TimeObj();
var endTime = new TimeObj();
var state = 0;

/*
//DB로 부터 받을 객체배열의 Dummy
var gotData = {
  testAdmin_19071501001907160623 : {
    id : 'testAdmin_19071501001907160623',
    title : '01:00~06:23',
    startDate : '190715',
    endDate :'190716',
    startTime : '0100',
    endTime : '0623',
    userId : 'testAdmin',
    fromUser : {'user_id' : 'testAdmin'},
    state : '0',
    manPower : '10',
  },
  testAdmin_19071503001907160623 : {
    id : 'testAdmin_19071503001907160623',
    title : '01:00~06:23',
    startDate : '190715',
    endDate :'190716',
    startTime : '1300',
    endTime : '0623',
    userId : 'testAdmin',
    fromUser : {'user_id' : 'testAdmin'},
    state : '0',
    manPower : '10',
  },
  testAdmin_19072101001907220623 : {
    id : 'testAdmin_19072101001907220623',
    title : '01:00~06:23',
    startDate : '190721',
    endDate :'190722',
    startTime : '0100',
    endTime : '0623',
    userId : 'testAdmin',
    fromUser : {'user_id' : 'testAdmin'},
    state : '0',
    manPower : '5',
  },
  testAdmin_19070101001907020623 : {
    id : 'testAdmin_19070101001907020623',
    title : '01:00~06:23',
    startDate : '190701',
    endDate :'190702',
    startTime : '0100',
    endTime : '0623',
    userId : 'testAdmin',
    fromUser : {'user_id' : 'testAdmin'},
    state : '0',
    manPower : '7',
  }
};

//일정 신청 시 response더미
var receiveD = {
  testAdmin_19070101001907020623 : {
    id : 'testAdmin_19070101001907020623',
    title : '01:00~06:23',
    startDate : '190701',
    endDate :'190702',
    startTime : '0100',
    endTime : '0623',
    userId : 'testAdmin',
    fromUser : {'user_id' : 'testAdmin'},
    state : '0',
    manPower : '2',
  }
}
//신청 취소 시 response더미
var receiveS = {
  testAdmin_19070101001907020623 : {
    id : 'testAdmin_19070101001907020623',
    title : '01:00~06:23',
    startDate : '190701',
    endDate :'190702',
    startTime : '0100',
    endTime : '0623',
    userId : 'testAdmin',
    fromUser : {'user_id' : 'testAdmin'},
    state : '0',
    manPower : '200',
  }
}
*/
//>> requestWork0.0.1
// 페이지 로드 시 DB에서 받은 정보 : eventList (기존과 동일한 장소.)
$('#calendar').data('eventList',{});
var originalDataLoc = $('#calendar').data('eventList');
$('#calendar').data('selectedList',{});
var selectedDataLoc = $('#calendar').data('selectedList');
// calendar로딩 시 표시될 객체배열
var initialData = [];
//수신데이터가 있을 경우, 저장.
var gotData = $.parseJSON('${jsonList}');

// 받은데이터가 있는 경우, calendar element에 data로 저장한다.
if(gotData != "") {
	for(var key in gotData) {
		originalDataLoc[""+key+""] = gotData[""+key+""];
	}
  /*
	for(var i=0; i<gotData.length; i++){
		var tempKey = gotData[i].id;
		var tempObj = gotData[i];
		originalDataLoc[""+tempKey+""] = tempObj;
	}
  */
}

//페이지 로딩 시 받은 일정정보를 화면에 Rendering : calendar로딩 시 초기값으로 지정하여 표시.
$.each(originalDataLoc, function(id, obj) {
  initialData.push(convertToEventObj(obj))
})

//fullCalendar 로드, 초기화
$(function() {
  $('#calendar').fullCalendar({
    header    : {
      left  : 'prev,next today',
      center: 'title',
      right : 'month,agendaWeek,agendaDay'
    },
    buttonText: {
      today: 'today',
      month: 'month',
      week : 'week',
      day  : 'day'
    },
//>>//Ajax로 가져올 event data
    defaultDate : selectedMonth,
    events : initialData,
    //월별 표시되는 달력의 길이조정.
    fixedWeekCount : false,
    //현재 월 외의 날짜 표시조정. --> 현재표시된 날짜 외 이벤트 발생x.
    showNonCurrentDates : false,

    //이벤트 선택
    eventClick: function (calEvent, jsEvent, view) {
      //이벤트를 눌렀을 때, ajax통신 발생, 이벤트 셀렉트 표시
      var comSign = false;
      if($('span:contains("'+calEvent.id+'")').parent().parent().hasClass('selectedEvent')) {
        //alert('취소')
        // 선택되어 있는 경우 --> 취소요청
        comSign = false;
        communicationProcess(comSign, calEvent.id, userId);
      } else {
        //alert('신청')
        // 선택되어 있지 않은경우 --> 등록요청
        comSign = true;
        communicationProcess(comSign, calEvent.id, userId);
      }
    },
  });
  remakeDisplayedEvents(originalDataLoc);
});
console.log('originalDataLoc : 일정 신청 전')
console.log(originalDataLoc)

// ./ End of fullCalendar 초기화 -------------------------------------

function communicationProcess(sign, id, user) {
  //user = userId :전역변수. 세선값.
  var packedTarget = createSendingObj(id, user);
  //ajax 통신 후 성공한 값을 수신 받는다.
  var communicateResult = sendInfo(sign, packedTarget);
  //var communicateResult = receiveS; //더미 - 일정신청 시
  //var communicateResult = receiveD; //더미 - 신청취소 시
  if(communicateResult == 'fail') {
    alert('등록에 실패하였습니다. 다시 신청해주세요.');
  } else {
    //성공한 경우, 경우에 따라 리턴값 화면에 업데이트 방법 추가필요....
    if((sign == true) && (id == Object.keys(communicateResult)[0])){
      //alert('신청')
      //등록과정 --> 등록한 내용 List로 저장.
      //origin에 저장(for manpower업데이트 후 이벤트 렌더링)
      // & select에 저장(렌더링 후 신청여부 다시 표시하기 위해).
      originalDataLoc[""+id+""] = communicateResult[""+id+""];
      selectedDataLoc[""+id+""] = communicateResult[""+id+""];
      console.log('originalDataLoc : 일정 신청 후')
      console.log(originalDataLoc)
      console.log(selectedDataLoc)

    } else if((sign == false) && (id == Object.keys(communicateResult)[0])) {
      //alert('취소')
      //취소과정 --> 등록한 내용에서 삭제.
      //$('span:contains("'+id+'")').parent().parent().removeClass('selectedEvent');
      originalDataLoc[""+id+""] = communicateResult[""+id+""];
      delete selectedDataLoc[""+id+""];
      console.log('originalDataLoc : 신청 취소 후')
      console.log(originalDataLoc)
      console.log(selectedDataLoc)
    } else {
      alert('요청한 일정과 받은 일정이 일치하지 않습니다.');
    }
    //communicateResult를 저장된 data에 업데이트. 화면을 다시 그려주어야 한다.
    renderingProcessWithList(originalDataLoc, selectedDataLoc)
  }
}


function renderingProcessWithList(eList, sList) {
  //화면 비움.
  $('#calendar').fullCalendar('removeEvents');
  for(var key in eList) {
    //객체 Converting
    var convertdObj = convertToEventObj(eList[""+key+""]);
    //화면에 객체를 표시.
    $('#calendar').fullCalendar('renderEvent',convertdObj, true);
  }
  //화면의 manpower와 title을 다시 표시.
  remakeDisplayedEvents(eList);
  //신청된 목록을 다시 표시
  for(var key in sList) {
    $('span:contains("'+key+'")').parent().parent().addClass('selectedEvent');
  }
}

function createSendingObj(targetEvent, user) {
  var newObj = {
    userId : { user_id : user },
    schedule_id : targetEvent
  }
  return newObj;
}

function sendInfo(sign, obj) {
  //controll url필요.
  var addPlan = "add" //일정 신청.
  var rmPlan = "rm" //신청 취소.
  var selectedUrl;
  if(sign) {
    selectedUrl = addPlan;
  } else {
    selectedUrl = rmPlan;
  }
  var result;
  $.ajax({
		url : selectedUrl,
		method : 'post',
    	// data : 서버로 보낼 데이터
		data : JSON.stringify(obj),
    	// contentType : 서버로 보낼 데이터의 타입.
    	contentType : 'application/json;charset=UTF-8',
    	// dataType : 서버로 부터 수신받을 데이터 타입.
		dataType : 'json',
    	async : false,
		error : function(response) {
			//alert("통신실패, response: " + response);
			result = 'fail';
		},
		success : function(response) {
			alert("통신성공, response: " + response);
      	result = response;
		}
	});
  return result;
}

function remakeDisplayedEvents(antPeopleObjList) {
  //if(Object.keys(antPeopleObjList).length > 0) {
  //console.log(antPeopleObjList)
  //console.log(Object.keys(antPeopleObjList))
  //console.log(Object.keys(antPeopleObjList).length > 0)
    for(var key in antPeopleObjList) {
      //목록으로부터 요구 인원수를 추출.
      var mpValue = antPeopleObjList[""+key+""].manPower;
      var newTitle = antPeopleObjList[""+key+""].title;

      //새로 생성한 tag가 있는경우 --> 값만 변경.

      //tag정보 생성.
      var mpSpan = $('<span />').addClass('eventMarker').css({'padding-left':3}).text(mpValue+"명");
      var titleSpan = $('<span />').addClass('eventTitle').css({'margin-left':10}).text(newTitle);

      //존재하는 span을 모두 숨김.
      $('span:contains("'+key+'").fc-title').css({'display':'none'})
      $('span:contains("'+key+'")').prev('span.fc-time').css({'display':'none'})

      //생성한 span tag를 추가. - 추가된 span이 이미 있는지 확인 후, 있으면 값만 변경.
      var checkExist = $('span:contains("'+key+'")').parent().find('.eventMarker');

      if(checkExist.length <= 0) {
        $('span:contains("'+key+'")').parent().prepend(titleSpan);
        $('span:contains("'+key+'")').parent().prepend(mpSpan);
      } else if (checkExist.length > 0) {
        $('span:contains("'+key+'")').parent().find('.eventMarker').text(mpValue+"명");
        $('span:contains("'+key+'")').parent().find('.eventTitle').text(newTitle);
      }
    }
  //}
}
</script>
</body>
</html>