<!DOCTYPE html>

<html>

<head>
 <title>ICT Helpdesk</title>
 <meta charset=utf-"8">
 <link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
</head>

<body>

<h2> IT Helpdesk</h2>
<h3> Adding a Service Request...</h3>

<?php 

	require_once('includes/mysqli_connect.php');

//initialize an errors array
	$errors = array();

	if ($_SERVER['REQUEST_METHOD'] =='POST') {
		
		$to = 'shw_cl@hotmail.com';
		$subject = 'Service Request';
		$body = wordwrap('this is a test mail', 70);
		$from = 'From: test@localhost.com';

		mail($to, $subject,$body, $from);
		echo 'Thank you. Your email was sent.';

	} else {

		echo '<p>Please click submit button</p>';

	}

?>

<form action="email.php" method="post">

<input type="submit" name="submit" value="Send Email" />	




</body>
</html>