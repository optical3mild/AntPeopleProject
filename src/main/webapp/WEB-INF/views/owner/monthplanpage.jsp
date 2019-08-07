<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>MonthPlanPage</title>
  
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
        운영 계획
        <small>Calendar</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="${path}/main/mainpage"><i class="fa fa-home"></i> Home</a></li>
        <li class="active">Calendar</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-md-3">

          <div class="box box-solid">
            <div class="box-header with-border">
              <h4 class="box-title"></h4>
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

            <div class="box-body">

              <div class="input-group" style="width:100%">
                <div>
                  <label for="monthForPlan">연월선택</label>
                  <input id="monthForPlan" class="form-control forColor panel" name='monthForPlan' type="month">
                </div>
                <br><br>
                <hr class="divArea">
                <button id="plannerButton" type="button" class="btn btn-primary btn-flat pull-right createPlanner">생성</button>
                <!-- /btn-group -->
              </div>
              <!-- /input-group -->
            </div>
          </div>

          <div class="box box-solid" id="individual-box">
            <div class="box-header with-border">
              <h4 class="box-title">StaffList</h4>
            </div>
            <div class="box-body">
              <!-- the events -->
              <div id="individual-events">
              </div>
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
<script src="../resources/dist/js/adminlte.min.js"></script>

<%@ include file = "../common/_commonScriptList.jspf" %>

<!-- AntPeople FullCalendar function -->
<script src="setfiles/js/ant_fullcalendar1.0.3.js?ver=1"></script>

<!-- fullCalendar -->
<script src="setfiles/bower_components/moment/moment.js"></script>
<script src="setfiles/bower_components/fullcalendar/dist/fullcalendar.min.js"></script>

<!-- Page specific script -->
<script>
//var userId = 'testAdmin';
var userId = "${user.user_id}";
// 페이지 로드 시 받을 수신받을 데이터 형태. --> '연월' : 수정가능여부
var nowDate = new Date();

var gotList = ${monthList} //더미로 확인필요...
console.log('gotList');
console.log(gotList);
/*//수정 : controll에서 [월:boolean, , ,] 형태로 전송.
//현재 달을 확인하여 현재달 포함 이전은 수정불가로 List객체 조정.
//var gotList = ['1907','1905','1906','1909','1900','1911']; //더미 목록, 월정보는 월 인덱스 값.
function convertToMonthObj(originDate, array){
  var result = {};
  var year = ""+originDate.getFullYear();
  console.log("year: " + year)
  var month = ""+originDate.getMonth();
  if(month<10) {
    month = "0"+month;
  }
  console.log("month: " + month)
  var now =  year.slice(2) + month;
  for(var i=0; i<array.length; i++) {
    var thisIndex = array[i];
    console.log('thisIndex')
    console.log(thisIndex)
    var nowValue = parseInt(now)
    console.log('now')
    console.log(nowValue)
    console.log(now)

    if(parseInt(now) >= parseInt(thisIndex)) {
      result[""+thisIndex+""] = false;
    } else if(parseInt(now) < parseInt(thisIndex)) {
      result[""+thisIndex+""] = true;
    }
  }
  return result;
}
var convertedList = convertToMonthObj(nowDate,gotList);
*/
var convertedList = gotList;
console.log('convertedList');
console.log(convertedList);
/*var gotList = {
  '1907' : true, '1905' : false, '1906' : true,
  '1909' : false, '1900' : true , '1911' : false
}; //더미 목록, 월정보는 월 인덱스 값.
*/
/*var newMonthlyPlanDummyArray = [
  {
    id : 'testAdmin_19061501001906160623',
    title : '01:00~06:23',
    startDate : '190615',
    endDate :'190616',
    startTime : '0100',
    endTime : '0623',
    userId : 'testAdmin',
    fromUser : {'user_id' : 'testAdmin'},
    state : '0',
    manPower : '10',
  },
  {
    id : 'testAdmin_19061503001906160623',
    title : '01:00~06:23',
    startDate : '190615',
    endDate :'190616',
    startTime : '1300',
    endTime : '0623',
    userId : 'testAdmin',
    fromUser : {'user_id' : 'testAdmin'},
    state : '0',
    manPower : '10',
  },
  {
    id : 'testAdmin_19062101001906220623',
    title : '01:00~06:23',
    startDate : '190621',
    endDate :'190622',
    startTime : '0100',
    endTime : '0623',
    userId : 'testAdmin',
    fromUser : {'user_id' : 'testAdmin'},
    state : '0',
    manPower : '5',
  },
  {
    id : 'testAdmin_19060101001906020623',
    title : '01:00~06:23',
    startDate : '190601',
    endDate :'190602',
    startTime : '0100',
    endTime : '0623',
    userId : 'testAdmin',
    fromUser : {'user_id' : 'testAdmin'},
    state : '0',
    manPower : '7',
  },
]

//>>배열로 받은 객체목록을 저장할 객체형태로 변환한다.
function convertToAntPeopleEventList(rArray) {
  var newMonthKey = rArray[0].startDate.slice(0,4);
  console.log("newMonthKey")
  console.log(newMonthKey);

  var returnObj = {};
  for(var i=0; i<rArray.length; i++) {
    var thisObj = rArray[i]
    var thisKey = rArray[i].id

    returnObj[""+thisKey+""] = thisObj;
  }
  return returnObj;
}
*/

