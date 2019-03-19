<?php 
	//Importing Database Script 
	require_once('dbConnect.php');
	
	//Creating sql query
	$sql = "SELECT Lat, Lng, nameactivity, description, dateevent  FROM `activity`";
	//getting result 
	$r = mysqli_query($con,$sql);
	
	//creating a blank array 
	$result = array();
	
	//looping through all the records fetched
	while($row = mysqli_fetch_array($r)){
		
		//Pushing name and id in the blank array created 
		array_push($result,array(
			"Lat"=>$row['Lat'],
			"Lng"=>$row['Lng'],
			"nameactivity"=>$row['nameactivity'],
			"description"=>$row['description'],
			"dateevent"=>$row['dateevent']
		));
	}
	
	//Displaying the array in json format 
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);
