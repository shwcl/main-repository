

<?php

include('includes/header.php');

?>



<div class="container">


<?php 

	echo '<h4>Delete User</h4>';
	echo '<br />';

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
			
				//Display record to be deleted
				echo  'Are you sure you want to delete the user: <b>' . $row['first_name'].' '.$row['last_name'].'</b>?';
				
				echo '<br> </br>';

				// Create the Form .. create a hidden form element so that it is passed on to the form handling page when the form is submitted.
				echo'							
				<form action="deleteuserx.php" method="post">
					<div class="form-check form-check-inline">
				    	<input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="yes">
				    	<label class="form-check-label" for="inlineRadio1">Yes</label>
			     	</div>

				    <div class="form-check form-check-inline">
				    	<input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="no">
				    	<label class="form-check-label" for="inlineRadio2">No</label>
				    </div>

				    <input type="hidden" name="id" value="' . $id . '" />

				    <br> </br>

				    <div class="form-group row">
				     <div class="col-sm-10">
				       <button type="submit" class="btn btn-primary">Submit</button>
				     </div>
				    </div>

				</form>';

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