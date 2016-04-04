package com.nepis.body.building.types;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

public class LoginDetails implements Serializable {
	private static final long serialVersionUID = 1L;
	@SerializedName("userName")
	private String userName;
	@SerializedName("password")
	private String password;
	@SerializedName("token")
	private String token;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
