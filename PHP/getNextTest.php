<?php

// getNextTest.php
// Retrieves test information from the database
//

require "./AutomationTest.php";

// Process all key/value pairs that come with the $GET
// and take the appropriate action to select mobile phones
//
if (isset($_GET["action"])) {

    $action = $_GET["action"];

    $test = new AutomationTest();     // Setup a test object
    $result = $test->getNextTest();	  // Retrive the test from the database
    $myJson = "Unknown Error";        // Setup json for the return info

    // Encode all of the test information
    //
    if ($action == "getTest") {

        $myJson = json_encode($test->getTest());
    }

	// Encode just the test name, which is a description of the test
	//
	else if ($action == "getTestName") {
		$myJson = json_encode($test->getTestName());
	}

	// Encode the script name, which is the Python filename to execute on the client
	//
	else if ($action == "getScriptName") {
		$myJson = json_encode($test->getScriptName());
	}

	// Return the json
	print "$myJson";
}

?>
