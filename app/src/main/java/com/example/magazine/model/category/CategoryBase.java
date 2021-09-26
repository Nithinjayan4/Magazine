package com.example.magazine.model.category;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CategoryBase{

	@SerializedName("data")
	private List<CategoryItem> data;

	@SerializedName("success")
	private boolean success;

	public void setData(List<CategoryItem> data){
		this.data = data;
	}

	public List<CategoryItem> getData(){
		return data;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	@Override
 	public String toString(){
		return 
			"CategoryBase{" + 
			"data = '" + data + '\'' + 
			",success = '" + success + '\'' + 
			"}";
		}
}