package com.tinyego.cart;

import lombok.Data;

@Data
public class CartVo {
	private String ctno; // 카트 번호
	private String mid; // 회원 아이디 ★★★
	private String role; // 회원 등급
	private String mname; // 회원명
	private String mphone; // 회원 휴대폰번호
	private String gno; // 골프장 번호
	private String cno; // 코스 번호 ★★★
	private int amount; // 주문 수량
	private int rpeople; // 예약 인원
	private String regdate; // 장바구니 담은 일시
	
	private String golfcourseNm; // 골프장명
	private String courseNm; // 코스명
	private int courseHole; // 코스 홀수
	private int regPrice; // 정상요금(평일)
	private int regPrice_wknd; // 정상요금(주말)
}
