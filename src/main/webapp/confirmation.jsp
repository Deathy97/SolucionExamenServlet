<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete confirmation</title>
</head>
<body>

	<c:choose>
		<c:when test="${name != null}">
		Are you sure to delete this console? 
		<form action="deleteConsole" method="post">
				<input type="text" value="${name}"/>
				 <input type="submit" value="Yes"/>
			</form>
		</c:when>
		<c:otherwise>
		Are you sure to delete this videogame? 
      <form action="deleteVideoGame" method="post">
				<input type="text" value="${title}"/> 
				<input type="submit" value="Yes"/>
			</form>
		</c:otherwise>
	</c:choose>


</body>
</html>


