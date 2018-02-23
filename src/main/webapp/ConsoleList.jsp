<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.io.*,java.util.*,es.Rafa.model.Console"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>List of consoles</title>
	</head>
	<body>
	<%
		List<Console> listConsole = (List<Console>) request.getAttribute("listAllConsole");
		pageContext.setAttribute("console", listConsole);
	%>
	
		<form action="dataConsoles" method="post">
			<table border="1">
				<thead>
					<tr>
						<td>Name: </td>
						<td>Company: </td>
					</tr>
				</thead>
				<c:forEach items="${listConsole}" var="console">
					<tr>
						<td><c:out value="${console.name}" /></td>
						<td><c:out value="${console.codCompany}" /></td>
						<td><a href="/deleteConsole?name=${console.name}">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
			<input type="submit" value="Cargar tabla">
		</form>
	</body>
</html>