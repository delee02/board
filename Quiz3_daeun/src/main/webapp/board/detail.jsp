<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dto.Board" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
	.container{width:80%;
	margin: auto;
	}
	.id{
		width:30%;
	}
	.buttons{
		float:right;
		margin:5px;
	}
</style>
</head>
<body>
<%@include file="../menu.jsp" %>
<%
	Board board = (Board) request.getAttribute("boardDetail");
%>
<form method="post" action="boardDetailUpdate">
	<div class= "container">
		<div class="mb-3">
		<input type=hidden name="num" value="<%=board.getNum()%>">
		  <label for="id" class="form-label">아이디</label>
		  <div class="col-sm-3">
	      <input type="text" class="form-control" id="id" name="id" value="<%=board.getId()%>" readonly>
	    </div>
		</div>
		<div class="mb-3">
		  <label for="subject" class="form-label">제목</label>
		  <input type="text" class="form-control" id="subject" name="subject" placeholder="제목 입력" value="<%=board.getSubject()%>" readonly><%=board.getSubject() %>
		</div>
		<div class="mb-3">
		  <label for="content" class="form-label">내용</label>
		  <textarea class="form-control" id="content" name="content" rows="10" readonly><%=board.getContent() %></textarea>
		</div>
		
		<div class = "buttons">
			<button type="submit" class="btn btn-outline-success">수정</button>
			<button type="button" class="btn btn-outline-warning" onclick="location.href='deleteBoard?num=<%=board.getNum()%>'">삭제</button>
			<button type="button" class="btn btn-outline-danger" onclick="location.href='list'">뒤로</button>
		</div>
	</div>
</form>
</body>
</html>