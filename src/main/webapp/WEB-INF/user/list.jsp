<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f9;
    margin: 0;
    padding: 20px;
}
.container {
	display: flex;
	flex-direction: column;
	align-items: flex-start;
}

h3 {
	margin-bottom: 10px;
}

table {
	width: 80%;
    border-collapse: collapse;
    margin: 20px auto;
    background-color: #fff;
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
}

th, td {
    padding: 12px;
    text-align: center;
    border: 1px solid #ddd;
}

th {
    background-color: #4CAF50;
    color: white;
    font-weight: bold;
}

tr:nth-child(even) {
    background-color: #f9f9f9;
}

tr:hover {
    background-color: #f1f1f1;
}

td {
    font-size: 16px;
    color: #333;
}
img {
	margin-top: 30px;
	margin-left: 100px;
	border-radius: 50px;
	border: 2px solid gray; 
}
	
#pagingDiv {
    text-align: center;
}
#currentPaging {
    padding: 10px;
    margin: 15px;
    border: 1px solid blue;
    color: red;
}
#currentPaging:hover, #paging:hover {
    cursor: pointer;
}
</style>
</head>
<body>

<a href="/spring/">
	<img src="../image/mangom3.png" width="130" height="140" alt="mangom" />
</a>

<div class="container">
<h3>회원 목록</h3>
	<table>
	    <thead>
	        <tr>
	            <th>이름</th>
	            <th>아이디</th>
	            <th>비밀번호</th>
	        </tr>
	    </thead>
	    <tbody>
	        <c:forEach var="userDTO" items="${map2.list}">
	            <tr>
	                <td>${userDTO.name}</td>
	                <td><a href="/spring/user/updateForm?id=${userDTO.id }&pg=${map2.pg}"> ${userDTO.id} </a></td>
	                <td>${userDTO.pwd}</td>
	            </tr>
	        </c:forEach>
	    </tbody>
	   	<tfoot>
	   
		</tfoot>
	</table>
</div><!-- container -->
    
    <div id="pagingDiv">
        ${map2.userPaging.pagingHTML }
    </div>

<script type="text/javascript">
function userPaging(pg){
	location.href='/spring/user/list?pg=' + pg;
}
</script>

</body>
</html>