package com.nepis.body.building.controller.Impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.nepis.body.building.connector.Impl.SQLConnectorImpl;
import com.nepis.body.building.controller.LoginController;
import com.nepis.body.building.types.LoginDetails;
import com.nepis.body.building.util.Utils;

import org.codehaus.jettison.json.JSONObject;

@Component("loginControllerImpl")
public class LoginControllerImpl implements LoginController {
	private static final Logger LOGGER = Logger.getLogger(LoginControllerImpl.class);
	
	@Autowired
    @Qualifier("sQLConnectorImpl")
	private SQLConnectorImpl sQLConnectorImpl;
	
	@Autowired
    @Qualifier("utils")
	private Utils utils;
	
	@Override
	public Response setAuthTable(JSONObject requestBody) {
		LOGGER.debug("MethodEnter::setAuthTable");
		JSONObject failureObj = new JSONObject();
		try {
			Gson gson = new Gson();
	    	LoginDetails loginDetails = gson.fromJson(requestBody.toString(), LoginDetails.class);
			failureObj = utils.buildOutputObject(1001, "SQL Exception"); 
			sQLConnectorImpl.setAuthTable(loginDetails);
			LOGGER.debug("MethodExit::setAuthTable");
			return Response.ok().entity(utils.buildOutputObject(1000, "Success")).build();
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e);
			LOGGER.debug("MethodExit::setAuthTable");
			return Response.status(500).entity(failureObj).build();
		}
	}
	
	@Override
	public Response signUpData(HttpHeaders httpHeaders, JSONObject loginDetails) {
		LOGGER.debug("MethodEnter::setAuthTable");
		JSONObject failureObj = new JSONObject();
		try {
			sQLConnectorImpl.signUpData(loginDetails);
			return Response.ok().entity(utils.buildOutputObject(1000, "Success")).build();
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e);
			LOGGER.debug("MethodExit::setAuthTable");
			return Response.status(500).entity(failureObj).build();
		}
	}
	
	public Response forgetPassword(HttpHeaders httpHeaders, String emailId) {
		LOGGER.debug("MethodEnter::forgetPassword");
		JSONObject failureObj = new JSONObject();
		try {
			//Logic to write one time password
			return Response.ok().entity(utils.buildOutputObject(1000, "Success")).build();
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e);
			LOGGER.debug("MethodExit::forgetPassword");
			return Response.status(500).entity(failureObj).build();
		}
	}
	
	@Override
	public Response signInData(HttpHeaders httpHeaders, JSONObject loginDetails) {
		LOGGER.debug("MethodEnter::signInData");
		JSONObject failureObj = new JSONObject();
		try {
			failureObj = utils.buildOutputObject(1001, "SQL Exception"); 
			JSONObject resp = sQLConnectorImpl.validateAuth(loginDetails);
			return Response.ok().entity(resp).build();
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e);
			LOGGER.debug("MethodExit::signInData");
			return Response.status(500).entity(failureObj).build();
		}
	}
	
	@Override
	public Response getProfilePic(HttpHeaders httpHeaders, String emailId) {
		JSONObject failureObj = new JSONObject();
		try {
			String filename = emailId + ".png";
			File file = new File("/Users/nthang003c/opt/props/bodyBuilding/" + filename);
			 
			javax.ws.rs.core.Response.ResponseBuilder response = Response.ok((Object) file);
			response.header("Content-Disposition",
				"attachment; filename=" + filename);
			return response.build();
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e);
			LOGGER.debug("MethodExit::setAuthTable");
			return Response.status(500).entity(failureObj).build();
		}
	}
	
	@Override
	public Response postProfilePic(HttpHeaders httpHeaders, String emailId, InputStream is) {
		JSONObject failureObj = new JSONObject();
		try {
			//InputStream is = new ByteArrayInputStream(payload);
			BufferedImage img = ImageIO.read(is);
			File outputfile = new File("/Users/nthang003c/opt/props/bodyBuilding/"+emailId+".png");
		    ImageIO.write(img, "png", outputfile);
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e);
			LOGGER.debug("MethodExit::setAuthTable");
			return Response.status(500).entity(failureObj).build();
		}
	}
}
