<?php


class Transaction {

    var $id;
    var $room_id;
    var $transaction_date;

    var $guest_ref;
    var $name;
    var $address;
    var $email;
    var $mobile;

    var $services;
    var $check_in;
    var $check_out;
    var $number;
    var $type;
    var $ref_id;

    var $method;
    var $total;
    var $paid;
    var $balance;

    var $status;
    var $confirmed = "No";


    //methods
    public function paymentInformation(array $payment_info) {

        $this -> method = $payment_info[0];
        $this -> total = $payment_info[1];
        $this -> paid = $payment_info[2];
        $this -> balance = $payment_info[3];      
    }

    public function bookingInformation(array $booking_info) {

        $this -> services = $booking_info[0];
        $this -> check_in = $booking_info[1];
        $this -> check_out = $booking_info[2];
        $this -> number = $booking_info[3];
        $this -> type = $booking_info[4];
        $this -> ref_id = $booking_info[5];

    }

    public function userInformation(array $user_info) {

        $this -> guest_ref = $user_info[0];
        $this -> name = $user_info[1];
        $this -> address = $user_info[2];
        $this -> email = $user_info[3];
        $this -> mobile = $user_info[4];

    }

   public function searchRoom($type, $check_in, $check_out, $ref_id){

        $location = "D:/Users/Bavie/School/Second Semester/Third Quarter/4 Computer Programming 3 (Intermidiate Java Programming)/Activities/ILS/Coding/Website/";
        include("$location/files/php/database.php");

        $query = "select * from room where type = '$type' && (('$check_in' not between occupied_from and occupied_to && $check_out not between occupied_from and occupied_to) || (occupied_from is null && occupied_to is null)) && ref_id = $ref_id;";
        $stmt = $dbh -> query($query);
        $row = $stmt -> fetch();

        $room_info = array($row['id'], $row['number']);

        return $room_info;

   }

   public function transactionCost($package_name, $ref_id, $room_cost, $no_of_days){
        $location = "D:/Users/Bavie/School/Second Semester/Third Quarter/4 Computer Programming 3 (Intermidiate Java Programming)/Activities/ILS/Coding/Website/";
        include("$location/files/php/database.php");

        $query = "select * from services where ref_id = $ref_id && name = '$package_name'";
        $stmt = $dbh -> query($query);
        $row = $stmt -> fetch();
        
        $package_cost = $row['rates'];
        $room_total_cost = $room_cost * $no_of_days;

        $transaction_cost = $package_cost + $room_total_cost;

        return $transaction_cost;

   }

   public function submitTransaction(array $user_info, array $booking_info, array $payment_info){
        $location = "D:/Users/Bavie/School/Second Semester/Third Quarter/4 Computer Programming 3 (Intermidiate Java Programming)/Activities/ILS/Coding/Website/";
        include("$location/files/php/database.php");

        $transaction_date = date("Y/m/d");

        $guest_ref = $user_info[0];
        $name = $user_info[1];
        $address = $user_info[2];
        $email = $user_info[3];
        $mobile = $user_info[4];

        $services = $booking_info[0];
        $check_in = $booking_info[1];
        $check_out = $booking_info[2];
        $number = $booking_info[3];
        $type = $booking_info[4];
        $ref_id = $booking_info[5];

        $method = $payment_info[0];
        $total = $payment_info[1];
        $paid = $payment_info[2];
        $balance = $payment_info[3];

        $confirmed = "No";

        try {
            
            $query = "insert into transaction 
                (ref_id, transaction_date, guest_ref, name, address, email, mobile, method, total, paid, balance, type, services, check_in, check_out, number, confirmed)
                values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            $stmt = $dbh -> prepare($query);
            $stmt -> execute(array($ref_id, $transaction_date, $guest_ref, $name, $address, $email, $mobile, $method, $total, $paid, $balance, $type, $services, $check_in, $check_out, $number, $confirmed));
           
        } catch (Exception $e) {
           echo '<script>alert("'.$e -> getMessage().'")</script>';
        }
   }

   public function setRoomAvailability($room_id, $check_in, $check_out){
        $location = "D:/Users/Bavie/School/Second Semester/Third Quarter/4 Computer Programming 3 (Intermidiate Java Programming)/Activities/ILS/Coding/Website/";
        include("$location/files/php/database.php");

        $query = "select * from room where id = $room_id";
        $stmt = $dbh -> query($query);

        while($row = $stmt -> fetch()){
            if($check_in < $row['occupied_from'] || $row['occupied_from'] == null){
                $query = "update room set occupied_from = '$check_in' where id = $room_id";
                $stmt = $dbh -> query($query);

            }else {
                echo '<script>console.log("occupied_from not set")</script>';
            }

            if($check_out > $row['occupied_to'] || $row['occupied_to'] == null){
                $query = "update room set occupied_to = '$check_out' where id = $room_id";
                $stmt = $dbh -> query($query);

            }else {
                echo '<script>console.log("occupied_to not set")</script>';
            }
        }


   }




}

?>