// receivedDummy : 월별목록 선택시 수신받을 데이터 형태.
/*var receivedDummy = {
  '1906' : {
    testAdmin_19061501001906160623 : {
      id : 'testAdmin_19061501001906160623',
      title : '01:00~06:23',
      startDate : '190615',
      endDate :'190616',
      startTime : '0100',
      endTime : '0623',
      userId : 'testAdmin',
      fromUser : {'user_id' : 'testAdmin'},
      state : '0',
      manPower : '10',
    },
    testAdmin_19061503001906160623 : {
      id : 'testAdmin_19061503001906160623',
      title : '01:00~06:23',
      startDate : '190615',
      endDate :'190616',
      startTime : '1300',
      endTime : '0623',
      userId : 'testAdmin',
      fromUser : {'user_id' : 'testAdmin'},
      state : '0',
      manPower : '10',
    },
    testAdmin_19062101001906220623 : {
      id : 'testAdmin_19062101001906220623',
      title : '01:00~06:23',
      startDate : '190621',
      endDate :'190622',
      startTime : '0100',
      endTime : '0623',
      userId : 'testAdmin',
      fromUser : {'user_id' : 'testAdmin'},
      state : '0',
      manPower : '5',
    },
    testAdmin_19060101001906020623 : {
      id : 'testAdmin_19060101001906020623',
      title : '01:00~06:23',
      startDate : '190601',
      endDate :'190602',
      startTime : '0100',
      endTime : '0623',
      userId : 'testAdmin',
      fromUser : {'user_id' : 'testAdmin'},
      state : '0',
      manPower : '7',
    }
  },
  user1 : [
    'testAdmin_19061501001906160623','testAdmin_19062101001906220623'
  ],
  user2 : [
    'testAdmin_19062101001906220623'
  ]
}
*/

//Create Month Bar
//1. 연월선택 inputbox : 목록이 없을 때 현재연월로 불러오도록 초기값 지정.
var nowYear = nowDate.getFullYear();
var nowMonth = nowDate.getMonth();
var calMonth;
if(nowMonth<10) { calMonth =  "0" + (nowMonth+1);
} else { calMonth = nowMonth + 1;}
var defaultYMValue = nowYear+"-"+calMonth;
console.log('1.현재연월')
console.log("\""+ nowYear+"-"+calMonth+"\"");
$('#monthForPlan').val(defaultYMValue);
// ./End of 1. 연월선택 inputbox

