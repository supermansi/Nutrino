<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile</title>
</head>
<body>
	<h1>
		Hi ${user.username}
	</h1>
		
	<h3>
		Height: ${user.height }
	</h3>
	
	<h3>
		Weight: ${user.weight }
	</h3>
	
	<h3>
		Diet: ${user.diet}
	</h3>
	
	<a href="listrecipes?diet=${user.diet}">Show recipes for me</a>
	
	<a href="ProfileUpdate.jsp">Update Profile</a>
	
	
</body>
</html>