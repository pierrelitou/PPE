<?php
	
	define('HOST','localhost');
	define('USER','root');
	define('PASS','root');
	define('DB','findyourspot2');
	
	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');