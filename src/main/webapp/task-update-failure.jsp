<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.entity.TaskBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク編集失敗</title>
</head>
<body>
	
	<h1>タスクの編集に失敗しました。</h1>
	
	<%
	TaskBean updateTask = (TaskBean) session.getAttribute("updateTask");
	int taskId = updateTask.getTaskId();
	%>
	
	<!-- セッションからタスクidを読み出し -->
	<%
	//セッションからupdateTaskを消す
	session.removeAttribute("updateTask");
	%>
	
	<table>
	<tr>
	<!-- タスク詳細画面に戻る -->
	<td>
	<a href="update-task-servlet?task_code=<%=taskId%>"><button>詳細画面に戻る</button></a>
	</td>
	<!-- 一覧へ戻る -->
	<td>
	<form action="task-list-servlet" method="POST">
		<input type="submit" value="タスク一覧">
	</form>
	</td>
	<!-- メニューへ戻る -->
	<td>
	<form action="task-menu.jsp" method="POST">
		<input type="submit" value="メニュー画面へ">
	</form>
	</td>
	</tr>
	</table>
	
</body>
</html>