package com.ezen.antpeople.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeFormat {
	// ------------------------------- 01. 현재 시간 관련 함수
	// ------------------------------------------------

	// 현재 시간을 구하는 함수
	public String curTime() {
		LocalDateTime curTime = LocalDateTime.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return curTime.format(dateTimeFormatter);
	}

	// 현재 날짜를 구하는 함수
	public String curDate() {
		LocalDateTime curTime = LocalDateTime.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return curTime.format(dateTimeFormatter);
	}

	// 현재 달을 구하는 함수
	public String curMonth() {
		LocalDateTime curTime = LocalDateTime.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
		return curTime.format(dateTimeFormatter);
	}

	// 현재 년도를 구하는 함수
	public String curYear() {
		LocalDateTime curTime = LocalDateTime.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy");
		return curTime.format(dateTimeFormatter);
	}

	// -------------------------------- 02. 날짜 계산 함수
	// ----------------------------------------------------
	// 전날 날짜 구하기
	public String yesterday(String date) {
		LocalDate lastDay = LocalDate.parse(date);
		lastDay = lastDay.minusDays(1);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return lastDay.format(dateTimeFormatter);
	}

	// 다음날 날짜 구하기
	public String tomorrow(String date) {
		LocalDate tomorrow = LocalDate.parse(date);
		tomorrow = tomorrow.plusDays(1);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return tomorrow.format(dateTimeFormatter);
	}

	// 전달 날짜 구하기
	public String lastMonth(String date) {
		LocalDate lastMonth = LocalDate.parse(date + "-01");
		lastMonth = lastMonth.minusMonths(1);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
		return lastMonth.format(dateTimeFormatter);
	}

	// 다음달 날짜 구하기
	public String nextMonth(String date) {
		LocalDate nextMonth = LocalDate.parse(date + "-01");
		nextMonth = nextMonth.plusMonths(1);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
		return nextMonth.format(dateTimeFormatter);
	}

	// 전년 날짜 구하기
	public String lastYear(String date) {
		LocalDate lastYear = LocalDate.parse(date + "-01-01");
		lastYear = lastYear.minusYears(1);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy");
		return lastYear.format(dateTimeFormatter);
	}

	// 다음년 날짜 구하기
	public String nextYear(String date) {
		LocalDate nextYear = LocalDate.parse(date + "-01-01");
		nextYear = nextYear.plusYears(1);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy");
		return nextYear.format(dateTimeFormatter);
	}

}
