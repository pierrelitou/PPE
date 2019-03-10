<?php 
	//Importing Database Script 
	require_once('dbConnect.php');
	
	//Creating sql query
	$sql = "SELECT firstname,lastname,dateofbirth,pseudo FROM `users`";
	//getting result SELECT firstname,lastname,dateofbirth,pseudo FROM `users`
	//SELECT firstname,lastname,dateofbirth,pseudo,namep FROM `userpreference`,`users`,`preference` WHERE users.mail=userpreference.mailuser AND userpreference.idpref=preference.idpreference 
	$r = mysqli_query($con,$sql);
	
	//creating a blank array 
	$result = array();
	
	//looping through all the records fetched
	while($row = mysqli_fetch_array($r)){
		
		//Pushing name and id in the blank array created 
		array_push($result,array(
			"firstname"=>$row['firstname'],
			"lastname"=>$row['lastname'],
			"dateofbirth"=>$row['dateofbirth'],
			"pseudo"=>$row['pseudo']
		));
	}
	
	//Displaying the array in json format 
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);
