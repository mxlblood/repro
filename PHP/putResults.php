<?php

// putResults.php
// Puts results into database
//

require_once "./AutomationTest.php";
require_once "./DBConnection.php";

// Process all key/value pairs that come with the $GET
// and take the appropriate action to select mobile phones
//
if (isset($_GET["action"])) {
    
    $action = $_GET["action"];
    $result = $_GET["result"];
    $errorLog = $_GET["error"];
    $startTime = $_GET["startTime"];
    $endTime = $_GET["endTime"];
    $scriptName = $_GET["scriptName"];
    
    //$myJson = "Unknown Error";        // Setup json for the return info

        $conn = new DBConnection();
        $db = $conn->dbConnect();
        //print "I'm getting this far";
        
        if ($db == NULL) {
            $err = array("ERROR" => "Cannot connect to database");
            return $err;
        }
        
        $sql = 'select testID from tests where scriptName="'.$scriptName.'"';
        //print $sql;
        $rs = $db->Execute($sql);
        $row=$rs->FetchRow();
        $testID=$row[0];
        
        
        if ($rs == false) {
            $err = array("ERROR" => "No results from database");
            return $err;
        }
    

    $test = new AutomationTest();     // Setup a test object
    $result = $test->putResults($result,$startTime,$endTime,$errorLog,0,$testID);
    
    
}

?>