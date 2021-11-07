<?php
    $location = "D:/Users/Bavie/School/Second Semester/Third Quarter/4 Computer Programming 3 (Intermidiate Java Programming)/Activities/ILS/Coding/Website/";
    include("$location/files/php/database.php");
    session_start();

    $guest_ref = $_SESSION['user-id'];
    $_SESSION['query'] = null;

    
?>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="/files/images/logo.png" type="image/x-icon">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'>
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="/files/css/user-bookings.css">
        <link rel="stylesheet" href="/files/css/general.css">
        <title>HMS | Bookings</title>
    </head>
    <body class = "body">
        <header class = "header">
            <img src="/files/images/logo.png" alt="logo" class="logo">
            <ul class="nav">
                <li><a href="/files/html/home.php">HOME</a></li>
                <li><a href="/files/html/user-bookings.php">MY BOOKINGS</a></li>
                <li><a href="/files/html/contact.php">CONTACT US</a></li>
                <li><a href="/files/html/terms_conditions.html">TERMS AND CONDITIONS</a></li>
                <li><a href="/files/html/account.php">ACCOUNT</a></li>
            </ul>
        </header>
        <main class="main">
            <div class="page-head">
                <h1>Bookings</h1>
                <form action="#" method="post" name="filter_form" id="filter_form" class="filter-form" enctype="multipart/form">
                    <select name="filter_option" id="filter_option" class="filter-option">
                        <option value="All Bookings">All Bookings</option>
                        <option value="Pending">Pending</option>
                        <option value="Cancelled">Cancelled</option>
                        <option value="Cancelling">Cancelling</option>
                        <option value="In the Future">In the Future</option>
                        <option value="Completed">Completed</option>
                    </select>
                    <button type="submit" name="filter" class="filter-btn">FILTER</button>
                </form>
            </div>
            <?php

                if(isset($_POST['filter'])) {
                    extract($_POST);

                    echo '<script>
                            document.querySelector(".filter-option").value = "'.$filter_option.'";
                        </script>';

                    $date = date("Y/m/d");
                    
                    if($filter_option == 'Pending'){
                        $_SESSION['query'] = "select * from transaction where guest_ref = $guest_ref && status = 'Pending' order by confirmed desc, check_in asc";
                    }else if($filter_option == 'All Bookings'){
                        $_SESSION['query'] = "select * from transaction where guest_ref = $guest_ref order by confirmed desc, check_in asc";
                    }else if($filter_option == 'Cancelled'){
                        $_SESSION['query'] = "select * from transaction where guest_ref = $guest_ref && (status = 'Cancelled' || status = 'Cancelled by Staff') order by confirmed desc, check_in asc";
                    }else if($filter_option == 'Cancelling'){
                        $_SESSION['query'] = "select * from transaction where guest_ref = $guest_ref && status = 'Cancelling' order by confirmed desc, check_in asc";
                    }else if($filter_option == 'In the Future'){
                        $_SESSION['query'] = "select * from transaction where guest_ref = $guest_ref && !(check_in <= '$date') order by confirmed desc, check_in asc";
                    }else if($filter_option == 'Completed'){
                        $_SESSION['query'] = "select * from transaction where guest_ref = $guest_ref && status = 'Completed';";
                    }
                }     


            ?>
            <div class="booking-carousel">
                <?php

                    if($_SESSION['query'] == null){
                        $query = "select * from transaction where guest_ref = $guest_ref order by confirmed desc, check_in asc";
                    }else {
                        $query = $_SESSION['query'];
                    }

                    
                    $stmt = $dbh -> query($query);

                    while($row = $stmt -> fetch()){
                        echo bookings($row['number'], $row['type'], $row['company'], $row['check_in'], $row['check_out'], $row['status'] , $row['id']);
                    }


                ?>
                
            </div>

        </main>
        <footer>
            <img src="/files/images/logo.png" alt="logo">
            <p>© All Rights Reserved | 2021</p>
        </footer>
    </body>
    <?php
  

        if(isset($_GET['view_booking'])){
            extract($_GET);
            
            $query = "select * from transaction where id = $transaction_id";
            $stmt = $dbh -> query($query);
            $row = $stmt -> fetch();


            $_SESSION['room_id'] = $row['id'];
            $_SESSION['room_number'] = $row['number'];
            $_SESSION['room_type'] = $row['type'];
            $_SESSION['room_company'] = $row['company'];
            $_SESSION['room_check_in'] = $row['check_in'];
            $_SESSION['room_check_out'] = $row['check_out'];
            $_SESSION['room_package'] = $row['services'];
            $_SESSION['room_total'] = $row['total'];
            $_SESSION['room_down_payment'] = $row['paid'];
            $_SESSION['room_balance'] = $row['balance'];
            $_SESSION['room_confirm'] = $row['confirmed'];
            $_SESSION['room_status'] = $row['status'];
            $_SESSION['room_notes'] = $row['notes'];


            echo '
            
                    <div class="booking-pop-up">
                <form action="#" method="post" class="booking-full" name="booking_full" id="booking_full" enctype ="multipart/form">
                    <i class="fas fa-times close" ></i>
                    <input type="text" name="pop_room_number" id="pop_room_number" class="pop-room-number" readonly value = "'.$_SESSION['room_number'].'"><br>
                    <input type="text" name="pop_room_type" id="pop_room_type" class="pop-room-type" readonly value = "'.$_SESSION['room_type'].'"><br>
                    <input type="text" name="pop_room_package" id="pop_room_company" class="pop-room-company" readonly value = "'.$_SESSION['room_company'].'"><br>
                    <div class="info">
                        <div class="booking-info">

                            <h2>Booking Information</h2>

                            <label for="pop_room_check_in">Check In: </label>
                            <input type="text" name="pop_room_check_in" id="pop_room_check_in" class="pop-room-check-in" readonly value = "'.$_SESSION['room_check_in'].'"><br>
                            
                            <label for="pop_room_check_out">Check Out: </label>
                            <input type="text" name="pop_room_check_out" id="pop_room_check_out" class="pop-room-check-out" readonly value = "'.$_SESSION['room_check_out'].'"><br>

                            <label for="pop_room_package">Package: </label>
                            <input type="text" name="pop_room_package" id="pop_room_package" class="pop-room-package" readonly value = "'.$_SESSION['room_package'].'"><br>
                        </div>
                        <div class="payment-info">

                            <h2>Payment Information</h2>

                            <label for="pop_room_total">Total: </label>
                            <input type="text" name="pop_room_total" id="pop_room_total" class="pop-room-total" readonly value = "'.$_SESSION['room_total'].'"><br>

                            <label for="pop_room_down_payment">Down Payment: </label>
                            <input type="text" name="pop_room_down_payment" id="pop_room_down_payment" class="pop-room-down-payment" readonly value = "'.$_SESSION['room_down_payment'].'"><br>

                            <label for="pop_room_balance">Balance: </label>
                            <input type="text" name="pop_room_balance" id="pop_room_balance" class="pop-room-balance" readonly value = "'.$_SESSION['room_balance'].'"><br>
                        </div>
                    </div>

                    <label for="pop_room_status">Status: </label>
                    <input type="text" name="pop_room_status" id="pop_room_status" class="pop-room-status" readonly value = "'.$_SESSION['room_status'].'"><br>
                    <textarea form="booking_full" name="pop_room_notes" id="pop_room_notes" class="pop-room-notes" readonly>'.$_SESSION['room_notes'].'</textarea><br>
                    <button type="submit" class="cancel-booking" name ="cancel_booking">CANCEL RESERVATION</button>
                </form>
            </div>
            
            ';

            echo '<script>
            document.querySelector(".booking-pop-up").classList.add("active");
            console.log("button click")
            document.querySelector(".main").style.filter = "blur(20px)";
            document.querySelector(".header").style.filter = "blur(20px)";
            document.querySelector(".body").style.overflow = "hidden";
        </script>';


            
        }

    ?>
    <?php
        
        
        if(isset($_POST['cancel_booking'])){
            extract($_POST);

            if($_SESSION['room_confirm'] == 'No'){

                $room_id = $_SESSION['room_id'];

                $query = "update transaction set status = 'Cancelled' where id = $room_id";
                $stmt = $dbh -> query($query);

                echo '<script>   
                    alert("Your booking is cancelled as the reservation is not yet confirmed. Your reservation fee is refunded. Thank you.") 
                    window.location.replace("http://localhost:3000/files/html/user-bookings.php"); 
                
                </script>';

            }else {

                $date_of_cancellation = date("Y/m/d");
                $room_check_in = $_SESSION['room_check_in'];
                $date_difference = (new DateTime($date_of_cancellation))->diff(new DateTime($room_check_in))->days;

                if($date_difference < 0 || $date_difference < 7){

                    $room_id = $_SESSION['room_id'];

                    $query = "update transaction set status = 'Cancelling' where id = $room_id";

                    echo '<script>console.log("'.$query.'")</script>';
                    $stmt = $dbh -> query($query);

                    echo '<script>
                    
                           alert("Your booking cancellation will be reviewed as the reservation is already confirmed. In addition, if cancellation occured 7 days before of check in or after the check in date, minimum reservation fee will be charged. Thank you.")

                           window.location.replace("http://localhost:3000/files/html/user-bookings.php"); 
                    
                   </script>';




                } else {

                    $room_id = $_SESSION['room_id'];

                    $query = "update transaction set status = 'Cancelling' where id = $room_id";
                    $stmt = $dbh -> query($query);

                    echo '<script>
                    
                            alert("Your booking cancellation will be reviewed as the reservation is already confirmed. If the cancellation is confirmed, reservation will be refunded. Thank you.")

                            window.location.replace("http://localhost:3000/files/html/user-bookings.php"); 
                    
                    
                    </script>';

                }
            }

           

        }


        function bookings($room_number, $room_type, $room_company, $room_check_in, $room_check_out, $room_status, $transaction_id){
            $bookings_form = '
                <form action="#" method="get" enctype = "multipart/form" class="bookings">
                <input type="text" name="room_number" id="room_number" class="room-number" value="'.$room_number.'"><br>
                <input type="text" name="room_type" id="room_type" class="room-type" value="'.$room_type.'"><br>
                <input type="text" name="room_company" id="room_company" class="room-company" value="'.$room_company.'"><br>

                <label for="room_check_in">Check in: </label>
                <input type="text" name="room_check_in" id="room_check_in" class="room-check-in" value="'.$room_check_in.'"><br>
                <label for="room_check_out">Check out: </label>
                <input type="text" name="room_check_out" id="room_check_out" class="room-check-out" value="'.$room_check_out.'"><br>

                <input type="text" name="room_status" id="room_status" class="room-status" value="'.$room_status.'"><br>

                <input type="hidden" name="transaction_id" value="'.$transaction_id.'">
                <button type="submit" class="view-booking" name ="view_booking" >VIEW</button>
            </form>
            
            ';

            echo $bookings_form;
        }


    ?>
    <script>
        const exit_button = document.querySelector(".close");
        const booking_pop_up = document.querySelector(".booking-pop-up");
        const main = document.querySelector(".main");
        const body = document.querySelector(".body");
        const header = document.querySelector(".header");

        function showPopUp() {
            
           
        }

        exit_button.addEventListener("click", function() {
            booking_pop_up.classList.remove("active");
            console.log("exit click")
            main.style.filter = "blur(0px)";
            header.style.filter = "blur(0px)";
            body.style.overflow = "auto";
        });


    </script>

</html>