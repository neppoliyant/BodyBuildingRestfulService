package com.nepis.body.building.config;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("envConfig")
public class envConfig extends clientConfig {
	
	private static Logger LOGGER = Logger.getLogger(envConfig.class);
	
	@Autowired
	@Qualifier("envConfigProps")
	private Properties envConfigProps;
	
	@Override
	@PostConstruct
	protected void init() {
		LOGGER.info("Setting Config : Started");
		
		setAppName(envConfigProps.getProperty("app.server.id"));
		
		setAppContentType(envConfigProps.getProperty("app.content.type"));
		
		setCouchServer(envConfigProps.getProperty("BodyBuildingWS.couchBase.server"));
		
		setCouchBucket(envConfigProps.getProperty("BodyBuildingWS.couchBase.bucketName"));
		
		setCouchbucketPassword(envConfigProps.getProperty("BodyBuildingWS.couchBase.bucketPassword"));
		
		LOGGER.info("Setting Config : Completed");
	}
}
