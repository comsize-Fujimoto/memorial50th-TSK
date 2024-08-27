package model.entity;

import java.io.Serializable;

/**
 * @author dadadaaaaaaike
 */

public class StatusBean implements Serializable{
	
	private int statusCode;		//ステータスコード
	private String statusName;	//ステータスネーム
	
	public StatusBean() {
		
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
}
