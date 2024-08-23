package model.entity;

import java.io.Serializable;

/**
 * @author dadadaaaaaaike
 */

public class CategoryBean implements Serializable{
	
	private int categoryId;			//カテゴリID
	private String categoryName;	//カテゴリネーム
	
	public CategoryBean() {
		
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
	
}
