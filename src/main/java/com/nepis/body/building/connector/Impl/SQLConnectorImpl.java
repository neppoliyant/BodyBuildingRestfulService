package com.nepis.body.building.connector.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.nepis.body.building.config.envConfig;
import com.nepis.body.building.types.LoginDetails;

@Component("sQLConnectorImpl")
public class SQLConnectorImpl {
	private static final Logger LOGGER = Logger.getLogger(SQLConnectorImpl.class);
	
	@Autowired
	@Qualifier("envConfig")
	private envConfig envConfig;
	
	@Autowired
    @Qualifier("dataSource")
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@PostConstruct
	public void dbInit() throws Exception {
		LOGGER.debug("MethodEnter::Couchbase dbInit");
		LOGGER.debug("MethodExit::Couchbase dbInit");
	}
	
	public void setAuthTable(LoginDetails loginDetails) throws SQLException, Exception{
		LOGGER.debug("MethodEnter::setAuthTable");
		Connection conn = null;
		try {
			String sql = "INSERT INTO Login_Details " +
					"(UserName, Password) VALUES (?, ?)";
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, loginDetails.getUserName());
			ps.setString(2, loginDetails.getPassword());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		LOGGER.debug("MethodExit::setAuthTable");
	}
	
	public void signUpData(JSONObject loginDetails) throws SQLException, Exception{
		LOGGER.debug("MethodEnter::setAuthTable");
		Connection conn = null;
		try {
			
			String sql = "INSERT INTO tbl_login " +
					"(name, email, password, usertype, gender) VALUES (?, ?, ?, ?, ?)";
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, loginDetails.getString("name"));
			ps.setString(2, loginDetails.getString("email"));
			ps.setString(3, loginDetails.getString("password"));
			ps.setInt(4, loginDetails.getInt("userType"));
			ps.setInt(5, loginDetails.getInt("gender"));
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		LOGGER.debug("MethodExit::setAuthTable");
	}
	
	public JSONObject validateAuth(JSONObject loginDetails) throws SQLException, Exception{
		LOGGER.debug("MethodEnter::validateAuth");
		Connection conn = null;
		Statement stmt = null;
		try {
			String actualQuery = "select name, email, gender, usertype from tbl_login where "
					+ "email = '"+ loginDetails.getString("userName")+ "' and password = '" + loginDetails.getString("password") + "' and active = 1";
			
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(actualQuery);
			
			 while (rs.next()) { 
				loginDetails.put("name", rs.getString("name"));
				loginDetails.put("email", rs.getString("email"));
				loginDetails.put("gender", rs.getString("gender"));
				loginDetails.put("usertype", rs.getString("usertype"));
			 }
		} catch (Exception e) {
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		LOGGER.debug("MethodExit::validateAuth");
		return loginDetails;
		
	}
	
	public String CreateToken (String userName, String password) {
		return userName + "-" + password;
	}
}
