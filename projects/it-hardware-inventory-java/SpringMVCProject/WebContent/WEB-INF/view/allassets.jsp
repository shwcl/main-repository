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


<div class="container">

<h2>IT Hardware Inventory App</h2>

<h3>List of Assets</h3>

<br />

<table class="table table-striped">
<tr><th>Asset ID</th><th>Make</th><th>Model</th><th>Asset Type</th><th colspan="2">Action</th>
	<c:forEach var="asset" items="${allAssetList}">
		<tr>
			<td>${asset.assetId}</td>
			<td>${asset.make}</td>
			<td>${asset.model}</td>
			<td>${asset.assetType}</td>
		<!--  	<td><a href="editasset/${asset.assetId}">Edit</a></td>		-->
			<td><a href="editasset/${asset.assetId}"><button class="btn btn-primary">Edit</button></a></td>
			<td><a href="deleteasset/${asset.assetId}"><button type="button" class="btn btn-primary btn-danger">Delete</button></a></td>
		</tr>
	</c:forEach>
</table>

</div>

</body>
</html>
