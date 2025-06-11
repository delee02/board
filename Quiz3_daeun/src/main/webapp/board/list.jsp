<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import="dto.Board" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
 .btn{
 	margin:5px;
 	float:right;
 }
 a{
 	text-decoration: none;
 	color: black;
 }
 table, th, td, tr {
 	text-align: center;
 }
</style>
</head>
<body>
<%@include file="../menu.jsp" %>
<div class="container">
	<div>
		<button type="button" class="btn btn-outline-primary" onclick="location.href='register'">글쓰기</button>
	</div>
	<%
		List<Board> boardList = (List<Board>) request.getAttribute("boardList");
	%>
	<table class="table table-striped table-hover table-bordered">
	  <tr>
	  	  <th style="width:10%">순서</th>
	      <th style="width:60%">제목</th>
	      <th style="width:15%">아이디</th>
	      <th style="width:15%">등록일</th>  
	  </tr>
	  <%
	  	if(boardList==null || boardList.isEmpty()){
	  %>
	  <tr>
	  	<td colspan="5"> 등록된 게시물이 없습니다. </td>
	  </tr>
	  <%
	  	}else{
	  		for(Board board: boardList){	
	  %>
	  <tr>
		  <td style="width:10%" name="num"><%=board.getNum() %></td>
	      <td style="width:60%"><a href="detail?num=<%= board.getNum() %>"><%=board.getSubject() %></td>
	      <td style="width:15%"><%=board.getId() %></td>
	      <td style="width:15%"><%=board.getRegist_day() %></td> 
	  </tr>
	  <%
	  		}
	  	}
	  %>
	</table>
	
	<!-- 페이징 -->
	 <% int cnt = (int)(request.getAttribute("cnt")); %>
<c:set var="totalPage" value="${cnt / 10 + 1}" />

<nav aria-label="Page navigation example" class="d-flex justify-content-center">
  <ul class="pagination">
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>

    <c:forEach begin="1" end="${totalPage}" var="i">
      <li class="page-item">
        <a class="page-link" href="list?page=${i}">${i}</a>
      </li>
    </c:forEach>

    <li class="page-item">
      <a class="page-link" href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
	
</div>
</body>
</html>