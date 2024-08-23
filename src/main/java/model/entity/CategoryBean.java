package model.entity;

import java.io.Serializable;

public class CategoryBean implements Serializable{
	
	private int categoryCode;
	private String categoryName;
	
	public CategoryBean() {
		
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
