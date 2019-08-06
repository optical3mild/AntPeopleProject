<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Accept</title>
  
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
        Admin - Accept (근무승인화면)
        <small>Control panel</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Dashboard</li>
      </ol>
    </section>
    
      <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-md-3">

          <div class="box box-solid">
            <div class="box-header with-border">
              <h4 class="box-title">Monthly Plan List</h4>
            </div>
            <div class="box-body">
              <!-- the events -->
              <div id="external-events">
              </div>
              <hr class="divArea">
              <button id="approvalAndCancel" type="button" class="btn btn-primary btn-flat pull-right approval" disabled>승인</button>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /. box -->


          <div class="box box-solid" id="individual-box">
            <div class="box-header with-border">
              <h4 class="box-title">StaffList</h4>
            </div>
            <div class="box-body">
              <!-- the events -->
              <div id="individual-events">
              </div>
              <hr class="divArea">
              <button id="modifyAndCancel" type="button" class="btn btn-primary btn-flat pull-right modifyStaffEvent" disabled>수정</button>
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
<!-- AdminLTE App -->
<script src="setfiles/dist/js/adminlte.min.js"></script>

<%@ include file = "../common/_commonScriptList.jspf" %>

<!-- AntPeople FullCalendar function -->
<script src="setfiles/js/ant_fullcalendar1.0.3.js?ver=1"></script>

<!-- fullCalendar -->
<script src="setfiles/bower_components/moment/moment.js"></script>
<script src="setfiles/bower_components/fullcalendar/dist/fullcalendar.min.js"></script>

<!-- Page specific script -->
<script>
//var userId = 'testUser';
var userId = "${user.user_id}";
// 페이지 로드 시 받을 수신받을 데이터 형태. --> '연월' : 수정가능여부
var nowDate = new Date();

//** 승인/승인취소 버튼 클릭 시 보낼 월의 인덱스를 저장할 변수.
var selectedMonthIndex = "";

var gotList = ${monthIndex} //더미로 확인필요...
//현재 달을 확인하여 현재달 포함 이전은 수정불가로 List객체 조정.

/*var gotList = {
  '1907' : true, '1905' : false, '1906' : true,
  '1909' : false, '1900' : true , '1911' : false
}; //더미 목록, 월정보는 월 인덱스 값.
*/
var convertedList = gotList;

/*
// receivedDummy : 월별목록 선택시 수신받을 데이터 형태.
var receivedDummy = {
  '1907' : {
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
      peopleCount : '0',
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
      peopleCount : '0',
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
      peopleCount : '5',
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
      peopleCount : '1',
    }
  },
  user1 : [
    'testAdmin_19070101001907020623','testAdmin_19072101001907220623'
  ],
  user2 : [
    'testAdmin_19072101001907220623'
  ]
}
*/
//직원일정에서 제거한 목록.
$('#calendar').data('unSelectedList',[]);
var unSelectedDataLoc = $('#calendar').data('unSelectedList');

//1. 이벤트바 자동생성 : 리스트를 수신받아서 push
var monthlyMenu = [];
var targetDiv = '#external-events'
var barClass = 'external-event'
//var tempColor = '#26678d'
//페이지 로딩 시 목록을 수신받아 값이 있는경우 순차적으로 배열에 삽입.
if( Object.keys(convertedList).length > 0 ) {
  var afterConvert = convertForMonthlyMenuList(convertedList);
  $.each(afterConvert, function(key, value){
    monthlyMenu.push(key);
  });
  //배열 순서대로 정렬
  monthlyMenu.sort();
  console.log(monthlyMenu);
  //month 배열을 읽어 전부 추가.
  for(var i = 0; i<monthlyMenu.length; i++) {
    var index = monthlyMenu[i];
    var infObj = afterConvert[""+index+""];
    var tempColor = 'rgb(60, 141, 188)'
    if(infObj.mendable == false) {
      tempColor = 'rgb(120, 136, 168)'
    }
    drawMenuBar(monthlyMenu[i], infObj, targetDiv, barClass, tempColor);
  }
}

// 4자리를 객체로 변환.
function convertForMonthlyMenuList(mapObj) {
  // 객체의 key들을 array로 반환.
  var list = Object.keys(mapObj);
  console.log('list')
  console.log(list)
  var resultObj = {};
  for(var i=0; i<list.length; i++) {
    var yearPart = list[i].slice(0,2);
    var monthPart = parseInt(list[i].slice(2));
    var calYear = "20" + yearPart;
    var calMonth = monthPart + 1;
    //연월 인덱스 값을 표시될 현재연월 형태(inputbox에 사용)와 인덱스값(데이터 요청시 사용)이 들어있는 객체로 변환
    if(calMonth < 10) {
      calMonth = "0" + calMonth;
    }
    var title = calYear + "-" + calMonth;
    var result = {
      'title': title, 'monthIndex' : list[i], 'mendable' :  mapObj[""+list[i]+""]
    }
    resultObj[""+title+""] = result;
  }
  console.log("결과객체")
  console.log(resultObj);
  return resultObj;
}

