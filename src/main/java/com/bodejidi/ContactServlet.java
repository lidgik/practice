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
			resultSet = statement.executeQuery("select * from contact where id=1");

			resultSet.next();
			response.getWriter().println("the name of No.1 is " + resultSet.getString("name"));

			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException sqle) {
			response.getWriter().println("cannot connect to db");
			sqle.printStackTrace();
		}
		
	}
}
