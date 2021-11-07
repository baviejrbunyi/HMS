<?php
include("database.php");
extract($_GET);

if($username == null){
    $username = 'Username';
}
if ($email == null) {
    $email = 'Email';
}
if ($password == null){
    $password = 'Password';
}

$query= "select * from user where username = '$username' or email = '$email'";
$sth = $dbh -> prepare($query);
$sth -> execute();

if ($sth -> rowCount() > 0){
    include("$location/files/html/sign-up.html");
    echo "<script>
            document.getElementById('username').value = '$username'
            document.getElementById('email').value = '$email'
            document.getElementById('password').value = '$password'
            document.getElementById('error').innerHTML = 'Email or Username is already taken.'
        </script>";
} else {
    
    $query= "insert into $dbname.user(username, password, email) values (?,?,?)";
    $sth = $dbh -> prepare($query);
    $sth -> execute(array($username, $password, $email));

    include("$location/files/html/sign-in.html");
    echo "<script>
            document.getElementById('username').value = '$username'
            document.getElementById('error').innerHTML = 'Sign in to your account to continue.'
        </script>";
}

?>