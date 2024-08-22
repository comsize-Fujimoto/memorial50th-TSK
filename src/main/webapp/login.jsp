<!-- ログイン画面表示 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
	<h1>ログイン画面</h1>
	<hr>
	<h1>
		<b>ユーザIDとパスワードを入力してください</b>
	</h1>
	<div>
	<!-- POSTでLoginServletへ送る -->
		<form action="LoginServlet" method="POST">
			<table>
				<tr>
					<th>ユーザID</th>
					<td><input type="text" name="user_id">
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type="password" name="password">
				</tr>
			</table>
		<br>
	<div>
	<input type="submit" value="ログイン">
	<input type="reset" value="取消">
	</div>
	</form>
	</div>
</body>
</html>