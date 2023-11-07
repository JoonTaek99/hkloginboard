package com.hk.calboard.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hk.calboard.command.InsertCalCommand;
import com.hk.calboard.dtos.CalDto;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CalServiceImp implements ICalService{

	public Map<String, Integer> makeCalendar(HttpServletRequest request){
		Map<String, Integer> map = new HashMap<>();
		// 달력의 날짜
		String paramYear = request.getParameter("year");
		String paramMonth = request.getParameter("month");
		
		Calendar cal = Calendar.getInstance(); // 추상클래스이고, static 메서드 new(X)
		
		//											기본 오늘 날짜로 저장할지  :  요청된 날짜로 저장할지
		int year = (paramYear==null)?cal.get(Calendar.YEAR):Integer.parseInt(paramYear);
		int month = (paramMonth==null)?cal.get(Calendar.MONTH)+1:Integer.parseInt(paramMonth);
		//													calendar 객체에서 month는 0~11월임
		
		// 11월, 12월, 13월.. 오류 처리
		if(month>12) {
			month = 1;
			year++;
		}
		if(month<1) {
			month = 12;
			year--;
		}
		
		
		// 1. 월의 1일에 대한 요일 구하기
		cal.set(year, month-1, 1); // 원하는 날짜로 세팅
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1~7중에 반환(1: 일요일 ~ 7: 토요일)
		
		// 2. 월의 마지막 날 구하기
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		map.put("year", year);
		map.put("month", month);
		map.put("dayOfWeek", dayOfWeek);
		map.put("lastDay", lastDay);
		
		return map;
	}
	
	//Validated 유효값처리 라이브러리 사용
	// - Command 클래스를 생성하여 유효값 확인
	// 화면 --> command --> 컨트롤러/서비스 (command-> dto)--> Mapper로 전달
   	//         command에 저장된 값을 확인하여 오류가 있으면 화면으로 보내고 
	//         오류가 없으면 서비스로 보냄
	@Override
	public boolean insertCalBoard(InsertCalCommand insertCalCommand) {
		// command --> dto로  값을 이동
		
		
		
		return true;
	}

	@Override
	public List<CalDto> calBoardList(String id, String yyyyMMdd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CalDto calBoardDetail(int seq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean calBoardUpdate(CalDto dto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean calMulDel(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CalDto> calViewList(String id, String yyyyMM) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int calBoardCount(String id, String yyyyMMdd) {
		// TODO Auto-generated method stub
		return 0;
	}

}