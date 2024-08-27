<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>
</head>
<body>

	<%
		String userName = (String) session.getAttribute("userName");
	%>
	<h1>メニュー画面</h1>
	<hr>
	<%
		if (userName != null) {
	%>
	<h4>ようこそ！<%=userName%>さん</h4>
	<%
		}
	%>
	<div>

<!-- タスク登録のaction先を齋藤さんに確認 -->
	<form action="Task-register.jsp" method="POST">
		<input type="submit" value="タスク登録">
		
	</form>
	
	<form action="task-list-servlet" method="POST">
		<input type="submit" value="タスク一覧">
		
	</form>
	
		<!-- formの送り先決める -->
<!-- 	<form action="comment-menu.jsp" method="POST">
		<input type="submit" value="コメント">
		
	</form> -->
	
	<form action="logout-success.jsp" method="POST">
		<input type="submit" value="ログアウト">
		
	</form>

	</div>
</body>
</html>