// 목록생성 함수.
function drawMenuBar(title, convertedObj, targetDiv, bClass, color ) {
  //생성되는 이벤트 바 title.
  var val = title;
  //시간에 따른 색상변화
  //var monthBarColor = '#26678d'; //for test.
  //dropdown external eventbar 생성.
  var monthEventBar = $('<div />');
  monthEventBar.css({
    'background-color': color,
    'border-color': color,
    'color': '#fff',
    'width': '100%',
    'display': 'inline-block',
  }).addClass(''+bClass+'').html(val)
  //생성될 바에 data()로 시간정보 저장.
  monthEventBar.data(convertedObj);
  //DOM 엘리먼트를 화면에 삽입
  $(''+targetDiv+'').prepend(monthEventBar);
}

//>>accept.0.0.1에서 수정됨. : inputbox수정  --> 변수에 직접입력.
//month 배열에 정보가 있는경우, 캘린더 초기값 지정.
var initialMonth;
if(monthlyMenu.length>0){
  initialMonth = setCalendarToNextMonth(monthlyMenu);
} else {
  initialMonth = nowDate;
}

function setCalendarToNextMonth(menuList) {
  var lastVal = menuList[menuList.length-1];
  console.log('lastVal')
  console.log(lastVal)

  var year = parseInt(lastVal.slice(0,4));
  var month = parseInt(lastVal.slice(5));
  var newYear;
  var newMonth;
  if(month > 11) {
    newMonth = 0;
    newYear = year + 1;
  } else {
    newMonth = month;
    newYear = year;
  }
  var result = new Date(newYear, newMonth, 1);

  return result;
}
// ./ End of 2. 이벤트 바 등록


function convertMonthIndexToMomentDateObj(mIndex) {
  var yearPart = parseInt('20'+mIndex.slice(0,2));
  var monthPart = parseInt(mIndex.slice(2));
  return new Date(yearPart, monthPart);
}



