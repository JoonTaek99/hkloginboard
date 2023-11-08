package com.hk.calboard.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

@Component
public class Util {
	// 한자리를 두자리로 변환
	public static String isTwo(String str) {
		return str.length()<2?"0"+str:str; // 5 --> "05", 10 --> "10"
	}
	
	//mdate : 날짜 형식 변환 --> yy-MM-dd HH:mm
	public String toDates(String mdate) {
		// Date 형식 : yyyy-MM-dd HH:mm:ss
		String m = mdate.substring(0, 4)+"-"
				+mdate.substring(4,6)+"-"
				+mdate.substring(6,8)+" "
				+mdate.substring(8,10)+":"
				+mdate.substring(10)+":00";
				
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm");
		Timestamp tm = Timestamp.valueOf(m);
		sdf.format(tm); // 문자열 타입일 경우 date 타입으로 변환해서 사용해야 한다.
		return sdf.format(tm);
	}
}
