<?php

$host="127.0.0.1";
$port=3306;
$socket="";
$user="root";
$password="465a7dhn";
$dbname="hotel_management_system";

try {
  $dbh = new PDO("mysql:host={$host};port={$port};dbname={$dbname}", $user, $password);
} catch (PDOException $e) {
    echo 'Connection failed: ' . $e->getMessage();
}

$location = "D:/Users/Bavie/School/Second Semester/Third Quarter/4 Computer Programming 3 (Intermidiate Java Programming)/Activities/ILS/Coding/Website/";

?>