<?php

include('includes/header.php');

?>


<div class="container">

																																																													   
<h4>Account login</h4>


<form action="loginx.php" method="post"> 

	<div class="form-group row">
			<label for="inputEmail3" class="col-sm-2 col-form-label">Email Address</label>
			<div class="col-sm-3">
				<input type="email" name="email_address" class="form-control" id="inputEmail3" placeholder="Email Address">
			</div>
	</div>

	<div class="form-group row">
			<label for="inputPassword3" class="col-sm-2 col-form-label">Password</label>
			<div class="col-sm-3">
				<input type="password" name="password" class="form-control" id="inputPassword3" placeholder="Password">
			</div>
	</div>

	<button type="submit" class="btn btn-primary">Submit</button>


	</form>

	<br />

	Don't have an account? Create an account <a href="registeruser.php">here</a>.



</div>

</body>
</html>