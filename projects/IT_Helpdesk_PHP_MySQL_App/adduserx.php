
<?php
	
	// this code must be at the very top of each PHP page before any HTML: code
	//checking if session variable is set before page is accessed.. 
	session_start();

	if(!isset($_SESSION['user_id'])) {

		echo 'Accessed in error, redirecting...';
		header('Location:http://localhost/project6/login_page.php');
		exit();
	}

include('includes/header.php');

?>


<div class="container">

<?php

require_once('includes/mysqli_connect.php');

//initialize an errors array
	$errors = array();


	// Validating input:- check if fields are empty
	if(empty($_POST['first_name'])) {
		$errors[]='You forgot to enter First Name';

	} else {
		$fn = mysqli_real_escape_string($dbc, trim($_POST['first_name']));
	}


	if(empty($_POST['last_name'])) {
		$errors[]='You forgot to enter Last Name';

	} else {
		$ln= mysqli_real_escape_string($dbc, trim($_POST['last_name']));
	}
	

	if(empty($_POST['email_address'])) {
		$errors[]='You forgot to enter your email address';

	} else {
		$em = mysqli_real_escape_string($dbc, trim($_POST['email_address']));
	}
	

		if(empty($_POST['unit_id'])) {
		$errors[]='You forgot to enter the Unit Name';
	} else {
		$un = mysqli_real_escape_string($dbc, trim($_POST['unit_id']));
	}


	if(empty($errors)) { // if "error array" is empty

		//check that the email address provided is not already in the database
		$q = "SELECT employee_id from employee where email = '$em'";
		$r = @mysqli_query($dbc, $q);
		$num = @mysqli_num_rows($r);


		if ($num == 0) { 
			
			//Create and run the INSERT query
			$q2 = "INSERT INTO employee (first_name, last_name, email, unit_id) VALUES ('$fn', '$ln', '$em', '$un')" ;
			$r2 = @mysqli_query($dbc, $q2);

			//checkign if the query executing successfully, that is if $r2 returns a boolean value of TRUE
			if ($r2) {
				echo '<p>Information was successfully submitted</p>';
			} else {
				echo '</p>The record was not added due to a system error</p>';

				//debugging message
				echo '<p>'. mysqli_error($dbc) . '. Query: ' . $q2 . '</p>';	//to look into further to dicipher
			}

			mysqli_close($dbc); //close the connection
			
		} else { //if record does not exist
			echo '<p>An error has occured.  The email address already exist in the database. </p>';
		}

	} else { //if "error array" is not empty
		echo '<h1> Error! </h1>
		<p>The following error occured: <br />';
		foreach ($errors as $msg) {
			echo "$msg <br />\n";
		}
		echo 'Please try again';
		mysqli_close($dbc); //close the connection
	}
	
?>


</div>	

<div class="footer">
		<div class="footer-text">  IT Helpdesk. All Rights Reserved.  &copy; 2018</div>
</div>


</body>

</html>