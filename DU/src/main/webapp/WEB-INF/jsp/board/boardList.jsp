<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/jsp/layout/header.jsp"></jsp:include>
	</header>
	
	<div id="filterDiv">
		<table>
			<tr>
				<th>제목</th>
				<td><input type="text" id="searchTitle"
						value="<c:out value='${title }'></c:out>"/></td>
				<td><button type="button" id="searchBtn" class="btn btn-primary">검색</button>
				</td>
			</tr>
		</table>
		<button type="button" class="btn btn-success"
			onclick="window.location.href='boardWritePage.do'">게시글 등록</button>
	</div>
	
	<table id="dataList" class="table table-stiped table-hover">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>등록날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${boardList}" var="item" varStatus="status">
				<tr ondblclick="trDblClick('${item.idx}');">
					<td><c:out value="${status.count + pagination.startList}"/></td>
					<td><c:out value="${item.title}"/></td>
					<td><c:out value="${item.writerName}"/></td>
					<td><c:out value="${item.registDate}"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<div id="paginationBox">
		<ul class="pagination">
			<c:if test="${pagination.prev}">
				<li class="page-item"><a class="page-link" href="#" onClick="fn_prev('${pagination.page}', '${pagination.range}', '${pagination.rangeSize}')">Previous</a></li>
			</c:if>
			
			<c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="idx">
				<li class="page-item <c:out value="${pagination.page == idx ? 'active' : '' }"/> "><a class="page-link" href="#" onClick="fn_pagination('${idx}', '${pagination.range}', '${pagination.rangeSize}')">${idx}</a></li>  
			</c:forEach>
			
			<c:if test="${pagination.next}">
				<li class="page-item"><a class="page-link" href="#" onClick="fn_next('${pagination.page}', '${pagination.range}', '${pagination.rangeSize}')">Next</a></li>
			</c:if>
		</ul>
	
	</div>
	
</body>

<script>
	
	window.onload = function() {
		var searchTitle = document.getElementById("searchTitle");
		var searchBtn = document.getElementById("searchBtn");
		
		searchTitle.addEventListener("keydown", function(event){
			if(event.keyCode === 13){
				searchBtn.click();
			}
		})
		
		searchBtn.onclick = function() {
// 			var tr = document.querySelectorAll("#dataList tbody tr");
			
// 			for(var item of tr){
// 				var title = item.getElementsByTagName('td')[1].innerHTML;
				
// 				if(title.includes(searchTitle.value)){
// 					item.style.display = "";
// 				} else {
// 					item.style.display = "none";
// 				}
// 			}

			var url = "boardListPage.do";
			url = url + "?title=" + searchTitle.value;
			
			location.href = url;
		}
	}
	
	//이전 버튼 이벤트
	function fn_prev(page, range, rangeSize){
		var page = ((range - 2) * rangeSize) +1;
		var range = range - 1;
		
		var url = "boardListPage.do";
		url = url + "?page=" + page;
		url = url + "&range=" + range;
		url = url + "&title=" + searchTitle.value;
		
		location.href = url;
	}
	
	//페이지 번호 클릭 이벤트
	function fn_pagination(page, range, rangeSize){
		var url = "boardListPage.do";
		url = url + "?page=" + page;
		url = url + "&range=" + range;
		url = url + "&title=" + searchTitle.value;
		
		location.href = url;
	}
	
	// 다음 버튼 이벤트
	function fn_next(page, range, rangeSize) {
		var page = parseInt((range * rangeSize)) + 1;
		var range = parseInt(range) + 1;
		
		var url = "boardListPage.do";
		url = url + "?page=" + page;
		url = url + "&range=" + range;
		url = url + "&title=" + searchTitle.value;
		
		location.href = url;
	}

	function trDblClick(idx){
		var url = "boardInfoPage/"+idx+".do"
		
		location.href = url;
	}

</script>




</html>