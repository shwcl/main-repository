
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

	<h3>Search results... </h3>

	<?php
		require_once('includes/mysqli_connect.php');

		//initialize an errors array
		$errors = array();


		if(empty($_POST['search'])) {
			$errors[]='You forgot to enter a keyword';
		} else {
			$sr = mysqli_real_escape_string($dbc, trim($_POST['search']));
		}

		//check if array has any errors
		if(empty($errors)) { 

			$q = "SELECT service_request_id, category.category_name, description, priority.priority_name, status_type.status_type, date_time, CONCAT(employee.first_name,' ',employee.last_name) as User 
			FROM service_request 
			LEFT JOIN category on service_request.category_id = category.category_id 
			LEFT JOIN priority on service_request.priority_id = priority.priority_id 
			LEFT JOIN status_type on service_request.status_type_id = status_type.status_type_id 
			LEFT JOIN employee on service_request.employee_id = employee.employee_id
			WHERE service_request.description like '$sr%' or
			service_request.description like '%$sr' or
			employee.first_name ='%$sr' or 
			employee.first_name ='$sr%' or 
			employee.last_name = '%$sr' or
			employee.last_name ='$sr%' or
			employee.first_name ='$sr' or
			employee.last_name ='$sr'
			ORDER BY service_request_id ASC";

			$r = @mysqli_query($dbc, $q);

			$num = @mysqli_num_rows($r);


			if ($r) { // checking if query executely successfully (without errors)

				// If query result is > 1
				if ($num > 0) { 

					//create table header
					echo '<table class="table1" width="65%"><tr><td> Service Request ID</td><td>Category</td><td>Request/Issue Details</td><td>Priority</td><td>Job Status</td><td>Date Submitted</td><td>User</td><td>Edit</td><td>Send Email</td></tr>';

					$bg = '#eeeeee'; // Set the initial background color.

					//fetch and print all records
					while ($row = mysqli_fetch_array($r, MYSQLI_ASSOC)) {
						$bg = ($bg=='#eeeeee' ? '#ffffff' : '#eeeeee');

						echo '<tr bgcolor="' . $bg . ' "><td>'. $row['service_request_id'].'</td><td>'.$row['category_name'].'</td><td>'.$row['description'].'</td>
						<td>'. $row['priority_name'].'</td><td>'.$row['status_type'].'</td><td>'.$row['date_time'].'</td>
						<td>'. $row['User'].'</td><td><a href="editticket.php?id='.$row['service_request_id'].'">Edit</a></td>
						<td><a href="emailticket.php?id='.$row['service_request_id'].'">Send Email</a></td></tr>';
					}

					echo '</table>';
					echo '<p></p>';

					echo '<form action ="search.php"><input type="submit" name="submit" value="Search Again" /></form>';
					echo '<p></p>';
					mysqli_free_result ($r);

				} else {

					//If query result is 0	
					echo '<p>No result found. Please try your search again.</p>';
					echo '<p></p>';
					echo '<form action ="search.php"><input type="submit" name="submit" value="Search Again" /></form>';



				}

			} else {

				// If query did not run successfully, dump error message
				echo '<p>' . mysqli_error($dbc) . '<br /><br />Query: ' . $q . '</p>';
			}
				
		//if "error array" is not empty
		} else { 
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