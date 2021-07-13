<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<table class="table table-light" style="width: 50%;">
			<tr>
				<th>제목</th>
				<td><c:out value="${board.title }"></c:out></td>
				<th style="width: 10%">작성자</th>
				<td style="width: 10%"><c:out value="${board.writerName }"></c:out></td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3" style="width: 90%; height: 100px;"><c:out value="${board.content }"></c:out></td>
			</tr>
		</table>
		<button type="button" class="btn btn-secondary" onclick="history.back(); return false;"> 
			이전 </button>
			
		<c:if test="${board.writerId == USER.userId }">
			<button type="button" class="btn btn-secondary" id="deleteBtn">삭제</button>
			<button type="button" class="btn btn-primary" id="modifyBtn">수정 </button>
		</c:if>
		
		<div id="replyDiv" style="margin-top: 10px;">
			<form action="${pageContext.request.contextPath}/replyWrite.do" method="post">
				<table class="table table-light" style="width: 50%;">
					<tr>
						<th style="width: 10%">댓글</th>
						<td>
							<input type="text" name="content" style="width: 90%"/>
							<button type="submit" class="btn btn-success">등록</button>
						</td>
					</tr>
					<c:forEach items="${replyList}" var="item" varStatus="status">
						<tr>
							<th style="width: 10%"><c:out value="${item.writerName }"></c:out></th>
							<td><c:out value="${item.content }"></c:out>
								<fmt:parseDate value="${item.registDate }" pattern="yyyy-MM-dd'T'HH:mm:ss" var="date"/>
								<button type="button" style="float: right;" class="btn btn-secondary" 
									onclick="deleteReply('${item.idx}')">삭제</button>	
								<br>(<fmt:formatDate value="${date }" pattern="yyyy-MM-dd HH:mm:ss"/>)
							</td>
						</tr>
					</c:forEach>
				</table>
				<input type="hidden" name="boardIdx" value="${board.idx}"/>
			</form>
		</div>
	</section>
</body>

<script>

	window.onload = function(){
		
		var deleteBtn = document.getElementById("deleteBtn");
		
		deleteBtn.onclick = function() {
			if(confirm("삭제하시겠습니까?") == true){
				var path = "${pageContext.request.contextPath}/boardDelete.do";
				var params = {
						"idx": "${board.idx}"
				};
				post(path, params);
			}
			else{
				return;
			}
		}
		
		var modifyBtn = document.getElementById("modifyBtn");
		
		modifyBtn.onclick = function() {
			var path = "${pageContext.request.contextPath}/boardModifyPage.do";
			var params = {
					"idx": "${board.idx}"
			};
			post(path, params);
		}
		
	}
	
	function deleteReply(idx){
		if(confirm("댓글을 삭제하시겠습니까?") == true){
			var path = "${pageContext.request.contextPath}/replyDelete.do";
			var params = {
					"idx": idx,
					"boardIdx": "${board.idx}"
			};
			post(path, params);
		}
		else{
			return;
		}
	}
	
	function post(path, params) {
		
		const form = document.createElement('form');
		form.method = "post";
		form.action = path;
		
		for (const key in params) {
		  if (params.hasOwnProperty(key)) {
		    const hiddenField = document.createElement('input');
		    hiddenField.type = 'hidden';
		    hiddenField.name = key;
		    hiddenField.value = params[key];
		    form.appendChild(hiddenField);
		  }
		}
		
		document.body.appendChild(form);
		form.submit();
	}

</script>
</html>