<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
		<script src="${contextPath}/js/bootstrap/bootstrap.bundle.min.js"></script>
		
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
					<img src="${contextPath}/images/logo.PNG" onclick="window.location.href='mainPage.do'"/>
				</div>
				
				<h1>[<c:out value="${USER.name}" />]님 반갑습니다.</h1>
				
				<div class="rightBtnDiv">
					<button type="button" class="btn btn-primary" style="margin-rigth: 3px;" 
						onclick="window.location.href='${contextPath}/userInfoConfirm.do'">회원정보 </button>
						
					<button type="button" class="btn btn-secondary" 
						onclick="window.location.href='${contextPath}/logout.do'">로그아웃 </button>
				</div>
				
			</div>
		</header>
		
		<section>
			<form action="${contextPath}/boardWrite.do" method="post" enctype="multipart/form-data">
				<table class="table table-light" style="width: 50%;">
					<tr>
						<th>제목</th>
						<td><input type="text" name="title" style="width: 100%;" required/></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea style="width: 100%; height: 100px;" name="content" required></textarea></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td><input type="file" name="attFile" /></td>
					</tr>
				</table>
				<button type="submit" class="btn btn-primary">등록</button>
			</form>
		</section>
	</body>
</html>