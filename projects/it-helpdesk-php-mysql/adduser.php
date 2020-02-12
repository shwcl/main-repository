
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

	<h4>Add a new User</h4>

	<form action="adduserx.php" method="post">

		<table>	

			<tr>	
				<td>First Name:</td>
				<td><input type="text" name="first_name" size="30" maxlength="30" required /></td>	
			</tr> 	

			<tr></tr>

			<tr>	
				<td>Last Name:</td> 
				<td> <input type="text" name="last_name" size="30" maxlength="30" required /></td> 
			</tr>

			<tr></tr>

			<tr>	
				<td>Telephone No:</td> 
				<td> <input type="text" name="tel" size="30" maxlength="30" /></td> 
			</tr>

			<tr></tr>

			<tr>	
				<td>Telephone Extension: </td> 
				<td> <input type="text" name="tel_ext" size="30" maxlength="30" / ></td> 
			</tr>

			<tr></tr>

			<tr><td>Email Address:</td>
				<td><input type="email" name="email_address" size="47" maxlength="50" required /></td>
			</tr>

			<tr></tr>

			<tr><td>Unit:</td>
				<td><select name="unit_id"><option selected value=""></option>

				<?php
					require_once('includes/mysqli_connect.php');
					$q = "SELECT unit_name  FROM unit ORDER BY unit_id ASC";
					$r = @mysqli_query($dbc, $q);
					while ($row = mysqli_fetch_array($r, MYSQLI_ASSOC)) {
						$unit_name[] = $row['unit_name'];
					}

					$unit_id = 0;
					foreach ($unit_name as $value) {
						$unit_id = $unit_id + 1;
						echo '<option value="' . $unit_id . '">' . $value . '</option>';
						echo $count;
					}

					echo '</select>';
					echo '</td></tr>';

				?>

			<tr></tr>

			<tr>
				<td></td><td align="right"><input type="submit" name="submit" value="Submit"></td>
			</tr>
		</table>
	</form>

</div>	

<div class="footer">
		<div class="footer-text">  IT Helpdesk. All Rights Reserved.  &copy; 2018</div>
</div>

</body>

</html>