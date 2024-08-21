package com.tinyego;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class HolidayChecker {
	private static final Set<LocalDate> holidays = new HashSet<>();
	
	static {
        holidays.add(LocalDate.of(2024, 1, 1)); // 신정
        holidays.add(LocalDate.of(2024, 2, 9)); // 설날 연휴 시작
        holidays.add(LocalDate.of(2024, 2, 10)); // 설날
        holidays.add(LocalDate.of(2024, 2, 11)); // 설날 연휴 마지막
        holidays.add(LocalDate.of(2024, 3, 1)); // 삼일절
        holidays.add(LocalDate.of(2024, 5, 15)); // 부처님 오신 날
        holidays.add(LocalDate.of(2024, 6, 6)); // 현충일
        holidays.add(LocalDate.of(2024, 7, 17)); // 제헌절
        holidays.add(LocalDate.of(2024, 8, 15)); // 광복절
        holidays.add(LocalDate.of(2024, 9, 16)); // 추석 연휴 시작
        holidays.add(LocalDate.of(2024, 9, 17)); // 추석
        holidays.add(LocalDate.of(2024, 9, 18)); // 추석 연휴 마지막
        holidays.add(LocalDate.of(2024, 10, 3)); // 개천절
        holidays.add(LocalDate.of(2024, 10, 9)); // 한글날
        holidays.add(LocalDate.of(2024, 12, 25)); // 크리스마스
	}
	
	public static boolean isHoliday(LocalDate date) {
		return holidays.contains(date);
	}
}
