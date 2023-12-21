<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">글 내용</h3>
		<div class="box-tools pull-right">
			<a href="#" class="btn btn-box-tool" data-toggle="tooltip" title="" data-original-title="Previous"><i class="fa fa-chevron-left"></i></a> <a href="#" class="btn btn-box-tool" data-toggle="tooltip" title="" data-original-title="Next"><i class="fa fa-chevron-right"></i></a>
		</div>
	</div>
	
	<form role="form" method="post">
		<input type="hidden" name="bno" value="${vo.bno }">
	</form>

	<div class="box-body no-padding">
		<div class="mailbox-read-info">
			<h3>${vo.title }</h3>
			<h5>
				<span class="mailbox-read-time pull-right">${vo.regdate }</span>
			</h5>
		</div>

		<div class="mailbox-read-message">
			<p>${vo.content }</p>
		</div>

	</div>

	<div class="box-footer">
		<button type="button" class="btn btn-warning">수정</button>
		<button type="button" class="btn btn-danger">삭제</button>
		<button type="button" class="btn btn-primary">목록</button>
	</div>
</div>

<script>
	$(document).ready(function(){
		// 목록 버튼 클릭 시 목록으로 페이지 이동
		$(".btn-primary").click(function(){
			location.href="/board/listPage";
		});
		
		var formObj = $("form[role='form']");
		console.log(formObj);
		
		// 수정 버튼 클릭시 글번호를 가지고 submit,
		// 이동 페이지 주소와 전달 방식도 변경
		$(".btn-warning").click(function(){
			formObj.attr("action", "/board/modify");
			formObj.attr("method", "GET");
			formObj.submit();
		});
		
		// 삭제 버튼 클릭시 글번호를 가지고 페이지 이동
		$(".btn-danger").click(function(){
			var result = "${vo.bno}";
			location.href="/board/delete?bno="+result;
		});
	});
</script>

<%@ include file="../include/footer.jsp"%>