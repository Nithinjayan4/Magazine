package com.example.magazine.model.login;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class LoginBase{

	@SerializedName("data")
	private List<LoginItem> data;

	@SerializedName("success")
	private boolean success;

	public void setData(List<LoginItem> data){
		this.data = data;
	}

	public List<LoginItem> getData(){
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
			"LoginBase{" + 
			"data = '" + data + '\'' + 
			",success = '" + success + '\'' + 
			"}";
		}
}