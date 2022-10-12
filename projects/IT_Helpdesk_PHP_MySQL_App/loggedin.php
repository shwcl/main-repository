<?php 

include('includes/header.html');
?>

	<div class="container">

	<h4>Logged in page</h4>
	
	<?php

	//checking if session variable is set before page is accessed.. 
	session_start();
	
	if(!isset($_SESSION['user_id'])) {

		echo 'accessed in error, redirecting...';
		header('Location:http://localhost/project6/index.php');
		exit();
	}

	//Print custom message

	echo 'Congrats! You are now logged in';

	?>

</body>

</html>