package com.hk.board.main;

import java.util.List;

import com.hk.board.daos.UserDao;
import com.hk.board.dtos.UserDto;

public class MainTest {
	
	public static void main(String[] args) {
		UserDao dao = new UserDao(); //객체생성을 하면 드라이버 로딩 실행
		
		//회원목록조회가능
		List<UserDto> list = dao.getAllUser();
		System.out.println();
		System.out.println("==회원목록==");
		for (int i = 0; i < list.size(); i++) {
			//System.out.println(list.get(i));
			System.out.println(list.get(i).getUserID()+"|"
										+list.get(i).getName()+"|"
										+list.get(i).getmDate());
		}
		
		//회원정보 등록하기
		UserDto dto = new UserDto();
		dto.setUserID("ARS");
		dto.setName("쌀");
		dto.setBirthYear(1999);
		dto.setAddr("런던");
		dto.setMobile1("010");
		dto.setMobile2("44444444");
		dto.setHeight(192);
		
		//boolean isS = dao.insertUser(dto);
		
		//회원정보수정하기
		//생성자 오버로딩을 활용하여 값 초기화
		System.out.println("------------------------------------------------");
		System.out.println("회원정보수정하기");
		boolean isS2 = dao.updateUser(new UserDto("ARS",
																		"이승기",
																		2025,
																		"부산",
																		"016",
																		"44444444",
																		205, 
																		null));
		
		//회원정보상세조회
				System.out.println("------------------------------------------------");
				System.out.println("회원상세정보조회");
				UserDto dto2 = dao.getUser("ARS");
				System.out.println(dto2);
				
		//회원삭제
		boolean isS3 = dao.deleteUser("LSG");
		if(isS3) {
			System.out.println("회원삭제성공");
		}
		
	}//main메서드 종료
	
}//class
