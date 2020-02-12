<?php 

include('includes/header.php');
?>
	
<div class="container">

<h3> Edit Department details</h3>

<?php 

	require_once('includes/mysqli_connect.php');

	$u = mysqli_real_escape_string($dbc,trim($_POST['unit_name']));
	$id = mysqli_real_escape_string($dbc,trim($_POST['id']));  // posted via hidden form value
				
	//Make and run the query
	$q = "UPDATE unit
	SET unit_name = '$u' WHERE unit_id = $id";
	$r = @mysqli_query($dbc, $q);
					
	if (mysqli_affected_rows($dbc) == 1) {  //If query ran ok
		//Print a mesage
		echo '<p>The department was edited.</p>';
		echo '<p>Do you want to edit another?</p>';

		echo '<form><button formaction="viewunits.php">Yes</button> ' . '&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp' .
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