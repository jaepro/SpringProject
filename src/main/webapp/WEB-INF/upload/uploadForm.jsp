<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Upload</title>
<style type="text/css">
table {
	border-collapse: collapse;
}


</style>
</head>
<body>
<form action="/spring/user/upload" method="post" enctype="multipart/form-data">
	<table border="1">
		<tr>
			<th>상품명</th>
			<td>
				<input type="text" name="imageName" size="35">
			</td> 
		</tr>
		

		<tr>
			<td colspan="2">
				<textarea name="imageContent" rows="10" cols="50"></textarea>
			</td>
		</tr>
		
		<%-- 
		<tr>
			<td colspan="2">
				<input type="file" name="img">
			</td>
		</tr>
		
		<!-- 다중 업로드할 떄는 name속성의 이름이 같아야 한다!! => => 서버에서 배열로 받는다!! (똑같은 이름이 여러 개 오는 경우에는 배열로 받음-->
		<tr>
			<td colspan="2">
				<input type="file" name="img">
			</td>
		</tr>
		--%>
		
		<!-- 한번에 1개 또는 드래그해서 여러 개를 선택하여 업로드 할려는 경우 => 서버에서 List로 받는다!!  얘는 똑같은 이름이 아닌 다중 선택이므로 list로ㅓ 박ㄷ음-->
		<tr>
			<td colspan="2">
				<input type="file" name="img[]" multiple="multiple">  <!-- multiple : 여러 개를 선택할 수 있게 해줌 -->
			</td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="업로드">
				<input type="reset" value="취소">
			</td>
		</tr>
	</table>
	
</form>
</body>
</html>