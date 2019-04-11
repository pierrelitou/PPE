<?php
/*
/* ===== www.findyourspot.com ===== */
	
	 include_once "koneksi.php";

	 class usr{}

	 $username = $_POST["username"];
	 $password = $_POST["password"];
	 $confirm_password = $_POST["confirm_password"];
	 $dof =$_POST["dateofbirth"];
	 $pseudo=$_POST["pseudo"];
	 $firstname=$_POST["firstname"];
	 $lastname=$_POST["lastname"];


	 if ((empty($username))) {
	 	$response = new usr();
	 	$response->success = 0;
	 	$response->message = "You need to enter an username";
	 	die(json_encode($response));
	 } else if ((empty($password))) {
	 	$response = new usr();
	 	$response->success = 0;
	 	$response->message = "You need to enter a password ";
	 	die(json_encode($response));
	 } else if ((empty($confirm_password)) || $password != $confirm_password) {
	 	$response = new usr();
	 	$response->success = 0;
	 	$response->message = "You confirmation password does not match or is empty";
	 	die(json_encode($response));
	} else {
		 if (!empty($username) && $password == $confirm_password){
		 	$num_rows = mysqli_num_rows(mysqli_query($con, "SELECT * FROM users WHERE username='".$username."'"));

		 	if ($num_rows == 0){
		 		$requete="INSERT INTO users(`username`, `pseudo`, `password`, `firstname`, `lastname`, `dateofbirth`) VALUES('".$username."','".$pseudo."','".$password."','".$firstname."','".$lastname."','".$dof."')";
		 		$query = mysqli_query($con, $requete); 
		 		if ($query){
		 			$response = new usr();
		 			$response->success = 1;
		 			$response->message = "Register successful, you could login.";
		 			die(json_encode($response));

		 		} else {
		 			$response = new usr();
		 			$response->success = 0;
		 			$response->message = "Username error";
		 			die(json_encode($response));
		 		}
		 	} else {
		 		$response = new usr();
		 		$response->success = 0;
		 		$response->message = "Username already exists";
		 		die(json_encode($response));
		 	}
		 }
	 }

	 mysqli_close($con);

?>	