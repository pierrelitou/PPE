<?php 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		$id = $_POST['iduser'];
		$idactivity = $_POST['idactivity'];
		
		
		$sql = "INSERT INTO participate (iduser, idactivity) VALUES('$id','$idactivity')";

		require_once('dbConnect.php');
		
		if(mysqli_query($con,$sql)){
			echo " Successfully registered";
		}else{
			echo ' You are already registered';
		}
		
		mysqli_close($con);
	}