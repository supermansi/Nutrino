<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Planner</title>
</head>
<body><%-- 
	<h1>${planner.planID }</h1>
	<h1>${planner.user.username }</h1> --%>
	<h1>Plan for this week</h1>
	<a href="EditPlanner.jsp">Edit Plan</a>
	<table border="1">
            <tr>
                <th>Day</th>
                <th>Time</th>
                <th>Recipe</th>
            </tr>
            <c:forEach items="${planner}" var="plan" >
                <tr>
                	<td><c:out value="${plan.getDay()}" /></td>
                    <td><c:out value="${plan.getTime()}" /></td>
                    <td><c:out value="${plan.getLabel()}" /></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>