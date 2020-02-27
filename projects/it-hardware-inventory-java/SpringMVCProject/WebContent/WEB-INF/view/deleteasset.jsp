<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


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

<h4>Delete an Asset</h4>

<p>Are you sure you want to delete an asset record with the following details? </p>

<form action="deleteassetx" method="POST">

Asset ID: <input type="text" name="assetid" value="${asset.assetId}" readonly ><br></br>
Make: <input type="text" name="assetid" value="${asset.make}	" readonly ><br></br>
Model: <input type="text" name="assetid" value="${asset.model}" readonly ><br></br>
Asset type: <input type="text" name="assetid" value="${asset.assetType}" readonly ><br></br>

<br> </br>

	<div class="buttongroup">
		 <input class="btn btn-primary" type="submit" value="Submit">
		<a href="${pageContext.request.contextPath}"><button class="btn btn-primary" type="button" id="home">Back to Home</button></a>
	</div>

</form>

</div>

</body>
</html>
