
<!DOCTYPE html>

<html lang="en">
  <head>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="css/style.css" type="text/css">
	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
</head>



<body>

<div class="jumbotron jumbotron-fluid">
  <div class="container">
    <h1 class="display-4">User Registration System</h1>
    <p class="lead">Get registered now!
    </p>
  </div>
</div>



<div class="navbar-container">

	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	  <a class="navbar-brand" href="#"></a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
	    <div class="navbar-nav">
	      <a class="nav-item nav-link active" href="index.php">Home <span class="sr-only">(current)</span></a>
	      <a class="nav-item nav-link active" href="registeruser.php">Register</a>
	      <a class="nav-item nav-link active" href="viewusers.php">Registered Users</a>
	      <a class="nav-item nav-link active" href="<?php if(isset($_SESSION['user_id'])) echo 'logout.php'; else echo 'login.php';  ?>"> <?php if(isset($_SESSION['user_id'])) echo 'Logout'; else echo 'Log in';  ?></a>

	    </div>
	  </div>
	</nav>

</div>
