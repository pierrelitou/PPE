<?php
	/* ===== findyourspot ===== */
	$server		= "localhost"; 
	$user		= "root"; 
	$password	= ""; 
	$database	= "findyourspot"; 
	//$database	= "kuncoro_login"; 
	
	$con = mysqli_connect($server, $user, $password, $database);
	if (mysqli_connect_errno()) {
		echo "Error MySQL: " . mysqli_connect_error();
	}

?>