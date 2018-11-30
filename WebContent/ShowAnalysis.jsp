<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>Weekly intake of nutrients: </h3>
	<table border="1">
		<tr>
			<th>Label</th>
			<th>Quantity</th>
			<th>Unit</th>
		</tr>
		<c:forEach items="${analysis}" var="a" >
                <tr>
                    <td><c:out value="${a.getLabel()}" /></td>
                    <td><c:out value="${a.getQuantity()}" /></td>
                    <td><c:out value="${a.getUnit()}" /></td>
                </tr>
            </c:forEach>
	</table>
</body>
</html>