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
	
<p>No Asset record found.  Search again?</p>
	
<br />

<a href="${pageContext.request.contextPath}/getasset"><button class="btn btn-primary" type="button">Yes</button></a>
&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;

<a href="${pageContext.request.contextPath}"><button class="btn btn-primary" type="button">No</button></a>


</div>

</body>
</html>