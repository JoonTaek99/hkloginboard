package com.hk.user.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hk.datasource.DataBase;
import com.hk.user.dtos.RoleStatus;
import com.hk.user.dtos.UserDto;

//외부에서 메서드를 호출하는 방법
// 객체생성해서 객체명.메서드()
// Static선언 클래스명.메서드()

public class UserDao extends DataBase{

	//싱글톤 패턴 : 객체를 한번만 생성하자
	private static UserDao userDao;//생성된 객체를 저장
	private UserDao() {}// new LoginDao() X : 외부접근금지
	public static UserDao getUserDao() {//메서드를 통해 접근 가능
		if(userDao==null) {
			userDao=new UserDao();//내부에서 객체생성
		}
		return userDao;
	}
	
	//사용자 기능
	
	//1. 회원가입 기능(enabled:'Y', role:'USER', regDate:현재날짜)
	// insert문 작성
	public boolean insertUser(UserDto dto) {
		int count=0;
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql=" INSERT INTO T_USER "
				 + " VALUES (?,?,?,?,?,?,'Y',?,SYSDATE())";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, dto.getTid());
			psmt.setString(2, dto.getTpassword());
			psmt.setString(3, dto.getTname());
			psmt.setString(4, dto.getTaddress());
			psmt.setString(5, dto.getTphone());
			psmt.setString(6, dto.getTemail());
			psmt.setString(7, String.valueOf(RoleStatus.USER));
			count=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		return count>0?true:false;
	}
	
	//2. 아이디 중복 체크하기
	//   select문 실행
	public String idCheck(String id) {
		String resultId=null;
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql=" SELECT TID FROM T_USER WHERE TID=? ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs=psmt.executeQuery();
			while(rs.next()) {
				resultId=rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}
		return resultId;
	}
	
	//3. 로그인 기능 : ID와 password를 통해 회원정보 조회
	// 로그인 기능 만들어보기
	// main 메서드에 지금 만든 메서드 실행해 보기
	public UserDto getLogin(String id, String password) {
		UserDto dto=new UserDto();
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
	
		//String 객체에 값이 빈번하게 변경되는 상황이라면.. 객체 생성해서 사용
		//--> 메모리 효율이 더 좋음
		StringBuffer sb=new StringBuffer();
		sb.append(" SELECT TID, TNAME,TROLE ");
		sb.append(" FROM T_USER ");
		sb.append(" WHERE TID=? AND TPASSWORD=? AND TENABLED='Y' ");
		
		try {
			conn=getConnection();
			
			psmt=conn.prepareStatement(sb.toString());
			psmt.setString(1, id);
			psmt.setString(2, password);
			
			rs=psmt.executeQuery();
			
			while(rs.next()) {
				dto.setTid(rs.getString(1));
				dto.setTname(rs.getString(2));
				dto.setTrole(rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}
		
		return dto;
	}
	
	//나의 정보 조회
	public UserDto getUserInfo(String id) {
		UserDto dto=new UserDto();
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql=" SELECT TID, TNAME, TADDRESS, TEMAIL, TROLE, REGDATE "
				 + " FROM T_USER "
				 + " WHERE TID=? ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs=psmt.executeQuery();
			while(rs.next()) {
				dto.setTid(rs.getString(1));
				dto.setTname(rs.getString(2));
				dto.setTaddress(rs.getString(3));
				dto.setTemail(rs.getString(4));
				dto.setTrole(rs.getString(5));
				dto.setRegdate(rs.getDate(6));
				System.out.println(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}
		return dto;
	}
	
	//나의 정보 수정하기 : update문, 파라미터:UserDto
	public boolean updateUser(UserDto dto) {
		int count=0;
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql=" UPDATE T_USER "
				 + " SET TADDRESS=? , TEMAIL=? "
				 + " WHERE TID = ? ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, dto.getTaddress());
			psmt.setString(2, dto.getTemail());
			psmt.setString(3, dto.getTid());
			count=psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		return count>0?true:false;
	}
	
	//회원 탈퇴하기: update문 작성, enabled='N'
	public boolean delUser(String id) {
		int count=0;
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql=" UPDATE T_USER "
				 + " SET TENABLED = 'N' "
				 + " WHERE TID =? ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id);
			count=psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		return count>0?true:false;
	}
	
	//회원목록 전체 조회
	public List<UserDto> getAllUserList(){
		List<UserDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT TID, TNAME, TADDRESS, TPHONE, TEMAIL, TROLE, TENABLED, REGDATE "
				+ "	FROM T_USER ";
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				UserDto dto = new UserDto();
				dto.setTid(rs.getString(1));
				dto.setTname(rs.getString(2));
				dto.setTaddress(rs.getString(3));
				dto.setTphone(rs.getString(4));
				dto.setTemail(rs.getString(5));
				dto.setTrole(rs.getString(6));
				dto.setTenabled(rs.getString(7));
				dto.setRegdate(rs.getDate(8));
				list.add(dto);
				System.out.println(dto);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}
		
		return list;
		
	}
	
	//회원목록 전체 조회[사용중]
	public List<UserDto> getAllUser(){
		List<UserDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT TID, TNAME, TROLE, REGDATE "
				+ "	FROM T_USER "
				+ " WHERE TENABLED='Y' ";
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				UserDto dto = new UserDto();
				dto.setTid(rs.getString(1));
				dto.setTname(rs.getString(2));
				dto.setTrole(rs.getString(3));
				dto.setRegdate(rs.getDate(4));
				list.add(dto);
				System.out.println(dto);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}
		
		return list;
		
	}
	
	//회원등급 수정
	public boolean userUpdateRole(String id, String role) {
		int count=0;
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql=" UPDATE T_USER "
				 + " SET TROLE=? "
				 + " WHERE TID=? ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, role);
			psmt.setString(2, id);
			count=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		
		return count>0?true:false;
	}
	
	
	
}
















