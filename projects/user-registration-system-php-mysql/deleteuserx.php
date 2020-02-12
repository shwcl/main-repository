

<?php

include('includes/header.php');

?>




<div class="container">

<?php

require_once('includes/mysqli_connect.php');
			
	//store ID value passed to current page from previous page to a variable																			
	$id = mysqli_real_escape_string($dbc,trim($_POST['id'])); 																																							

	//run the query
	$q = "DELETE FROM users WHERE user_id = $id LIMIT 1";
	$r = @mysqli_query($dbc, $q);

	
	if (mysqli_affected_rows($dbc) == 1) {
		echo '<p>The user was deleted.</p>';
		echo '<p>Click <a href="viewusers.php">here </a>to go back to View Users.</p>';

	} else {
		echo 'Error: the user could not be deleted due to an error';
		echo mysqli_error($dbc);
		echo '<p>Query: '. $q . '<p>';

	}

?>

</div>
</body>
</html>