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

//범위 내 모든 날짜정보 생성, 객체로 리턴.
function divideRange (start, end) {
  var start = new Date(start.year,start.month,start.date).getTime();
  var end = new Date(end.year,end.month,end.date).getTime();
  var oneDayValue = 24*60*60*1000;
  var diffDay = (Math.abs(end - start))/(1000 * 3600 * 24);

  var newDayListArray = [];
  for (var i=0; i<diffDay; i++) {
    var newDay = new Date(start + oneDayValue * i);
    var newDayInfo = new DayObj();
    newDayInfo.year = newDay.getFullYear();
    newDayInfo.month = newDay.getMonth();
    newDayInfo.date = newDay.getDate();
    newDayInfo.day = newDay.getDay();

    newDayListArray[i] = newDayInfo;
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
  var yInfo = parseInt("20"+antObj.dayInfo.slice(0,2));
  var mInfo = parseInt(antObj.dayInfo.slice(2,4));
  var dInfo = parseInt(antObj.dayInfo.slice(4));

  var sTInfo = parseInt(antObj.start.slice(0,2));
  var sMInfo = parseInt(antObj.start.slice(2));

  var eTInfo = parseInt(antObj.end.slice(0,2));
  var eMInfo = parseInt(antObj.end.slice(2));

  var eventForRendering = {
    id : antObj.id,
    title : antObj.title,
    start : new Date(yInfo,mInfo,dInfo,sTInfo,sMInfo),
    end : new Date(yInfo,mInfo,dInfo,eTInfo,eMInfo),
    allDay : false,
    backgroundColor : antObj.backgroundColor,
    borderColor: antObj.borderColor
  };
  return eventForRendering;
};

//객체생성함수
function createObj (startD,endD,startT,endT) {
  //범위 내 날짜정보 객체들로 구성된 배열 생성
  var dayObjArray = divideRange(startD, endD);
  console.log(dayObjArray);
  console.log(dayObjArray.length);
  var objTitle = startT.colonType() + "~" + endT.colonType();
  console.log(objTitle);

  var newObjArray = new Array();
  for(var i=0; i<dayObjArray.length; i++) {
    var dayObj = dayObjArray[i];

    //id : userId+'_' + year두자리 + month두자리 + date두자리 + 시작시간4자리 + 종료시간4자리 + 전체data index 세자리
    var objId = userId + "_" + dayObj.shortType() + startT.shortType() + endT.shortType();

    // obj순차적으로 생성.
    var antPeopleObj = {
      id : objId,
      title : objTitle,
      dayInfo : dayObj.shortType(),
      start : startT.shortType(),
      end : endT.shortType(),
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
