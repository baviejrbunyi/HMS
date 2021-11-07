<?php

session_start();
include("database.php");

$general = $_GET['general'];
$checkIn = $_GET['check-in'];
$checkOut = $_GET['check-out'];

if($general == null && $checkIn != null && $checkOut != null){
    echo $checkIn . $checkOut;;
}else if ($general != null && $checkIn == null && $checkOut == null){
    echo $general;
}else {
    echo 'edi waw';
}


function filterGeneral($general){
    $query = 'select * from room where type = '.$general.' || address = '.$general.'';
    $stmt = $dbh -> query($query);
    
}

function filterDate($checkIn, $checkOut){

}

function filter($general, $checkIn, $checkOut){

}

?>