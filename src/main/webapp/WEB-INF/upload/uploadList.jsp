<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>upload List</title>
<style type="text/css">
table {
	border-collapse: collapse;
}

th, td {
	padding: 5px;
}
</style>
</head>
<body>
<a href="/spring/index">
    <img src="../image/4.jpg" alt="home" width="100" height="100">
</a>

<table border="1" frame="hsides" rules="rows">
	<tr>
		<th width="100"><input type="checkbox" id="checkAll">번호</th>
		<th width="200">이미지</th>
		<th width="200">상품명</th>
	</tr>
	
	<c:forEach var="userUploadDTO" items="${list }">
		<tr>
			<td align="center">
				<input type="checkbox" class="seqCheckbox" name="seqCheckbox" value="${userUploadDTO.seq}" />${userUploadDTO.seq}</td>
			<td align="center">
				<%-- <img alt="userUploadDTO.imageName" 
					src="http://localhost:8080/spring/storage/${userUploadDTO.imageOriginalFileName }" width="50" height="50" /> --%>
				
				<!-- 내이버 클라우드에 있는 사진들을 끌고 오고 싶은 경우 -->
				<a href="/spring/user/uploadView?seq=${userUploadDTO.seq }">
					<img alt="userUploadDTO.imageName"
						src="https://kr.object.ncloudstorage.com/bitcamp-9th-bucket-124/storage/${userUploadDTO.imageFileName }" width="50" height="50" />
				</a>
				
			</td>
			<td align="center">${userUploadDTO.imageName }</td>
		</tr>
	</c:forEach>
	
</table>
<div style="margin-top: 10px;">
        <input type="button" value="삭제" id="deleteBtn" />
</div>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="../js/uploadDelete.js"></script>
</body>
</html>