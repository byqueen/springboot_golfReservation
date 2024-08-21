package com.tinyego.golf;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class GolfVo {
	private String gno; // 골프장번호
	private String golfcourseNm; // 골프장명
	private String golfImgStr; // 골프장사진(String)
	private MultipartFile golfImg; // 골프장사진(file)
	private String usertype; // 종류: 회원제·대중제
	private String region; // 지역
	private String glocation; // 소재지
	private String gown; // 사업자명(대표자)
	private String totalarea; // 총면적(제곱미터)
	private String holeNo; // 홀수(홀)
	private String regdate; // 업데이트일시
	
	private String searchkey; // 검색어
	
	private int start;
	private int end;
	private int totalRecord;
	
}
