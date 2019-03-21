<?php
	/* ===== www.findyourspot.com ===== */

	 include_once "koneksi.php";

	 class usr{}
	
	 $username = $_POST["username"];
	 $password = $_POST["password"];
	
	 if ((empty($username)) || (empty($password))) { 
	 	$response = new usr();
	 	$response->success = 0;
	 	$response->message = "You need to enter an username and a password"; 
	 	die(json_encode($response));
	}
	
	 $query = mysqli_query($con, "SELECT * FROM users WHERE username='$username' AND password='$password'");
	
	 $row = mysqli_fetch_array($query);
	
	 if (!empty($row)){
	 	$response = new usr();
	 	$response->success = 1;
	 	$response->message = "Login successful ".$row['username'];
	 	$response->id = $row['id'];
	 	$response->username = $row['username'];
		
		$response->pseudo = $row['pseudo'];
		$response->dateofbirth = $row['dateofbirth'];
		$response->firstname = $row['firstname'];
		$response->lastname = $row['lastname'];
		$response->photoprofil = $row['photoprofil'];
		
	 	die(json_encode($response));
		
	 } else { 
	 	$response = new usr();
	 	$response->success = 0;
	 	$response->message = "Username or password wrong";
	 	die(json_encode($response));
	 }
	
	 mysqli_close($con);

?>