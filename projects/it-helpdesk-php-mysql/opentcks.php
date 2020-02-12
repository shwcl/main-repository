
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

	<h3>Opened Tickets</h3>

<?php 

	require_once('includes/mysqli_connect.php');

	//number of records to show per page
	$display = 5;

	//Determine number of pages

	if (isset($_GET['p']) && is_numeric($_GET['p'])) {

		$no_pages = $_GET['p'];

	} else {
		//need to determine numer of pages
		//step 1:  count # of records
		$q = 'SELECT count(service_request_id) FROM service_request WHERE status_type_id IN (1,2)';
		$r = @mysqli_query($dbc, $q);

		$row = @mysqli_fetch_array($r, MYSQLI_NUM);
		$no_records = $row[0];

		//Step 2: calcualte # of pages
		if ($no_records > $display) {

			$no_pages = ceil($no_records/$display);
		} else {
			$no_pages = 1;
		}
	}


	//Determine where in the database to start returning results
	if (isset($_GET['s']) && is_numeric($_GET['s'])) {

		$start = $_GET['s'];

	} else {
		$start = 0;
	}

	// //Create and run a query to select all tickets that "not started" or "in progress"
	$q = "SELECT service_request_id, category.category_name, description, priority.priority_name, status_type.status_type, date_time, CONCAT(employee.first_name,' ',employee.last_name) as User 
	FROM service_request 
	LEFT JOIN category on service_request.category_id = category.category_id 
	LEFT JOIN priority on service_request.priority_id = priority.priority_id 
	LEFT JOIN status_type on service_request.status_type_id = status_type.status_type_id 
	LEFT JOIN employee on service_request.employee_id = employee.employee_id 
	WHERE service_request.status_type_id IN (1,2)
	ORDER BY service_request_id ASC LIMIT $start, $display";

	$r = @mysqli_query($dbc, $q);
	
	if ($r) {

		//create table header
		echo '<table class="table1" width="65%"><tr><td> Service Request ID</td><td>Category</td><td>Request/Issue Details</td><td>Priority</td><td>Job Status</td><td>Date Submitted</td><td>User</td> <td>Edit</td></tr>';

		// Set the initial background color.
		$bg = '#eeeeee'; 

		//fetch and print all records
		while ($row = mysqli_fetch_array($r, MYSQLI_ASSOC)) {

			$bg = ($bg=='#eeeeee' ? '#ffffff' : '#eeeeee');

			echo '<tr bgcolor="' . $bg . ' "><td>'. $row['service_request_id'].'</td><td>'.$row['category_name'].'</td><td>'.$row['description'].'</td>
			<td>'. $row['priority_name'].'</td><td>'.$row['status_type'].'</td><td>'.$row['date_time'].'</td>
			<td>'. $row['User'].'</td><td><a href="edittck.php?id='.$row['service_request_id'].'">Edit</a></td></tr>';
		}

		echo '</table>';
		mysqli_free_result ($r);

	} else {

		echo '<p>There was an error in retrieving the data.</p>';
		
		 // Debugging message:
		 echo '<p>' . mysqli_error($dbc) . '<br /><br />Query: ' . $q . '</p>';
		
	} // End of if ($r) IF.


	//Make the link to the other pages, if necessary
	if ($no_pages > 1) {

		$current_page = ($start/$display) + 1;

		//Make a Previous page link
		If ($current_page != 1) {
			echo '<a href="opentcks.php?s=' . ($start - $display) . '&p=' . $no_pages . '">Previous</a> ';
		
		} 

		//make the numbered pages
		for ($i = 1; $i <= $no_pages; $i++) {
			if ($i != $current_page) {

				echo '<a href="opentcks.php?s=' . $display * ($i - 1) . '&p=' . $no_pages . '">' . $i . '</a> ';

			} else {
				echo $i . ' ';
			}
		}

		//Make a Next page link
		If ($current_page != $no_pages) {
			echo '<a href="opentcks.php?s=' . ($start + $display) . '&p=' . $no_pages . '">Next</a> ';
		} 
	}

?>

</div>

<div class="footer">
	<div class="footer-text">  IT Helpdesk. All Rights Reserved.  &copy; 2018</div>
</div>

</body>
</html>