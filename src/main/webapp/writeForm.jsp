<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	System.out.println("☆여기는 writeForm.jsp");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>주소록</h1>
	
	<h2>전화번호 등록폼</h2>
	
	<p>전화번호를 등록하는 폼 입니다</p>
	
	<form action="http://192.168.0.99:8080/phonebook2/pbc" method="get">
	    <input type="hidden" name="action" value="write">
	
	    <label>이름(name)</label>
	    <input type="text" name="name" value="">
	    <br>
	
	    <label>핸드폰(hp)</label>
	    <input type="text" name="hp" value="">
	    <br>
	
	    <label>회사(company)</label>
	    <input type="text" name="company" value="">
	    <br>
		
	    <button type="submit">등록</button>
	</form>
	
</body>
</html>

