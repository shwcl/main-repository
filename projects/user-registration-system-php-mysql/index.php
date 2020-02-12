<?php
	
	// this code must be at the very top of each PHP page before any HTML: code
	//checking if session variable is set before page is accessed.. 
	session_start();

	if(!isset($_SESSION['user_id'])) {

		echo 'Accessed in error, redirecting...';
		header('Location:http://localhost/register/login.php');
		exit();
	}
?>



<?php

include('includes/header.php');

?>

<div class ="loggedinuser">Logged in: <?php echo $_SESSION['first_name'] . ' ' . $_SESSION['last_name'] ?> </div>

<div class="container">


<h4>Welcome <?php echo $_SESSION['first_name'] . ' ' . $_SESSION['last_name'] . '! You are now logged in.' ?> </h4>

</p>




</div>

</body>
</html>