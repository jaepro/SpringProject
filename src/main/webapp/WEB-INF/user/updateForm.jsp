<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/write.css">
<title>update Form</title>
</head>
<body>
<div id="write-jsp">

	<div id="right">
		<a href="/spring/">
			<img src="../image/mangom3.png" width="130" height="140" alt="mangom" />
		</a>
		<div id="container">
			<div id="edit-header">회원정보 수정</div>
			<form name="userUpdateForm" id="userUpdateForm">
				<input type="hidden" id="pg" value="${pg }" />
				<table>
				<tr>
				     <th class="label">이름</th>
				     <td class="input">
				        <input type="text" name="name" id="name" value="${userDTO.name }" />
				       <div id="nameDiv"></div>
				    </td>
				</tr>
				<tr>
				    <th class="label">아이디</th>
				    <td class="input">
				       <input type="text" name="id" id="id" value="${userDTO.id }" readonly />
				       <div id="idDiv"></div>
				    </td>
				</tr>
				<tr>
				    <th class="label">비밀번호</th>
				    <td class="input">
				       <input type="password" name="pwd" id="pwd" value="${userDTO.pwd }" />
				       <div id="pwdDiv"></div>
				    </td>
				</tr>
				<tr align="center">
			        <td colspan="2" height="20">
			           <button type="button" id="listBtn" onclick="location.href='/spring/user/list?pg=${pg}'">목록</button>
			           <button type="button" id="updateBtn">수정</button>
			           <button type="button" id="deleteBtn">회원탈퇴</button>
			           <button type="reset" id="resetBtn">초기화</button>
			        </td>
			    </tr>
			</table>
		</form>
			
		</div>
	</div>
</div>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script> 
<script type="text/javascript" src="../js/update.js"></script>
<script type="text/javascript" src="../js/delete.js"></script>
</body>
</html>