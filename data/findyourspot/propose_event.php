<?php
/*
/* ===== www.findyourspot.com ===== */
	
	 include_once "koneksi.php";

	 class usr{}

	 $nameactivity = $_POST["nameactivity"];
	 $location = $_POST["location"];
	 $description = $_POST["description"];
	 $dateevent= $_POST["dateevent"];
	 $proposeby=$_POST["proposeby"];
	 $EVENT="1";

	 if ((empty($nameactivity))) {
	 	$response = new usr();
	 	$response->success = 0;
	 	$response->message = "You need to enter a Title";
	 	die(json_encode($response));
	 } else if ((empty($location))) {
	 	$response = new usr();
	 	$response->success = 0;
	 	$response->message = "You need to enter a location ";
	 	die(json_encode($response));
	 } else if ((empty($dateevent))) {
	 	$response = new usr();
	 	$response->success = 0;
	 	$response->message = "You need to enter a date ";
	 	die(json_encode($response)); 
	 
	} else {
		
		 $query = mysqli_query($con, "INSERT INTO activity (nameactivity,location, description, dateevent,proposeby,EVENT) VALUES('".$nameactivity."','".$location."','".$description."','".$dateevent."','".$proposeby."','".$EVENT."')");

		 if ($query){
		 		$response = new usr();
		 		$response->success = 1;
		 		$response->message = "Submit successful";
		 		die(json_encode($response));

		 		} else {
		 			$response = new usr();
		 			$response->success = 0;
		 			$response->message = "Error while submitting";
		 			die(json_encode($response));
				}
		 
	 }

	 mysqli_close($con);

?>	