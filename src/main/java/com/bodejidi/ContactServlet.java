package com.bodejidi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class ContactServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws IOException, ServletException {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		response.getWriter().println("Contact");

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			// handle the error
		}

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from contact where id=" + request.getParameter("id"));

			resultSet.next();
			response.getWriter().println("the name of No." + request.getParameter("id") +" is " + resultSet.getString("name"));
		} catch (SQLException sqle) {
			response.getWriter().println("cannot connect to db");
			sqle.printStackTrace();
		}

		if(resultSet != null) {
			try {
				resultSet.close();
			} catch (Exception e) {

			}
		}

		if(statement != null) {
			try {
				statement.close();
			} catch (Exception e) {

			}
		}
		
		if(connection != null) {
			try {
				connection.close();
			} catch (Exception e) {

			}
		}
	}
}
