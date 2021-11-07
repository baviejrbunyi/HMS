<?php
    session_start();

?>

<!DOCTYPE html>
<html lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="/files/images/logo.png" type="image/x-icon">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'>
    <link href="https://fonts.googleapis.com/css2?family=Anton&family=Roboto&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/files/css/general.css">
    <link rel="stylesheet" href="/files/css/landing.css">
    <title>HMS | Terms and Conditions</title>
</head>
<body>
    <header>
        <img src="/files/images/logo.png" alt="logo" class="logo">
        <ul class="nav">
            <li><a href="/files/html/home.php">HOME</a></li>
            <li><a href="">MY BOOKINGS</a></li>
            <li><a href="/files/html/contact.php">CONTACT US</a></li>
            <li><a href="/files/html/terms_conditions.html">TERMS AND CONDITIONS</a></li>
            <li><a href="/files/html/account.php">ACCOUNT</a></li>
        </ul>
    </header>
    <main>
        <img src="/files/images/thanks.png" alt="image">
        <div class="div">
            <h1>Thank you for getting in touch!</h1>
            <p>
                We appreciate you contacting us about <?php echo $_SESSION['subject']?>. One of our represintatives will be getting back to you shortly. <br><br>

                While we do our best to answer your queries quickly, it may take about 10 hours to receive a response from us during peak hours. <br><br>
    
                Thanks in advance for your patience. <br><br>
    
                Have a great day!
            </p>
            <a href="/files/html/home.php"><button class = "home">Return to Home</button></a>
        </div>
    </main>
    <footer>
        <img src="/files/images/logo.png" alt="logo">
        <p>© All Rights Reserved | 2021</p>
    </footer>
    
</body>
</html>