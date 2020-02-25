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

<h4> Asset info</h4>

<a href="getallassets"><button class="btn btn-primary">Show Assets</button></a>
<a href="addasset"><button class="btn btn-primary">Add an Asset</button></a>
<a href="viewassets/1"><button class="btn btn-primary">All Assets by Page </button></a>
<a href="getasset"><button class="btn btn-primary">Search by Asset ID </button></a>



<br></br>
<br />
<h4>Asset Allocation</h4>

<a href="#"><button class="btn btn-primary">Show Asset Allocations</button></a>
<a href="#"><button class="btn btn-primary">Add an Asset Allocation</button></a>
<a href="#"><button class="btn btn-primary">Search for an Asset Allocation record</button></a>


<br></br>
<br />
<h4>Asset repairs</h4>

<a href="#"><button class="btn btn-primary">Show Asset Repairs</button></a>
<a href="#"><button class="btn btn-primary">Add an Asset Repair </button></a>
<a href="#"><button class="btn btn-primary">Search for an Asset Repair record</button></a>



</div>

</body>
</html>