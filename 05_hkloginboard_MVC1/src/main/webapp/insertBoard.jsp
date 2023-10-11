<%@page import="com.hk.user.daos.HkDao"%>
<%@page import="com.hk.user.dtos.HkDto"%>
<%@page import="com.hk.user.dtos.UserDto"%>
<%@page import="com.hk.user.daos.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	//insertBoardForm.jsp에서 전달된 파리미터를 받는다.
	// id=hk, title=제목, content=내용
	HkDto ldto = (HkDto)session.getAttribute("ldto");
	String id=ldto.getTid();
	String title=ldto.getTtitle();
	String content=ldto.getTcontent();
	
	UserDao dao=UserDao.getUserDao();
	HkDao dao2 = new HkDao();
	boolean isS=dao2.insertBoard(new HkDto(id,title,content));
	if(isS){
		%>
		<script type="text/javascript">
			alert("글을 추가합니다.");
			location.href="boardList.jsp";//글추가하고, 글목록 페이지로 이동하기
		</script>
		<%
	}else{
		%>
		<script type="text/javascript">
			alert("글추가실패!");
			location.href="insertBoardForm.jsp";//글추가 실패하면, 글추가 페이지로 이동하기
		</script>
		<%
	}
%>
</body>
</html>










