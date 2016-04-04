package com.nepis.body.building.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("utils")
public class Utils {
	private static final Logger LOGGER = Logger.getLogger(Utils.class);
	
	public static boolean isNullOREmpty(String pStr) {
		LOGGER.debug("Method::isNullOREmpty");
		if (pStr == null || pStr.trim().length() == 0) {
			return true;
		}
		return false;
	}
	
	public List<URI> loadUriList(String fullURIs) {
		LOGGER.debug("MethodEnter::loadUriList");
		ArrayList<URI> uriList = new ArrayList<URI>();

		if (isNullOREmpty(fullURIs)) {
			LOGGER.error("URI list is Empty!!");
		} else {
			String[] uriStrings = fullURIs.split("\\|");
			for (int i = 0; i < uriStrings.length; i++) {
				try {
					String uri = uriStrings[i].trim();
					if (uri.length() > 0) {
						uriList.add(new URI(uri));
					}
				} catch (URISyntaxException e) {
					LOGGER.error("Exception : " + e.getMessage());
				}
			}
		}
		LOGGER.debug("MethodExit::loadUriList");
		return uriList;
	}
	
	public JSONObject buildOutputObject(int StatusCode, String message) throws JSONException {
		JSONObject obj = new JSONObject();
		JSONObject subObj = new JSONObject();
		subObj.put("statusCode", StatusCode);
		subObj.put("message", message);
		obj.put("responseHeader", subObj);
		return obj;
	}
}
