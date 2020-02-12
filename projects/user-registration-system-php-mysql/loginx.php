<?php

include('includes/header.php');

?>


<div class="container">




<?php 

	require_once('includes/mysqli_connect.php');

	//initialize the errors array
	$errors = array();


	//validating input

	if(empty($_POST['email_address'])) {
		$errors[] = 'You forgot to enter your email address.';
	} else {
		$em = mysqli_real_escape_string($dbc, trim($_POST['email_address']));
	}


	if(empty($_POST['password'])) {
		$errors[] = 'You forgot to enter your password.';
	} else {
		$pw = mysqli_real_escape_string($dbc, trim($_POST['password']));
	}


	if(empty($errors)) { // if "error array" is empty

		//check that the email address provided is not already in the database
		$q = "SELECT user_id, first_name, last_name from users where email_address = '$em' and password = '$pw'";
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
				$_SESSION['first_name'] = $row['first_name'];
				$_SESSION['last_name'] = $row['last_name'];

				//do a redirect
				header('Location:index.php');
				exit();

			} else {
				//if no match in database
				echo '<p>An error has occured.  The username or password is incorrect </p>';

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

		echo '<h2> Error! </h2>
		<p>The following error occured: <br />';
		foreach ($errors as $msg) {
			echo "$msg <br />\n";
		}
		echo 'Please try again.';

		//close the connection
		mysqli_close($dbc);
	}

?> 


</div>

</body>
</html>