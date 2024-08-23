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
	
	<h1>タスク一覧</h1>
	<b>This page is Task_List!!</b>
	<hr>
	<!-- リストに存在している場合テーブル表示 -->
	<%if(!taskList.isEmpty()){ %>
	
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
				href="update-task-servlet?task_code=<%=task.getTaskId()%>"><%=task.getTaskName()%>
			</a>
			
			</td>
			<td><%=task.getCategoryName()%></td>
			
			<!-- 期限がnullの場合期限なしを表示する -->
			<td><% if(task.getLimitDate() != null){ %>
				
				<%=task.getLimitDate() %>
				<%
				}else{
					%> 期限なし<%
				}
				%>
			</td>
			<td><%=task.getUserName()%></td>
			<td><%=task.getStatusName()%></td>
			<td><%=task.getMemo()%></td>
		</tr>
		<%
		}
		%>
	</table>
	
	<br>
	<!-- リストに存在していない場合表示する -->
	<% 
	}else{
	%>
	<h1>タスクが存在していません。</h1>
	<b>Error!! Task is not found!!</b>
	<% 
	}
	%>
	<hr>
	<div>
		<form action="task-menu.jsp" method="POST">
			<input type="submit" value="メニュー画面へ">
		</form>
	</div>
	
	
</body>
</html>