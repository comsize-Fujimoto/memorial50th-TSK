<!-- ログイン失敗画面表示 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン失敗画面</title>
</head>
<body>
	<h1>ログイン失敗画面</h1>
	<hr>
	<h1>ログインに失敗しました。</h1>
	
		<!-- POSTでlogin.jspへ送る -->	
		<form action="login.jsp" method="POST">
		<input type="submit" value="ログイン画面へ">
	</form>
</body>
</html>