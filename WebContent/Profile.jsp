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
<title>Profile</title>
</head>
<body>


	<div class="wrapper">

		<div class="profile-card js-profile-card">
			<div class="profile-card__img">
				<img
					src="man.svg"
					alt="profile card">
			</div>

			<div class="profile-card__cnt js-profile-cnt">
				<div class="profile-card__name">${user.username}</div>
				<div class="profile-card__txt">
					<strong>${user.diet}</strong>
				</div>

				<div class="profile-card-inf">
					<div class="profile-card-inf__item">
						<div class="profile-card-inf__title">${user.height }</div>
						<div class="profile-card-inf__txt">Height</div>
					</div>

					<div class="profile-card-inf__item">
						<div class="profile-card-inf__title">${user.weight }</div>
						<div class="profile-card-inf__txt">Weight</div>
					</div>

					<div class="profile-card-inf__item">
						<div class="profile-card-inf__title">${bmi}</div>
						<div class="profile-card-inf__txt">BMI</div>
					</div>
				</div>

				<div class="profile-card-ctr">
					<a href="listrecipes?diet=${user.diet}">Show recipes for me</a> <br>

					<a href="ProfileUpdate.jsp">Update Profile</a> <br> <a
						href="planner?username=${user.username}">Show my plan</a> <br>

					<a href="calories?username=${user.username}">Daily Average
						Calorie Count</a>
					<c:if test="${not empty calories}"> = ${calories}</c:if>
					<br> <a href="analysis">Show analysis of meals</a>

					<form class="form-inline my-2 my-lg-0" action="recipeSearch"
						method="post">
						<input class="form-control mr-sm-2" name="recipeName"
							type="search" placeholder="Search" aria-label="Search"
							id="recipeName">
						<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
					</form>
				</div>
			</div>

		</div>
		<a href="HomePage.jsp" id="pglogout">Log Out</a>

	</div>

<%-- 
	<a href="HomePage.jsp">Log Out</a>

	<h1>Hi ${user.username}</h1>

	<h3>Height: ${user.height }</h3>

	<h3>Weight: ${user.weight }</h3>

	<h3>Diet: ${user.diet}</h3>

	<h3>BMI: ${bmi}</h3>

	<a href="listrecipes?diet=${user.diet}">Show recipes for me</a>
	<br>

	<a href="ProfileUpdate.jsp">Update Profile</a>
	<br>

	<a href="planner?username=${user.username}">Show my plan</a>
	<br>

	<a href="calories?username=${user.username}">Daily Average Calorie
		Count</a>
	<c:if test="${not empty calories}"> = ${calories}</c:if>
	<br>

	<a href="analysis">Show analysis of meals</a>

	<form class="form-inline my-2 my-lg-0" action="recipeSearch"
		method="post">
		<input class="form-control mr-sm-2" name="recipeName" type="search"
			placeholder="Search" aria-label="Search" id="recipeName">
		<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
	</form> --%>

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