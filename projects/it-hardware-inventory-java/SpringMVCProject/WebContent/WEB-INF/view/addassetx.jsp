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

	<p>${message}</p>

	Make: ${make}  <br />
	Model: ${model} <br />
	Asset Type: ${assetType}  <br />

	<br></br>
	<br />

	<a href="${pageContext.request.contextPath}"><button class="btn btn-primary" type="button" id="home">Back to Home</button></a>

</div>

</body>
</html>
