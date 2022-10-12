
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

	<h3>Edit User details</h3>

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

		$q = "SELECT first_name, last_name,tel_no, tel_no_ext, email, employee.unit_id, unit.unit_name
		FROM employee 
		JOIN unit
		ON employee.unit_id = unit.unit_id
		WHERE employee_id = $id";

		$r = @mysqli_query($dbc, $q);

		//error handling

		if($r) { //Eroror handling: if query ran ok


			if (mysqli_num_rows($r) == 1) {  // If valid user_id show the form

				$row = mysqli_fetch_array($r, MYSQLI_ASSOC);
			
				// Create the Form
				echo '<form action="edituserx.php" method="post"> 

				<table>

				<tr><td>First Name</td><td><input type="text" name="first_name" value="' . $row['first_name'] . '" size="30" maxlength="30"/></td></tr>

				<tr></tr>

				<tr><td>Last Name</td><td><input type="text" name="last_name" value="' . $row['last_name'] . '" size="30" maxlength="30"/></td></tr>

				<tr></tr>

				<tr><td>Telephone No</td><td><input type="text" name="tel_no" value="' . $row['tel_no'] . '" size="15" maxlength="15"/></td></tr>

				<tr></tr>

				<tr><td>telephone No Extension:</td><td><input type="text" name="tel_no_ext" value="' . $row['tel_no_ext'] . '" size="15" maxlength="15"/></td></tr>

				<tr></tr>

				<tr><td>Email Address</td><td><input type="email" name="email" value="' . $row['email'] . '" size="30" maxlength="30"/></td></tr>

				<tr></tr>

				<tr><td>Unit</td><td><select name="unit_id"><option selected value="' . $row['unit_id'] . '">'. $row['unit_name'] . '</option>';
		
				$q2 = "SELECT unit_name  FROM unit ORDER BY unit_id ASC";
				$r2 = @mysqli_query($dbc, $q2);

				while ($rw = mysqli_fetch_array($r2, MYSQLI_ASSOC)) {
					$cat_name[] = $rw['unit_name'];
				}

				$count = 0;
				foreach ($cat_name as $value) {
					$count = $count + 1;
				echo '<option value="' . $count . '">'.$value.'</option>';
				echo $count;
				}
				echo  '</select></td></tr>';



				echo '</table>';

				//hidden input type to pass $id to form handler page edituserx.php
				echo '<input type="hidden" name="id" value="' . $id . '" />'; 

				echo '<p><input type="submit" name="submit" value="Submit" /></p></form>';

			} else {
				//Not a valid user id 	
				echo 'This page was accessed incorrectly';
			}

		} else {

			//Error handling: If query did not run ok
			echo 'An error occured.';
			echo '<p>' . mysqli_error($dbc). '<br></br>' . 'Query: ' . $q . '</p>'; 

		}

	?>




</div>

<div class="footer">

	<div class="footer-text">  IT Helpdesk. All Rights Reserved.  &copy; 2018</div>

</div>	

</body>
</html>