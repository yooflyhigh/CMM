<?php
    header("Content-Type: text/html;charset=UTF-8");
    $conn = mysqli_connect("localhost","root","123456","mydb");

	$data_stream = "'".$_POST['CLUB_ID']."','".$_POST['STUDENT_ID']."','".$_POST['Order']."'";
	//$pattern = ",";

	$DataArr = split(",",$money);
		
	$query = "update club_member 
	set JOIN_CD = $DataArr[2] 
	where CLUB_ID = $DataArr[0] and STUDENT_ID = $DataArr[1] and JOIN_CD = '3';";
	
	//$query = "insert into CLUB_MEMBER value($data_stream,'모모모','빅데이터','4','1','004001','01012341234','123456','춘천','OK','1234@naver.com','20190512','550808','005001','$Order',' ',' ','20190512',' ',' ','20190512');";
		
	$result = mysqli_query($conn, $query);
    	 
    if($result)
      echo 1;
    else
      echo -1;
     
    mysqli_close($conn);
?>