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
	
	<a href="HomePage.jsp">Log Out</a>

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
	
	<a href="listrecipes?diet=${user.diet}">Show recipes for me</a> <br>
	
	<a href="ProfileUpdate.jsp">Update Profile</a>
	
	<form class="form-inline my-2 my-lg-0" action="recipeSearch" method="post">
		<input class="form-control mr-sm-2" name="recipeName" type="search" placeholder="Search"
			aria-label="Search" id="recipeName">
		<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
	</form>
	
</body>
</html>