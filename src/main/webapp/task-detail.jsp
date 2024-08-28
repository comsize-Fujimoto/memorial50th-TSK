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
		List<CommentBean> commentList = (List<CommentBean>) session.getAttribute("commentList");
		
		//ログインIDはタスク編集・削除とコメント削除の判定用
		String userIdLogin = (String) session.getAttribute("userId");
		String userIdDatabase = taskBean.getUserId();
		
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
	<!-- メモがnullの場合何も表示しない -->
	<% if(taskBean.getMemo() != null){ %>
	<%=taskBean.getMemo() %>
	<%
	}else{
	%>
	<%
	}
	%>
	<br>
	
	<hr>
	
	<b>-- コメント一覧 --</b>
	<!-- リストがある時表示 -->
	<%if(!commentList.isEmpty()){ %>
	
	<!-- 追加：コメント欄 
		1.コメントの表示
			a.投稿者の情報 user_idをもとにユーザー名を表示
			b.コメントの内容
			c.コメント削除欄(userIdが一致した時、押下可能)
	-->
		<table border="1">
			<tr>
				<th>ユーザー名</th>
				<th>コメント</th>
				<th>削除</th>
			</tr>
			
			<% for(CommentBean comment : commentList){ %>
			
			<tr>
				<td><%=comment.getUserName() %></td>
				<td><%=comment.getComment() %></td>
				<% if(userIdLogin.equals(comment.getUserId())){ %>
				<td>
					<a
					href="comment-servlet?comment_id=<%=comment.getCommentId()%>">
					<button>削除する</button>
					</a>
				</td>
				<% 
				}else{ 
				%>
				<td>
					<button disabled="disabled">削除する</button>
				</td>
				<%
				}
				%>
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
		<form action="comment-servlet" method="POST">
		
		<textarea rows="2" cols="100" name="comment"></textarea>
		
		<br>
		
		<input type="submit" value="コメントを投稿">
		</form>
		
		
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