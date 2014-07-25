package com.bodejidi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ContactServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws IOException, ServletException {
		
		Connection connection = null;
		
		response.getWriter().println("Contact");

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			// handle the error
		}

		try {
			response.getWriter().println("try");

			connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
		
			response.getWriter().println("connection");
			response.getWriter().println(connection);
			connection.close();
		} catch (SQLException sqle) {
			response.getWriter().println("cannot connect to db");
			sqle.printStackTrace();
		}
		
	}
}
