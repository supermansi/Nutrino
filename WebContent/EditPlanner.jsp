<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Planner</title>
</head>
<body>
	<form action="editplan" method="post">
		UserName: <input name="username" id="username"/> <br>
		Recipe ID: <input name="recipeID" id="recipeID" value="${recipeID }"/> <br>
		Day: <select id="day" name="day">
			<option value="Monday">Monday</option>
			<option value="Tuesday">Tuesday</option>
			<option value="Wednesday">Wednesday</option>
			<option value="Thursday">Thursday</option>
			<option value="Friday">Friday</option>
			<option value="Saturday">Saturday</option>
			<option value="Sunday">Sunday</option>
		</select><br>
		Time: <select id="time" name="time">
			<option value="Breakfast">Breakfast</option>
			<option value="Lunch">Lunch</option>
			<option value="Dinner">Dinner</option>
		</select>
		<input type="submit">
	</form>
</body>
</html>