package com.tinyego;

import java.text.SimpleDateFormat;

import com.tinyego.product.ProductVo;

public class TimeStr {
    
	public String CurrentTime() {
		long time = System.currentTimeMillis();
		SimpleDateFormat daytime = new SimpleDateFormat("yyMMdd");
		String timeStr = daytime.format(time);
		System.out.println("===> timeStr : "+timeStr);
		
		return timeStr;
	}
}
