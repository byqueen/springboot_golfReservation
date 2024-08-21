package com.tinyego.product;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductVo {
	private int pno;
	private String gno;
	private String golfcourseNm;
	private String usertype;
	private String region;
	private String glocation;
	private String holeNo;
	private String courseNm;
	private String courseHole;
	private int regPrice;
	private int regPrice_wknd;
	private String courseImgStr;
	private MultipartFile courseImg;
	private String regdate;
	
	private String searchkey;
	
	private int start;
	private int end;
	private int totalRecord;
}