<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Users</title>
</head>
<body>
	<h1>
		Listing Users
	</h1>
	
	<form action="listusers" method="get">
		<button type="submit">Refresh</button>
	</form>
	
	<table border="1">
            <tr>
                <th>UserName</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Email</th>
                <th>Privileges</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${users}" var="user" >
                <tr>
                    <td><c:out value="${user.getUsername()}" /></td>
                    <td><c:out value="${user.getFirstName()}" /></td>
                    <td><c:out value="${user.getLastName()}" /></td>
                    <td><c:out value="${user.getEmail()}" /></td>
                    <td><c:out value="${user.getPrivileges()}" /></td>
                     <td>
                     	<form action="userdelete" method="post">
                     	<button type="submit" id="username" name="username" value="${user.getUsername()}">Delete</button>
                     	</form>
                     </td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>