//2. 이벤트바 자동생성 : 리스트를 수신받아서 push
var monthlyMenu = [];
var targetDiv = '#external-events'
var barClass = 'external-event'
//var tempColor = '#26678d'
//페이지 로딩 시 목록을 수신받아 값이 있는경우 순차적으로 배열에 삽입.
if( Object.keys(convertedList).length > 0 ) {
  var afterConvert = convertForMonthlyMenuList(convertedList);
  console.log('convertedList');
  console.log(convertedList);
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

//month 배열에 정보가 있는경우, inputbox초기값 지정.
if(monthlyMenu.length>0){
  insertMonthValue(monthlyMenu);
}

function insertMonthValue(monthlyMenu) {
  var lastVal = monthlyMenu[monthlyMenu.length-1];
  var newInputMonthValue = calNextMonthValue(lastVal);
  $('#monthForPlan').val(newInputMonthValue);
}

// 목록과 연계하여 month값 계산.
function calNextMonthValue(title) {
  var year = parseInt(title.slice(0,4));
  var month = parseInt(title.slice(5));
  var newYear;
  var newMonth = month + 1;
  if(newMonth > 12) {
    newMonth = "01";
    newYear = year + 1;
  } else {
    newYear = year;
    if(newMonth < 10) {
      newMonth = "0"+newMonth;
    };
  }
  return newYear+"-"+newMonth;
}
// ./ End of 2. 이벤트 바 등록

//입력값 감지, 리스트와 비교하여 생성버튼 비활성화.
$(document).on('change','#monthForPlan',function() {
  var targetBtn = $('#plannerButton');
  var targetBtnText = targetBtn.text();
  var setVal = $(this).val();
  var dateforCalendar = convertToMomentDateObj(setVal);
  console.log('월정보')
  console.log(dateforCalendar)
  $('#calendar').fullCalendar('gotoDate',dateforCalendar);
  //버튼 초기화
  targetBtn.attr('disabled', false);
  if(targetBtnText == "생성") {
    //렌더링된 달 목록에 입력값이 존재하는 경우 --> 사용불가로.
    if($('#external-events div:contains('+setVal+')').length>0) {
      targetBtn.attr('disabled', true);
    } else if($('#external-events div:contains('+setVal+')').length<0) {
      targetBtn.attr('disabled', false);
    }
  }
});

//역파싱 : 입력값을 date객체로.
function convertToMomentDateObj(val) {
  var yearP = parseInt(val.slice(0,4));
  var monthP = parseInt(val.slice(5))-1;
  var newDateObj = new Date(yearP,monthP);
  return newDateObj;
}

//3. external eventbar 선택기능 + 버튼 형상 전환 + 캘린더에 표시될 내용 수신.
$(document).on('click','.external-event',function() {
  $('#individual-box').css({'display':'none'});
  $("#individual-events").empty();
  $('.clickEvent_Opacity').not($(this)).removeClass('clickEvent_Opacity');
  var checkSelect = $(this).hasClass('clickEvent_Opacity');
  var targetBtn = $('#plannerButton');
  $('#calendar').fullCalendar('removeEvents');

  if(checkSelect == true) {
    $('#individual-box').css({'display':'none'});
    $(this).removeClass('clickEvent_Opacity');
    targetBtn.removeClass('modifyPlanner')
    targetBtn.addClass('createPlanner').text('생성');
    targetBtn.attr('disabled', false);
    //입력창 값을 리스트의 마지막 달 보다 한달이후의 값으로 변화시킨다.
    insertMonthValue(monthlyMenu);
    //입력창 활성화.
    $('#monthForPlan').attr('disabled', false);
    //입력창 값을 읽어 캘린더 화면 위치 조정.
    var inputValue = $('#monthForPlan').val();
    var dateforCalendar = convertToMomentDateObj(inputValue);
    $('#calendar').fullCalendar('gotoDate',dateforCalendar);

  } else if(checkSelect == false) {
    $(this).addClass('clickEvent_Opacity');
    targetBtn.removeClass('createPlanner')
    targetBtn.addClass('modifyPlanner').text('수정');
    // 입력창 값을 선택한 바의 값으로 변화 후 입력창 비활성화.
    var thisMonth = $(this).data().title;
    $('#monthForPlan').val(thisMonth).attr('disabled', true);
    //변화된 입력창 값을 읽어 캘린더 화면도 이동.
    var dateforCalendar = convertToMomentDateObj(thisMonth);
    $('#calendar').fullCalendar('gotoDate',dateforCalendar);
    //calender rendering 작업, 화면을 해당월로 표기,
    //a. 수정가능 여부 확인하여 불가한 경우 버튼 비활성화. --> 객체에 넣는것으로...
    targetBtn.attr('disabled', false);
    var switchInfo = $(this).data().mendable;
    if(switchInfo == false) {
      targetBtn.attr('disabled', true);
    }
    //b. 월별이벤트 데이터를 받아온다. --> {key = 1901 : value = {해당월의 계획} }
    var targetMonth = $(this).data().monthIndex; // ex) 1901 = 19년 2월.
    console.log('targetMonth')
    console.log(targetMonth)
    
    var userInfo = {'user_id' : userId};

    //수신받은 월별일정을 변환.
    //var rEventList = convertToAntPeopleEventList(newMonthlyPlanDummyArray);

    //ajax로 데이터 수신.
    var receivedData = $.parseJSON(getMonthlyPlan(targetMonth));
    console.log('check')
    console.log(receivedData)
    //var receivedData = receivedDummy; //테스트용. 수신받았다고 가정. --> 못받은 경우?
    var keyVal;
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
		url : 'monthplan',
		method : 'post',
    // data : 서버로 보낼 데이터 - string or json(key/value)
		data : targetMonthInfo,
    // contentType : 서버로 보낼 데이터의 타입.
    contentType: 'application/json',
    dataType : 'text',
    // dataType : 서버로 부터 수신받을 데이터 타입.
    async : false,
		error : function(response) {
			alert("통신실패, response: " + response);
			console.log(response);
		},
		success : function(response) {
			//alert("통신성공, response: " + response);
			//document.location.href = response;
			console.log(response);
			result = response;
		}
	});
  return result;
}

//d. 화면 재구성함수.
function remakeDisplayedEvents(antPeopleObjList){
  for(var key in antPeopleObjList) {
    //목록으로부터 요구 인원수를 추출.
    var mpValue = antPeopleObjList[""+key+""].manPower;
    var newTitle = antPeopleObjList[""+key+""].title;
    //tag정보 생성.
    var mpSpan = $('<span />').addClass('eventMarker').css({'padding-left':3}).text(mpValue+"명");
    var titleSpan = $('<span />').css({'margin-left':10}).text(newTitle);

    //존재하는 span을 모두 숨김.
    $('span:contains("'+key+'")').css({'display':'none'})
    $('span:contains("'+key+'")').prev('span').css({'display':'none'})
    //생성한 span tag를 추가.
    $('span:contains("'+key+'")').parent().prepend(titleSpan);
    $('span:contains("'+key+'")').parent().prepend(mpSpan);
  }
}
//./ End of 3. external eventbar 선택기능


//4. 직원목록 버튼 : 선택 시 캘린더 이벤트에 표시.
$(document).on('click','.staffBar',function() {
  $('.selectStaffBar_Opacity').not($(this)).removeClass('selectStaffBar_Opacity');
  var checkSelect = $(this).hasClass('selectStaffBar_Opacity');
  var targetIdList = $(this).data()
  console.log(targetIdList)
  //클릭 시 선택 초기화.
  $('.selectedEvent').removeClass('selectedEvent');
  if(checkSelect == true) {
    $(this).removeClass('selectStaffBar_Opacity');
  } else if(checkSelect == false) {
    $(this).addClass('selectStaffBar_Opacity');
    //calendar에서 이벤트의 id와 일치하는 경우 효과부여. 그외는 효과지움.
    for(var key in targetIdList) {
      var targetId = targetIdList[""+key+""];
      console.log('tag')
      console.log($('span:contains("'+targetId+'")'))
      $('span:contains("'+targetId+'")').parent().parent().addClass('selectedEvent');
    }
  }
})


//5. 수정/등록버튼
// 경우마다 수정과 등록페이지로 연결.
$('#plannerButton').click(function() {
  var target = convertToMonthIndex($('#monthForPlan').val());
  console.log("target")
  console.log(target)
  if($('#plannerButton').hasClass('createPlanner') == true) {
    var planName = 'createplan';
    direction(planName,target);
    console.log("ajax : 생성")
  } else if($('#plannerButton').hasClass('modifyPlanner') == true) {
    var planName = 'modifyplan';
    direction(planName,target);
    console.log("ajax : 수정")
  }
})

function convertToMonthIndex(inputVal) {
  var yearPart = inputVal.slice(2,4);
  var monthPart = parseInt(inputVal.slice(5)-1);
  var newMonth;
  if(monthPart< 10) {
    newMonth = "0" + monthPart;
  } else {
    newMonth = "" + monthPart;
  }
  return yearPart + newMonth;
}

function direction(planName,target) {
  //url 지정.
  var modifyPlan = 'modifyplan' //수정페이지 경로
  var createPlan = 'createplan' //생성페이지 경로
  var selectedUrl;
  if(planName == modifyPlan) {
    selectedUrl = modifyPlan;
    console.log('selectedUrl')
    console.log(selectedUrl)
  } else if(planName == createPlan){
    selectedUrl = createPlan;
    console.log('selectedUrl')
    console.log(selectedUrl)
  };
  $.ajax({
		url : selectedUrl,
		method : 'post',
    // data : 서버로 보낼 데이터 - string or json(key/value)
		data : target,
    // contentType : 서버로 보낼 데이터의 타입.
    contentType: 'application/json',
    // dataType : 서버로 부터 수신받을 데이터 타입.
		dataType : 'text',
    async : false,
		error : function(response) {
			alert("통신실패, response: " + response);
			console.log('월별 - 실패')
			console.log(response)
		},
		success : function(response) {
			alert("통신성공, response: " + response);
			console.log('월별 - 성공')
			console.log(response)
			document.location.href = response+'?date='+target;
		}
	});
}

//입력창 값을 읽어 초기 화면위치 표시.
var selectedMonth = $('#monthForPlan').val();

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

//수신데이터가 있을 경우, 저장.
//var gotData = "<c:out value= '${plannereventdata}'/>"
//if(gotData != "") {
//  $.extend(emptyObj, gotData)
//}


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
    defaultDate : selectedMonth,
//>>//Ajax로 가져올 event data
    events : initialData,
    //월별 표시되는 달력의 길이조정.
    fixedWeekCount : false,
    //현재 월 외의 날짜 표시조정. --> 현재표시된 날짜 외 이벤트 발생x.
    showNonCurrentDates : false,
  });
});
// ./ End of fullCalendar 초기화 -------------------------------------


/* //버튼 사용안함.
// 왼쪽 버튼을 클릭하였을 경우
$(document).on('click','button.fc-prev-button', function() {
  var date = $("#calendar").fullCalendar("getDate");
  changeDateInputArea(date);
});
// 오른쪽 버튼을 클릭하였을 경우
$(document).on('click','button.fc-next-button', function() {
  var date = $("#calendar").fullCalendar("getDate");
  changeDateInputArea(date);
});
// 좌우 버튼 클릭시 input ele의 값을 바꿈.
function changeDateInputArea(date) {
  var yearP = date.get('year');
  var monthP = date.get('month')+1;
  if(monthP < 10) {
    monthP = "0" + monthP
  }
  var forInput = yearP +"-"+ monthP;
  $("#monthForPlan").val(forInput);
}
*/

//>>>추가사항
//내용이 없는 경우, staffListDiv 숨김
//캘린더 상단 버튼 누를때마다 가진 정보를 바탕으로 다시 화면을 렌더링.

</script>
</body>
</html>