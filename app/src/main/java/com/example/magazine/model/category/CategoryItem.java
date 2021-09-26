package com.example.magazine.model.category;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CategoryItem implements Serializable {

	@SerializedName("category_name")
	private String categoryName;

	@SerializedName("id")
	private String id;

	@SerializedName("image")
	private String image;

	public CategoryItem(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}

	public String getCategoryName(){
		return categoryName;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"CategoryItem{" +
			"category_name = '" + categoryName + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}