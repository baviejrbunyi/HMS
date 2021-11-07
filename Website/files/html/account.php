<?php
    session_start();
    $location = "D:/Users/Bavie/School/Second Semester/Third Quarter/4 Computer Programming 3 (Intermidiate Java Programming)/Activities/ILS/Coding/Website/";
    include("$location/files/php/database.php");

    $user_id = $_SESSION['user-id'];
    $user_name = $_SESSION['user-name'];
    $user_username = $_SESSION['user-username'];
    $user_address = $_SESSION['user-address'];
    $user_email = $_SESSION['user-email'];
    $user_telephone = $_SESSION['user-telephone'];
    $user_password = $_SESSION['user-password'];
    $user_profile = $_SESSION['user-profile'];

    $defaultprof = '/files/images/profile.png';
    $defaultname = "Set name";
    $defaultaddress = "Set Address";
    $defaulttel = "Set Telephone";
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
    <link href="https://fonts.googleapis.com/css2?family=Anton&family=Roboto&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/files/css/general.css">
    <link rel="stylesheet" href="/files/css/account.css">
    <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
    <title>HMS | My Account</title>
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
    <main class = "main">
        <div class="left">
            <h1>My Account</h1>
            <div class="tab-container">
                <div class="button-container">
                    <button onclick="showPanel(0,'#FCDFA6')">Account</button>
                    <button onclick="showPanel(1,'#FCDFA6')">Payment Details</button>
                </div>
            </div>
        </div>
        <div class="panels">
            <div class="tab-panel account">
                <div class="accnt">
                    <div class="left-account">
                        <img src="<?php echo ($user_profile == 'data:image/jpeg;base64,') ? $defaultprof : $user_profile;?>" alt="profile" class="profile-pic" id="img"><br>
                         <p class="cancel">Remove Image</p>
                        <button class="upload-picture" onclick="defaultBtnActive()" >UPLOAD</button><br>
                        <a href="/files/html/index.html"><button class="log-out">SIGN OUT</button></a>

                    </div>
                    <div class="right-account">
                        <form action="/files/html/account.php" method="post" enctype="multipart/form-data">
                            <input type="file" class="default" name = "myimage">
                            <label for="name">Name:</label>
                            <input type="text" name="name" value= "<?php echo ($user_name == null) ? $defaultname : $user_name;?>" class="name"><br>
                            <label for="username">Username:</label>
                            <input type="text" name="username" value= "<?php echo $user_username ?>" class="username"><br>
                            <label for="address">Address:</label>
                            <input type="address" name="address" value= "<?php echo ($user_address == null) ? $defaultaddress : $user_address;?>"class="address"><br>
                            <label for="emai">Email:</label>
                            <input type="email" name="email" value= "<?php echo ($user_email == null) ? $defaultemail : $user_email;?>" class="email"><br>
                            <label for="phone">Phone:</label>
                            <input type="text\" name="phone" value= "<?php echo ($user_telephone == null) ? $defaulttel : $user_telephone;?>" class="phone"><br>
                            <label for="password">Password:</label>
                            <input type="password" name="password" value= "<?php echo $user_password ?>" class="password"><br>
                            <p id = "message" class = "message"></p>
                            <input type = "submit" class="save-changes" name = "save-changes" value = "SAVE CHANGES">
                            <p class="edit">Edit</p>
                        </form>
                        
                    </div>
                    <div class="change-password">
                        <form action="/files/html/account.php" method = "get" enctype = "multipart/form-data">
                            <input type="password" name="oldpass" id="oldpass pass" placeholder = "Old Password"><br>
                            <input type="password" name="newpass" id="newpass pass" placeholder = "New Password"><br>
                            <input type="password" name="retypepass" id="retypepass pass" placeholder = "Retype New Password"><br>
                            <input type="submit" value="SAVE" class = "submit-pass" name = "submit-pass">
                            <input type="button" value="CANCEL" class = "cancel-change">
                            <input type="checkbox" onclick="togglePassword(oldpass)" class = "check-one">
                            <input type="checkbox" onclick="togglePassword(newpass)" class = "check-two">
                            <input type="checkbox" onclick="togglePassword(retypepass)" class = "check-three">
                        </form>
                    </div>
                </div>
            </div>
            <div class="tab-panel payment-details">
                <?php
                    $query = "select * from payment_info where ref_id = $user_id";
                    $stmt = $dbh -> query($query);

                    while($row = $stmt -> fetch()){
                        echo '<div class="payment-option">
                                <h2>Credit Card</h2>
                                <h3 class="card-type info"><span class = "label">Card Type: </span>'.$row['type'].'</h3>
                                <h3 class="card-number info"><span class = "label">Card Number: </span>XXXX XXXX XXXX '.$row['card_code'].'</h3>
                                <h3 class="card-expiry info"><span class = "label">Card Expiry: </span>'.$row['expiry'].'</h3>
                            </div>';
                    }
                ?>
                <button class = "add-payment">ADD NEW PAYMENT OPTION</button>
            </div>
        </div>
    </main>
    <div class="add-payment-option">
        <h2>Add a Card</h2>
        <form action="/files/html/account.php" method = "post" enctype="multipart/form-data" id="payment-form">
            <label for="cardtype">Card Type: </label>
            <select tabindex="3" class="add-card-type" form = "payment-form" name = "cardtype">
                <option value="AmEx">American Express</option>
                <option value="MasterCard">MasterCard</option>
                <option value="Visa">Visa</option>
            </select><br>
            <label for="cardnumber">Card Number: </label>
            <input type="text" placeholder = "Type your card number here" class = "add-card-number" name = "cardnumber" maxlength="24"><br>
            <label for="cardexpiry">Expiry: </label>
            <input type="month" name="cardexpiry" class="add-card-expiry" ><br>
            <label for="cardcvv">Security Code: </label>
            <input type="text" name="cardcvv" class="add-card-cvv" placeholder = "CVC/CVV"><br>

            <p class = "add-payment-error"></p>

            <input type="submit" value="ADD" class = "add-payment-method" name = "addpayment" id = "addpayment">
            <input type="button" value="CANCEL" class = "cancel-payment-add">
            
        </form>
                    
    </div>
    
    <footer>
        <img src="/files/images/logo.png" alt="logo">
        <p>© All Rights Reserved | 2021</p>
    </footer>

    <script src="/files/script/account.js"></script>
    <script src="https://www.braemoor.co.uk/software/_private/creditcard.js"></script>
    <script>
        //validating the card information

        var cardNumber = document.querySelector(".add-card-number");
        var cardType = document.querySelector(".add-card-type");
        var addError = document.querySelector(".add-payment-error")

        cardNumber.onkeyup = function() {    
            console.log("cardNumber is on focus");   
            var val = cardNumber.value;         
            var newval = '';         
            val = val.replace(/\s/g, ''); 
            
            // iterate to letter-spacing after every 4 digits   
            for(var i = 0; i < val.length; i++) {             
            if(i%4 == 0 && i > 0) newval = newval.concat(' ');             
            newval = newval.concat(val[i]);         
            }        

            // format in same input field 
            cardNumber.value = newval;
        }; 

        cardNumber.onblur = function(){
            if (!checkCreditCard(cardNumber.value, cardType.value)) {
                addError.innerHTML = ccErrors[ccErrorNo];
            } else {
                addError.innerHTML = "";
            }
        };

    </script>
    <?php

        include("$location/files/php/database.php");

        if (isset($_POST['save-changes'])) {
            extract($_POST);

            $user_id = $_SESSION['user-id'];

            if($_FILES['myimage']['name'] == ""){
                $query = "update user set name = '$name', username = '$username', address = '$address', email = '$email', telephone = '$phone' where(id = $user_id);";
                $stmt = $dbh -> query($query);
            }else {
                $imagetmp = addslashes(file_get_contents($_FILES['myimage']['tmp_name']));
                $query = "update user set name = '$name', username = '$username', address = '$address', email = '$email', telephone = '$phone', profile = '$imagetmp' where(id = $user_id);";
                $stmt = $dbh -> query($query);
            }
            echo "<script>
                    document.getElementById('message').innerHTML = 'Changes to your account will appear after your next login.';
                </script>";

        }


        if (isset($_GET['submit-pass'])) {
            $oldpass = $_GET['oldpass'];
            $newpass = $_GET['newpass'];
            $retypepass = $_GET['retypepass'];


            if($oldpass == $user_password){

                if($oldpass != $newpass){
                
                    if($newpass == $retypepass){
                        $query = "update user set password = '$newpass' where (id = $user_id);";
                        $stmt = $dbh -> query($query);

                        echo "<script>
                                document.getElementById('message').innerHTML = 'Password changed successfully.';
                            </script>";
                    }else {
                        echo "<script>
                                document.getElementById('message').innerHTML = 'Retyped password and new password does not match.';
                            </script>";
                    }
                }else {
                    echo "<script>
                                document.getElementById('message').innerHTML = 'Your old password cannot be your new password.';
                        </script>";
                }
            }else {
                echo "<script>
                        document.getElementById('message').innerHTML = 'Your given password does not match your current password.';
                    </script>";
            }
        }

        if(isset($_POST['addpayment'])){
            extract($_POST);

            $card_code = explode(" ", $cardnumber);
            echo $card_code[3];

            $query = "insert into payment_info (ref_id, type, number, balance, status, expiry, card_code) values (?,?,?,10000.00,'Valid',?,?)";
            $stmt = $dbh -> prepare($query);
            $stmt -> execute(array($user_id, $cardtype, $cardnumber, $cardexpiry, $card_code[3]));

            echo '<script>
                    window.location.replace("http://localhost:3000/files/html/account.php"); 
                </script>';


        }
    ?>
    
</body>
</html>