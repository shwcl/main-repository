<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/mystyle.css">
	<title>IT Hardware Inventory App</title>
</head>

<body>
<div class="header">
	<h2>IT Hardware Inventory App</h2>
</div>
<div class="container">

	<h4> Add an Asset</h4>
	<br />

	<form class="form" action="addassetx" method="post"> 

	Make: <input type="text" name="make" /> <br></br>
	Model: <input type="text" name="model" /> <br></br>
	Type: <select name="assettype">
		<c:forEach var="assetType" items="${assetTypeList}">
			<option value="${assetType.assetTypeId}"> ${assetType.assetTypeName} </option>
		</c:forEach>
	       </select>

	<br></br>
	<br />

	<div class="buttongroup">
		<input class="btn btn-primary" type="submit" value="Submit">
		<a href="${pageContext.request.contextPath}"><button class="btn btn-primary" type="button" id="home">Back to Home</button></a>
	</div>
	</form>
</div>
</body>
</html>
