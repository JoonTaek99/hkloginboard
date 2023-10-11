<%@page import="com.hk.user.dtos.UserDto"%>
<%@page import="com.hk.user.dtos.HkDto"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세조회하기</title>
<%
	//request 객체에 저장된 dto객체가 전달됨
	HkDto dto = (HkDto)request.getAttribute("dto");
	UserDto ldto = (UserDto)session.getAttribute("ldto");
	
		
%>
<script type="text/javascript">
	onload=function(){
		var str = document.getElementsByTagName("td")[0].childNodes[0].nodeValue;
		
		var str2 = "<%=ldto.getTid()%>";
		
		if(str2 != str){
			document.getElementById("sujung").style.display = "none";
			document.getElementById("delete").style.display = "none";
		}
		
	}
	
	
	function updateForm(seq){
		//수정폼으로 이동하기--> 수정폼에 게시글 데이터는 계속 보여지게 하기위해 상세조회기능 실행
		location.href="hkController.jsp?command=updateBoardForm&seq="+seq;
	}
	
	//삭제하기
	function deleteBoard(seq){
		location.href="hkController.jsp?command=deleteBoard&seq="+seq;
	}
	
</script>
</head>
<body>
<h1>글 상세조회</h1>
<table border="1">
	<tr>
		<th>작성자</th>
		<td><%=dto.getTid()%></td>
	</tr>
	<tr>
		<th>제목</th>
		<td><%=dto.getTtitle()%></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea rows="10" cols="60" readonly="readonly"><%=dto.getTcontent()%></textarea> </td>
	</tr>
	<tr>
		<td colspan="2">
			<button id="sujung" onclick="updateForm(<%=dto.getTseq()%>)">수정</button>
			<button id="delete" onclick="deleteBoard(<%=dto.getTseq()%>)">삭제</button>
			<button type="button" onclick="location.href='hkController.jsp?command=boardList'">글목록</button>
		</td>
	</tr>
</table>

</body>
</html>


