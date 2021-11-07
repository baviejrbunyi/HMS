<?php
    $location = "D:/Users/Bavie/School/Second Semester/Third Quarter/4 Computer Programming 3 (Intermidiate Java Programming)/Activities/ILS/Coding/Website/";
    

    session_start();
    
    if(isset($_POST['submit'])){
        extract($_POST);

        if($email == null || $subject == null || $message == null){
            echo "<script>
                    document.getElementById('error').innerHTML = 'All fields are reuired.';
                </script>";
        }else {
            header('location: /files/html/landing.php');
            $_SESSION['subject'] = $subject;
        }
        
    }
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
    <link rel="stylesheet" href="/files/css/contact.css">
    <title>HMS | Contact Us</title>
</head>
<body>
    <header>
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
        <h1>Contact Us</h1>
        <p id = "error"></p>
        <form action="/files/html/contact.php" method = "post" enctype="multipart/form-data" id = "contact-form">
            <input type="email" name =  "email" id = "email" placeholder = "Email"  value = "<?php echo $_SESSION['user-email'] ?>"><br>
            <input type="text" name = "subject" id = "subject" placeholder = "What's this about?"><br>
            <textarea name="message" id="message" placeholder = "Go ahead were listening"></textarea><br>
            <input type="submit" value="SEND" name = "submit" id = "submit">
        </form>
    </main>
    <footer>
        <img src="/files/images/logo.png" alt="logo">
        <p>© All Rights Reserved | 2021</p>
    </footer>  
</body>
</html>