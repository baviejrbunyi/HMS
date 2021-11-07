<?php 

    session_start();
    $location = "D:/Users/Bavie/School/Second Semester/Third Quarter/4 Computer Programming 3 (Intermidiate Java Programming)/Activities/ILS/Coding/Website/";
    include("$location/files/php/database.php");
    include("$location/files/php/Transaction.php");




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
        <link rel="stylesheet" href="/files/css/booking.css">
        <link rel="stylesheet" href="/files/css/general.css">
        <title>HMS | Book Now</title>
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
        <main>
            <div class="hotel-info">
                <img src="<?php echo $_SESSION['room-image'] ?>" alt="hotel-image">
                <h2 class="room-type"><?php echo $_SESSION['room-type'] ?></h2>
                <div class="branch">
                    <h2 class="company-name"><?php echo $_SESSION['room-company'] ?></h2>
                    <h2 class="hotel-price">P <?php echo $_SESSION['room-price'] ?>.00</h2>
                </div>
                
                <p class="description">
                    <?php echo $_SESSION['room-details'] ?>
                </p>
            </div>

            <div class="booking-info">
                <form action="/files/html/booking.php" id = "book" name = "book" enctype = "multipart/form" method ="get">
                    <h2>Booking Information</h2>
                    <label for="package">Package:</label>
                    <select name="package" id="package" form="book" tabindex= "3" class ="package">
                        <?php 
                            $ref_id = $_SESSION['room-ref_id'];
                            $query = "select * from services where ref_id = $ref_id";
                            $stmt = $dbh -> query($query);
                        
                            while($row = $stmt -> fetch()){
                                echo '<option value="'.$row['name'].'">'.$row['name'].'</option>';
                            }
                        ?>
                    </select><br>

                    <label for="checkin">Check in:</label>
                    <input type="date" name="checkin" id="checkin" class="check-in" onblur = "validateDate(check_in)" value="<?php echo ($_SESSION['result-check_in'] == null) ? null : $_SESSION['result-check_in'];?>"><br>

                    <label for="days">Check out:</label>
                    <input type="date" name="checkout" id="checkout" class="check-out" onblur = "validateDate(check_out)" value="<?php echo ($_SESSION['result-check_out'] == null) ? '' : $_SESSION['result-check_out'];?>"><br>

                    <label for="roomno">Room Number:</label>
                    <input type="text" name="roomno" id="roomno" class="room-number" readonly>

                    <p class="error room">
                        
                    </p>

                    <input type="submit" name="search" id="search" class="search" value="FIND ROOM">

                    <h2>Payment Method</h2>
                    <label for="method">Payment Method:</label>
                    <select name="method" id="method" class="payment">
                        <?php
                             $id = $_SESSION['user-id'];
                             $query = "select * from payment_info where ref_id = $id";
                             $stmt = $dbh -> query($query);

                             $card_type = $row['type'];
                             $card_code = $row['card_code'];
                         
                             while($row = $stmt -> fetch()){
                                 echo '<option value="'.$row['type'].' '.$row['card_code'].'">'.$row['type'].' '.$row['card_code'].'</option>';
                             }
                        ?>
                    </select><br>

                    <p class="error payment">
                        
                    </p>

                    <div class="buttons">
                        <input type="submit" name ="submit-payment" id="submit-payment" class="submit-payment" value="BOOK NOW" >
                        <input type="button" name="cancel" id="cancel" class="cancel" value="CANCEL">
                    </div>
                    
                    
                </form>
                <div class="payment-summary" >
                    <h2>Payment Summary</h2>
                    <label for="total">Total:</label>
                    <input type="number" name="total" id="total" class="total" readonly form="book"><br>

                    <label for="downpayment">Down Payment:</label>
                    <input type="number" name="downpayment" id="downpayment" class="down-payment" onblur = "validateDownPayment(total, balance, down_payment)" form="book"><br>

                    <p class="note">
                        <span class="note-header">Note:</span><br><br>
                        <span class="note-content">
                            Down payment must be atleast 20% of the total sum of the transaction. Values less than 20% are not permited. Thank you.
                        </span> 
                    </p>

                    <label for="balance">Balance:</label>
                    <input type="number" name="balance" id="balance" class="balance" readonly form="book"><br>

                    <input type="text" name="ready" id="ready" form="book" class="ready" hidden>
                    <input type="text" name="room_id" id="room_id" form="book" class="room_id" hidden>

                   
                </div>
                
            </div>
        </main>
        <footer>
            <img src="/files/images/logo.png" alt="logo">
            <p>© All Rights Reserved | 2021</p>
        </footer>
        <?php

            $transaction = new Transaction();

            if(isset($_GET['submit-payment'])){
                extract($_GET);

                if($ready == 'true'){
                    $user_info = array($_SESSION['user-id'], $_SESSION['user-name'], $_SESSION['user-address'], $_SESSION['user-email'], $_SESSION['user-telephone'],);

                    $booking_info = array($package, $checkin, $checkout, $roomno, $_SESSION['room-type'], $_SESSION['room-ref_id']);

                    $payment_info = array($method, $total, $downpayment, $balance);

                    $transaction -> submitTransaction($user_info, $booking_info, $payment_info);
                    $transaction -> setRoomAvailability($room_id, $checkin, $checkout);

                    echo '<script>
                            alert("Room Booked Successfully. Wait for the confirmation in the the Bookings Menu. Thank you.");
                            window.location.replace("http://localhost:3000/files/html/home.php"); 
                        </script>';

                    
                }else {

                    echo '<script> 
                            document.querySelector(".error.payment").innerHTML = "Transaction Not Permitted. Try Again";
                        </script>';
                    
                }

            }

            if(isset($_GET['search'])){
                extract($_GET);

                $type = $_SESSION['room-type'];
                $ref_id = $_SESSION['room-ref_id'];

                $room_info = $transaction -> searchRoom($type, $checkin, $checkout, $ref_id);

                echo '<script>
                        document.querySelector(".room-number").value = "'.$room_info[1].'";
                    </script>';

                if($room_info[1] != null){
                    
                    $no_of_days = (new DateTime($checkout))->diff(new DateTime($checkin))->days;
                    $room_cost = $_SESSION['room-price'];
                    $transaction_cost = $transaction -> transactionCost($package, $ref_id, $room_cost, $no_of_days);
                    $min_payment = $transaction_cost * .20;


                     echo'<script>
                     
                        document.querySelector(".note-content").innerHTML = "Down payment must be atleast 20% (P'.$min_payment.') of the total sum of the transaction. Values less than P'.$min_payment.' are not permited. Thank you.";

                        document.querySelector(".room_id").value = "'.$room_info[0].'";
                        document.querySelector(".check-in").value = "'.$checkin.'";
                        document.querySelector(".check-out").value = "'.$checkout.'";
                        document.querySelector(".package").value = "'.$package.'";
                        document.querySelector(".total").value = '.$transaction_cost.';

                    </script>';

                } else {
                    echo '<script> 
                            document.querySelector(".error.room").innerHTML = "No room found";
                        </script>';
                }
 
            }

                

    ?>
        <script>
            const cancelButton = document.querySelector(".cancel");

            cancelButton.addEventListener("click", function(){
                if(confirm("Clicking cancel means youre going back to the Homepage. Are you sure that you want to continue?")){
                    window.location.replace("http://localhost:3000/files/html/home.php"); 
                }
            });

            //validation for input
            const payment_error = document.querySelector(".error.payment");
            const room_error = document.querySelector(".error.room");
            const check_in = document.querySelector(".check-in");
            const check_out = document.querySelector(".check-out");
            const total = document.querySelector(".total");
            const down_payment = document.querySelector(".down-payment");
            const balance = document.querySelector(".balance");
            const ready = document.querySelector(".ready");

            function validateDate(date) {
                var givenDate = date.value;
                var currentDate = new Date();
                givenDate = new Date(givenDate);

                if(!(givenDate > currentDate)){
                    room_error.innerHTML = "Date must be greater than the current date";
                }else {
                    room_error.innerHTML = "";
                }
            }

            check_out.onblur = function(){
                var checkOut = check_out.value;
                var checkIn = check_in.value;
                checkOut = new Date(checkOut);
                checkIn = new Date(checkIn);

                if(!(checkOut > checkIn)){
                    room_error.innerHTML = "Check out date must be greater than your check in date";
                }else {
                    room_error.innerHTML = "";
                }
            }

            function validateDownPayment(total, balance, downpayment){
                var totalValue = total.value;
                var minValue = totalValue * .20;
                var bal = totalValue - downpayment.value;

                if(downpayment.value == ""){
                    payment_error.innerHTML = "Downpayment field is empty";
                    balance.value = "";
                    ready.value = "false";
                } else {
                    if(downpayment.value < minValue){
                        payment_error.innerHTML = "Downpayment is less than 20% (P" + minValue + "). Please provide a higher denomination";
                        balance.value = "";
                        ready.value = "false";
                    } else if(bal< 0) {
                        payment_error.innerHTML = "Downpayment is too high. It must be at least P" + minValue + " but cannot be higher than the total value";
                        balance.value = "";
                        ready.value = "false";
                    } else {
                        payment_error.innerHTML = "";
                        balance.value = bal.toFixed(2);
                        ready.value = "true";
                    }

                    
                }
                

            }


            
            


        </script>
    
</body>
</html>