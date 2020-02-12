<?php 

include('includes/header.php');
?>


	<div class="container">

	<h3>Email notification to user</h3>

	<h4>The following message will be sent to the user: </h4>

	<?php 

		//check for valid service requests ID through GET or POSt

		if ( (isset($_GET['id'])) && (is_numeric($_GET['id'])) ) {
			$id = $_GET['id'];
		} elseif ( (isset($_POST['id'])) && (is_numeric($_POST['id'])) ) {
			$id = $_POST['id'];
		} else { //no valid id, kill the script
			echo ' This page has been accessed in error';
			exit();
		}

	 	// Retrieve the service request information

		require_once('includes/mysqli_connect.php');

		$q = "SELECT service_request.service_request_id, service_request.category_id, category.category_name, service_request.description, service_request.priority_id, priority.priority_name, service_request.status_type_id, status_type.status_type, service_request.employee_id, CONCAT(employee.first_name,' ',employee.last_name) as User, employee.email, service_request.date_time FROM service_request LEFT JOIN category on service_request.category_id = category.category_id LEFT JOIN priority on service_request.priority_id = priority.priority_id LEFT JOIN status_type on service_request.status_type_id = status_type.status_type_id LEFT JOIN employee on service_request.employee_id = employee.employee_id WHERE service_request_id = $id";

		$r = @mysqli_query($dbc, $q);

		if (mysqli_num_rows($r) == 1) {
			$row = mysqli_fetch_array($r, MYSQLI_ASSOC);
		
			// Create the Form

		
	
		$msg = '<p>-- Ticket Update Notification --</p>' .
		'Ticket no: ' . $row['service_request_id'] . '<br \>' .
		'Category: ' . $row['category_name'] . '<br \>' .
		'Priority: ' . $row['priority_name'] . '<br \>' . 
		'Details: ' . $row['description'] . '<br \>' .
		'Submitted by: ' . $row['User'] . '<br \>' . 
		'User email: ' . $row['email'] . '<br \><br \>' .
		'STATUS: ' . strtoupper($row['status_type']);

		echo $msg;

		
		$body = "-- Ticket Update Notification --\n\nTicket no: " . $row['service_request_id'] . "\nCategory: " . $row['category_name'] . "\nPriority: " . $row['priority_name'] . "\nDetails: " . $row['description'] . "\nSubmitted by: " . $row['User'] . "\nUser email: " . $row['email'] . "\n\nSTATUS: " . strtoupper($row['status_type']);

	

		//echo $body;


		$to = $row['email'];
		$subject = 'IT Helpdesk Ticket Update Notification';
	

		echo '<form action="emailtckx.php" method="post"> 
		
		<input type="hidden" name="to" value="' . $to . '" />
		<input type="hidden" name="body" value="' . $body . '" />

		<p><input type="submit" name="submit" value="Send Email" /></p></form>';






		//mail($to, $subject, $body, 'From: registry.gy@undp.org');
		

		} else {
			echo 'error occured';
		}
	?>




</div>

<div class="footer">

	<div class="footer-text">  IT Helpdesk. All Rights Reserved.  &copy; 2018</div>

</div>	

</body>
</html>