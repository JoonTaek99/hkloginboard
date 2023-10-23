<%@include file="../header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	@charset "UTF-8";

*{
	margin:0px;
	padding:0px;
}

#navbar{
	background-color:#3c3c3c; 
	height: 60px; 
}

.navbar ul{
	width: 800px;
	margin-left: 100px;
}

.navbar li{
	display: inline-block;
	margin-top:15px;
	font-size: 20px;
	color:white;
	margin-left:15px;
}

.navbar a{
	color:white;
	text-decoration: none;
}

.footer{
	position: absolute;
	left: 0px;
	right: 0px;
	bottom:0px;
	text-align: center;
	color:white;
	line-height: 50px;
	height: 50px;
	background-color: #3c3c3c;
}

form{
	width:800px;
	margin: 10% auto;
}



input, button{
	margin:2px;
}

.lead{
	text-align: right;
	padding: 10px;
}

#myinfo,#userAllList{
	width:1000px;
	margin:0 auto;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	$(function(){
// 		alert("jquery 연결");
		$("#replyBtn").click(function(){
			$("#replyForm").toggle();//답글폼 보여주기
			document.getElementsByTagName("form")[0].reset();//JS 초기화
// 			$("#replyForm").reset()// X
		});
	});
	
	onload=function(){
		var str = document.getElementsByTagName("td")[0].childNodes[0].nodeValue;
		
		var str2 = "${sessionScope.ldto.id}";
		
		if(str2 != str){
			document.getElementById("sujung").style.display = "none";
			document.getElementById("delete").style.display = "none";
		}
		
	}
	
</script>
<style type="text/css">
	/*답글 폼 안보이게 처리*/
	#replyForm{
		display: none;
	}
</style>
</head>
<body>
<h1>답변형 게시판</h1>
<div id="container">
	<h2>상세보기</h2>
	<table border="1">
		<tr>
			<th>작성자</th>
			<td>${dto.id}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${dto.title}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea readonly="readonly" rows="10" cols="60">${dto.content}</textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="button" id="replyBtn">답글</button>
				<button type="button" id="sujung"
						onclick="location.href='updateBoardForm.board?seq=${dto.seq}'">수정</button>
				<button type="button" id="delete"
						onclick="location.href='mulDel.board?chk=${dto.seq}'">삭제</button>
				<button type="button"
				        onclick="location.href='boardList.board'">목록</button>
			</td>
		</tr>
	</table>
	<div id="replyForm">
		<h2>답글 작성하기</h2>
		<form action="replyBoard.board" method="post">
		<input type="hidden" name="seq" value="${dto.seq}"/>
		<table border="1">
		<tr>
			<th>작성자</th>
			<td><input type="text" style="border: none;" readonly="readonly" name="id" value="${sessionScope.ldto.id}" /></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" required="required" /></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="content" required="required" rows="10" cols="60"></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="submit">답글등록</button>
				<button type="button"
				        onclick="location.href='boardList.board'">목록</button>
			</td>
		</tr>
	</table>
	</form>
	</div>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>









