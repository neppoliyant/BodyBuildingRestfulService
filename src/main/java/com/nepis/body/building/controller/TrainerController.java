package com.nepis.body.building.controller;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

public interface TrainerController {
	public Response getTrainers(HttpHeaders httpHeaders);
	public Response getTrainees(HttpHeaders httpHeaders);
}
