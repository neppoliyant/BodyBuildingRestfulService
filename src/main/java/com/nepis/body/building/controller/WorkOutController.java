package com.nepis.body.building.controller;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

public interface WorkOutController {
	public Response getDailyWorkout(HttpHeaders httpHeaders, String uid);
	public Response getBackWorkout(HttpHeaders httpHeaders, String uid);
}
