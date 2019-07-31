<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Planning</title>
  
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
  <link rel="stylesheet" href="setfiles/css/ant_fullcalendar1.0.3.css">
  
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
        Calendar
       
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
<script src="setfiles/js/ant_fullcalendar1.0.3.js"></script>

<!-- fullCalendar -->
<script src="setfiles/bower_components/moment/moment.js"></script>
<script src="setfiles/bower_components/fullcalendar/dist/fullcalendar.min.js"></script>

<!-- Page specific script -->
<script>
var userId = parseInt("${user.user_id}");

//calendar 초기 로드값.
var selectedMonth; //캘린더에 연결된 변수.
//var getInitialMonth = '1901'; //테스트
var getInitialMonth = "${monthIndex}";
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
// calendar로딩 시 표시될 객체배열
var initialData = [];

//빈 객체.
var emptyObj = {};

//수신데이터가 있을 경우, 저장.
var gotData = $.parseJSON('${jsonList}');
console.log('gotData')
console.log(gotData)
if(gotData != "") {
	for(var i=0; i<gotData.length; i++){
		var tempKey = gotData[i].id;
		var tempObj = gotData[i];
		emptyObj[""+tempKey+""] = tempObj;
	}
} 
console.log('emptyObj')
console.log(emptyObj)
/*{
  testAdmin_19061501001906160623 : {
    id : 'testAdmin_19061501001906160623',
    title : '1111111',
    startDate : '190615',
    endDate :'190616',
    startTime : '0100',
    endTime : '0623',
    userId : 'testAdmin',
    state : '0',
    manPower : '10',
  },
  testAdmin_190615010019999160623 : {
    id : 'testAdmin_190615010019999160623',
    title : '1111111',
    startDate : '190615',
    endDate :'190616',
    startTime : '0100',
    endTime : '0623',
    userId : 'testAdmin',
    state : '0',
    manPower : '10',
  }
};*/

$('#calendar').data('eventList',emptyObj);
var dataLocation = $('#calendar').data('eventList');
console.log('로딩확인')
console.log(dataLocation)
//페이지 로딩 시 받은 일정정보를 화면에 Rendering
$.each(dataLocation, function(id, obj) {
  console.log('id')
  console.log(id);
  console.log('obj')
  console.log(obj)
  initialData.push(convertToEventObj(obj))

  //$('#calendar').fullCalendar('renderEvent', obj, true);
})

// initialize the external events : 이벤트 바 생성 시 drag 가능하게 속성 추가.
function init_events(ele) {
  ele.each(function () {
    var eventObject = {
      title : $.trim($(this).text()),
      //객체로 시간저장.
      startTime : $(this).data().startTimeObj,
      endTime :  $(this).data().endTimeObj,
    }

    // store the Event Object in the DOM element so we can get to it later
    $(this).data('eventObject', eventObject)

    // make the event draggable using jQuery UI
    $(this).draggable({
      zIndex        : 1070,
      revert        : true, // will cause the event to go back to its
      revertDuration: 0  //  original position after the drag
    })
  })
}
//가져온 이벤트 정보로 이벤트 바 등록 시 drag기능 추가.
//init_events($('#external-events div.external-event'))

//이벤트 바 등록버튼
var insStartValue = parseInt($('.forColor').val().slice(0,2));
var newColor = colorPicker(insStartValue);
$('#add-new-event, #makeEvent').css({
  'backgroundColor' : newColor,
  'borderColor' : newColor,
});
$(document).on('change','.forColor',function() {
  var insValue = parseInt($(this).val().slice(0,2));
  var newColor = colorPicker(insValue);
  if($(this).hasClass('in-modal')) {
    $('#makeEvent').css({
      'backgroundColor' : newColor,
      'borderColor' : newColor,
    })
  } else {
    $('#add-new-event').css({
      'backgroundColor' : newColor,
      'borderColor' : newColor,
    });
  }
})
// ./ End of 이벤트 바 등록버튼

//이벤트 바 등록
$('#add-new-event').click(function (e) {
  e.preventDefault()
  //새로운 이벤트 시간입력 --> title로 변경.
  var new_st = $('#newE-startT').val(); if(new_st == "") { new_st = "00:00"; }
  var new_en = $('#newE-endT').val(); if(new_en == "") { new_en = "00:00"; }
  //생성되는 이벤트 바 title.
  var val = new_st+" ~ "+new_en;

  //정수형으로 시간정보 변환
  var newSTime = parseInt(new_st.slice(0,2));
  var newSMin = parseInt(new_st.slice(3));
  var newETime = parseInt(new_en.slice(0,2));
  var newEMin = parseInt(new_en.slice(3));
  //시간정보를 프로토타입 객체로 변환.
  var sumStartTime = new TimeObj(newSTime,newSMin);
  var sumEndTime = new TimeObj(newETime,newEMin);

  //시간에 따른 색상변화
  var colorByTime = colorPicker(newSTime);

  //dropdown external eventbar 생성.
  var eGroup = $('<div />');
  var event = $('<div />');
  event.css({
    'background-color': colorByTime,
    'border-color': colorByTime,
    'color': '#fff',
    'width': '85%',
    'display': 'inline-block',
  }).addClass('external-event').html(val)
  //생성될 바에 data()로 시간정보 저장.
  event.data({
    startTimeObj : sumStartTime,
    endTimeObj : sumEndTime,
  });
  //인원수를 입력할 input[type='number'] 추가
  var eCountSlot = $('<input />');
  eCountSlot
    .attr({ type : 'number', min : '0', step : '1', value : '0', })
    .css({ 'width' : '13%', 'margin' : '0 0 0 2px', }
  );
  //DOM 엘리먼트 구성
  eGroup.append(event);
  eGroup.append(eCountSlot);
  //DOM 엘리먼트를 화면에 삽입
  $('#external-events').prepend(eGroup);
  //드래그 기능 추가, 시간정보를 담은 객체를 만들어 추가된 DOM엘리먼트에 삽입.
  init_events(event)
})
// ./ End of 이벤트 바 등록

