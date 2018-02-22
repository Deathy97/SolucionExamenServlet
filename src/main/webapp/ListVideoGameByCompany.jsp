<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.io.*,java.util.*,es.Rafa.model.*"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Video Games By Company </title>
</head>
<body>
	<form action="listCompanyGame" method="post">
	  <select name="company" > 
	         <c:forEach var="list" items="${listAllCompanyGame}">
		  		<option value="${list.id}">${list.name}</option>
	         </c:forEach>
	 </select>
	<input type="submit" value="Companies"/>
	</form>
	<form action="listByVideoGame" method="post">
	<input type="submit" value="List"/>
		<table border="1">
			<thead>
				<tr>
					<td>Title: </td>
					<td>PEGI: </td>
					<td>Release Date: </td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="videogame" items="${listAllGameByCompany}">
					<tr>
						<td><c:out value="${videogame.title}"/></td>
						<td><c:out value="${videogame.pegie}"/> </td>
						<td><c:out value="${videogame.releaseDate}"/></td>
			    	</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
</body>
</html>