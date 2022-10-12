
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

<?php 

	require_once('includes/mysqli_connect.php');

//initialize an errors array
	$errors = array();

	//check if empty
	if(empty($_POST['description'])) {
		$errors[]='You forgot to enter the details of the request';
	} else {
		$des = mysqli_real_escape_string($dbc, trim($_POST['description']));
	}


	if(empty($_POST['email_address'])) {
		$errors[]='You forgot to enter your email address';

	} else {
		$em = mysqli_real_escape_string($dbc, trim($_POST['email_address']));
	}


	$to = 'shw_cl@hotmail.com';
	$subject = 'Service Request';
	$body = wordwrap($des, 70);
	$from = $em;




	if(empty($errors)) { // if "error array" is empty

		//run the query
		$q1 = "SELECT employee_id from employee where email = '$em'";
		$r1 = @mysqli_query($dbc, $q1);
		$num = @mysqli_num_rows($r1);

		// select first name and last name where first name = what was inputted and last name  = input...
		// if no match then carry on... if match warn and continue???

		// checking if email address exist
		if ($num == 1) { 
			
			//Gettign the employee ID from the email address provided
			while ($row = mysqli_fetch_row($r1)) { 	
				$emp_id = $row[0];
			}

			//Create and run the INSERT query
			$q2 = "INSERT INTO service_request (category_id, description, priority_id, status_type_id, date_time, employee_id) VALUES (31, '$des', 2, 1, NOW(), '$emp_id')" ;
			$r2 = @mysqli_query($dbc, $q2);

			if ($r2) {
				echo '<p>The Ticket was added.</p>';
			

			} 	else {
				echo '</p>The record was not added due to a system error</p>';
				//debugging message
				echo '<p>'. mysqli_error($dbc) . '. Query: ' . $q2 . '</p>';	//to look into further to dicipher
			}
			mysqli_close($dbc); //close the connection
			
		} else { //if record does not exist
			echo '<p>Sorry, the email address does not exist.  Contact IT to register your name and email address in the IT Helpdesk application. </p>';
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

</div>

<div class="footer">

	<div class="footer-text">  IT Helpdesk. All Rights Reserved.  &copy; 2018</div>

</div>


</body>
</html>