<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	<h4>Search for an Asset</h4>

	<form action="getassetx" method="GET">

		Asset ID: <input type="text" name="assetid"><br></br>
		<div class="buttongroup">
			<input class="btn btn-primary" type="submit" value="Submit">
			<a href="${pageContext.request.contextPath}"><button class="btn btn-primary" type="button" id="home">Back to Home</button></a>
		</div>
	</form>
</div>

</body>
</html>
