<?php

include('includes/header.php');

?>





<div class="container">

<?php 

	echo '<h3>Edit User</h3>';

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

		$q = "SELECT first_name, last_name, email_address, password FROM users WHERE user_id = $id";

		$r = @mysqli_query($dbc, $q);

		//error handling

		if($r) { //Eroror handling: if query ran ok


			if (mysqli_num_rows($r) == 1) {  // If valid user_id show the form

				$row = mysqli_fetch_array($r, MYSQLI_ASSOC);
			
				// Create the Form
				echo '<form action="edituserx.php" method="post"> 

				  <div class="form-group row">
				    <label for="inputEmail3" class="col-sm-2 col-form-label">First Name</label>
				    <div class="col-sm-4">
				      <input type="text" name="first_name" value="' . $row['first_name'] . '" class="form-control" id="inputEmail3" placeholder="First Name">
				    </div>
				  </div>

				  <div class="form-group row">
				    <label for="inputPassword3" class="col-sm-2 col-form-label">Last Name</label>
				    <div class="col-sm-4">
				      <input type="text" name="last_name" value="' . $row['last_name'] . '" class="form-control" id="inputPassword3" placeholder="Last Name">
				    </div>
				  </div>


				  <input type="hidden" name="id" value="' . $id . '" />

				  <div class="form-group row">
				    <div class="col-sm-10">
				      <button type="submit" class="btn btn-primary">Submit</button>
				    </div>
				  </div>

				</form>';

;

			} else {
				//Not a valid user id 	
				echo 'This page was accessed incorrectly';

			} 
		} else {

			//if query did not run ok
			echo 'An error occured.';
			echo '<p>' . mysqli_error($dbc). '<br></br>' . 'Query: ' . $q . '</p>'; 
		}

?>




</div>

</body>
</html>