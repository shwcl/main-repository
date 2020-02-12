<?php

include('includes/header.php');

?>



<div class="container">

<?php 


	echo '<h3>Edit User</h3>';


		//check for valid service requests ID through GET or POSt
		if ( (isset($_GET['id'])) && (is_numeric($_GET['id'])) ) {
			$id = $_GET['id'];
		} elseif ( (isset($_POST['id'])) && (is_numeric($_POST['id'])) ) {
			$id = $_POST['id'];
		} else { //no valid id, kill the script
			echo 'This page has been accessed in error';
			exit();
		}

	 	// Retrieve the data in the form submitted
		require_once('includes/mysqli_connect.php');


		// form validation 
		$errors = array();

	 	if (empty($_POST['first_name'])) {
	 		$errors[] = 'You forgot to enter a first name';
	 	} else {
	 		$fn = mysqli_real_escape_string($dbc, trim($_POST['first_name']));
	 	}


	 	if (empty($_POST['last_name'])) {
	 		$errors[] = 'You forgot to enter a last name';
	 	} else {
	 		$ln = mysqli_real_escape_string($dbc, trim($_POST['last_name']));
	 	}

	
	 	//check if any errors
	 	if(empty($errors)) {

	 		//make the update
	 		$q = "UPDATE USERS SET first_name = '$fn', last_name = '$ln' WHERE user_id = $id";
	 		$r = @mysqli_query($dbc, $q);

	 		if(mysqli_affected_rows($dbc) == 1) {

	 			//Print message
	 			echo '<p>The record was updated.</p>';

	 		} else {
	 			echo 'An error occured updating the record';
	 			echo '<p>'. mysqli_error($dbc); 
	 			echo '<br />'; 
	 			echo 'Query: ' . $q . ' </p>';
	 		}


	 	} else {
	 		echo '<p> The following error occured:</p>';
	 		foreach($errors as $msg) {
	 			echo '- ' . $msg . '<br />';
	 		}

	 		echo '<p>Please try again.</p>';
	 	}

?>


</div>

</body>
</html>