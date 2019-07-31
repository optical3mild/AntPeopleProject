// Start of 프로토타입 객체 정의 -------------------------------------------
// *Day 객체 속성 : year, month, date, day
function DayObj(year,month,date,day) {
  this.year = year;
  this.month = month;
  this.date = date;
  this.day = day;
  this.arrayType = function() { return [this.year,this.month,this.date]; };
  this.shortType = function() {
    var tmpY = ("" + this.year).slice(2);
    var tmpM, tmpD;
    if(this.month<10) { tmpM = "0" + this.month; } else { tmpM = "" + this.month; }
    if(this.date<10) { tmpD = "0" + this.date; } else { tmpD = "" + this.date; }
    return tmpY + tmpM + tmpD;
  };
};

// *Time 객체 속성 : time, min
function TimeObj(time,min) {
  this.time = time;
  this.min = min;
  this.arrayType = function() { return [this.time,this.min]; };
  this.shortType = function() {
    var tmpTime, tmpMin;
    if(this.time<10) { tmpTime = "0" + this.time; } else { tmpTime = "" + this.time; }
    if(this.min<10) { tmpMin = "0" + this.min; } else { tmpMin = "" + this.min; }
    return tmpTime + tmpMin;
  };
  this.colonType = function() {
    var tmpTime, tmpMin;
    if(this.time<10) { tmpTime = "0" + this.time; } else { tmpTime = "" + this.time; }
    if(this.min<10) { tmpMin = "0" + this.min; } else { tmpMin = "" + this.min; }
    return tmpTime + ":" + tmpMin;
  };
};
// ./ End of 프로토타입 객체 정의 -----------------------

//범위 내 모든 시작일 날짜정보 생성, 객체로 리턴.
function divideRange (start, end, start_T, end_T) {
  var start = new Date(start.year,start.month,start.date).getTime();
  console.log('[fn : divideRange]')
  var end = new Date(end.year,end.month,end.date).getTime();
  var oneDayValue = 24*60*60*1000;
  var diffDay = (Math.abs(end - start))/(1000 * 3600 * 24);

  var newDayListArray = [];

  for (var i=0; i<diffDay; i++) {
    //시작일 정보.
    var newStart = new Date(start + oneDayValue * i);
    var newStartInfo = new DayObj();
    newStartInfo.year = newStart.getFullYear();
    newStartInfo.month = newStart.getMonth();
    newStartInfo.date = newStart.getDate();
    newStartInfo.day = newStart.getDay();

    //종료일 정보.
    var newEnd;
    var newEndInfo = new DayObj();
    if(start_T.time > end_T.time) {
      newEnd = new Date(start + oneDayValue * (i+1));
      newEndInfo.year = newEnd.getFullYear();
      newEndInfo.month = newEnd.getMonth();
      newEndInfo.date = newEnd.getDate();
      newEndInfo.day = newEnd.getDay();
    } else if((start_T.time == end_T.time) && (start_T.min >= end_T.min)) {
      newEnd = new Date(start + oneDayValue * (i+1));
      newEndInfo.year = newEnd.getFullYear();
      newEndInfo.month = newEnd.getMonth();
      newEndInfo.date = newEnd.getDate();
      newEndInfo.day = newEnd.getDay();
    } else {
      newEndInfo.year = newStart.getFullYear();
      newEndInfo.month = newStart.getMonth();
      newEndInfo.date = newStart.getDate();
      newEndInfo.day = newStart.getDay();
    }
    newDayListArray[i] = {
      startInfo : newStartInfo,
      endInfo : newEndInfo
    }
    console.log('1. '+ i +' 번째 divrange내 array');
    console.log(newDayListArray[i]);
  };
  console.log('fn: divideRange 종료.')
  return newDayListArray;
};

// //Parsing 함수: 관리객체 --> 이벤트객체;
function convertToEventObj(antObj) {
  var syInfo = parseInt("20"+antObj.startDate.slice(0,2));
  var smInfo = parseInt(antObj.startDate.slice(2,4));
  var sdInfo = parseInt(antObj.startDate.slice(4));

  var eyInfo = parseInt("20"+antObj.endDate.slice(0,2));
  var emInfo = parseInt(antObj.endDate.slice(2,4));
  var edInfo = parseInt(antObj.endDate.slice(4));

  var sTInfo = parseInt(antObj.startTime.slice(0,2));
  var sMInfo = parseInt(antObj.startTime.slice(2));

  var eTInfo = parseInt(antObj.endTime.slice(0,2));
  var eMInfo = parseInt(antObj.endTime.slice(2));


  var colorByTime = colorPicker(sTInfo);

  var eventForRendering = {
    id : antObj.id,
    title : antObj.id, //plannerMain0.0.2에서 바뀜. remakeDisplayedEvents()함수참조.
    start : new Date(syInfo,smInfo,sdInfo,sTInfo,sMInfo),
    end : new Date(eyInfo,emInfo,edInfo,eTInfo,eMInfo),
    allDay : false,
    backgroundColor : colorByTime,
    borderColor: colorByTime,
  };
  console.log('[fn : convertToEventObj]')
  console.log('1.start:');
  console.log(eventForRendering.start);
  console.log('2.end:');
  console.log(eventForRendering.end);
  console.log('3.Obj');
  console.log(eventForRendering);
  return eventForRendering;
};

//객체생성함수
function createObj (startD,endD,startT,endT,numOfWorkers,state) {
  //범위 내 시작일 날짜정보 객체들로 구성된 배열 생성
  var dayObjArray = divideRange(startD, endD, startT, endT);
  console.log('[fn : createObj]')
  console.log('1.[fn : divideRange]생성할 기간 배열, 길이')
  console.log(dayObjArray);
  console.log(dayObjArray.length);
  var objTitle = startT.colonType() + "~" + endT.colonType();
  
  var newObjArray = new Array();
  for(var i=0; i<dayObjArray.length; i++) {
    var startObj = dayObjArray[i].startInfo;
    var endObj = dayObjArray[i].endInfo;

    //id : userId+'_' + 시작일6자리 + 시작시간4자리 + 종료알6자리 + 종료시간4자리
    var objId = userId + "_" + startObj.shortType() + startT.shortType() + endObj.shortType() + endT.shortType();

    // obj순차적으로 생성.
    var antPeopleObj = {
      id : objId,
      title : objTitle,
      startDate : startObj.shortType(),
      endDate : endObj.shortType(),
      startTime : startT.shortType(),
      endTime : endT.shortType(),
      userId : userId,
      fromUser : {user_id : userId},
      state : state,
      manPower : numOfWorkers
      // groupName : groupName
    }
    newObjArray[i] = antPeopleObj;
    console.log('2.생성되는 관리객체 배열')
    console.log(newObjArray[i]);
    console.log(newObjArray[i].title);
  }
  return newObjArray;
};

var colorSet = {
  earlyMoring : '#26678d',//06~08
  morning : '#5da3cd', //09~11
  noon : '#5eccd7',//12~14
  afternoon : '#7dda60', //15~17
  beforSunset: '#f39c12', //18~20
  night : '#4e48b9', //21~05
}
//color picker
function colorPicker(time) {
  //var num = startT.time;
  if((time>=6)&&(time<9)) { return colorSet.earlyMoring; }
  else if((time>=9)&&(time<12)) { return colorSet.morning; }
  else if((time>=12)&&(time<15)) { return colorSet.noon; }
  else if((time>=15)&&(time<18)) { return colorSet.afternoon; }
  else if((time>=18)&&(time<21)) { return colorSet.beforSunset; }
  else { return colorSet.night; }
}
