<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원등록</title>
</head>
<body>
<h2>회원등록</h2>
<form action='add.do' method='post'>
이름 : <input type='text' name='name'><br>
이메일 : <input type='text' name='email'><br>
패스워드 : <input type='password' name='password'><br>
<input type='submit' value='추가'>
<input type='reset' value='취소'>
</form>
</body>
</html>