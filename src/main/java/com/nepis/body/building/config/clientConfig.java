package com.nepis.body.building.config;

public abstract class clientConfig {
	private String appName;
	private String appContentType;
	private String couchServer;
	private String couchBucket;
	private String couchbucketPassword;
	
	protected abstract void init();
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppContentType() {
		return appContentType;
	}
	public void setAppContentType(String appContentType) {
		this.appContentType = appContentType;
	}

	public String getCouchServer() {
		return couchServer;
	}

	public void setCouchServer(String couchServer) {
		this.couchServer = couchServer;
	}

	public String getCouchBucket() {
		return couchBucket;
	}

	public void setCouchBucket(String couchBucket) {
		this.couchBucket = couchBucket;
	}

	public String getCouchbucketPassword() {
		return couchbucketPassword;
	}

	public void setCouchbucketPassword(String couchbucketPassword) {
		this.couchbucketPassword = couchbucketPassword;
	}
}
