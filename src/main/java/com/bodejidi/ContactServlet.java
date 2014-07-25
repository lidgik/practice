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

import java.util.List;
import java.util.ArrayList;

public class ContactServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws IOException, ServletException {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		if((request.getParameter("id") == null) || (request.getParameter("id") == "")) {
			response.getWriter().println("ContactList");
			List<Contact> contacts = new ArrayList();

			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (Exception e) {
				// handle the error
			}

			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
				statement = connection.createStatement();
				resultSet = statement.executeQuery("select * from contact");

				while(resultSet.next()) {
					Contact contact = new Contact();

					contact.setName(resultSet.getString("name"));
					contact.setMobile(resultSet.getString("mobile"));
					contact.setVpmn(resultSet.getString("vpmn"));
					contact.setEmail(resultSet.getString("email"));
					contact.setHomeAddress(resultSet.getString("home_address"));
					contact.setOfficeAddress(resultSet.getString("office_address"));

					contacts.add(contact);
				}
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

			for(Contact contact: contacts) {
				response.getWriter().println("Following is the information of:" + contact.getName());
				response.getWriter().println("Name:" + contact.getName());
				response.getWriter().println("Mobile:" + contact.getMobile());
				response.getWriter().println("Vpmn:" + contact.getVpmn());
				response.getWriter().println("Email:" + contact.getEmail());
				response.getWriter().println("HomeAddress:" + contact.getHomeAddress());
				response.getWriter().println("OfficeAddress:" + contact.getOfficeAddress());
				response.getWriter().println();
			}
		}
		else {
			response.getWriter().println("ContactShowById");
			Contact contact = new Contact();

			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (Exception e) {
				// handle the error
			}

			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
				statement = connection.createStatement();
				resultSet = statement.executeQuery("select * from contact where id=" + request.getParameter("id"));

				if(resultSet.next()) {
					contact.setId(resultSet.getLong("id"));
					contact.setName(resultSet.getString("name"));
					contact.setMobile(resultSet.getString("mobile"));
					contact.setVpmn(resultSet.getString("vpmn"));
					contact.setEmail(resultSet.getString("email"));
					contact.setHomeAddress(resultSet.getString("home_address"));
					contact.setOfficeAddress(resultSet.getString("office_address"));
					contact.setMemo(resultSet.getString("memo"));
					contact.setJob(resultSet.getString("job"));
					contact.setJobLevel(resultSet.getLong("job_level"));
				}
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
			
			request.setAttribute("contact", contact);

			getServletContext()
				.getRequestDispatcher("/WEB-INF/jsp/contact/show.jsp")
				.forward(request, response);
		}
	}
}

