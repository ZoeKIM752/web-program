<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.bundle.min.js"></script>
	
	<style>
		header {
			margin-bottom: 10px;
		}
		h1 {
			margin: auto;
		}
		.rightBtnDiv {
			display: flex; 
			float: right; 
			width: 13%;
		}
		.leftBtnDiv {
			display: flex; 
			float: left; 
			width: 6%;
		}
		img {
			margin-left: auto;
			margin-right: auto; 
			display: block;
		}
	</style>
	<title>Main Page</title>
</head>
<body>
	<header>
		<div style="display: flex; border-bottom: 1px solid black">
			
			<div class="leftBtnDiv">
				<img src="${pageContext.request.contextPath}/images/logo.PNG" onclick="window.location.href='${pageContext.request.contextPath}/mainPage.do'"/>
			</div>
			
			<h1>[<c:out value="${USER.name}"></c:out>]님 반갑습니다.</h1>
			
			<div class="rightBtnDiv">
				<button type="button" class="btn btn-primary" style="margin-rigth: 3px;" onclick="window.location.href='${pageContext.request.contextPath}/userInfo.do'"> 
					회원정보 </button>
					
				<button type="button" class="btn btn-secondary" onclick="window.location.href='${pageContext.request.contextPath}/logout.do'"> 
					로그아웃 </button>
			</div>
		</div>
	</header>
	<section>
		<form action="${pageContext.request.contextPath}/boardModify.do" method="post">
			<table class="table table-light" style="width: 50%;">
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" style="width: 100%;" 
							value="${board.title }" required/></td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea style="width: 100%; height: 100px;" name="content" 
							required><c:out value="${board.content }"></c:out></textarea>
					</td>
				</tr>
			</table>
			<input type="hidden" name="idx" value="${board.idx }"/>	
			<button type="button" class="btn btn-secondary" onclick="history.back(); return false;"> 
				이전 </button>
			<button type="submit" class="btn btn-primary"> 수정 </button>
		</form>
	</section>
</body>
</html>