package com.tinyego.member;

import lombok.Data;

@Data
public class MemberVo {
	private String mno; // 회원번호
	private String mid; // 아이디
	private String mpwd; // 비밀번호
	private String mname; // 회원명
	private String mbirth; // 생년월일
	private String mphone; // 휴대폰번호
	private String maddr; // 주소
	private String role; // 회원 등급
	private String mmemo; // 메모
	private String joindate; // 가입일시
	
	private String username; // 스프링 시큐리티 id
	private String password; // 스프링 시큐리티 pwd
	
	private String searchcondition;
	private String searchkey;
	
	private int start;
	private int pageSize;
	private int totalRecord;
	
}