//2. external eventbar 선택기능 + 버튼 형상 전환 + 캘린더에 표시될 내용 수신.
$(document).on('click','.external-event',function() {
  //직원목록 아래 수정버튼 초기화.
  $('#modifyAndCancel').removeClass('finishModify');
  $('#modifyAndCancel').addClass('modifyStaffEvent').text('수정').css({'background-color' : '#e47636'});
  //직원목록 박스 숨기기
  $('#individual-box').css({'display':'none'});
  //직원목록 초기화
  $("#individual-events").empty();
  // 캘린더 초기화
  $('#calendar').fullCalendar('removeEvents');
  // 선택여부 표시 : 투명도 조정.
  $('.clickEvent_Opacity').not($(this)).removeClass('clickEvent_Opacity');
  // 선택된 것을 다시 누른것인지 판별.
  var checkSelect = $(this).hasClass('clickEvent_Opacity');
  // 변화시킬 버튼 선정.
  var targetBtn = $('#approvalAndCancel');

  // 선택되어 있는지 여부에 따라 실행.
  // 선택되어 있었던 경우 = true.
  if(checkSelect == true) {
    // 1. 셀렉트 취소. --> 원래대로 되돌리고, 버튼 비활성화.
    // 직원목록 박스 제거.
    $('#individual-box').css({'display':'none'});
    // 음영처리 제거.
    $(this).removeClass('clickEvent_Opacity');
    // 승인취소 --> 승인으로 변경, 버튼 비활성화.
    targetBtn.removeClass('approvalCanelation')
    targetBtn.addClass('approval').text('승인').css({'background-color' : '#00a65a'});
    targetBtn.attr('disabled', true);

    //캘린더의 화면을 목록의 마지막 다음달로 변경.
    $('#calendar').fullCalendar('gotoDate',initialMonth);

    // 월 인덱스가 저장되었던 변수를 초기화.
    selectedMonthIndex = "";

  } else if(checkSelect == false) {
    // 2. 셀렉트
    // 음영처리 : 선택한것을 시각적으로 보여줌.
    $(this).addClass('clickEvent_Opacity');
    // data로 저장된 monthIndex값을 읽어 화면 이동.
    var thisMonthIndex = $(this).data().monthIndex;
    console.log('thisMonthIndex');
    console.log(thisMonthIndex);
    var thisMonthObj = convertMonthIndexToMomentDateObj(thisMonthIndex);
    $('#calendar').fullCalendar('gotoDate',thisMonthObj);

    // 월 인덱스를 전역변수에 저장.
    selectedMonthIndex = thisMonthIndex;

    //calender rendering 작업, 화면을 해당월로 표기,
    //a. 승인여부 판단하여 승인된 경우 승인취소로 표기
    targetBtn.attr('disabled', false);
    var switchInfo = $(this).data().mendable;
    if(switchInfo == false) {
      // false : 수정불가 == 승인된 상태 --> 승인취소 가능하게 변경.
      targetBtn.removeClass('approval')
      targetBtn.addClass('approvalCanelation').text('승인취소').css({'background-color' : '#e47636'});
    } else {
      // true : 수정가능 == 승인되지 않은 상태 --> 승인 가능하게 변경.
      targetBtn.removeClass('approvalCanelation')
      targetBtn.addClass('approval').text('승인').css({'background-color' : '#00a65a'});
    }
    //b. 월별이벤트 데이터를 받아온다. --> {key = 1901 : value = {해당월의 계획} }
    var targetMonth = $(this).data().monthIndex; // ex) 1901 = 19년 2월.
    var userInfo = {'user_id' : userId};

    //ajax로 데이터 수신. : 텍스트로 받아 parse.
    var receivedData = $.parseJSON(getMonthlyPlan(targetMonth));
    console.log('receivedData')
    console.log(receivedData)
    //var receivedData = receivedDummy; //테스트용. 수신받았다고 가정. --> 못받은 경우?

    var rEventList;
    var individualList = {};
//>>
     for(var key in receivedData) {
       if(key == targetMonth) {
         rEventList = receivedData[""+key+""];
       } else {
         individualList[""+key+""] = receivedData[""+key+""];
       }
     }

    //c. 받아온 이벤트 데이터를 화면에 표시한다.
    for(var key in rEventList) {
      console.log('key')
      console.log(rEventList[""+key+""])
      var convertedEvent = convertToEventObj(rEventList[""+key+""]);
      $('#calendar').fullCalendar('renderEvent', convertedEvent, true);

    }
    //d. 화면에 표시된 이벤트의 title을 숨기고, 밖으로 보여질 엘리먼트를 추가.
    remakeDisplayedEvents(rEventList);

    //e. 받아온 이벤트 데이터를 직원별로 볼 수 있도록 목록을 생성한다.
    var staffListDiv = '#individual-events'
    var staffBarClass = 'staffBar'
    var staffColor = 'rgb(60, 141, 188)'
    console.log('인덱스')
    console.log(receivedData.hasOwnProperty($(this).data().monthIndex))
    console.log(receivedData)

    if(receivedData.hasOwnProperty($(this).data().monthIndex)){
      console.log('indlist')
      console.log(individualList)
      if(Object.keys(individualList).length > 0) {
        $('#individual-box').css({'display':'block'});
        for(var key in individualList) {
          var value = individualList[""+key+""]
          drawMenuBar(key, value, staffListDiv, staffBarClass, staffColor);
        }
      }
    }
  }
})

//b. 월별 이벤트 데이터를 받아오는 함수.
function getMonthlyPlan(inputVal) {
  var targetMonthInfo = inputVal
  var result;
  console.log('targetMonthInfo')
  console.log(targetMonthInfo)
  $.ajax({
		url : 'clickMonth',
		method : 'post',
    	// data : 서버로 보낼 데이터 - string or json(key/value)
		data : targetMonthInfo,
  		// contentType : 서버로 보낼 데이터의 타입.
    	contentType: 'application/json',
    	// dataType : 서버로 부터 수신받을 데이터 타입.
		dataType : 'text',
    	async : false,
		error : function(response) {
			alert("통신실패, response: " + response);
			console.log(response);
		},
		success : function(response) {
			alert("통신성공, response: " + response);
			//document.location.href = response;
			//return response;
			console.log(response);
			result = response;
		}
	});
  return result;
}
//./ End of 3. external eventbar 선택기능


