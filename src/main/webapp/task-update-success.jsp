<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.entity.TaskBean"%>
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
		TaskBean taskBean = (TaskBean) session.getAttribute("updateTask");
		
		//ログインIDは編集・削除判定用
		String userIdLogin = (String) session.getAttribute("userId");
		String userIdDatabase = taskBean.getUserId();
		//コメントに紐づいてるuser_idが欲しい
		//String userIdComment = コメントのリストからもらう。
	%>
	
	
	<div><b>タスク名</b></div>
	<%=taskBean.getTaskName() %>
	<br>
	
	<div><b>カテゴリ情報</b></div>
	<%=taskBean.getCategoryName() %>
	<br>
	
	<!-- 期限がnullの場合期限なしを表示 -->
	<div><b>期限</b></div>
	<% if(taskBean.getLimitDate() != null){ %>
				
			<%=taskBean.getLimitDate() %>
		<%
		}else{
			%> 期限なし<%
		}
		%>
	<br>
	
	<div><b>担当者情報</b></div>
	<%=taskBean.getUserName() %>
	<br>
	
	<div><b>ステータス情報</b></div>
	<%=taskBean.getStatusName() %>
	<br>
	
	<div><b>メモ</b></div>
	<%=taskBean.getMemo() %>
	<br>
	
	<hr>
	
	<%
	//セッションからupdateTaskを消す
	session.removeAttribute("updateTask");
	%>
	
	<table>
	<!-- 一覧へ戻る -->
	<td>
	<form action="task-list-servlet" method="POST">
		<input type="submit" value="タスク一覧">
		
	</form>
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