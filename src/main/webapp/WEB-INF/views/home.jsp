<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8" %>
<%@ include file="include/header.jsp" %>

작성자 : <input type="text" name="writer" id="writer"><br>
제목 : <input type="text" name="title" id="title"><br>
내용 : <input type="text" name="content" id="content"><br>

<input type="button" value="글쓰기" id="btnCreate">
<hr>

<!-- 특정 글번호를 조회 -->
<input type="button" value="글조회" id="btnRead">
<div id="divRead"></div>

<%@ include file="include/footer.jsp" %>

<script>
	$(document).ready(function(){
		var num = 100;
		$("#btnRead").click(function(){
			$.ajax({
				url:"/boards/"+num,
				type:"GET",
				success:function(data){
					alert("글 조회 페이지 다녀옴");
					// List -> $.each();
				}
			});
		});
		
		$("#btnCreate").click(function(){
			var boardVO = {
					"writer": $("#writer").val(),
					"title": $("#title").val(),
					"content": $("#content").val()
			}
			
			$.ajax({
				url:"/boards",
				type:"POST",
				data:JSON.stringify(boardVO),
				contentType:"application/json",
				success:function(data){
					alert("안녕!");
					alert(data);
					if(data =="createOK"){
						$('#writer').val("");
						$('#title').val("");
						$('#content').val("");
					}
					
				}
			});
		});
	})
</script>