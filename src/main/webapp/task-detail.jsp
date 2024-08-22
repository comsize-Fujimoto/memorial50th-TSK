<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.entity.TaskBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク詳細</title>
</head>
<body>
	<h3>タスク詳細</h3><hr>
	
	<% TaskBean taskBean = (TaskBean) session.getAttribute("updateTask");%>
	
	<div>タスク名</div><br>
	<%=taskBean.getTaskName() %>
	<br>
	
	<div>カテゴリ情報</div>
	<%=taskBean.getCategoryName() %>
	<br>
	
	<div>期限</div>
	<%=taskBean.getLimitDate() %>
	<br>
	
	<div>担当者情報</div>
	<%=taskBean.getUserName() %>
	<br>
	
	<div>ステータス情報</div>
	<%=taskBean.getStatusName() %>
	<br>
	
	<div>メモ</div>
	<%=taskBean.getMemo() %>
	<br>
	
	<!-- 編集へ飛ぶ -->
	<form action="update-task-servlet" method="POST">
		<input type="submit" value="タスク編集">
	</form>
	
	<!-- 削除確認画面へ飛ぶ -->
	<form action="task-delete-confirm.jsp" method="GET">
		<input type="submit" value="タスク削除">
	</form>
	
</body>
</html>