<!DOCTYPE html>

<html>

<head>
 <title>ICT Helpdesk</title>
 <meta charset=utf-"8">
 <link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
</head>

<body>


<div class="topline"> </div>
		
	<div class="header">
		<h3>UNDP Guyana</h3>
		<h1>IT Helpdesk</h1>
	</div>

	
	<div class="navbar">
		<ul>
			<li><a href="index.php"><button class="dropbtn">Home</button></a></li>

			<div class="dropdown">
				 <button class="dropbtn">Tickets</button>
				 <div class="dropdown-content">
				    <a href="tickets.php">View all Tickets</a>
				    <a href="addticket.php">Create new Ticket</a>
				    <a href="t2.php">Edit/update Ticket</a>
				 </div>
			</div>

			<div class="dropdown">
				 <button class="dropbtn">Tools</button>
				 <div class="dropdown-content">
				 	<a href="adduser.php">Email/notify User</a>
				    <a href="adduser.php">Add a new User</a>
				    <a href="addcat.php">Add a new Category</a>
				    <a href="addunit.php">Add a new Unit</a>
				  </div>
			</div>
			<li><a href="search.php"><button class="dropbtn">Search</button></a></li>
		</ul>

</div>


<div class="container">

	<h4>Select a Ticket to edit or use the Search function below</h4>


<?php 

	require_once('includes/mysqli_connect.php');

	//number of records to show per page
	$display = 5;

	//Determine number of pages

	if (isset($_GET['p']) && is_numeric($_GET['p'])) {

		$no_pages = $GET['p'];

	} else {
		//need to determine numer of pages
		//step 1:  count # of records
		$q = 'SELECT count(service_request_id) FROM service_request';
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


	//run the query
	$q = "SELECT service_request_id, category.category_name, description, priority.priority_name, status_type.status_type, date_time, CONCAT(employee.first_name,' ',employee.last_name) as User 
	FROM service_request 
	LEFT JOIN category on service_request.category_id = category.category_id 
	LEFT JOIN priority on service_request.priority_id = priority.priority_id 
	LEFT JOIN status_type on service_request.status_type_id = status_type.status_type_id 
	LEFT JOIN employee on service_request.employee_id = employee.employee_id 
	ORDER BY service_request_id ASC";

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
			<td>'. $row['User'].'</td><td><a href="editticket.php?id='.$row['service_request_id'].'">Edit</a></td></tr>';
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