//3. 직원목록 버튼 : 선택 시 캘린더 이벤트에 표시.
$(document).on('click','.staffBar',function() {
  //직원목록 클릭 시 수정버튼 초기화.
  $('#modifyAndCancel').removeClass('finishModify');
  $('#modifyAndCancel').addClass('modifyStaffEvent').text('수정').css({'background-color' : '#e47636'});
  
  //var unSelectedDataLoc = $('#calendar').data('unSelectedList');
  //삭제할 이벤트의 id목록 초기화.
  $.removeData('#calendar','unSelectedList');
  $('#calendar').data('unSelectedList',[]);
  unSelectedDataLoc = $('#calendar').data('unSelectedList');
  //승인버튼 잠금 초기화 : <----- 직원목록 아래의 수정, 수정완료 버튼작동과 맞물려 잠금설정.
  $('#approvalAndCancel').attr('disabled',false);
  $('.selectStaffBar_Opacity').not($(this)).removeClass('selectStaffBar_Opacity');
  var checkSelect = $(this).hasClass('selectStaffBar_Opacity');
  var targetIdList = $(this).data()
  console.log(targetIdList)
  
  //클릭 시 선택 초기화.
  $('.selectedEvent').removeClass('selectedEvent');
  $('#modifyAndCancel').attr('disabled', true);
  if(checkSelect == true) {
    $(this).removeClass('selectStaffBar_Opacity');
    $('#modifyAndCancel').attr('disabled', true);

  } else if(checkSelect == false) {
    $(this).addClass('selectStaffBar_Opacity');
    //calendar에서 이벤트의 id와 일치하는 경우 효과부여. 그외는 효과지움.
    for(var key in targetIdList) {
      var targetId = targetIdList[""+key+""];
      console.log('tag')
      console.log($('span:contains("'+targetId+'")'))
      $('span:contains("'+targetId+'")').parent().parent().addClass('selectedEvent');
    }
    // selectedMonthIndex에 값이 있는 경우 --> 월 정보 선택되어 있음.
    // 선택된 월별 이벤트 바의 data를 읽어 승인여부를 판단.
    var checkApprovalState = $('.clickEvent_Opacity').data().mendable;
    if((selectedMonthIndex != "")&&($(checkApprovalState == true))) {
      //해당 월의 인덱스 정보가 존재하고, 수정기능한 상태일때 수정버튼 활성화.
      $('#modifyAndCancel').removeClass('finishModify');
      $('#modifyAndCancel').attr('disabled', false).addClass('modifyStaffEvent').text('수정');
    } else {
      $('#modifyAndCancel').removeClass('modifyStaffEvent');
      $('#modifyAndCancel').attr('disabled', true).addClass('finishModify').text('수정완료');
    }
  }
})


//4. 승인/ 승인취소 버튼
// 셀렉트 된 월의 인덱스와 승인(== false로 변경) / 승인취소(== true로 변경)을 보냄..
$('#approvalAndCancel').click(function() {
  var changeValue;
  var selectedMBar = $('.clickEvent_Opacity').data().title;
  if($(this).hasClass('approval') == true) {
    //승인 --> 월별 수정가능 상태를 false(수정불가)로 바꾸도록 전송.
    alert(selectedMBar+"를 승인합니다.");
    changeValue = false;

  } else if($(this).hasClass('approvalCanelation') == true) {
    //승인취소 --> 월별 수정가능 상태를 true(수정가능)로 바꾸도록 전송.
    alert(selectedMBar+"의 승인을 취소합니다.");
    changeValue = true;
  }
  changeApprovalState(selectedMonthIndex, changeValue);
})

//>>
function changeApprovalState(targetMonthIndex, value) {
  var monthInfoObj = {};
  monthInfoObj[""+targetMonthIndex+""] = value;

  console.log('monthInfoObj')
  console.log(monthInfoObj)
  $.ajax({
		url : 'monthtf',
		method : 'post',
		// data : 서버로 보낼 데이터 - string or json(key/value)
		data : JSON.stringify(monthInfoObj),
		// contentType : 서버로 보낼 데이터의 타입.
		contentType: 'application/json',
		// dataType : 서버로 부터 수신받을 데이터 타입.
		dataType : 'text',
		async : false,
		error : function(response) {
		    alert("등록에 실패하였습니다. 다시 실행해주세요.")
			console.log("통신실패, response:");
		    console.log(response);
		},
		success : function(response) {
			alert("승인되었습니다.");
		    //승인 시 페이지 새로고침 --> 화면 초기화로 월별 이벤트 바를 다시 생성.
			document.location.href = response;
		}
	});
}

