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
    console.log(i +" 번째 divrange내 array");
    console.log(newDayListArray[i]);
  };
  return newDayListArray;
};

// 입력된 시간정보 값의 범위를 확인.
function checkRange(eleName,value) {
  if ((eleName.indexOf("Time") != -1) && (value >= 0) && (value <= 23)) {
    return "time";
  } else if ((eleName.indexOf("Min") != -1) && (value >= 0) && (value <= 59)) {
    return "min";
  } else {
    return "outOfRange";
  }
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

  var eventForRendering = {
    id : antObj.id,
    title : antObj.title,
    start : new Date(syInfo,smInfo,sdInfo,sTInfo,sMInfo),
    end : new Date(eyInfo,emInfo,edInfo,eTInfo,eMInfo),
    allDay : false,
    backgroundColor : antObj.backgroundColor,
    borderColor: antObj.borderColor
  };
  console.log('start:');
  console.log(eventForRendering.start);
  console.log('end:');
  console.log(eventForRendering.end);
  return eventForRendering;
};

//객체생성함수
function createObj (startD,endD,startT,endT) {
  //범위 내 시작일 날짜정보 객체들로 구성된 배열 생성
  var dayObjArray = divideRange(startD, endD, startT, endT);
  console.log(dayObjArray);
  console.log(dayObjArray.length);
  var objTitle = startT.colonType() + "~" + endT.colonType();
  console.log(objTitle);

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
      backgroundColor : '#00a65a',
      borderColor     : '#00a65a',
      userId : userId,
      // status : status,
      // groupName : groupName
    }
    newObjArray[i] = antPeopleObj;
    console.log("함수안에서 생성되는 객체:");
    console.log(newObjArray[i]);
  }
  return newObjArray;
};
