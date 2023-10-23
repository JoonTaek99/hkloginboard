<%@include file="header.jsp" %>
<%@page import="com.hk.user.dtos.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("utf-8"); %>
<% response.setContentType("text/html; charset=utf-8"); %>
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

input{
	border: 1px solid rgb(192, 192, 192) ;
	width: 780px;
	height: 40px;
	border-radius: 5px;
	padding-left: 10px;
}

input, button{
	margin:2px;
}

.lead{
	text-align: right;
	padding: 10px;
}

.table{
	width:100%;
	border-collapse: collapse;
}

.table tr>th, .table tr>td {
	padding:5px;
} 

.table tr:nth-child(odd){
	background-color: #dcdcdc;
}

#myinfo,#userAllList{
	width:1000px;
	margin:0 auto;
}
</style>
<script type="text/javascript">
	function delUser(id){
		location.href="delUser.user?id="+id;
	}
</script>
</head>
<body>
<div id="container">
	<div class="main">
		<div class="lead">
			<span>${ldto.id}[${ldto.role}]님이 로그인 함</span> |
			<span><a href="myinfo.user?id=${sessionScope.ldto.id}">나의정보</a></span> |
			<span><a href="logout.user">로그아웃</a></span>
		</div>
		<div class="contents">
			<h1>사용자 페이지</h1>
			<h2>나의 정보</h2>
			<div id="myinfo">
				<form action="updateUser.user" method="post">
					<input type="hidden" name="id" value="${ldto.id}"/>
					<table class="table">
						<tr>
							<th>아이디</th>
							<td>${dto.id}</td>
						</tr>
						<tr>
							<th>이름</th>
							<td>${dto.name}</td>
						</tr>
						<tr>
							<th>등급</th>
							<td>${dto.role}</td>
						</tr>
						<tr>
							<th>주소</th>
							<td>
								<input type="text" name="address" value="${dto.address}"/>
							</td>
						</tr>
						<tr>
							<th>이메일</th>
							<td>
								<input type="email" name="email" value="${dto.email}"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<button type="submit">수정</button>
								<button type="button" onclick="delUser('${dto.id}')">탈퇴</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>
<%@include file="footer.jsp" %>
</body>
</html>


