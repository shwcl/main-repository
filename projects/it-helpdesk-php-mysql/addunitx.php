
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

<?php

require_once('includes/mysqli_connect.php');

//initialize an errors array
	$errors = array();

	// Validating input:- check if fields are empty
	if(empty($_POST['unit_name'])) {
		$errors[]='You forgot to enter the Unit Name';

	} else {
		$un = mysqli_real_escape_string($dbc, trim($_POST['unit_name']));
	}

	if(empty($errors)) { // if "error array" is empty

		//check that the Unit provided is not already in the database
		$q = "SELECT unit_id from unit where unit_name = '$un'";
		$r = @mysqli_query($dbc, $q);
		$num = @mysqli_num_rows($r);

		// If no records found for Unit Name 
		if ($num == 0) { 
			
			//Create and run the INSERT query
			$q2 = "INSERT INTO unit (unit_name) VALUES ('$un')" ;
			$r2 = @mysqli_query($dbc, $q2);

			//checkign if the query executing successfully, that is if $r2 returns a boolean value of TRUE
			if ($r2) {
				echo '<p>The record was added.</p>';
			} else {
				echo '</p>The record was not added due to a system error</p>';

				//debugging message
				echo '<p>'. mysqli_error($dbc) . '. Query: ' . $q2 . '</p>';	//to look into further to dicipher
			}

			mysqli_close($dbc); //close the connection
			
		} else { //if record does not exist
			echo '<p>An error has occured.  The Unit Name is already in the database. </p>';
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

</body>

</html>

