
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

<h3> Edit Ticket Category details</h3>

<?php 

	require_once('includes/mysqli_connect.php');

	$ct = mysqli_real_escape_string($dbc,trim($_POST['category_name']));
	$id = mysqli_real_escape_string($dbc,trim($_POST['id']));  // posted via hidden form value
				
	//Make and run the query
	$q = "UPDATE category
	SET category_name = '$ct' WHERE category_id = $id";
	$r = @mysqli_query($dbc, $q);
					
	if (mysqli_affected_rows($dbc) == 1) {  //If query ran ok
		//Print a mesage
		echo '<p>The ticket category was edited.</p>';
		echo '<p>Do you wish to edit another?</p>';

		echo '<form><button formaction="viewctgs.php">Yes</button> ' . '&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp' .
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