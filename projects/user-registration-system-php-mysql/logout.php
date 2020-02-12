
<?php
	
	// this code must be at the very top of each PHP page before any HTML code
	//checking if session variable is set before page is accessed.. 
	session_start();

	// If no session variable exists, redirect the user:
	if(!isset($_SESSION['user_id'])) {

		echo 'Accessed in error, redirecting...';
		header('Location:http://localhost/register/login.php');
		exit();

	// Cancel the session
	} else { 

		// Clear thevariables
		$_SESSION = array( ); 

		// Destroy thesession itself
		session_destroy( ); 

		// Destroy the cookie
		setcookie ('PHPSESSID', '', time( )-3600,'/', '', 0, 0); 

		// redirect to the login page
		header('Location:http://localhost/register/login.php');
	}

?>

