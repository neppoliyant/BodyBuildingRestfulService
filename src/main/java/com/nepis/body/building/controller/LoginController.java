package com.nepis.body.building.controller;

import java.io.InputStream;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONObject;

public interface LoginController {
	public Response setAuthTable(JSONObject loginDetails);
	
	public Response signUpData(HttpHeaders httpHeaders, JSONObject loginDetails);
	
	public Response signInData(HttpHeaders httpHeaders, JSONObject loginDetails);
	
	public Response getProfilePic(HttpHeaders httpHeaders, String emailId);
	
	public Response postProfilePic(HttpHeaders httpHeaders, String emailId, InputStream payload);
	
	public Response forgetPassword(HttpHeaders httpHeaders, String emailId);
}
