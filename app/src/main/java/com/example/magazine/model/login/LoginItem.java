package com.example.magazine.model.login;


import com.google.gson.annotations.SerializedName;

public class LoginItem {

	@SerializedName("qrcode_id")
	private String qrcodeId;

	@SerializedName("id")
	private String id;

	public void setQrcodeId(String qrcodeId){
		this.qrcodeId = qrcodeId;
	}

	public String getQrcodeId(){
		return qrcodeId;
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
			"LoginItem{" +
			"qrcode_id = '" + qrcodeId + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}