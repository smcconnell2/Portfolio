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
        <div class='Body'>
        
        <div>

            <div id='topScreen' class='HeaderBox'>
            <h1 id='title' class='Headers'> DINNER CLUB </h1>
            </div>

        </div>

        <div id='options' class='Options'>

            <div class='HomeDrop'>
                <insert class='Home' onclick='href = '../Dinner Club/DinnerClubStructure.html''> HOME </insert>
                
            </div>

        </div>

        <div>
            <h1 class='Recipes'>~ RECIPES ~</h1>
            <img id='mainPicture' class='Picture' src='./ingredients.jpg' alt='food'>
        </div>
    
    ");
        
}

function displayButtons($recipe){
    //echo $address["first_name"];
    $title = $recipe["Title"];
    //echo " ";
    //echo $address["last_name"];
    $description = $recipe["Description"];
    //echo " ";
    //echo $address["street_address"];
    $ingredients = $recipe["Ingredients"];
    //echo " ";
    //echo $address["city"];
    $directions = $recipe["Directions"];
    //echo " ";
    //echo $address["id"];
    $id = $recipe["id"];
    

    echo("

            
            <div class='Display'>
                <form method='POST'>
                    <div class='RecipeTitle'>
                        <insert type='text'>$title</insert>
                    </div>
                    <div class='RecipeDesc'>
                        <insert type='text'>$description</insert>
                    </div>
                    <div class='RecipeIngredients'>
                        <insert type='text'>$ingredients</insert>
                    </div>
                    <div class='RecipeDesc'>
                        <insert type='text'>$directions</insert>
                    </div>
                    <div class='RecipeDesc'>
                        <insert type='text'>$id</insert>
                    </div>

                    <div id='editDrop' class='EditDrop'>
                        <div>
                            <insert class='Edit'>EDIT </insert>
                        </div>
                        <div class='EditContent'>
                            <br><input type='insert' value='$title' name='title'> <br>
                            <input type='insert' value='$description' name='description'>  <br>
                            <input type='insert' value='$ingredients' name='ingredients'> <br>
                            <input type='insert' value='$directions' name='directions'> <br>
                            <input type='insert' value='$id' name='id'> <br>
                            <div>
                                <input type='submit' id='edit' value='SUBMIT' class='button'>
                                <input type='submit' id='delete' value='DELETE' name='deleting' class='button'>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
    ");
}

function endHTML(){
    echo( "   
                <form method='POST'>
                    <div id='addDrop' class='AddDrop'>
                        <insert class='Edit'>ADD RECIPE</insert><br>
                        <div class='AddContent'>
                            <br><input type='insert' name='Title'> <br>
                            <input type='insert' name='Description'>  <br>
                            <input type='insert' name='Ingredients'> <br>
                            <input type='insert' name='Directions'> <br>
                            <br><input type='submit' id='add' value='ADD' name='adding' class='button'> <br>
                        </div>
                    </div>

                </form>     
            </div>
    </body> ");
}


if($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST["deleting"]) ) {

    
    parse_str(file_get_contents('php://input'), $_DELETE); // makes a delete variable

    $id = $_DELETE["id"];
    $result = PDOQuery("DELETE FROM recipes WHERE id=?", [$id]); // ? to avoide sql injection
    http_response_code(200);
    echo "Delete Successful"; // response code won't get sent util send something e.g. "echo"

}else if ($_SERVER["REQUEST_METHOD"] == "GET") {
    if (isset($_GET["id"])) {
        $result = PDOQuery("SELECT * FROM recipes WHERE id=?", [$_GET["id"]]);

        if ( $result) {
            http_response_code(200);
            echo json_encode($result);
        }       


    } else {
        $result = PDOQuery("SELECT * FROM recipes ORDER BY title", []);

        if ( $result) {
            http_response_code(200);
            //echo json_encode($result); // it is printing results here

            initializeHTML(); // start html
            foreach($result as $array){
                displayButtons($array); // calling html code function
            }
           endHTML(); // end html
        }
    }
} else if ($_SERVER["REQUEST_METHOD"] == "POST") {

    
    $title = $_POST["Title"];
    $description = $_POST["Description"];
    $ingredients = $_POST["Ingredients"];
    $directions = $_POST["Directions"];
    $id = $_POST["id"];
    
    $result = PDOInsert("INSERT INTO recipes (Title, Description, Ingredients, Directions, id) VALUES (?,?,?,?,?) ON DUPLICATE KEY 
    UPDATE Title='$title', Description='$description', Ingredients='$ingredients', Directions='$directions'", 
    [$title, $description, $ingredients, $directions, $id]);

    echo $result;
    
}



// put logic to display from GET
?>