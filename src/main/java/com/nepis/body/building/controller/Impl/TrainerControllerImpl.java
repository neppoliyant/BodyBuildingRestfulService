package com.nepis.body.building.controller.Impl;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.nepis.body.building.connector.Impl.DBConnectorImpl;
import com.nepis.body.building.controller.TrainerController;
import com.nepis.body.building.util.Utils;

@Component("trainerControllerImpl")
public class TrainerControllerImpl implements TrainerController {
	private static final Logger LOGGER = Logger.getLogger(TrainerControllerImpl.class);
	
	@Autowired
    @Qualifier("dBConnectorImpl")
	private DBConnectorImpl dBConnectorImpl;
	
	@Autowired
    @Qualifier("utils")
	private Utils utils;
	
	@Override
	public Response getTrainers(HttpHeaders httpHeaders) {
		LOGGER.debug("MethodEnter::getTrainers");
		JSONObject failureObj = new JSONObject();
		try {
			return Response.ok().entity(dBConnectorImpl.getTrainers()).build();
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e);
			LOGGER.debug("MethodExit::getTrainers");
			return Response.status(500).entity(failureObj).build();
		}
	}
	
	@Override
	public Response getTrainees(HttpHeaders httpHeaders) {
		LOGGER.debug("MethodEnter::getTrainers");
		JSONObject failureObj = new JSONObject();
		try {
			return Response.ok().entity(dBConnectorImpl.getTrainees()).build();
		} catch (Exception e) {
			LOGGER.error("Error Occured : " + e);
			LOGGER.debug("MethodExit::getTrainers");
			return Response.status(500).entity(failureObj).build();
		}
	}
}
