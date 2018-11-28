<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./css/style.css">

<title>Search</title>
</head>
<body>

	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th>Name</th>
				<th>Ingredients</th>
				<th>Calories</th>
				<th>Weight</th>
				<th>Health Labels</th>
				<th>URL</th>
				<th>Image</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${recipes}" var="recipe">
				<tr>
					<td><c:out value="${recipe.getLabel()}" /></td>
					<td><c:out value="${recipe.getIngredient_lines()}" /></td>
					<td><c:out value="${recipe.getCalories()}" /></td>
					<td><c:out value="${recipe.getTotal_weight()}" /></td>
					<td><c:out value="${recipe.getHealth_labels()}" /></td>
					<td><a href="${recipe.getUrl()}" target="_blank">Link</a></td>
					<td><img src="${recipe.getImage()}"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>

<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<script src="./js/main.js"></script>

</html>