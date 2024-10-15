<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/write.css">  <!-- 이렇게 앞에 '/'가 있으면 디스패쳐로 넘어간다. 넘어가는 것을 막기 위해 servlet-context.xml에서 막은 것이다. -->
<title>회원가입</title>
</head>
<body>
<div id="write-jsp">

	<div id="right">
		<a href="/spring/">
			<img src="../image/mangom3.png" width="130" height="140" alt="mangom" />
		</a>
		<div id="container">
			<div id="edit-header">회원가입</div>
			<form name="userWriteForm" id="userWriteForm">
				<table>
				<tr>
				     <th class="label">이름</th>
				     <td class="input">
				        <input type="text" name="name" id="name" placeholder="이름 입력" />
				       <div id="nameDiv"></div>
				    </td>
				</tr>
				<tr>
				    <th class="label">아이디</th>
				    <td class="input">
				       <input type="text" name="id" id="id" placeholder="아이디 입력" />
				       <div id="idDiv"></div>
				    </td>
				</tr>
				<tr>
				    <th class="label">비밀번호</th>
				    <td class="input">
				       <input type="password" name="pwd" id="pwd" placeholder="비밀번호 입력" />
				       <div id="pwdDiv"></div>
				    </td>
				</tr>
				<tr align="center">
			        <td colspan="2" height="20">
			            <button type="button" id="writeBtn">회원가입</button>
			            <button type="reset" id="resetBtn">초기화</button>
			        </td>
			    </tr>
			</table>
		</form>
			
		</div>
	</div>
</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script> 
<script type="text/javascript" src="../js/write.js"></script> <!-- css와 마찬가지로 디스패쳐로 가지 못하게 막음 -->
</body>
</html>





