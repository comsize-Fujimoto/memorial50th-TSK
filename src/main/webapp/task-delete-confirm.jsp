<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除確認画面</title>
</head>
<body>
	<%
		 TaskBean taskDetail = (TaskBean) session.getAttribute("taskDetail");
	%>

	<h1>タスク情報-削除確認画面</h1>
	<hr>
	<h2>タスク情報を削除します。よろしいですか？</h2>
	<br>
		<table border="1">
			<tr>
				<th>タスク名</th>
				<td><%=taskDetail.getItemCode()%></td>
			</tr>
			<tr>
				<th>カテゴリ情報</th>
				<td><%=taskDetail.getCategoryName()%></td>
			</tr>
			<tr>
				<th>期限</th>
				<td><%=itemDetail.getItemName()%></td>
			</tr>
			<tr>
				<th>担当者情報</th>
				<td><%=itemDetail.getPrice()%></td>
			</tr>
			<tr>
				<th>ステータス情報</th>
				<td><%=itemDetail.getPrice()%></td>
			</tr>
			<tr>
				<th>メモ</th>
				<td><%=itemDetail.getPrice()%></td>
			</tr>
		</table>
	<br>

	<form action="task-delete-servlet" method="POST">
		<!-- task_idの情報を送信する -->
		<input type="hidden" name="task_id" value="<%=.getTaskId()%>">
		<input type="submit" value="削除する">
	</form>
	<br>
	<!-- 詳細情報に戻る -->
	<form action="" method="POST">
		<input type="submit" value="詳細表示へ">
	</form>

</body>
</html>