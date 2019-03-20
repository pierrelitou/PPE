<?php
	
	define('HOST','localhost');
	define('USER','root');
	define('PASS','');
	define('DB','findyourspot');
	
	
	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');
	if (mysqli_connect_errno()) {
		echo "Error MySQL: " . mysqli_connect_error();
	 }