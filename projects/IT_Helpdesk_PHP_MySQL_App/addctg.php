
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
	
	<h4>Add a new Category</h4>

	<form action="addctgx.php" method="post">
		<table>	
			<tr>	
				<td>Category Name:</td>
				<td><input type="text" name="cat_name" size="30" maxlength="30" required /></td>	
			</tr> 	

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

