<!DOCTYPE html>

<html>

<head>
 <title>UNDP Guyana | IT Helpdesk</title>
 <meta charset=utf-"8">
 <link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
</head>

<body>


<div class="topline"> </div>
		
	<div class="header">
		<h3>UNDP Guyana</h3>
		<h1>IT Helpdesk</h1>
	</div>

	
	<div class="container">

	<h4>Login Page result..</h4>


<?php

	require_once('includes/mysqli_connect.php');

	//initialize an errors array
		$errors = array();


		// Validating input:- check if fields are empty

		if(empty($_POST['username'])) {
			$errors[]='You forgot to enter your username';

		} else {
			$un = mysqli_real_escape_string($dbc, trim($_POST['username']));
		}
		

			if(empty($_POST['pwd'])) {
			$errors[]='You forgot to enter the password';
		} else {
			$pw = mysqli_real_escape_string($dbc, trim($_POST['pwd']));
		}


		if(empty($errors)) { // if "error array" is empty

			//check that the email address provided is not already in the database
			$q = "SELECT user_id, username from register where username = '$un' and pass = '$pw'";
			$r = @mysqli_query($dbc, $q);
			$num = @mysqli_num_rows($r);
			
			// if query ran without errors
			if ($r) {

				//if there is a match in the database
				if ($num == 1) { 

					$row = mysqli_fetch_array($r, MYSQLI_ASSOC);


					//set the Session data

					session_start();

					$_SESSION['user_id'] = $row['user_id'];
					$_SESSION['username'] = $row['username'];

					//do a redirect
					header('Location:index.php');
					exit();

				} else {
					//if no match in database
					echo '<p>The username or password is incorrect. Click <a href="login_page.php">here</a> to try again. </p>';

					// also print the echo 'includes/header.thml'
				}
			
			//If query ran with errors
			} else {



				echo '<p>An error has occured.</p>';
				//Debugging message:
				echo '<p>' . mysqli_error($dbc) . '<br /><br />Query: ' . $q . '</p>';
			}


		} else { //if "error array" is not empty

		//print out HTML page first 'includes/header.thml'
		//print out HTML page first 'includes/header.thml'
		//print out HTML page first 'includes/header.thml'	

			echo '<h1> Error! </h1>
			<p>The following error occured: <br />';
			foreach ($errors as $msg) {
				echo "$msg <br />\n";
			}
			echo 'Please try again';

			//close the connection
			mysqli_close($dbc);
		}
	
?> 

</body>

</html>