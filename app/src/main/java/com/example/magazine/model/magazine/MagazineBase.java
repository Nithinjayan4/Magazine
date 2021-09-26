package com.example.magazine.model.magazine;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MagazineBase {

	@SerializedName("data")
	private List<Magazinetem> data;

	@SerializedName("success")
	private boolean success;

	public void setData(List<Magazinetem> data){
		this.data = data;
	}

	public List<Magazinetem> getData(){
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
			"MagazineBase{" +
			"data = '" + data + '\'' + 
			",success = '" + success + '\'' + 
			"}";
		}
}