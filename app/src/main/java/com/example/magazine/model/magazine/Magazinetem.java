package com.example.magazine.model.magazine;


import com.google.gson.annotations.SerializedName;

public class Magazinetem {

	@SerializedName("category_name")
	private String categoryName;

	@SerializedName("image_path")
	private String imagePath;

	@SerializedName("id")
	private String id;

	@SerializedName("audio")
	private String audio;

	@SerializedName("status")
	private String status;

	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}

	public String getCategoryName(){
		return categoryName;
	}

	public void setImagePath(String imagePath){
		this.imagePath = imagePath;
	}

	public String getImagePath(){
		return imagePath;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setAudio(String audio){
		this.audio = audio;
	}

	public String getAudio(){
		return audio;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Magazinetem{" +
			"category_name = '" + categoryName + '\'' + 
			",image_path = '" + imagePath + '\'' + 
			",id = '" + id + '\'' + 
			",audio = '" + audio + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}