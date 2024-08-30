<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.entity.TaskBean,java.time.LocalDate"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク編集完了</title>
</head>
<body>
	<h3>タスク編集完了</h3>
	
	<hr>
	
	<% 
		TaskBean updateTask = (TaskBean) session.getAttribute("updateTask");
		int taskId = updateTask.getTaskId();
	%>
	
	<div><b>タスク名</b></div>
	<%=updateTask.getTaskName() %>
	<br>
	
	<div><b>カテゴリ情報</b></div>
	<%=updateTask.getCategoryName() %>
	<br>
	
	<!-- 期限がnullの場合期限なしを表示 -->
	<div><b>期限</b></div>
	<% if(updateTask.getLimitDate() != null){ %>
				
			<%=updateTask.getLimitDate() %>
		<%
		}else{
			%> 期限なし<%
		}
		%>
	<br>
	
	<div><b>担当者情報</b></div>
	<%=updateTask.getUserName() %>
	<br>
	
	<div><b>ステータス情報</b></div>
	<%=updateTask.getStatusName() %>
	<br>
	
	<div><b>メモ</b></div>
	<% if(updateTask.getMemo() != null){ %>
				
			<%=updateTask.getMemo() %>
		<%
		}else{
			
		}
		%>
	<br>
	
	<hr>
	
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