//5. 직원일정 수정기능 활성화 버튼.
$('#modifyAndCancel').click(function() {
  if($(this).hasClass('modifyStaffEvent') == true) {
    //수정으로 표시되는 경우, --> 수정완료로 변경.
    $('#modifyAndCancel').removeClass('modifyStaffEvent');
    $('#modifyAndCancel').addClass('finishModify').text('수정완료').css({'background-color' : '#00a65a'});
    $('#approvalAndCancel').attr('disabled',true);

  } else if($(this).hasClass('finishModify') == true) {
    //수정완료로 표시되는 경우, --> 수정으로 변경
    $('#modifyAndCancel').removeClass('finishModify');
    $('#modifyAndCancel').addClass('modifyStaffEvent').text('수정').css({'background-color' : '#e47636'});
    $('#approvalAndCancel').attr('disabled',false);
    //통신발생, 페이지 새로고침
    var staffCancelInfo = unSelectedDataLoc;
    var staffId = $('.selectStaffBar_Opacity').text();
    console.log('staffCancelInfo')
    console.log(staffCancelInfo)
    communicateModifyStaffEvent(staffId, staffCancelInfo);
  }
});

function communicateModifyStaffEvent(staffId, staffCancelInfo) {
  var staffIdPart = staffId.split("_")[0];
  var canceledEventList = {};
  canceledEventList[""+staffIdPart+""] = staffCancelInfo;
  console.log('canceledEventList')
  console.log(canceledEventList)
  
  console.log("selectedMonthIndex")
  console.log(selectedMonthIndex)

  $.ajax({
		url : 'modifymonthplan?month='+selectedMonthIndex,
		method : 'post',
		// data : 서버로 보낼 데이터 - string or json(key/value)
		data : JSON.stringify(canceledEventList),
		// contentType : 서버로 보낼 데이터의 타입.
		contentType: 'application/json',
		// dataType : 서버로 부터 수신받을 데이터 타입.
		dataType : 'text',
		async : false,
		error : function(response) {
  		alert("등록에 실패하였습니다. 다시 실행해주세요.");
		},
		success : function(response) {
			alert("수정되었습니다.");
			//승인 시 페이지 새로고침 --> 화면 초기화로 월별 이벤트 바를 다시 생성.
			document.location.href = response;
		}
	});
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
var emptyObj = {}

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

  $('#calendar').fullCalendar('renderEvent', obj, true);
})

//최초페이지 로딩시 숨김으로 초기화.
$('#individual-box').css({'display':'none'});


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
    defaultDate : initialMonth,
//>>//Ajax로 가져올 event data
    events : initialData,
    //월별 표시되는 달력의 길이조정.
    fixedWeekCount : false,
    //현재 월 외의 날짜 표시조정. --> 현재표시된 날짜 외 이벤트 발생x.
    showNonCurrentDates : false,

    eventClick: function (calEvent, jsEvent, view) {
      //수정버튼이 눌러져 있는경우에 작동. --> 수정완료로 표기되어 있고 == finishModify클래스가 있음.
      //클릭시 해당 직원의 선택된 일정을 취소. --> 취소목록을 list로 저장.
      // 수정완료를 누르면 { staffId : [이벤트 id 배열]}을 ajax로 송신.
      alert('클릭')
      console.log($('modifyAndCancel').hasClass('finishModify'))
      if($('#modifyAndCancel').hasClass('finishModify') == true){
        $('span:contains("'+calEvent.id+'")').parent().parent().removeClass('selectedEvent');
        unSelectedDataLoc.push(calEvent.id);
        console.log('삭제목록')
        console.log(unSelectedDataLoc)
      }
    },
  });
});
// ./ End of fullCalendar 초기화 -------------------------------------

//화면 재구성함수.
function remakeDisplayedEvents(antPeopleObjList) {
  //if(Object.keys(antPeopleObjList).length > 0) {
  //console.log(antPeopleObjList)
  //console.log(Object.keys(antPeopleObjList))
  //console.log(Object.keys(antPeopleObjList).length > 0)
    for(var key in antPeopleObjList) {
      //목록으로부터 요구 인원수를 추출.\
      var pcValue = antPeopleObjList[""+key+""].peopleCount;
      var mpValue = antPeopleObjList[""+key+""].manPower;
      var newTitle = antPeopleObjList[""+key+""].title;

      //새로 생성한 tag가 있는경우 --> 값만 변경.

      //tag정보 생성.
      var mpSpan = $('<span />').addClass('eventMarker').css({'padding-left':3}).text(pcValue+'/'+mpValue+"명");
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
        $('span:contains("'+key+'")').parent().find('.eventMarker').text(pcValue+'/'+mpValue+"명");
        $('span:contains("'+key+'")').parent().find('.eventTitle').text(newTitle);
      }
    }
  //}
}

</script>
</body>
</html>