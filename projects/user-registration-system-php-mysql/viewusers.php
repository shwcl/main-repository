<?php
	
	// this code must be at the very top of each PHP page before any HTML code
	//checking if session variable is set before page is accessed.. 
	session_start();

	if(!isset($_SESSION['user_id'])) {

		echo 'Accessed in error, redirecting...';
		header('Location:http://localhost/register/accessdenied.php');
		exit();
	}
?>


<?php

include('includes/header.php');

?>


<div class ="loggedinuser">Logged in: <?php echo $_SESSION['first_name'] . ' ' . $_SESSION['last_name'] ?> </div>

<div class="container">


<?php 
echo '<h4>Registered users</h4>';
echo '<br />';
require_once('includes/mysqli_connect.php');


	//run the query
	$q = "SELECT user_id, first_name, last_name, email_address, password, registration_date FROM users";
	$r = @mysqli_query($dbc, $q);

	
	if ($r) {

		//create table header
		echo '<table class="table table-striped" <thead><tr><th>User ID</th><th>First Name</th><th>Last Name</th><th>Email Address</th>
		<th>Password</th><th>Registration Date</th><th>Edit</th><th>Delete</th></tr></thead>';

		// Retrieve query results one row per loop placing it in a table one row per loop
		while ($row = mysqli_fetch_array($r, MYSQLI_ASSOC)) {

			echo '<tr><td>'. $row['user_id'].'</td><td>'.$row['first_name'].'</td><td>'.$row['last_name'].'</td>
			<td>'. $row['email_address'].'</td><td>'.$row['password'].'</td><td>'. $row['registration_date'].'</td>
			<td><a href="edituser.php?id='.$row['user_id'].'">Edit</a></td><td><a href="deleteuser.php?id='.$row['user_id'].'">Delete</a></td></tr>';
		}

		echo '</table>';
		mysqli_free_result ($r);

	} else {

		echo '<p>The current users could not be retrieved.</p>';
		
		 // Debugging message:
		 echo '<p>' . mysqli_error($dbc) . '<br /><br />Query: ' . $q . '</p>';
		
	} // End of if ($r) IF.

?>


</div>

</body>

</html>
