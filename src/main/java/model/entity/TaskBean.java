package model.entity;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author dadadaaaaaaike
 */

public class TaskBean implements Serializable{
	
	private int taskId;				//タスクID
	private String taskName;		//タスク名
	private int categoryId;			//カテゴリID
	private String categoryName;	//カテゴリ名
	private LocalDate limitDate;	//期限
	private String userId;			//ユーザID
	private String userName;		//ユーザ名
	private String statusCode;		//ステータスコード
	private String statusName;		//ステータス名
	private String memo;			//メモ
	
	public TaskBean() {
		
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public LocalDate getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(LocalDate limitDate) {
		this.limitDate = limitDate;
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

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
}
