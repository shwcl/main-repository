
<?php
	
	// this code must be at the very top of each PHP page before any HTML code
	//checking if session variable is set before page is accessed.. 
	session_start();

	if(!isset($_SESSION['user_id'])) {

		echo 'Accessed in error, redirecting...';
		header('Location:http://localhost/register/login_page.php');
		exit();
	}
?>



<?php

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
	

	if(!empty($_POST['password'])) {
		if($_POST['password'] != $_POST['password2']) {
			$errors[]='Your password did not match the confirmed password.';
		} else {
			$pw = mysqli_real_escape_string($dbc, trim($_POST['password']));
		}
	} else {
		$errors[] = 'You forgot to enter a password';
	}


	// if "error array" is empty
	if(empty($errors)) { 

		//check that the email address provided is not already in the database
		$q = "SELECT first_name, last_name, email_address FROM users WHERE email_address = '$em'";
		$r = @mysqli_query($dbc, $q);
		$num = @mysqli_num_rows($r);


		// If email address does not exist in database, INSERT the new record
		if ($num == 0) { 
			
			//Create and run the INSERT query
			$q2 = "INSERT INTO users(first_name, last_name, email_address, password, registration_date) VALUES ('$fn', '$ln', '$em', SHA1('$pw'), NOW() )" ;
			$r2 = @mysqli_query($dbc, $q2);

			//checking if the query executing successfully
			if ($r2) {
				echo '<h1>Thank you</h1>
				<p>You are now registered.</p>';
				echo '<p>Click <a href="registeruser.php">here </a>to register another user.</p>';
			} else {
				echo '</p>Registration incomplete due to a system error.</p>';

				//debugging message
				echo '<p>'. mysqli_error($dbc) . '. Query: ' . $q2 . '</p>';
			}

			//close the connection
			mysqli_close($dbc); 
			
		} else { //if record exists
			echo '<p>An error has occured.  This email address is already registered in the system.</p>';
		}


	//if "error array" is not empty
	} else { 
		echo '<h1> Error! </h1>
		<p>The following error occured: <br />';
		foreach ($errors as $msg) {
			echo "$msg <br />\n";
		}
		echo 'Please try again';

		mysqli_close($dbc); 
	}
	
?>

</div>	
