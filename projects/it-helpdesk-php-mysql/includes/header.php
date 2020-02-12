<!DOCTYPE html>

<html>

<head>
 <title>IT Helpdesk App</title>
 <meta charset="UTF-8">
 <link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
</head>

<body>

<div class="topline"> </div>


		
<div class="header">
	<h3>Company Name</h3>
	<h1>IT Helpdesk App</h1>
</div>


<div class="loggedinuser"> 


<?php 


if (isset($_SESSION['user_id'])) {
	print ("Logged in as: " . $_SESSION['username']); 
}

?>


</div>


<div class="navbar">
	<ul>
		<li><a href="index.php"><button class="dropbtn">Home</button></a></li>

		<div class="dropdown">
			 <button class="dropbtn">Tickets</button>
			 <div class="dropdown-content">
			    <a href="alltcks.php">View All Tickets</a>
			    <a href="opentcks.php">View Opened Tickets</a>
			    <a href="pendtcks.php">View Pending Tickets</a>
			    <a href="addtck.php">Create a New Ticket</a>
			  </div>
		</div>

		<li><a href="search.php"><button class="dropbtn">Search</button></a></li>

		<div class="dropdown">
			 <button class="dropbtn">Options</button>
			 <div class="dropdown-content">
			 	<a href="viewusers.php">View/edit Users</a>
			 	<a href="adduser.php">Add a new User</a>
			    <a href="viewunits.php">View/edit Departments</a>
			    <a href="addunit.php">Add a new Department</a>	
			 	<a href="viewctgs.php">View/edit Ticket Catagories</a>
			    <a href="addctg.php">Add a new Ticket Category</a>
			    <a href="logout.php">Log off</a>
			    
			  </div>
		</div>
		
	</ul>

</div>