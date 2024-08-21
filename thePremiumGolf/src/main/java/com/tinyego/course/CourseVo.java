package com.tinyego.course;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CourseVo {
	private int cno;
	private String pno;
	private String gno;
	private String golfcourseNm;
	private String usertype;
	private String region;
	private String glocation;
	private String holeNo;
	private String courseNm;
	private String courseHole;
	private String courseImgStr;
	private MultipartFile courseImg;
	private int regPrice;
	private int regPrice_wknd;
	private String regdate;
	
	private String ino;
	
	private String searchcondition;
	private String searchkey;
	
	private int start;
	private int end;
	private int totalRecord;

}