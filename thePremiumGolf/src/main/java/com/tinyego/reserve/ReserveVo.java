package com.tinyego.reserve;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ReserveVo {
	private String rno; // 예약번호
	private String rstatus; // 예약상태(예약대기, 예약확인중, 예약확정, 예약취소, 라운드준비중, 라운드완료) 
	private String mid; // 회원아이디
	private String mname; // 회원명
	private String role; // 회원등급
	private String reserveDate; // 예약일시
	private String rname; // 예약자명
	private String rphone; // 예약자 휴대폰번호
	private String gno; // 골프장번호
	private String golfcourseNm; // 골프장명
	private String courseNm; // 코스명
	private String courseHole; // 코스홀수
	private int amount; // 주문수량
	private String roundDate; // 라운드일자 <-- selectedDate
	private String roundTime; // 라운드시각 <-- availableTimes
	private String rpeople; // 예약인원
	private String reqComm; // 요청사항
	private int regularPrice; // 정상요금 <-- totalPrice
	private float salePrice; // 할인요금
	
	private String selectedDate;
	private String availableTimes;
	private int totalPrice;
	
	private String  searchcondition;
	private String searchkey;
	
	private int start;
	private int pageSize;
	private int totalRecord;
	
	private String paynum; // 주문번호
	
	private String ctno;
	private String pno;
	private int cno;
	private String ino;
	private String usertype;
	private String region;
	private String glocation;
	private String holeNo;
	private String courseImgStr;
	private MultipartFile courseImg;
	private String regdate;
}
