
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

	<h3>Edit Ticket details</h3>

	<?php 

	require_once('includes/mysqli_connect.php');

	//initialize an errors array
	$errors = array();

	$cat_id = mysqli_real_escape_string($dbc,trim($_POST['category_id']));
	$pri_id = mysqli_real_escape_string($dbc,trim($_POST['priority_id']));
	$sta_id = mysqli_real_escape_string($dbc,trim($_POST['status_type_id']));
	$desc = mysqli_real_escape_string($dbc,trim($_POST['description']));
	$id = mysqli_real_escape_string($dbc,trim($_POST['id']));  // posted via hidden value
				
		//check if array has any errors
		if(empty($errors)) { 

			//Make and run the query
			$q = "UPDATE service_request 
			SET category_id = $cat_id, priority_id = $pri_id, status_type_id = $sta_id, description = '$desc' 
			WHERE service_request_id = $id";
			$r = @mysqli_query($dbc, $q);
						
			if (mysqli_affected_rows($dbc) == 1) {
				//Print a mesage
				echo '<p>The Ticket was edited.</p>';
				echo '<p>Do you want to edit another?</p>';
				echo '<form><button formaction="alltcks.php">Yes</button> ' . '&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp' .
				'<button formaction="index.php">No</button></form>';

			} else {
				echo 'The Ticket was not edited due to a system error... add more code to show sql erroe message...';
				echo mysqli_error($dbc);
			}

		} else {
			echo 'error occur... dump messages in error array...';
		}
	?>

	</div>

	<div class="footer">

		<div class="footer-text">  IT Helpdesk. All Rights Reserved.  &copy; 2018</div>

	</div>	

</body>
</html>