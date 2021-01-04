<?php
    // utility functions for using PDO
    // by Brian A. Kamp
    //
    //
    // PDOQuery is used when an query data is expected on return
    // $sql = "SELECT * FROM addresses WHERE city = ? AND state = ?";
    // $varArray = ["Cheney", "WA"];
    //
    // returns associative array - NOT is JSON
	function PDOQuery($sql, $varArray) {
		include_once "./creds.php";
		

		$dsn = "mysql:host=$host;dbname=$db;charset=$charset"; // log in info
		$opt = [
			PDO::ATTR_ERRMODE            => PDO::ERRMODE_EXCEPTION, //error modes and fetch
			PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
			PDO::ATTR_EMULATE_PREPARES   => false
		];

		$pdo = new PDO($dsn, $user, $pw, $opt); // create a pdo object

		if (count($varArray) == 0) { // if array of vars is empty then use query function
			// no variables to bind
			$stmt = $pdo->query($sql);
		} else {
			// bind variables
			$stmt = $pdo->prepare($sql);
			$stmt->execute($varArray);			
		}
		return $stmt->fetchAll(PDO::FETCH_ASSOC);// return with an associative array of results
	}

    // PDOINSERT is used specifically for INSERT.
    // The return is the Auto Increment database assigned value.
    // $sql = "INSERT INTO addresses (address1, city, state, zip) VALUES (?,?,?,?)";
    // $varArray = ["S 124 Main St","Cheney","WA","99004"];
    //
    // returns integer of assigned auto-increment key
	function PDOInsert($sql, $varArray) {
		include_once "./creds.php";
		
		$dsn = "mysql:host=$host;dbname=$db;charset=$charset";
		$opt = [
			PDO::ATTR_ERRMODE            => PDO::ERRMODE_EXCEPTION,
			PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
			PDO::ATTR_EMULATE_PREPARES   => false
		];
		
		$pdo = new PDO($dsn, $user, $pw, $opt);

		if (count($varArray) == 0) {
			// no variables to bind
			$stmt = $pdo->query($sql);
		} else {
			// bind variables
			$stmt = $pdo->prepare($sql);
			$stmt->execute($varArray);	//inserting into the table		
		}
		return $pdo->lastInsertId(); // returns the id of the last inserted
	}


?>