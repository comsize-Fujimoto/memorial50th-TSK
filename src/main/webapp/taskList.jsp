<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List,model.entity.TaskBean"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク一覧</title>
</head>
<body>
	<% List<TaskBean> taskList = (List<TaskBean>)request.getAttribute("taskList");%>
	
	<hr>
	
		<table border="1">
		<tr>
			<th>タスク名</th>
			<th>カテゴリ情報</th>
			<th>期限</th>
			<th>担当者情報</th>
			<th>ステータス情報</th>
			<th>メモ</th>
		</tr>
		<%
		for (TaskBean task : taskList) {
		%>
		<tr>
			<td>
			<a
				href="update-task-servlet?task_code=<%=task.getTaskId()%>"><%=task.getTaskName()%></a>
			</td>
			<td><%=item.getCategoryName()%></td>
			<td><%=item.getItemName()%></td>
			<td><%=item.getPrice()%>円</td>
			<td><%=item.getCategoryName()%></td>
			<td><%=item.getItemName()%></td>
			<td><%=item.getPrice()%>円</td>
		</tr>
		<%
		}
		%>
	</table>
	
	
	
	
</body>
</html>