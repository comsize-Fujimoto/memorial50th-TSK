<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.entity.TaskBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除結果画面</title>
</head>
<body>
	<%
	TaskBean taskBean = (TaskBean) session.getAttribute("updateTask");
	%>

	<br>
	<table border="1">
		<tr>
			<th>タスク名</th>
			<td><%=taskBean.getTaskName()%></td>
		</tr>
		<tr>
			<th>カテゴリ情報</th>
			<td><%=taskBean.getCategoryName()%></td>
		</tr>
		<tr>
			<th>期限</th>
			<td><%=taskBean.getLimitDate()%></td>
		</tr>
		<tr>
			<th>担当者情報</th>
			<td><%=taskBean.getUserName()%></td>
		</tr>
		<tr>
			<th>ステータス情報</th>
			<td><%=taskBean.getStatusName()%></td>
		</tr>
		<tr>
			<th>メモ</th>
			<td><%=taskBean.getMemo()%></td>
		</tr>
	</table>
	<br>


</body>
</html>