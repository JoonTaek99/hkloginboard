<%@page import="com.hk.board.daos.UserDao"  %>
<%@page import="com.hk.board.dtos.UserDto"  %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
//request.getParameter("name"): 전달된 값을 받을 때 사용 -> name은 input 속성
	String userId=request.getParameter("userId");
	
	UserDao dao=new UserDao();
	boolean isS=dao.deleteUser(userId); //회원정보 삭제
	
	if(isS){
		//javascript를 활용해서 이동해보기
		response.sendRedirect("index.jsp");
	}else{
		response.sendRedirect("error.jsp");
	}
%>
</body>
</html>