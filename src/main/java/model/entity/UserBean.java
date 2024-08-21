package model.entity;

import java.io.Serializable;

public class UserBean implements Serializable {
	
	private String userId;			//ユーザID
	private	 String password;		//パスワード
	private String userName;		//ユーザ名
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
