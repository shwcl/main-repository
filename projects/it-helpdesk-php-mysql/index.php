<?php 

// this code must be at the very top of each PHP page before any HTML: code

	session_start();
	
	if(!isset($_SESSION['user_id'])) {

		echo 'accessed in error, redirecting...';
		header('Location:login_page.php');
		exit();
	}


include('includes/header.php');


?>


<?php 

	require_once('includes/mysqli_connect.php');
	
	//get number of new tickets - tickets created not more than 15 days ago
	$q1 = "SELECT service_request_id FROM service_request WHERE date_time BETWEEN DATE_SUB(NOW(), INTERVAL 15 DAY) AND NOW()";
	$r1 = @mysqli_query($dbc, $q1);
	$new = @mysqli_num_rows($r1);

	//determine number of unresolved tickets
	$q = "SELECT service_request_id FROM service_request WHERE status_type_id ='1'";
	$r = @mysqli_query($dbc, $q);
	$open = @mysqli_num_rows($r);


	//determine number of tickets in progress
	$q = "SELECT service_request_id FROM service_request WHERE status_type_id ='2'";
	$r = @mysqli_query($dbc, $q);
	$pend = @mysqli_num_rows($r);
	
	//calculating total number of tickets
	$q1 = "SELECT service_request_id FROM service_request" ;
	$r1 = @mysqli_query($dbc, $q1);
	$all = @mysqli_num_rows($r1);
