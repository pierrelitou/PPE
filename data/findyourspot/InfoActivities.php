<?php 
	$id = $_GET['id'];
	
	require_once('dbConnect.php');
	
	$sql = "SELECT * FROM `activity` WHERE id=$id";
	$r = mysqli_query($con,$sql);
	
	$result = array();
	
	$row = mysqli_fetch_array($r);
	array_push($result,array(
			"id"=>$row['id'],
			"nameactivity"=>$row['nameactivity'],
			"description"=>$row['description'],
			"location"=>$row['location']
			
			/*"harga_barang"=>$row['harga_barang'],
			"jenis_barang"=>$row['jenis_barang']*/
		));

	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);