<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除確認画面</title>
</head>
<body>
	削除確認画面

	<form action="task-delete-servlet" method="POST">
		<!-- task_idの情報を送信する -->
		<input type="hidden" name="item_code" value="<%=.getTaskId()%>">
		<input type="submit" value="削除する">
	</form>
	<br>
	<!-- 詳細情報に戻る -->
	<form action="" method="POST">
		<input type="submit" value="詳細表示へ">
	</form>

</body>
</html>