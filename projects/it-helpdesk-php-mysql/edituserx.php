
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

<h3> Edit User details</h3>

<?php 

	require_once('includes/mysqli_connect.php');

	$fn = mysqli_real_escape_string($dbc,trim($_POST['first_name']));
	$ln = mysqli_real_escape_string($dbc,trim($_POST['last_name']));
	$t = mysqli_real_escape_string($dbc,trim($_POST['tel_no']));
	$tx = mysqli_real_escape_string($dbc,trim($_POST['tel_no_ext']));
	$e = mysqli_real_escape_string($dbc,trim($_POST['email']));
	$id = mysqli_real_escape_string($dbc,trim($_POST['id']));  // posted via hidden form value
				
	//Make and run the query
	$q = "UPDATE employee 
	SET first_name = '$fn', last_name = '$ln', tel_no = '$t', tel_no_ext = '$tx', email = '$e' 
	WHERE employee_id = $id";
	$r = @mysqli_query($dbc, $q);
					
	if (mysqli_affected_rows($dbc) == 1) {  //If query ran ok
		//Print a mesage
		echo '<p>The user was edited.</p>';
		echo '<p>Do you want to edit another?</p>';
		echo '<form><button formaction="viewusers.php">Yes</button> ' . '&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp' .
		'<button formaction="index.php">No</button></form>';

	} else { // If it did not run ok
		echo '<p>An error has occured.</p>';
		echo mysqli_error($dbc);
	}

?>

</div>

<div class="footer">

	<div class="footer-text">  IT Helpdesk. All Rights Reserved.  &copy; 2018</div>

</div>	

</body>
</html>