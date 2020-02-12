
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

	<h4>Add a new Ticket</h4>

		<form action="addtckx.php" method="post">
			<table>
				<tr><td>Request Details:</td><td><textarea name="description" rows="3" cols="35"> </textarea></td></tr>
				
				<tr></tr>

				<tr><td>Email Address of Requester:</td><td><input type="email" name="email_address" size="47" maxlength="50" required /></td></tr>
				<tr><td></td><td align="right"><input type="submit" name="submit" value="Submit"></td></tr>
			</table>
		</form>


	</div>

	<div class="footer">
		<div class="footer-text">  IT Helpdesk. All Rights Reserved.  &copy; 2018</div>
	</div>

</body>

</html>