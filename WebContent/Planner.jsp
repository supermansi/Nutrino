<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./css/style.css">
<meta charset="ISO-8859-1">
<title>Planner</title>
</head>
<body>
	<div class="wrapper">
		<%-- 
	<h1>${planner.planID }</h1>
	<h1>${planner.user.username }</h1> --%>
		<h3>Plan for this week</h3>
		<a href="EditPlanner.jsp" id="planedit">Edit Plan</a>
		<table border="1" class="table">
			<thead class="thead-dark">
				<tr>
					<th>Day</th>
					<th>Time</th>
					<th>Recipe</th>
				</tr>
			</thead>
			<c:forEach items="${planner}" var="plan">
				<tr>
					<td><c:out value="${plan.getDay()}" /></td>
					<td><c:out value="${plan.getTime()}" /></td>
					<td><c:out value="${plan.getLabel()}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
</html>