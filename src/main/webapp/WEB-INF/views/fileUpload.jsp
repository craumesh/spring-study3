<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
</head>
<body>
	<fieldset>
		<legend> 다중파일 업로드 </legend>
		<form action="/upload" method="post" enctype="multipart/form-data">
			아이디 : <input type="text" name="userid"> <br>
			이름 : <input type="text" name="username"> <hr>
			<input type="button" id="addButton" value="파일 추가">
			<div id="uploadContainer"></div>
			<hr>
			<input type="submit" value="파일 업로드">
		</form>
	</fieldset>
</body>
<script>
	$(document).ready(function() {
		var cnt = 1;
		$("#addButton").click(function(){
			$("#uploadContainer").append("<input type='file' name='file"+cnt+"' accept='image/*, application/pdf'><br>");
			cnt++;
		});
	});
</script>
</html>
