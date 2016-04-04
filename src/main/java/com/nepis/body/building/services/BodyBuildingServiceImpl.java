package com.nepis.body.building.services;

import java.io.InputStream;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nepis.body.building.controller.Impl.LoginControllerImpl;
import com.nepis.body.building.controller.Impl.TrainerControllerImpl;
import com.nepis.body.building.controller.Impl.WorkOutControllerImpl;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Service
@Path("/OnlinebodyBuilding")
@Api(value = "OnlinebodyBuilding", description = "BodyBuilding Services")
public class BodyBuildingServiceImpl {
	
	private static final Logger LOGGER = Logger.getLogger(BodyBuildingServiceImpl.class);
	
	@Autowired
    @Qualifier("loginControllerImpl")
    private LoginControllerImpl loginControllerImpl;
	
	@Autowired
    @Qualifier("trainerControllerImpl")
    private TrainerControllerImpl trainerControllerImpl;
	
	@Autowired
    @Qualifier("workOutControllerImpl")
    private WorkOutControllerImpl workOutControllerImpl;
	
	/*******************************************************Health and Version*************************************************************/
	
	/**
     * @return buildResponse the <code>Response</code>
     * @author Neppoliyan Thangavelu
     */
	@GET
    @Path("/health.html")
	@ApiOperation(value = "/health.html", notes = "Api to check server is Running")
    @ApiResponses(value = {
      @ApiResponse(code = 404, message = "Page not found") 
    })
    @Produces({ MediaType.TEXT_HTML })
    public Response getServiceAlive() {
    	LOGGER.info("Service is Alive : BodyBuildingWS is Up & Running!");
        String responseMessage = "BodyBuildingWS is Up & Running!";
        return Response.ok().entity(responseMessage).build();
    }
    
    /**
     * @return buildResponse the <code>Response</code>
     * @author Neppoliyan Thangavelu
     */
	 @GET
    @Path("/version.html")
    @ApiOperation(value = "/version.html", notes = "Api to check SAS version")
    @ApiResponses(value = {
      @ApiResponse(code = 404, message = "Page not found") 
    })
    @Produces({ MediaType.TEXT_HTML })
    public Response getSASRelVersion() {
        return Response.ok().entity("BodyBuildingWS build version v1.0").build();
    }
	 
	 /*******************************************************Login Functionality*************************************************************/ 
	 
	 //Login Page functionalities
    @PUT
    @Path("/signIn")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @ApiOperation(value = "signIn", notes = "signIn Functionality")
    public Response signIn(@Context HttpHeaders httpHeaders, @Context UriInfo fullPath, JSONObject requestBody) {
        return loginControllerImpl.signInData(httpHeaders, requestBody);
    }
    
    @PUT
    @Path("/signUp")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @ApiOperation(value = "signUp", notes = "signUp Functionality")
    public Response signUpData(@Context HttpHeaders httpHeaders, @Context UriInfo fullPath, JSONObject requestBody) {
        return loginControllerImpl.signUpData(httpHeaders, requestBody);
    }
    
    @PUT
    @Path("/forgetPassword")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @ApiOperation(value = "forgetPassword", notes = "forgetPassword Functionality")
    public Response forgetPassword(@Context HttpHeaders httpHeaders, @Context UriInfo fullPath, String emailId) {
        return loginControllerImpl.forgetPassword(httpHeaders, emailId);
    }
    
    /*******************************************************Workout Functionality*************************************************************/
    
    
    //Work out functionalities
    @GET
    @Path("/dailyExercise")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @ApiOperation(value = "getDailyExercise", notes = "getDailyExercise Functionality")
    public Response getDailyExercise(@Context HttpHeaders httpHeaders, @Context UriInfo fullPath, @QueryParam("uid") String uid) {
        return workOutControllerImpl.getDailyWorkout(httpHeaders, uid);
    }
    
    @GET
    @Path("/BackExercise")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @ApiOperation(value = "getBackExercise", notes = "getBackExercise Functionality")
    public Response getBackExercise(@Context HttpHeaders httpHeaders, @Context UriInfo fullPath, @QueryParam("uid") String uid) {
        return workOutControllerImpl.getBackWorkout(httpHeaders, uid);
    }
    
    @GET
    @Path("/profilePic")
    @Produces("image/png")
    public Response getprofilePic(@Context HttpHeaders httpHeaders, @Context UriInfo fullPath, @QueryParam("emailId") String emailId) {
        return loginControllerImpl.getProfilePic(httpHeaders, emailId);
    }
    
    @POST
    @Path("/profilePic")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response postprofilePic(@Context HttpHeaders httpHeaders, @Context UriInfo fullPath, @QueryParam("emailId") String emailId
    		,@FormDataParam("userfile") InputStream mediaIS, @FormDataParam("userfile") FormDataContentDisposition mediaDetail) {
        return loginControllerImpl.postProfilePic(httpHeaders, emailId, mediaIS);
    }
    
    @GET
    @Path("/Trainers")
    @Produces({ MediaType.APPLICATION_JSON })
    @ApiOperation(value = "getTrainers", notes = "getTrainers Functionality")
    public Response getTrainers(@Context HttpHeaders httpHeaders, @Context UriInfo fullPath, @QueryParam("emailId") String emailId) {
    	return trainerControllerImpl.getTrainers(httpHeaders);
    }
    
    @GET
    @Path("/Trainees")
    @Produces({ MediaType.APPLICATION_JSON })
    @ApiOperation(value = "getTrainees", notes = "getTrainees Functionality")
    public Response getTrainees(@Context HttpHeaders httpHeaders, @Context UriInfo fullPath, @QueryParam("emailId") String emailId) {
        return trainerControllerImpl.getTrainees(httpHeaders);
    }
    
    public class FileUploadForm {
        private byte[] filedata;

        public FileUploadForm() {}

        public byte[] getFileData() {
            return filedata;
        }

        @FormParam("file")
        @PartType("application/octet-stream")
        public void setFileData(final byte[] filedata) {
            this.filedata = filedata;
        }
    }
    
    @PUT
    @Path("/setAuth")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response setAuth(@Context HttpHeaders httpHeaders, @Context UriInfo fullPath, JSONObject requestBody) {
        return loginControllerImpl.setAuthTable(requestBody);
    }
}
