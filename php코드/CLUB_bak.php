﻿<?php

$con=mysqli_connect("localhost","root","","mydb");

if(mysqli_connect_errno($con)){
   echo "Failed to connect to MySql :".mysqli_connect_error();
}

mysqli_query($con,"set names utf8");
$res= mysqli_query($con,"select * from CLUB");

$result = array();

while($row = mysqli_fetch_array($res)){
   array_push($result,array('CLUB_ID'=>$row[0],'CLUB_NM'=>$row[1],'CLUB_GB_CD'=>$row[2]));
}
echo json_encode(array("result"=>$result),JSON_UNESCAPED_UNICODE);

mysqli_close($con);
?>