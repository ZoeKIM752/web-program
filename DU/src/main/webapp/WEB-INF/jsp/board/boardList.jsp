<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet">
	<script src="js/bootstrap/bootstrap.bundle.min.js"></script>
	
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
				<img src="images/logo.PNG" onclick="window.location.href='mainPage.do'"/>
			</div>
			
			<h1>[<c:out value="${USER.name}"></c:out>]님 반갑습니다.</h1>
			
			<div class="rightBtnDiv">
				<button type="button" class="btn btn-primary" style="margin-rigth: 3px;" onclick="window.location.href='userInfo.do'"> 
					회원정보 </button>
					
				<button type="button" class="btn btn-secondary" onclick="window.location.href='logout.do'"> 
					로그아웃 </button>
			</div>
		</div>
	</header>
	<section>
		<table class="table table-striped">
			<colgroup>  
			    <col style="width:5%">    	
		        <col style="width:65%">
		        <col style="width:5%">
		        <col style="width:10%">
		        <col style="width:5%">
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>등록날짜</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>게시판 등록 1</td>
					<td>홍길동</td>
					<td>2021-06-10 01:13</td>
				</tr>
				<tr>
					<td>2</td>
					<td>게시판 등록 2</td>
					<td>임꺽정</td>
					<td>2021-06-30 18:16</td>
				</tr>
				<tr>
					<td>3</td>
					<td>게시판 등록 3</td>
					<td>장길산</td>
					<td>2021-07-01 10:13</td>
				</tr>
				<tr>
					<td>4</td>
					<td>게시판 등록 4</td>
					<td>이상해</td>
					<td>2021-07-03 12:43</td>
				</tr>
				<tr>
					<td>5</td>
					<td>게시판 등록 5</td>
					<td>홍길동</td>
					<td>2021-07-04 08:06</td>
				</tr>
				<tr>
					<td>6</td>
					<td>게시판 등록 6</td>
					<td>이상해</td>
					<td>2021-07-04 15:52</td>
				</tr>
				<tr>
					<td>7</td>
					<td>게시판 등록 7</td>
					<td>장발장</td>
					<td>2021-07-14 16:07</td>
				</tr>
			</tbody>
		</table>
	</section>
</body>
</html>