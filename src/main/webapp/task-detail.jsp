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
	
	<form action="update-task-servlet" method="post">
	
	<div>タスク名</div><br>
	<input type="text" name="taskName">
	
	<div>カテゴリ情報</div>
	<select name="categoryId">
		<option value=""></option>
	</select>
	
	<div>期限</div>
	<input type="date" name="limitDate">
	
	<div>担当者情報</div>
	<select name="userId">
		<option value=""></option>
	</select>
	
	<div>ステータス情報</div>
	<select name="statusCode">
		<option value=""></option>
	</select>
	
	<div>メモ</div>
	<textarea name="memo" rows="4" cols="25">
	
	</textarea>
	
	</form>
</body>
</html>