?>



	<div class="container">	

		<div class="outerbox">

			<h2>Dashboard</h2>

			<div class="box1">
				<h4>New</h4>
				<p><a href="newtcks.php"><?php echo $new; ?></a></p>
			</div>

			<div class="box2">
				<h4>Opened</h4>
				<p><a href="opentcks.php"><?php echo $open; ?></a></p>
			</div>	

			<div class="box3">
				<h4>Pending</h4>
				<p><a href="pendtcks.php"><?php echo $pend; ?></a></p>
			</div>

			<div class="box4">
				<h4>Total</h4>
				<p><a href="alltcks.php"><?php echo $all; ?></a></p>
			</div>	

		</div>


		<?php


		
		echo '<h3 class="undrln">Recent Tickets</h3>';

		require_once('includes/mysqli_connect.php');

		//run the query
		$q = "SELECT service_request_id, category.category_name, description, priority.priority_name, status_type.status_type, date_time, CONCAT(employee.first_name,' ',employee.last_name) as User 
		FROM service_request 
		LEFT JOIN category on service_request.category_id = category.category_id 
		LEFT JOIN priority on service_request.priority_id = priority.priority_id 
		LEFT JOIN status_type on service_request.status_type_id = status_type.status_type_id 
		LEFT JOIN employee on service_request.employee_id = employee.employee_id 
		WHERE date_time BETWEEN DATE_SUB(NOW(), INTERVAL 15 DAY) AND NOW()
		ORDER BY service_request_id DESC
		LIMIT 3";

		$r = @mysqli_query($dbc, $q);

		
		if ($r) {

			//create table header
			echo '<table class="table1" width="65%"><tr><td> Service Request ID</td><td>Category</td><td>Request/Issue Details</td><td>Priority</td><td>Job Status</td><td>Date Submitted</td><td>User</td> <td>Edit</td></tr>';

			$bg = '#eeeeee'; // Set the initial background color.

			//fetch and print all records
			while ($row = mysqli_fetch_array($r, MYSQLI_ASSOC)) {

				$bg = ($bg=='#eeeeee' ? '#ffffff' : '#eeeeee');

				echo '<tr bgcolor="' . $bg . ' "><td>'. $row['service_request_id'].'</td><td>'.$row['category_name'].'</td><td>'.$row['description'].'</td>
				<td>'. $row['priority_name'].'</td><td>'.$row['status_type'].'</td><td>'.$row['date_time'].'</td>
				<td>'. $row['User'].'</td><td><a href="edittck.php?id='.$row['service_request_id'].'">Edit</a></td>
				<td><a href="emailtck.php?id='.$row['service_request_id'].'">Send Email</a></td></tr>';
			}

			echo '</table>';
			mysqli_free_result ($r);

		} else {

			echo '<p>The current users could not be retrieved.</p>';
			
			 // Debugging message:
			 echo '<p>' . mysqli_error($dbc) . '<br /><br />Query: ' . $q . '</p>';
			
		} // End of if ($r) IF.



	?>

	<?php
	echo '<br />';
	echo '<br />';

	echo '<h3 class="undrln">Open Tickets (<a href="opentcks.php">View all</a>)</h3>';

		require_once('includes/mysqli_connect.php');

		//run the query
		$q = "SELECT service_request_id, category.category_name, description, priority.priority_name, status_type.status_type, date_time, CONCAT(employee.first_name,' ',employee.last_name) as User 
		FROM service_request 
		LEFT JOIN category on service_request.category_id = category.category_id 
		LEFT JOIN priority on service_request.priority_id = priority.priority_id 
		LEFT JOIN status_type on service_request.status_type_id = status_type.status_type_id 
		LEFT JOIN employee on service_request.employee_id = employee.employee_id 
		WHERE service_request.status_type_id IN (1,2)
		ORDER BY service_request_id DESC
		LIMIT 4";

		$r = @mysqli_query($dbc, $q);

		
		if ($r) {

			//create table header
			echo '<table class="table1" width="65%"><tr><td> Service Request ID</td><td>Category</td><td>Request/Issue Details</td><td>Priority</td><td>Job Status</td><td>Date Submitted</td><td>User</td> <td>Edit</td></tr>';

			$bg = '#eeeeee'; // Set the initial background color.

			//fetch and print all records
			while ($row = mysqli_fetch_array($r, MYSQLI_ASSOC)) {

				$bg = ($bg=='#eeeeee' ? '#ffffff' : '#eeeeee');

				echo '<tr bgcolor="' . $bg . ' "><td>'. $row['service_request_id'].'</td><td>'.$row['category_name'].'</td><td>'.$row['description'].'</td>
				<td>'. $row['priority_name'].'</td><td>'.$row['status_type'].'</td><td>'.$row['date_time'].'</td>
				<td>'. $row['User'].'</td><td><a href="edittck.php?id='.$row['service_request_id'].'">Edit</a></td>
				<td><a href="emailtck.php?id='.$row['service_request_id'].'">Send Email</a></td></tr>';
			}

			echo '</table>';
			mysqli_free_result ($r);

		} else {

			echo '<p>The current users could not be retrieved.</p>';
			
			 // Debugging message:
			 echo '<p>' . mysqli_error($dbc) . '<br /><br />Query: ' . $q . '</p>';
			
		} // End of if ($r) IF.



	?>


	<?php
	echo '<br />';
	echo '<br />';

	echo '<h3 class="undrln">Pending Tickets (<a href="pendtcks.php">View all</a>)</h3>';

		require_once('includes/mysqli_connect.php');

		//run the query
		$q = "SELECT service_request_id, category.category_name, description, priority.priority_name, status_type.status_type, date_time, CONCAT(employee.first_name,' ',employee.last_name) as User 
		FROM service_request 
		LEFT JOIN category on service_request.category_id = category.category_id 
		LEFT JOIN priority on service_request.priority_id = priority.priority_id 
		LEFT JOIN status_type on service_request.status_type_id = status_type.status_type_id 
		LEFT JOIN employee on service_request.employee_id = employee.employee_id 
		WHERE service_request.status_type_id = 2
		ORDER BY service_request_id DESC
		LIMIT 4";

		$r = @mysqli_query($dbc, $q);

		
		if ($r) {

			//create table header
			echo '<table class="table1" width="65%"><tr><td> Service Request ID</td><td>Category</td><td>Request/Issue Details</td><td>Priority</td><td>Job Status</td><td>Date Submitted</td><td>User</td> <td>Edit</td></tr>';

			$bg = '#eeeeee'; // Set the initial background color.

			//fetch and print all records
			while ($row = mysqli_fetch_array($r, MYSQLI_ASSOC)) {

				$bg = ($bg=='#eeeeee' ? '#ffffff' : '#eeeeee');

				echo '<tr bgcolor="' . $bg . ' "><td>'. $row['service_request_id'].'</td><td>'.$row['category_name'].'</td><td>'.$row['description'].'</td>
				<td>'. $row['priority_name'].'</td><td>'.$row['status_type'].'</td><td>'.$row['date_time'].'</td>
				<td>'. $row['User'].'</td><td><a href="edittckt.php?id='.$row['service_request_id'].'">Edit</a></td>
				<td><a href="emailtck.php?id='.$row['service_request_id'].'">Send Email</a></td></tr>';
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

<div class="footer">

	<div class="footer-text">  IT Helpdesk. All Rights Reserved.  &copy; 2018</div>

</div>
</body>

</html>

