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
	int processingNumber = (Integer) request.getAttribute("processingNumber");
	
	//データの削除ができた場合
	if (processingNumber > 0) {
	%>
	<h2>次のデータを削除しました。</h2>
	<br>
	<br>

	<!-- 削除したタスクの詳細情報表示 -->
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
			<!-- 期限がnullの場合、期限なしを表示 -->
			<th>期限</th>

			<%
			if (taskBean.getLimitDate() != null) {
			%>
			<td><%=taskBean.getLimitDate()%></td>
			<%
			} else {
			%>
			<td>期限なし</td>
			<%
			}
			%>
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

	<%
	} else {
	%>
	<!-- データの削除が行われなかった場合 -->
	<h2>次のデータを削除できませんでした。</h2>
	<br>
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
	<%
	}
	%>

	<!-- タスク一覧画面へ飛ぶ -->
	<form action="task-list-servlet" method="POST">
		<input type="submit" value="タスク一覧へ">
	</form>

	<!-- メニュー画面へ飛ぶ -->
	<form action="task-menu.jsp" method="POST">
		<input type="submit" value="メニュー画面へ">
	</form>
	<br>


</body>
</html>