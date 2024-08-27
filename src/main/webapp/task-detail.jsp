<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.entity.TaskBean,model.entity.CommentBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク詳細</title>
</head>
<body>
	<h3>タスク詳細</h3>
	
	<hr>
	
	<% 
		TaskBean taskBean = (TaskBean) session.getAttribute("updateTask");
		List<CommentBean> commmentList = (List<CommentBean>) session.getAttribute("commentList");
		
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
	
	<b>-- コメント一覧 --</b>
	<!-- リストがある時表示 -->
	<%if(!commmentList.isEmpty()){ %>
	
	<!-- 追加：コメント欄 
		1.コメントの表示
			a.投稿者の情報 user_idをもとにユーザー名を表示
			b.コメントの内容
	-->
		<table border="1">
			<tr>
				<th>ユーザー名</th>
				<th>コメント</th>
			</tr>
			
			<% for(CommentBean comment : commmentList){ %>
			
			<tr>
				<td><%=comment.getUserName() %></td>
				<td><%=comment.getComment() %></td>
			</tr>
			
			<% } %>
			
		</table>
		<% 
		}else{
		%>
		<h3>コメントはありません</h3>
		<% 
		}
		%>
		
		<hr>
		
		<!-- 2.コメントの投稿  -->
		<b>コメントを記入</b>
		<form action="" method="POST">
		
		<textarea rows="2" cols="100" name="comment"></textarea>
		
		<br>
		
		<input type="submit" value="コメントを投稿">
		</form>
		
		<!--
		3.コメントの削除,user_idと投稿者が一致しているときに表示
		
		if(userIdLogin.equals(userIdComment)){
		
		<form action"" method="GET">
			
		</form>
		
		}
		
		2と3はコメントの投稿者がすることができる
		
		-->
		
	<hr>
	
	
	<table>
	<!-- ログイン時のuseridとTaskBean内のuseridが一致した時編集・削除できる -->
	
	<%if(userIdLogin.equals(userIdDatabase)){ %>
	<!-- 編集へ飛ぶ -->
	<tr>
	<td>
	<form action="task-update.jsp" method="POST">
		<input type="submit" value="タスク編集">
	</form>
	</td>
	<!-- 削除確認画面へ飛ぶ -->
	<td>
	<form action="task-delete-confirm.jsp" method="GET">
		<input type="submit" value="タスク削除">
	</form>
	</td>
	
	<% 
	}
	%>
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