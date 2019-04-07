<?php 
	//Importing Database Script 
	require_once('dbConnect.php');
	
	//Creating sql query
	$sql = "SELECT nameactivity,location,description,dateevent FROM `participate`,`activity`,`users` WHERE participate.iduser=users.id AND participate.idactivity=activity.id";
	//echo $sql;
	//getting result 
	$r = mysqli_query($con,$sql);
	
	//creating a blank array 
	$result = array();
	
	//looping through all the records fetched
	while($row = mysqli_fetch_array($r)){
		
		//Pushing name and id in the blank array created 
		array_push($result,array(
			//"id"=>$row['id'],
			"nameactivity"=>$row['nameactivity'],
			"location"=>$row['location'],
			"description"=>$row['description'],
			"dateevent"=>$row['dateevent'],
			//"//proposeby"=>$row['proposeby'],
			//"localproposer"=>$row['localproposer'],
			//"lienimg"=>$row['lienimg']
		));
	}
	
	//Displaying the array in json format 
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);
