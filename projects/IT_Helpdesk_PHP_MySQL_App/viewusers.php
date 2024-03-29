
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

<h3>Users</h3>


<?php 

	require_once('includes/mysqli_connect.php');

	//number of records to show per page
	$display = 10;

	//Determine number of pages

	if (isset($_GET['p']) && is_numeric($_GET['p'])) {

		$no_pages = $_GET['p'];

	} else {
		//need to determine numer of pages
		//step 1:  count # of records
		$q = 'SELECT count(employee_id) FROM employee';
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


	//run the query
	$q = "SELECT employee_id, first_name, last_name, tel_no, tel_no_ext, email, unit.unit_name
	 FROM employee 
	 JOIN unit
	 ON employee.unit_id = unit.unit_id
	 ORDER BY first_name ASC LIMIT $start, $display";

	$r = @mysqli_query($dbc, $q);

	
	if ($r) {

		//create table header
		echo '<table class="table1" width="65%"><tr><td>User ID</td><td>First Name</td><td>Last Name</td><td>Telephone Number</td>
		<td>Telephone No. Ext</td><td>Email Address</td><td>Unit</td><td>Edit</td></tr>';

		$bg = '#eeeeee'; // Set the initial background color.

		//fetch and print all records
		while ($row = mysqli_fetch_array($r, MYSQLI_ASSOC)) {

			$bg = ($bg=='#eeeeee' ? '#ffffff' : '#eeeeee');

			echo '<tr bgcolor="' . $bg . ' "><td>'. $row['employee_id'].'</td><td>'.$row['first_name'].'</td><td>'.$row['last_name'].'</td>
			<td>'. $row['tel_no'].'</td><td>'.$row['tel_no_ext'].'</td><td>'.$row['email'].'</td>
			<td>'.$row['unit_name'].'</td>
			<td><a href="edituser.php?id='.$row['employee_id'].'">Edit</a></td></tr>';
		}

		echo '</table>';
		mysqli_free_result ($r);

	} else {

		echo '<p>The current users could not be retrieved.</p>';
		
		 // Debugging message:
		 echo '<p>' . mysqli_error($dbc) . '<br /><br />Query: ' . $q . '</p>';
		
	} // End of if ($r) IF.

	echo '<br />';
	echo '<br />';
	//Make the link to the other pages, if necessary
	if ($no_pages > 1) {
		$current_page = ($start/$display) + 1;

		//Make a Previous page link
		If ($current_page != 1) {
			echo '<a href="viewusers.php?s=' . ($start - $display) . '&p=' . $no_pages . '">Previous</a> ';
		} 


		//make the numbered pages
		for ($i = 1; $i <= $no_pages; $i++) {
			if ($i != $current_page) {

				echo '<a href="viewusers.php?s=' . $display * ($i - 1) . '&p=' . $no_pages . '">' . $i . '</a> ';

			} else {
				echo $i . ' ';

			}
		}

		//Make a Next page link
		If ($current_page != $no_pages) {
			echo '<a href="viewusers.php?s=' . ($start + $display) . '&p=' . $no_pages . '">Next</a> ';
		
		} 

	}

?>



</div>

<div class="footer">

	<div class="footer-text">  IT Helpdesk. All Rights Reserved.  &copy; 2018</div>

</div>

</body>
</html>