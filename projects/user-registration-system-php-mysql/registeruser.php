<?php
	
	// this code must be at the very top of each PHP page before any HTML code
	//checking if session variable is set before page is accessed.. 
	session_start();

	if(isset($_SESSION['user_id'])) {

		// Clear thevariables
		$_SESSION = array( ); 

		// Destroy the session itself
		session_destroy( ); 

		// Destroy the cookie
		setcookie ('PHPSESSID', '', time( )-3600,'/', '', 0, 0); 

		// redirect to the login page
		header('Location:http://localhost/register/registeruser.php');

 	}

?>


<?php

 include('includes/header.php');

?>


<div class="container">


	<h5>Registration Form</h5>

	<br />

	<form action="registeruserx.php" method="post">
	  <div class="form-group row">
	    <label for="inputEmail3" class="col-sm-2 col-form-label">First Name</label>
	    <div class="col-sm-4">
	      <input type="text" name="first_name" class="form-control" id="inputEmail3" placeholder="First Name">
	    </div>
	  </div>

	  <div class="form-group row">
	    <label for="inputPassword3" class="col-sm-2 col-form-label">Last Name</label>
	    <div class="col-sm-4">
	      <input type="text" name="last_name" class="form-control" id="inputPassword3" placeholder="Last Name">
	    </div>
	  </div>


	    <div class="form-group row">
	    <label for="inputEmail3" class="col-sm-2 col-form-label">Email Address</label>
	    <div class="col-sm-4">
	      <input type="email" name="email_address" class="form-control" id="inputEmail3" placeholder="Email Address">
	    </div>
	  </div>

	  <div class="form-group row">
	    <label for="inputPassword3" class="col-sm-2 col-form-label">Password</label>
	    <div class="col-sm-4">
	      <input type="password" name="password" class="form-control" id="inputPassword3" placeholder="Password">
	    </div>
	  </div>

	  <div class="form-group row">
	    <label for="inputPassword3" class="col-sm-2 col-form-label">Confirm Password</label>
	    <div class="col-sm-4">
	      <input type="password" name="password2" class="form-control" id="inputPassword3" placeholder="Password">
	    </div>
	  </div>


	  <div class="form-group row">
	    <div class="col-sm-10">
	      <button type="submit" class="btn btn-primary">Register</button>
	    </div>
	  </div>

	</form>

</div>

</body>

</html>
