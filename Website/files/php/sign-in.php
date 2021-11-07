<?php
    session_start();
    include("database.php");
    extract($_GET);

    $query = "select * from user where (username = '$identity' or email = '$identity') and password = '$password'";
    $sth = $dbh -> query($query);

    if($sth -> rowCount() == 1){

        //fetches the data of the selected row
        $row = $sth -> fetch();

        //adds the information to the account class
        include("account.php");

        $_SESSION['user-id'] = $row['id'];
        $_SESSION['user-name'] = $row['name'];
        $_SESSION['user-username'] = $row['username'];
        $_SESSION['user-password'] = $row['password'];
        $_SESSION['user-telephone'] = $row['telephone'];
        $_SESSION['user-email'] = $row['email'];
        $_SESSION['user-address'] = $row['address'];
        $_SESSION['user-profile'] = 'data:image/jpeg;base64,'.base64_encode( $row['profile'] ).'';
        

        header("Location: /files/html/home.php");

    }else {

        if($identity == null){
            $identity = 'Username/Email';
        }
        if ($password == null) {
            $password = 'Password';
        }

        include("$location/files/html/sign-in.html");
        echo "<script>
            document.getElementById('identity').value = '$identity'
            document.getElementById('password').value = '$password'
            document.getElementById('error').innerHTML = 'Incorrect Email/Username and Password'
        </script>";

    }
?>