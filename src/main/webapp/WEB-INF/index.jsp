<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>***메인 화면***</h2>
<h3>
	<p><a href="http://localhost:8080/spring/user/writeForm">회원가입</a></p>
	<p><a href="/spring/user/list">출력</a></p> <!-- pg=1일 떄는 생략 가능하다!!! -->
	
	<p><a href="/spring/user/uploadForm">파일 업로드</a></p>
	<p><a href="/spring/user/uploadAJaxForm">파일 업로드(ajax 이용)</a></p>
	<br/>
	<p><a href="/spring/user/uploadList">이미지 출력</a></p>
</h3>
</body>
</html>

<!--
사용 기술 : Spring Framework  +  Maven  +  MySQL  +  MyBatis(MySQL / @Mapper사용)  +  JSP(JQuery)

 http://localhost:8080/SpringProject/ ==>http://localhost:8080/spring/ 이렇게 url을 다르게 하고 싶을 때 -> 톰캣 더블 클릭 -> 클릭 후 Edit -> 이름 바꾸기


 -->