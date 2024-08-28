package model.entity;

import java.io.Serializable;

/**
 * @author dadadaaaaaaike
 */

public class StatusBean implements Serializable{
	
	private String statusCode;		//ステータスコード
	private String statusName;	//ステータスネーム
	
	public StatusBean() {
		
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
	
}
