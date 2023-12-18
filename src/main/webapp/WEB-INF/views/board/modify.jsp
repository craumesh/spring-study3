<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

<!-- 글쓰기 폼태그 -->
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">ITWILL 게시판 글쓰기</h3>
	</div>
	<form role="form" method="post">
		<div class="box-body">
			<div class="form-group">
				<label for="exampleInputEmail1">작성자</label> 
				<input type="text" class="form-control" id="exampleInputEmail1" name="writer" value="${vo.writer }" placeholder="작성자명을 입력하세요" required="required">
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">제목</label> 
				<input type="text" class="form-control" id="exampleInputEmail1" name="title" value="${vo.title }" placeholder="제목을 입력하세요" required="required">
			</div>
			<div class="form-group">
				<label>내용</label>
				<textarea class="form-control" rows="3" name="content" placeholder="내용을 입력하세요">${vo.content }</textarea>
			</div>
		</div>

		<div class="box-footer">
			<button type="submit" class="btn btn-danger">작성 완료</button>
			<div class="pull-right">
				<button type="button" class="btn btn-primary" onclick="location.href='/board/listAll'">목록</button>
			</div>
		</div>
	</form>
</div>
<!-- 글쓰기 폼태그 -->

<%@ include file="../include/footer.jsp"%>