<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../include/header.jsp"%>

${result }
${pageVO }
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">ITWILL 게시판</h3>
		<div class="box-tools pull-right">
			<div class="has-feedback">
				<input type="text" class="form-control input-sm" placeholder="Search">
				<span class="glyphicon glyphicon-search form-control-feedback"></span>
			</div>
		</div>

	</div>

	<div class="box-body no-padding">		
		<div class="table-responsive mailbox-messages">
			<table class="table table-hover table-striped">
				<tbody>
					<tr>
						<td><div class="icheckbox_flat-blue" aria-checked="false" aria-disabled="false" style="position: relative;"><input type="checkbox" style="position: absolute; opacity: 0;"></div></td>
						<td class="mailbox-star"><b>글번호</b></td>
						<td class="mailbox-name"><b>제목</b></td>
						<td class="mailbox-subject"><b>작성자</b></td>
						<td class="mailbox-date"><b>작성일</b></td>							
						<td class="mailbox-attachment"><b>조회수</b></td>
					</tr>
					<c:forEach var="vo" items="${list }">
						<tr>
							<td><div class="icheckbox_flat-blue" aria-checked="false" aria-disabled="false" style="position: relative;"><input type="checkbox" style="position: absolute; opacity: 0;"></div></td>
							<td class="mailbox-star"><b>${vo.bno }</b></td>
							<td class="mailbox-name"><b><a href="/board/content?bno=${vo.bno }">${vo.title }</a></b></td>
							<td class="mailbox-subject"><b>${vo.writer }</b></td>
							<td class="mailbox-date"><fmt:formatDate value="${vo.regdate}" dateStyle="short" pattern="MM-dd"/></td>								
							<td class="mailbox-attachment"><b>${vo.viewcnt}</b></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>

	</div>

	<div class="box-footer no-padding">
		<div class="dataTables_paginate paging_simple_numbers col-xs-push-6">
			<div class="pagination pull-right">
				<button type="button" class="btn btn-primary" onclick="location.href='/board/regist'">글작성</button>
			</div>
			<ul class="pagination pagination-m pull-right">
				<c:if test="${pageVO.prev }">
					<li class="paginate_button previous">
						<a href="/board/listPage?page=${pageVO.endPage-pageVO.displayPageNum }">Previous</a>
					</li>
				</c:if>
				<c:forEach var="i" begin="${pageVO.startPage }" end="${pageVO.endPage }" step="1">
					<li ${pageVO.cri.page == i ? "class='paginate_button active'" : "class='paginate_button'"} >
						<a href="/board/listPage?page=${i }">${i }</a>
					</li>				
				</c:forEach>
				<c:if test="${pageVO.next }">
					<li class="paginate_button next">
						<a href="/board/listPage?page=${pageVO.startPage+pageVO.displayPageNum }">Next</a>
					</li>
				</c:if>
			</ul>
		</div>
	</div>
</div>

<script>
	var result = "${result}";
	if(result == "CREATEOK"){
		alert("작성완료!");	
	} else if(result == "MODIFYOK"){
		alert("수정완료!");
	} else if(result == "DELETEOK"){
		alert("삭제완료!");
	}
</script>

<%@ include file="../include/footer.jsp"%>