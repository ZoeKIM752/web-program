<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Main Page</title>
</head>
<body>
	<h1>[<c:out value="${USER.name}"></c:out>]님 반갑습니다.</h1>
	
	<button type="button" style="float: right" onclick="window.location.href='logout.do'"> 
		로그아웃 </button>
	
	<button type="button" style="float: right" onclick="window.location.href='userInfoConfirm.do'"> 
		회원정보 </button>
</body>
</html>