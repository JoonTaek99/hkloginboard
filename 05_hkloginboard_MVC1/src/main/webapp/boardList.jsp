<%@page import="com.hk.user.dtos.HkDto"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글목록조회</title>
<%//실행부: java 코드를 실행하는 영역 
   List<HkDto> list =(List<HkDto>)request.getAttribute("list");
%>
<script type="text/javascript">
   function insertBoardForm(){
      location.href="hkController.jsp?command=insertBoardForm";
   }
   
   //전체선택박스구현
   function allSel(bool) {
      var chks = document.getElementsByName("chk");
      for (var i = 0; i < chks.length; i++) {
         chks[i].checked = bool; //true면 체크, false 체크해제
      }
   }
   
   // 체크박스의 체크여부 확인하고 submit 실행하기
   //true를 반환하면 submit, false를 반환하면 submit X
   function isAllCheck() {
      if(confirm("삭제할거야?다시되돌릴수없다는건정말로무서운일이야마치엎질러진물을다시담아내기힘든것처럼말이야물론손수건으로닦은후그물을다시짜내면어느정도되돌린수는있겠지하지만그물이과연이전과동일한물일까?나는아니라고생각해다시한번심사숙고하여삭제하는습관을기르도록노력하는걸추천할게왜냐하면인생은선택의연속이기때문이야이렇게기회가주어진다는것은크나큰행운이니까이기회를놓치지않고잡기를바래")){
         var count=0;
         var chks = document.getElementsByName("chk");
         for (var i = 0; i < chks.length; i++) {
            if(chks[i].checked){ //체크여부확인 : 체크되면 true
               count++;
            }
         }
         if(count == 0){
            alert("최소 하나 이상 체크하라고 뭐하는데 진짜");
         }
         return count>0?true:false;
      }
      return false;
   }
   

   
</script>
</head>

<body>
<h1>게시판</h1>
<h2>글목록</h2>
<form action="hkController.jsp" method="post" >
<input type="hidden" name="command" value="boardList" />
<table>
   <tr>
      <td>
         <input type="text" name="sel" placeholder="아이디" />
         <input type="submit" value="검색">
      </td>
   </tr>
</table>
</form>
<form action="hkController.jsp" method="post" onsubmit="return isAllCheck()">
<input type="hidden" name="command" value="muldel" />
<table border="1">
   <col width="50px" />
   <col width="50px" />
   <col width="100px" />
   <col width="300px" />
   <col width="200px" />
   <tr>
      <th><input type="checkbox" name="all" onclick="allSel(this.checked)"></th>
      <th>seq</th><th>작성자</th><th>제목</th><th>작성일</th>
   </tr>
   <%
   if(list==null || list.size() == 0){
      out.print("<tr>"
               +"<td colspan='8'>--회원이존재하지않습니다.--</td>"
               +   "</tr>");
      
   }else{
      for(int i=0;i<list.size();i++){
         HkDto dto=list.get(i);// list[dto,dto,..]--> dto꺼내기
         String str = dto.getDelflag();
         
         %>
         <tr>
            <td><input type="checkbox" name="chk" value="<%=dto.getTseq()%>"/></td>
            <td><%=dto.getTseq()%></td>
            <td><%=dto.getTid()%></td>
             <% 
            if(str.equals("N")){
            %> 
               <td><a href="hkController.jsp?command=detailBoard&seq=<%=dto.getTseq()%>"><%=dto.getTtitle()%></a></td>
            <%
            }else{
            %>
               <td><%=dto.getTtitle()%></td>
            <%            
            }
            %>
            <td><%=dto.getTregdate() %></td>
         </tr>
         <%
      }
   }
   %>
   <tr>
      <td colspan="5">
         <button type="button" onclick="insertBoardForm()">글추가</button>
         <input type="submit" value="삭제"/>      
      </td>
   </tr>
</table>
</form>
</body>
</html>




