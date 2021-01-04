<?php
    include_once("pdo_utils.php");


function initializeHTML(){
    echo ( "
    <!DOCTYPE html>
    <head> 
       <title>DBEditorStrct</title>
       <link rel='stylesheet' href='./tableDisplay.css'> 
    </head> 
    <body>
        <div class='Body'>");
}

function displayButtons($address){
    echo $address["first_name"];
    $fname = $address["first_name"];
    echo " ";
    echo $address["last_name"];
    $lname = $address["last_name"];
    echo " ";
    echo $address["street_address"];
    $saddress = $address["street_address"];
    echo " ";
    echo $address["city"];
    $city = $address["city"];
    echo " ";
    echo $address["state"];
    $state = $address["state"];
    echo " ";
    echo $address["zip"];
    $zip = $address["zip"];
    echo " ";
    echo $address["phone"];
    $phone = $address["phone"];
    echo " ";
    echo $address["id"];
    $id = $address["id"];
    

    echo("

            <div class='buttons'>
                <form method='POST'>
                    <input type='submit' id='edit' value='EDIT' class='button'> <br>
                    <input type='insert' value='$fname' name='first_name'> <br>
                    <input type='insert' value='$lname' name='last_name'>  <br>
                    <input type='insert' value='$saddress' name='street_address'> <br>
                    <input type='insert' value='$city' name='city'> <br>
                    <input type='insert' value='$state' name='state'> <br>
                    <input type='insert' value='$zip' name='zip'> <br>
                    <input type='insert' value='$phone' name='phone'> <br>
                    <input type='insert' value='$id' name='id'>
                </form>
                <form method='POST'>
                    <input type='submit' id='delete' value='DELETE' name='deleting' class='button'>
                    <input type='hidden' value='$id' name='id'>
                </form>
             
            </div>
    ");

}

function endHTML(){
    echo( "   
            <form method='POST'>
                <input type='submit' id='add' value='ADD' name='adding' class='button'> <br>
                <input type='insert' name='first_name'> <br>
                <input type='insert' name='last_name'>  <br>
                <input type='insert' name='street_address'> <br>
                <input type='insert' name='city'> <br>
                <input type='insert' name='state'> <br>
                <input type='insert' name='zip'> <br>
                <input type='insert' name='phone'> <br>
            </form>     
        </div>

    </body> ");
}


if($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST["deleting"]) ) {

    
    parse_str(file_get_contents('php://input'), $_DELETE); // makes a delete variable

    $id = $_DELETE["id"];
    $result = PDOQuery("DELETE FROM steve331_addresses WHERE id=?", [$id]); // ? to avoide sql injection
    http_response_code(200);
    echo "Delete Successful"; // response code won't get sent util send something e.g. "echo"

}else if ($_SERVER["REQUEST_METHOD"] == "GET") {
    if (isset($_GET["id"])) {
        $result = PDOQuery("SELECT * FROM steve331_addresses WHERE id=?", [$_GET["id"]]);

        if ( $result) {
            http_response_code(200);
            echo json_encode($result);
        }       


    } else {
        $result = PDOQuery("SELECT * FROM steve331_addresses ORDER BY last_name", []);

        if ( $result) {
            http_response_code(200);
            echo json_encode($result); // it is printing results here

            initializeHTML(); // start html
            foreach($result as $array){
                displayButtons($array); // calling html code function
            }
           endHTML(); // end html
        }
    }
} else if ($_SERVER["REQUEST_METHOD"] == "POST") {

    $first_name = $_POST["first_name"];
    $last_name = $_POST["last_name"];
    $saddress = $_POST["street_address"];
    $city = $_POST["city"];
    $state = $_POST["state"];
    $zip = $_POST["zip"];
    $phone = $_POST["phone"];
    $id = $_POST["id"];

    $result = PDOInsert("INSERT INTO steve331_addresses (first_name, last_name, street_address, city, state, zip, phone, id) VALUES (?,?,?,?,?,?,?,?) ON DUPLICATE KEY 
    UPDATE first_name='$first_name', last_name='$last_name', street_address='$saddress', city='$city', state='$state', zip='$zip', phone='$phone'", 
    [$first_name, $last_name, $saddress, $city, $state, $zip, $phone, $id]);

    echo $result;
}



// put logic to display from GET
?>