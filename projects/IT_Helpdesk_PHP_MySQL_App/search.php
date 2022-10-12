
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

	<h3>Search for a ticket </h3>

	<form action="searchx.php" method="post">

		<table>
			<tr><td>Enter keyword(s): </td>
				<td><input type="text" name="search" size="60" maxlength="60"></td>
				<td><input type="submit" name="submit" value="Search"></td>
			</tr>
		</table>
		
	</form>



</div>

<div class="footer">

	<div class="footer-text">  IT Helpdesk. All Rights Reserved.  &copy; 2018</div>
	
</div>


</body>
</html>