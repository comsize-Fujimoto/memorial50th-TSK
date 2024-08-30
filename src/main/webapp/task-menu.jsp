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
		//String型のuserName変数にLoginServletでセットしたuserNameを使いたい
		String userName = (String) session.getAttribute("userName");
	%>
	<h1>メニュー画面</h1>
	<hr>

	<h4>ようこそ！<%=userName%>さん</h4>

	<div>

	<!-- タスク登録へ飛ぶ -->

	<form action="TaskAddServler" method="GET">
		<input type="submit" value="タスク登録">
		
	</form>
	
	<!--タスク一覧へ飛ぶ  -->
	<form action="task-list-servlet" method="POST">
		<input type="submit" value="タスク一覧">
		
	</form>
	
		<!-- コメントページへ飛ぶように当初作成した
 	<form action="comment-menu.jsp" method="POST">
		<input type="submit" value="コメント">
		
	</form> -->
	
	<!--ログアウトへ飛ぶ  -->
	<form action="logout-success.jsp" method="POST">
		<input type="submit" value="ログアウト">
		
	</form>

	</div>
</body>
</html>