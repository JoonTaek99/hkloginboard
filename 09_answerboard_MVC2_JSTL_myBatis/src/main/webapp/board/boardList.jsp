<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>답변형 게시판</h1>
   <div id="container">
      <h2>글목록조회</h2>
      <table border="1">
         <tr>
            <th><input type="checkbox" name="allChk(this.checked)"/></th>
            <th>번호</th>
            <th>작성자</th>
            <th>제목</th>
            <th>조회수</th>
            <th>작성일</th>
            <th>삭제여부</th>
            <th>refer</th>
            <th>step</th>
            <th>depth</th>
         </tr>
         <c:set var="lists" value="${list}"/>
         <c:choose>
            <c:when test="${empty lists}">
               <tr>
                  <td colspan="10">--작성된 글이 없습니다.--</td>
               </tr>
            </c:when>
            <c:otherwise>
               <c:forEach items="${lists}"  var="dto">
                  <tr>
                     <td>
                        <input type="checkbox" name="chk" value="${dto.seq}"/>
                     </td>
                     <td>${dto.seq}</td>
                     <td>${dto.id}</td>
                     <td>
                     	<c:set var="title" value="${fn:length(dto.title)>10?fn:substring(dto.title,0,10)+='...':dto.title}" />
                     	<c:choose>
                     		<c:when test="${dto.delflag=='Y'}">
                     			--- 삭제됨 ^ㅗ^ ---
                     		</c:when>
                     		<c:otherwise>
		                     	<a href="detailBoard.board?seq=${dto.seq}">${title}</a>
                     		</c:otherwise>
                     	</c:choose>
                     </td>
                     <td>${dto.readCount}</td>
                     <td><fmt:formatDate value="${dto.regDate}" pattern="yyyy년MM월dd일" /></td>
                     <td>${dto.delflag}</td>
                     <td>${dto.refer}</td>
                     <td>${dto.step}</td>
                     <td>${dto.depth}</td>
                  </tr>                  
               </c:forEach>
            </c:otherwise>
         </c:choose>
         <tr>
         	<td colspan="10">
         		<button type="button" onclick="location.href='insertForm.board'">글추가</button>
         		<button type="submit">삭제</button>
         	</td>
         </tr>
      </table>
   </div>
</body>
</html>