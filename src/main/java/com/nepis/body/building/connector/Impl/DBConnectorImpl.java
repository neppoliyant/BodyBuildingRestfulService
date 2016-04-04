package com.nepis.body.building.connector.Impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.couchbase.client.CouchbaseClient;
import com.couchbase.client.protocol.views.DesignDocument;
import com.couchbase.client.protocol.views.InvalidViewException;
import com.couchbase.client.protocol.views.ViewDesign;
import com.nepis.body.building.config.envConfig;
import com.nepis.body.building.util.Utils;
import com.couchbase.client.protocol.views.View;
import com.couchbase.client.protocol.views.ViewResponse;
import com.couchbase.client.protocol.views.ViewRow;
import com.couchbase.client.protocol.views.Query;
import com.couchbase.client.protocol.views.Stale;

@Component("dBConnectorImpl")
public class DBConnectorImpl {
	private static final Logger LOGGER = Logger.getLogger(DBConnectorImpl.class);
	
	private static final String DBDOCTYPE = "docType";

	@Autowired
	@Qualifier("envConfig")
	private envConfig envConfig;

	@Autowired
	@Qualifier("utils")
	private Utils utils;

	private CouchbaseClient client;

	private String trainersDesignDoc;
	private String traineesDesignDoc;

	private List<URI> hosts;

	@PostConstruct
	public void dbInit() throws Exception {
		LOGGER.debug("MethodEnter::Couchbase dbInit");
		hosts = utils.loadUriList(envConfig.getCouchServer());
		client = new CouchbaseClient(hosts, envConfig.getCouchBucket(), envConfig.getCouchbucketPassword());
		trainersDesignDoc = "trainers";
		traineesDesignDoc = "trainees";
		checkWebRTCUserDesignDocAndViews();
		LOGGER.debug("dbInit:" + "Connected to DB successfully, uri - " + hosts + ", Bucket Name - " + envConfig.getCouchBucket());
		LOGGER.debug("MethodExit::Couchbase dbInit");
	}

	/**
	 * Method to check id Design Doc is Exists
	 * @param designDocumentName
	 * @return
	 */
	private boolean isDesignDocExists(String designDocumentName) {

		try {
			client.getDesignDoc(designDocumentName);
			return true;
		} catch (InvalidViewException e) {
			return false;
		}
	}

	/**
	 * Method to check if view exists.
	 * @throws Exception
	 */
	private void checkWebRTCUserDesignDocAndViews() throws Exception {

		DesignDocument designDoc = null;
		List<ViewDesign> viewDesignList = null;
		List<String> viewNamesList = new ArrayList<String>();

		if (!isDesignDocExists(trainersDesignDoc)) {
			throw new Exception("Trainers Design Doc Not Exists.");
		}

		designDoc = client.getDesignDoc(trainersDesignDoc);

		viewDesignList = designDoc.getViews();

		for (ViewDesign view : viewDesignList) {
			String existingViewName = view.getName();
			viewNamesList.add(existingViewName);
		}
		
		if (!isDesignDocExists(traineesDesignDoc)) {
			throw new Exception("Trainees Design Doc Not Exists.");
		}

		designDoc = client.getDesignDoc(traineesDesignDoc);

		viewDesignList = designDoc.getViews();

		for (ViewDesign view : viewDesignList) {
			String existingViewName = view.getName();
			viewNamesList.add(existingViewName);
		}

		if (!viewNamesList.contains("trainees")) {
			throw new Exception("Trainees View Not Exists.");
		}
	}
	
	public JSONArray getTrainees() throws Exception {
		JSONArray resp = new JSONArray();
		try {

			String designDoc = traineesDesignDoc;
			String viewName = "trainees";
			
			View view = client.getView(designDoc, viewName);

			Query query = new Query();
			query.setIncludeDocs(true);
			query.setReduce(false);
			query.setLimit(1);
			query.setStale(Stale.OK);
			LOGGER.debug("Before CouchBase view query");
			ViewResponse response = client.query(view, query);
			LOGGER.debug("After CouchBase view query");
			for (ViewRow row : response) {
				JSONObject result = new JSONObject(row.getDocument().toString());

				if (result.has(DBDOCTYPE)) {
					result.remove(DBDOCTYPE);
				}

				resp.put(result);
			}

		} catch (Exception e) {
			throw e;
		}
		return resp;
	}
	
	public JSONArray getTrainers() throws Exception {
		JSONArray resp = new JSONArray();
		try {

			String designDoc = trainersDesignDoc;
			String viewName = "trainers";
			
			View view = client.getView(designDoc, viewName);

			Query query = new Query();
			query.setIncludeDocs(true);
			query.setReduce(false);
			query.setLimit(1);
			query.setStale(Stale.OK);
			LOGGER.debug("Before CouchBase view query");
			ViewResponse response = client.query(view, query);
			LOGGER.debug("After CouchBase view query");
			for (ViewRow row : response) {
				JSONObject result = new JSONObject(row.getDocument().toString());

				if (result.has(DBDOCTYPE)) {
					result.remove(DBDOCTYPE);
				}

				resp.put(result);
			}

		} catch (Exception e) {
			throw e;
		}
		return resp;
	}

	public Object getUser(String userId) throws Exception {
		LOGGER.debug("MethodEnter::Couchbase getUser");
		Object respone = new Object();
		if (client == null) {
			dbInit();
		}
		respone = client.get(userId);
		LOGGER.debug("MethodExit::Couchbase getUser");
		return respone;
	}
}
