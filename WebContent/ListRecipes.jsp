<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show Recipe</title>
</head>
<body>
	<h1>
		Listing Recipes
	</h1>
	
	<table border="1">
		<tr>
			<th>Recipe Name</th>
		</tr>
		<c:forEach items="${recipes}" var="recipes" >
                <tr>
                    <td><c:out value="${recipes.getLabel()}" /></td>
                </tr>
            </c:forEach>
	</table>
</body>
</html>