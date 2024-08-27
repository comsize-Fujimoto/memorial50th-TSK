package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.CommentBean;

public class CommentDAO {
	
	//コメントをタスク詳細に表示させる
	//CommentBean型のリストをかえす
	public List<CommentBean> commentDisplay(int taskId) throws ClassNotFoundException, SQLException{
		
		//コメント情報を入れるためのリスト
		List<CommentBean> commentList = new ArrayList<CommentBean>();
		
		//SQL文の準備
		String sql = "SELECT t1.comment_id,"
				+ "t1.task_id,"
				+ "t1.user_id,"
				+ "t2.user_name,"
				+ "t1.comment "
				+ "FROM t_comment t1 JOIN m_user t2 "
				+ "ON t1.user_id = t2.user_id "
				+ "WHERE task_id = ? ";
		
		//DB接続	
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			
			// ?に値をセット
			pstmt.setInt(1, taskId);
			
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				
				//コメントID
				int commentId = res.getInt("t1.comment_id");		
				//タスクID
				int taskIdComment = res.getInt("t1.task_id");
				//ユーザーID
				String userId = res.getString("t1.user_id");		
				//ユーザー名
				String userName = res.getString("t2.user_name");
				//コメント
				String comment = res.getString("t1.comment");
				
				//CommentBeanのインスタンス化
				CommentBean cb = new CommentBean(); 
				
				
				//セッターを値にセット
				cb.setCommentId(commentId);
				cb.setTaskId(taskIdComment);
				cb.setUserId(userId);
				cb.setUserName(userName);
				cb.setComment(comment);
				
				//CommentBeanをリストへ格納
				commentList.add(cb);
				
			}
			
		}
		
		
		//リストをリターン
		return commentList;
		
		
	}
	
}
