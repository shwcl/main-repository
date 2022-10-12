
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

	<h3>Edit Ticket details</h3>

	<?php 

		//check for valid service requests ID through GET or POSt

		if ( (isset($_GET['id'])) && (is_numeric($_GET['id'])) ) {
			$id = $_GET['id'];
		} elseif ( (isset($_POST['id'])) && (is_numeric($_POST['id'])) ) {
			$id = $_POST['id'];
		} else { //no valid id, kill the script
			echo 'This page has been accessed in error';
			exit();
		}

	 	// Retrieve the service request information

		require_once('includes/mysqli_connect.php');

		$q = "SELECT service_request.service_request_id, service_request.category_id, category.category_name, service_request.description, service_request.priority_id, priority.priority_name, service_request.status_type_id, status_type.status_type, service_request.employee_id, CONCAT(employee.first_name,' ',employee.last_name) as User, service_request.date_time FROM service_request LEFT JOIN category on service_request.category_id = category.category_id LEFT JOIN priority on service_request.priority_id = priority.priority_id LEFT JOIN status_type on service_request.status_type_id = status_type.status_type_id LEFT JOIN employee on service_request.employee_id = employee.employee_id WHERE service_request_id = $id";

		$r = @mysqli_query($dbc, $q);

		if (mysqli_num_rows($r) == 1) {
			$row = mysqli_fetch_array($r, MYSQLI_ASSOC);
		
			// Create the Form
			echo '<form action="edittckx.php" method="post"> 

			<table>

			<tr><td>Service Request ID</td><td><input type="text" name="service_request_id" value="' . $row['service_request_id'] . '" size="20" maxlength="20" readonly /></td></tr>

			<tr></tr>

			<tr><td>Category</td><td><select name="category_id"><option selected value="' . $row['category_id'] . '">'. $row['category_name'] . '</option>';
		
			$q2 = "SELECT category_name  FROM category ORDER BY category_id ASC";
			$r2 = @mysqli_query($dbc, $q2);

			while ($rw = mysqli_fetch_array($r2, MYSQLI_ASSOC)) {
				$cat_name[] = $rw['category_name'];
			}

			$count = 0;
			foreach ($cat_name as $value) {
				$count = $count + 1;
			echo '<option value="' . $count . '">'.$value.'</option>';
			echo $count;
			}
			echo  '</select></td></tr>';

			echo '<tr></tr>';

			echo '<tr><td>Problem description</td><td><textarea name="description" rows="3" cols="40">'.$row['description'].'</textarea></td></tr>';

			echo '<tr></tr>';

			echo '<tr><td>Priority</td><td><select name="priority_id"><option selected value="'.$row['priority_id'].'">'.$row['priority_name'].'</option>
			<option value="1">High</option>
			<option value="2">Normal</option>
			<option value="3">Low</option></select><td></tr>';

			echo '<tr></tr>';

			echo '<tr><td>Status</td><td><select name="status_type_id"><option selected value="'.$row['status_type_id'].'">'.$row['status_type'].'</option>
			<option value="1">Not started </option>
			<option value="2">In progress</option>
			<option value="3">Completed</option>
			</select></td></tr>';

			echo '<tr></tr>';

			echo '<tr><td>Date Submitted</td><td><input type="text" name="date" value="'. $row['date_time'] . '" size = "20" maxlength ="20" readonly /></td></tr>'; 

			echo '<tr></tr>';
			echo '<tr><td>User</td><td><input type="text" name="user" value="' . $row['User'] . '" size="20" maxlength ="20" readonly /></td></tr>'; 

			echo '</table>';

			//hidden input type to pass $id to form handler

			echo '<input type="hidden" name="id" value="' . $id . '" />'; 

			echo '<p><input type="submit" name="submit" value="Submit" /></p></form>';

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