<%@ page import="java.util.List, com.bodejidi.Contact" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% List<Contact> contactList = (List<Contact>) request.getAttribute("contactList"); %>
<html>
  <head>
	<title>Contact List</title>
  </head>
  <body>
	<h1>All Contacts</h1>
	<table>
	  <tr>
		<td>Name</td>
		<td>Phone</td>
	  </tr>

	  <c:forEach var="contact" items="${contactList}">
	  <tr>
		<td>${contact.name}</td>
		<td>${contact.mobile} ${contact.vpmn}</td>
	  </tr>
	  </c:forEach>

	</table>
  </body>
</html>
