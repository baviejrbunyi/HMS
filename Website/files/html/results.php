<?php
    session_start();
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
        <link rel="stylesheet" href="/files/css/results.css">
        <link rel="stylesheet" href="/files/css/general.css">
        <title>HMS | Results</title>
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
            <h1>Results</h1>
            <div class="results">
                <?php
                    $location = "D:/Users/Bavie/School/Second Semester/Third Quarter/4 Computer Programming 3 (Intermidiate Java Programming)/Activities/ILS/Coding/Website/";

                    include("$location/files/php/database.php");

                    if(isset($_GET['submit_search'])){
                        $general = $_GET['general'];
                        $checkIn = $_GET['check-in'];
                        $checkOut = $_GET['check-out'];

                        if($checkIn == null){
                            $_SESSION['result-check_in'] = null;
                        }else{
                            $_SESSION['result-check_in'] = $checkIn;
                        }

                        if($checkOut == null){
                            $_SESSION['result-check_out'] = null;
                        }else {
                            $_SESSION['result-check_out'] = $checkOut;
                        }

                        
                       



                        if($general != null && $checkIn == null && $checkOut == null){
                            $query = "select * from room where type like '%$general%' || address like '%$general%'";
                            $stmt = $dbh -> query($query);
                        }else if($general == null && $checkIn != null && $checkOut != null){
                            $query = "select * from room where  ('$checkIn' not between occupied_from and occupied_to && '$checkOut' not between occupied_from and occupied_to) || (occupied_from is null && occupied_to is null)";
                            $stmt = $dbh -> query($query);
                        }else if($general != null && $checkIn != null && $checkOut != null){
                            $query = "select * from room where  (type like '%$general%' || address like '%$general%') && ('$checkIn' not between occupied_from and occupied_to && '$checkOut' not between occupied_from and occupied_to) || (occupied_from is null && occupied_to is null && (type like '%$general%' || address like '%$general%'))";
                            $stmt = $dbh -> query($query);
                        }else{
                            echo '<h1 style = "min-height: 65vh; overflow: hidden;">No Results Found <h1>
                                    </main>
                                    <footer></footer>';

                            die();
                        }

                        while($row = $stmt -> fetch()){
                            $image = 'data:image/jpeg;base64,'.base64_encode( $row['image'] ).'';
                            echo '<form method="post" action="#" enctype="multipart/form" name="card" id="card" class="card">
                            <img src="'.$image.'" alt="image" class = "image">
                            <div class="info">
                                <input type="text" readonly name="room_type" class = "room-type" value="'.$row['type'].'"><br>
                                <input type="text" readonly name="room_address" class = "room-address" value="'.$row['address'].'"><br>
                                <textarea form="card" readonly name="room_details" class = "room-details">'.$row['description'].'</textarea><br>
                                <input type="text" hidden name="room_id" class = "room-id" value="'.$row['id'].'">
                                <button type="submit" class="book-room" name="book_room">BOOK</button>
                            </div>
                        </form>';
                        }

                    }

                ?>
            </div>
        </main>
        </section>
        
        <footer>
            <img src="/files/images/logo.png" alt="logo">
            <p>© All Rights Reserved | 2021</p>
        </footer>
        <?php
            if(isset($_POST['book_room'])){
                extract($_POST);

                

                $query = "select * from room where id = $room_id";

                echo '<script>console.log("'.$query.'")</script>';
                $stmt = $dbh -> query($query);
                $row = $stmt -> fetch();
                $room_image = 'data:image/jpeg;base64,'.base64_encode( $row['image'] ).'';

                $_SESSION['room-image'] = $room_image;
                $_SESSION['room-type'] = $row['type'];
                $_SESSION['room-company'] = $row['company'];
                $_SESSION['room-price'] = $row['rates'];
                $_SESSION['room-details'] = $row['description'];
                $_SESSION['room-ref_id'] = $row['ref_id'];

                echo '<section class = "pop-up">
            
                        <img src="'.$room_image.'" alt="image" class = "full-image">
                        <div class = "full-info">
                            <h3 class = "full-type">'.$row['type'].'</h3>
                            <h3 class = "full-company">'.$row['company'].'</h3>
                            <h3 class = "full-address">'.$row['address'].'</h3>
                            <p class = "full-details">'.$row['description'].'</p>
                            <h4 class = "full-rating">Rating: &nbsp'.$row['rating'].'</h4>
                            <h4 class = "full-price">Price: &nbsp'.$row['rates'].'&nbsp per night</h4>
                            <button class = "exit">EXIT</button>
                            <a href = "/files/html/booking.php"><button class = "book">BOOK NOW</button></a>
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
            



        ?>
        <script>
            const card = document.querySelector(".card");
            const popUp = document.querySelector(".pop-up");
            const exit = document.querySelector(".exit");
            const main = document.querySelector(".main");
            const body = document.querySelector(".body");
            const header = document.querySelector(".header");
            const results = document.querySelector(".results");
            const book = document.querySelector(".book");

            /*card.addEventListener("click", function(){
                popUp.classList.add("active");
                main.style.filter = "blur(20px)";
                header.style.filter = "blur(20px)";
                body.style.overflow = "hidden";
            });*/
            exit.addEventListener("click", function(){
                popUp.classList.remove("active");
                main.style.filter = "blur(0px)";
                header.style.filter = "blur(0px)";
                body.style.overflow = "auto";
               
            });

            book.addEventListener("click", function(){
                window.location.replace("http://localhost:3000/files/html/booking.php"); 
            });

        </script>
    </body>
</html>
