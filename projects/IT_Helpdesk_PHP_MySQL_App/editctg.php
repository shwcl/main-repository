
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

	<h3>Edit Ticket Category details</h3>

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

		$q = "SELECT category_name FROM category WHERE category_id = $id";

		$r = @mysqli_query($dbc, $q);

		//error handling

		if($r) { //Eroror handling: if query ran ok


			if (mysqli_num_rows($r) == 1) {  // If valid category_id show the form

				$row = mysqli_fetch_array($r, MYSQLI_ASSOC);
			
				// Create the Form
				echo '<form action="editctgx.php" method="post"> 

				<table>

				<tr><td>Category Name</td><td><input type="text" name="category_name" value="' . $row['category_name'] . '" size="40" maxlength="40"/></td></tr>

				<tr></tr>';

				echo '</table>';

				//unit hidden input type to pass $id to form handler page editctgx.php
				echo '<input type="hidden" name="id" value="' . $id . '" />'; 

				echo '<p><input type="submit" name="submit" value="Submit" /></p></form>';

			} else {
				//Not a valid category id 	
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