//external eventbar 선택기능
$(document).on('click','.external-event',function() {
  $('.clickEvent_Opacity').not($(this)).removeClass('clickEvent_Opacity');
  var checkSelect = $(this).hasClass('clickEvent_Opacity');
  if(checkSelect == true) {
    $(this).removeClass('clickEvent_Opacity')
  } else if(checkSelect == false) {
    $(this).addClass('clickEvent_Opacity')
  }
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
    defaultDate : selectedMonth,
//>>//Ajax로 가져올 event data
    events : initialData,

    //create
    // select dates.
    selectable: true,
    selectHelper: true,
    select: function(start, end) {
      // 선택한 연도,월,일을 객체에 저장. get()--> moments.js의 function.
      startDay.year = start.get('year');
      startDay.month = start.get('month');
      startDay.date = start.get('date');
      startDay.day = start.get('day');
      endDay.year = end.get('year');
      endDay.month = end.get('month');
      endDay.date = end.get('date');
      endDay.day = end.get('day');

      if($('.external-event').hasClass('clickEvent_Opacity') == true) {
        // 선택된 event bar를 찾아 data를 가져옴.
        var selectEventBar = $('.clickEvent_Opacity')
        var workersForEvent = selectEventBar.siblings('input[type="number"]').val();

        //엘리먼트에 저장된 시간정보를 읽어 전역변수에 시간정보 저장.
        var selectEBarData = selectEventBar.data();
        startTime.time = selectEBarData.startTimeObj.time;
        startTime.min = selectEBarData.startTimeObj.min;
        endTime.time = selectEBarData.endTimeObj.time;
        endTime.min = selectEBarData.endTimeObj.min;

        //rendering, DOM Element에 data로 객체 저장.
        plannerRenderingProcess(startDay,endDay,startTime,endTime,workersForEvent,state);
        console.log(state)

      } else {
        //modal popup.
        $('#selectPopup').modal('show');
      }

    },
    // End of select dates---------------

    //drag and drop 활성화 필요.
    droppable : true,
    drop      : function (date, allDay, element) { // this function is called when something is dropped
      // retrieve the dropped element's stored Event Object
      var originalEventObject = $(this).data('eventObject')
      // bar의 input값을 읽어옴
      var workersForEvent = $(this).siblings('input[type="number"]').val()
      // we need to copy it, so that multiple events don't have a reference to the same object
      var copiedEventObject = $.extend({}, originalEventObject)

      //시간정의 : drop된 날짜값을 받음.
      var sYear = date.get('year');
      var sMonth = date.get('month');
      var sDate = date.get('date');
      var sDay = date.get('day');

      //전역변수에 날짜정보 저장. --> drop시 시작날과 종료일은 동일하게 입력
      startDay.year = date.get('year');
      startDay.month = date.get('month');
      startDay.date = date.get('date');
      startDay.day = date.get('day');

      //drop 시 하루를 더해 종료날짜를 정함.
      var endInfo = date.add(1, 'days')
      endDay.year = endInfo.get('year');
      endDay.month = endInfo.get('month');
      endDay.date = endInfo.get('date');
      endDay.day = endInfo.get('day');

      //객체로 저장된 시간정보를 전역변수에 저장.
      startTime.time = copiedEventObject.startTime.time;
      startTime.min = copiedEventObject.startTime.min;
      endTime.time = copiedEventObject.endTime.time;
      endTime.min = copiedEventObject.endTime.min;

      plannerRenderingProcess(startDay,endDay,startTime,endTime,workersForEvent,state);
    },

    //delete
    eventClick: function (calEvent, jsEvent, view) {
      //운영계획 이벤트의 경우 : 그 자리에 개인 일정 생성.
      //개인일정의 경우 : 삭제 후 운영계획 이벤트 재생성.

      //calender에 보이는 부분과 내장객체 삭제
      $('#calendar').fullCalendar('removeEvents', calEvent.id);
      console.log('[삭제과정]');
      console.log('1.내장 이벤트:');
      console.log(calEvent.id);
      console.log('2.관리 이벤트:');
      console.log(dataLocation[""+calEvent.id+""]);
      //var dataLocation = $('#calendar').data('eventList') 으로 저장된 위치에서 id와 일치하는 값 삭제.
      delete dataLocation[""+calEvent.id+""];
      console.log('3.삭제 후 모든 event:');
      console.log(dataLocation);
    },
  });
});
// ./ End of fullCalendar 초기화 -------------------------------------



