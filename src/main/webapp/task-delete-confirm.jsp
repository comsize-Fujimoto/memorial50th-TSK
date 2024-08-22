<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.entity.TaskBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除確認画面</title>
</head>
<body>
	<%
	TaskBean taskBean = (TaskBean) session.getAttribute("updateTask");
	%>

	<h1>タスク情報-削除確認画面</h1>
	<hr>
	<h2>タスク情報を削除します。よろしいですか？</h2>
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
	<!-- 削除実行 -->
	 <form action="task-delete-servlet" method="POST">
        <input type="hidden" name="task_id" value="<%= taskBean.getTaskId() %>">
        <input type="submit" value="削除する">
    </form>
	<br>
	<!-- 詳細情報に戻る -->
	<form action="task-detail.jsp" method="POST">
		<input type="submit" value="詳細表示へ">
	</form>

</body>
</html>