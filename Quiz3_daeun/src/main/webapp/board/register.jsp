<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<form method="post" action="register">
	<div class= "container">
		<div class="mb-3">
		  <label for="id" class="form-label">아이디</label>
		  <div class="col-sm-3">
	      <input type="text" class="form-control" id="id" name="id" placeholder="아이디 입력" required>
	    </div>
		</div>
		<div class="mb-3">
		  <label for="subject" class="form-label">제목</label>
		  <input type="text" class="form-control" id="subject" name="subject" placeholder="제목 입력" required>
		</div>
		<div class="mb-3">
		  <label for="content" class="form-label">내용</label>
		  <textarea class="form-control" id="content" name="content" rows="10"></textarea>
		</div>

		<div class = "buttons">
			<button type="submit" class="btn btn-outline-success">등록</button>
			<button type="button" class="btn btn-outline-danger" onclick="location.href='list'">취소</button>
		</div>
	</div>
</form>
</body>
</html>