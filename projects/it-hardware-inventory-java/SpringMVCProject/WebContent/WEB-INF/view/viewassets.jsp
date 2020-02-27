<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    
<%@ page import="com.zinnia.springmvc.Asset" %>
<%@ page import="java.util.List" %>
<%@ page import=" org.springframework.ui.Model" %>

    
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
	<h4>List of Assets</h4>
	<table class="table table-striped">
	<tr><th>Asset ID</th><th>Make</th><th>Model</th><th>Asset Type</th><th colspan="2">Action</th>
		<c:forEach var="asset" items="${allAssetsByPageList}">
			<tr>
				<td>${asset.assetId}</td>
				<td>${asset.make}</td>
				<td>${asset.model}</td>
				<td>${asset.assetType}</td>
			<!--  	<td><a href="editasset/${asset.assetId}">Edit</a></td>		-->
				<td><a href="${pageContext.request.contextPath}/editasset/${asset.assetId}"><button class="btn btn-primary">Edit</button></a></td>
				<td><a href="${pageContext.request.contextPath}/deleteasset/${asset.assetId}"><button type="button" class="btn btn-primary btn-danger">Delete</button></a></td>
			</tr>
		</c:forEach>
	</table>

	<p></p>

	<!--<c:forEach var="pagenumber" items="${pageNumbersList}">
		<a href="getallassets2/${pagenumber}">${pagenumber}</a>

	</c:forEach>  	

	-->


	<c:forEach var="item" items="${pageNumbersList}">
		<a href="${pageContext.request.contextPath}/viewassets/${item}">${item}</a>
		&nbsp; &nbsp;

	</c:forEach>  

	<br> </br>

	<c:forEach var="item" items="${prevNextList}">
		${item}
		&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;

	</c:forEach>  

	<br> </br>
	<p><a href="${pageContext.request.contextPath}"><button class="btn btn-primary">Back to Home</button></a></p>

	<br />

	<div class="footer">
	<p>IT Hardware Inventory App | www.zinniatech.io | All Rights Reserved</p>
	</div>

</div>

</body>
</html>
