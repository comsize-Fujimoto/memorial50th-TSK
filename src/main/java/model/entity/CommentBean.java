package model.entity;

import java.io.Serializable;

public class CommentBean implements Serializable {
	
	//フィールド
	private int commentId;		//コメントID
	private int taskId;			//タスクID
	private String userId;		//ユーザーID
	private String userName;	//ユーザー名
	private String comment;		//コメント
	
	//コンストラクタ
	public CommentBean() {
		
	}
	
	//ゲッターセッターを定義

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	

	
	
	

}
