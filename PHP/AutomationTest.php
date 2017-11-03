<?php
// AutomationTest
// Provides an example of how to open a connection to a database and retrieve
// test information from the database.
// Returns test information in an array, which can easily be converted to JSON

require "./DBConnection.php";

Class AutomationTest {

	private $test = array(
		"testName" => '',
		"scriptName" => '');

	// getNextTest()
	// Fetches the test information from the database.  This is very limited in functionality,
	// in that it does not mark  the test as "in progress"
	//
	// After calling getNextTest(), the user should call getTest() to obtain all the test information
	// or call getTestName() for the test description, or call getScriptName for just the test script
	// file name
	public function getNextTest() {

        $conn = new DBConnection();
        $db = $conn->dbConnect();

        if ($db == NULL) {
            $err = array("ERROR" => "Cannot connect to database");
            return $err;
        }

        $sql = "select testName, scriptName from tests";
        $rs = $db->Execute($sql);

        if ($rs == false) {
            $err = array("ERROR" => "No results from database");
            return $err;
        }
        
        while (!$rs->EOF) {

            $this->test["testName"] = $rs->fields["testName"];
            $this->test["scriptName"] = $rs->fields["scriptName"];
            $rs->MoveNext();
        }
	}
    
    public function putResults($result,$startTime,$endTime,$errorLog,$emailed,$testID){
        $conn = new DBConnection();
        $db = $conn->dbConnect();

        if ($db == NULL) {
            $err = array("ERROR" => "Cannot connect to database");
            return $err;
        }

       $sql = "insert into testResults VALUES (null,'".$result."','". $startTime. "','". $endTime. "','".$errorLog."','".$emailed."','".$testID. "')";
       print $sql;
       $rs = $db->Execute($sql);
        
        if ($rs == false) {
            $err = array("ERROR" => "No results from database");
            return $err;
        }
        
    }

	// Return the entire array
	//
	public function getTest(){
        $conn = new DBConnection();
        $db = $conn->dbConnect();

        if ($db == NULL) {
            $err = array("ERROR" => "Cannot connect to database");
            return $err;
        }
	    
	    $scriptName=getScriptName();
        $dt = new DateTime();
        echo $dt->format('Y-m-d H:i:s');
        
        $count="count startTime from testResults, tests where startTime and endTime <" .$dt."and scriptName= ".$scriptName;
        $rs1 = $db->Execute($count);
        if ($rs1 == false) {
            $err1 = array("ERROR" => "No results from database");
            return $err1;
        }
        
        $dailyFreq="select dailyFrequenceCount from tests where scriptName= ".$scriptName;
        $rs2 = $db->Execute($dailyFreq);
        if ($rs2 == false) {
            $err2 = array("ERROR" => "No results from database");
            return $err2;
        }
        
        if($count<$dailyFreq){
		return $this->test;
	    }
	}

	// Return the test name which is the description
	//
	public function getTestName() {

		return $this->test["testName"];
	}

	// Return the script name to execute
	//
	public function getScriptName() {

    	return $this->test["scriptName"];
	}
}
?>
