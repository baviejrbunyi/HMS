<?php
    session_start();

    $location = "D:/Users/Bavie/School/Second Semester/Third Quarter/4 Computer Programming 3 (Intermidiate Java Programming)/Activities/ILS/Coding/Website/";
    include("$location/files/php/database.php");
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
        <link rel="stylesheet" href="/files/css/home.css">
        <link rel="stylesheet" href="/files/css/general.css">
        <title>HMS | Home</title>
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
            <div class="search-section">
                <h1 class="tag">Hotel rooms inhabit a separate moral universe. <br> Find one now!</h1>
                <div class="search">
                    <form action="/files/html/results.php" method = "GET" enctype="multipart/form-data">
                        <div class="main-search">
                            <input type="text" placeholder="Search for room types or address" name = "general" class="search-field">
                            <input type="submit" value="Search" class="search-button" name = "submit_search"><br>
                        </div>
                        <div class="secondary-search">
                            <label for="check-in">Check In</label>
                            <input type="date" name="check-in" class="check-in date">
                            <label for="check-out">Check Out</label>
                            <input type="date" name="check-out" class="check-out date">
                        </div>
                    </form>
                    
                </div>
            </div>
            <div class="recommendations">
                <h2>Recommendations</h2>
                <div class="carousel">
                    <?php
                        
                        $query = 'select * from room_types';
                        $stmt = $dbh -> query($query);
                        

                        while($row = $stmt -> fetch()) {
                            $room_image = 'data:image/jpeg;base64,'.base64_encode( $row['profile'] ).'';
                            
                            recommendations($room_image, $row['type'], $row['company'], $row['address'], $row['price'], $row['rating'], $row['id']);
                        }

                    ?>
                </div>
             </div>
        </main>
        <footer class="footer">
            <img src="/files/images/logo.png" alt="logo">
            <p>© All Rights Reserved | 2021</p>
        </footer>
        <?php 

            if(isset($_GET['book'])){
                extract($_GET);

                $_SESSION['result-check_in'] = null;
                $_SESSION['result-check_out'] = null;

                $query = "select * from room_types where id = $room_id";
                $stmt = $dbh -> query($query);
                $row = $stmt -> fetch();
                $room_image = 'data:image/jpeg;base64,'.base64_encode( $row['profile'] ).'';

                $_SESSION['room-image'] = $room_image;
                $_SESSION['room-type'] = $row['type'];
                $_SESSION['room-company'] = $row['company'];
                $_SESSION['room-price'] = $row['price'];
                $_SESSION['room-details'] = $row['details'];
                $_SESSION['room-ref_id'] = $row['ref_id'];

                //echo '<script>console.log("'.$_SESSION['ref_id'].'")</script>';

                echo '<section class = "pop-up">
            
                        <img src="'.$room_image.'" alt="image" class = "full-image">
                        <div class = "full-info">
                            <h3 class = "full-type">'.$row['type'].'</h3>
                            <h3 class = "full-company">'.$row['company'].'</h3>
                            <h3 class = "full-address">'.$row['address'].'</h3>
                            <p class = "full-details">'.$row['details'].'</p>
                            <h4 class = "full-rating">'.$row['rating'].'</h4>
                            <h4 class = "full-price">Price: &nbsp'.$row['price'].'&nbsp per night</h4>
                            <button class = "exit">EXIT</button>
                            <a href = "/files/html/booking.php" ><button class = "book">BOOK NOW</button></a>
                        </div>
                            
                        
                    </section>';
                
                echo '<script>
                        document.querySelector(".pop-up").classList.add("active");
                        document.querySelector(".main").style.filter = "blur(20px)";
                        document.querySelector(".footer").style.filter = "blur(20px)";
                        document.querySelector("header").style.filter = "blur(20px)";
                        document.querySelector(".body").style.overflow = "hidden";

                    </script>';

                


            }

            function recommendations($room_image, $room_type, $room_company, $room_address, $room_price, $room_rating, $room_id){
                $recommendations = '<form method="get" enctype="multipart/form" action="#" class="card" name="card" id="card">
                                        <img src="'.$room_image.'" alt="hotel-image" class="room-image">
                                        <div class="info">
                                            <input type="text" name="room_type" readonly class ="room-type" value = "'.$room_type.'">
                                            <input type="text" name="room_company" readonly class="room-company" value = "'.$room_company.'">
                                            <textarea form="card" name="room_address" readonly class="room-address">'.$room_address.'</textarea>
                                            <button type="submit" class="book" name="book">BOOK</button>
                                        </div>
                                        <div class="rating-price">
                                            <input type="text" name="room_price" readonly class="room-price" value = "P'.$room_price.'">
                                            <input type="text" name="room_rating" readonly class="room-rating" value = "'.$room_rating.'">
                                            <input type="text" name="room_id" hidden class="room-id" value = "'.$room_id.'">
                                        </div>
                                </form>';
                
                echo $recommendations;
            }



        ?>

        <script>
            const card = document.querySelector(".card");
            const popUp = document.querySelector(".pop-up");
            const exit = document.querySelector(".exit");
            const main = document.querySelector(".main");
            const body = document.querySelector(".body");
            const book = document.querySelector(".book");
            const footer = document.querySelector(".footer");

            //card.addEventListener("click", function(){
                
            //});

            exit.addEventListener("click", function(){
                popUp.classList.remove("active");
                main.style.filter = "blur(0px)";
                footer.style.filter = "blur(0px)";
                document.querySelector("header").style.filter = "blur(0px)";
                body.style.overflow = "auto";
            });

            book.addEventListener("click", function(){
                window.location.replace("http://localhost:3000/files/html/booking.php");
                console.log("click");
            });

        </script>
    </body>
</html>