<?php 

include('includes/header.php');
?>


<div class="container">

	<h4>Send email notification</h4>

	<?php 

		$to = $_POST['to'];
		$body = $_POST['body'];
		$subject = 'IT Helpdesk - Ticket update notification';
		
		
		$fcall = @mail('jsmith@localhost.com', $subject, $body, 'From: jsmith@localhost.com');

		if ($fcall = 1) {

			echo 'Error sending email.';
			echo '<p>Warning: Failed to connect to mailserver, verify your mail server settings. </p>';
			
		} else {
			Echo 'Your email was successfully sent to ' . $to; 
		}
	?>

</div>

<div class="footer">

	<div class="footer-text">  IT Helpdesk. All Rights Reserved.  &copy; 2018</div>

</div>	

</body>
</html>