// 모달창 이벤트 생성버튼
$('#makeEvent').click(function() {
  var new_st = $('#modal-startT').val(); if(new_st == "") { new_st = "00:00"; }
  var new_en = $('#modal-endT').val(); if(new_en == "") { new_en = "00:00"; }
  var workersForEvent = parseInt($('#modal-numP').val());
  if(workersForEvent == "") { workersForEvent = 0; }

  //정수형으로 시간정보 변환
  var newSTime = parseInt(new_st.slice(0,2));
  var newSMin = parseInt(new_st.slice(3));
  var newETime = parseInt(new_en.slice(0,2));
  var newEMin = parseInt(new_en.slice(3));

  //시간정보를 전역변수에 저장.
  startTime.time = newSTime;
  startTime.min = newSMin;
  endTime.time = newETime;
  endTime.min = newEMin;

  plannerRenderingProcess(startDay,endDay,startTime,endTime,workersForEvent,state);
});
// ./ End Of Modal창 이벤트 생성버튼

// Rendering process
function plannerRenderingProcess(sd,ed,st,et,wfe,state) {
  var newEventObj = createObj (sd,ed,st,et,wfe,state);
  console.log('[생성과정]')
  console.log('1.새로 생성된 obj배열:');
  console.log(newEventObj);

  var alreadyExistDatas = [];
  var alertMsg = "아래의 일정은 이미 존재합니다.\n";
  //순차적으로 rendering
  for(var i=0; i<newEventObj.length; i++) {

    //>> 1.0.3 data관리 방법 수정...
    //var dataLocation = $('#calendar').data('eventList');
    var objId = newEventObj[i].id
    //동일 id를 찾아 존재하면 return값을 받아 전체 수행 이후 경고창을 띄워 알려준다.
    if(dataLocation[""+objId+""] != null) {
      alreadyExistDatas[i] = newEventObj[i].startDate + "_" + newEventObj[i].startTime
                        + " ~ " + newEventObj[i].endDate + "_" + newEventObj[i].endTime;
    } else {
      //ConverTing : from antPeopleObj created by createObj() to fullCalendarObj
      var convertedEvent = convertToEventObj(newEventObj[i]);
      //Rendering
      $('#calendar').fullCalendar('renderEvent', convertedEvent, true);

//>>  //data 저장 : calendar DOM엘리먼트에 data()로 저장된 eventList 객체 내.
      dataLocation[""+objId+""] = newEventObj[i];

      console.log('2.생성한 event: ');
      console.log(dataLocation[""+objId+""]);
      console.log('3.모든 event:');
      console.log($('#calendar').data('eventList'));

      //$('#calendar').data(newEventObj[i].id, newEventObj[i]);
      //console.log('2.생성한 event: ');
      //console.log($('#calendar').data(newEventObj[i].id));
      //console.log('3.모든 event:');
      //console.log($('#calendar').data());
    }
  }
  //경고창 팝업.
  if(alreadyExistDatas.length>0) {
    for(var i=0; i<alreadyExistDatas.length; i++) {
      console.log(alreadyExistDatas[i]);
      alertMsg = alertMsg + alreadyExistDatas[i] + "\n";
    }
    alert(alertMsg);
    console.log('4. 중복제외 등록 후 모든 event:');
    console.log($('#calendar').data('eventList'));
  }
  /*
  //캘린더에서 날짜로 위치검색
  function calVal(num) {
    if(num<10) {
      return "0" + num;
    } else {
      return num;
    }
  }
  var selectByDate = sYear+'-'+calVal(sMonth+1)+'-'+calVal(sDate);
  console.log('합성날짜')
  console.log(selectByDate)
  var originPoint = $('.fc-day-top[data-date='+selectByDate+']').index()
  console.log('드롭한 이벤트의 캘린더 행 내 위치')
  console.log(originPoint)
  */
  console.log($('#calendar').data());
}
// ./ End of Rendering process

$('#submitPlan').click(function() {
	console.log(dataLocation)
	// dataLocation: 스크립트 헤드에 적힌 전역변수
	$.ajax({
		url : 'createplan',
		method : 'post',
		data : JSON.stringify(dataLocation),
		
		contentType: 'application/json;charset=UTF-8',
		
		dataType : 'text',
		async : false,
		error : function(response) {
			alert("통신실패, response: " + response);
			console.log(response);
		},
		success : function(response,num2) {
			alert("통신성공, response: " + response +","+ num2);
			
			document.location.href = response;
			//성공 시 이메일 존재여부 판별.
			//존재 --> 이메일이 존재한다는 알림 띄움.
			//없음 --> 회원가입 폼 자동으로 전송.
		}
	});
});

</script>
</body>
</html>