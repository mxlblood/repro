My Contributions:

Implemented All Web UI 
Assisted in all PHP files

Other Contributions:

P0 php command/control, java controller, database - implement the capability to request a test
script to run from the php command/control, execute the Python test and return a result
(SUCCESS/FAIL) to the PHP command/control. This is minimal functionality for one test.
getTest(SUTId) API
putResult(SUTId, Result) API
-Implemented By: Carlos, Max

P0 Database, Java Controller, PHP Command/Control - Set the maximum number of times a test
should run on a given day. This setting should be in the database and the PHP
Command/Control should take this into account when designating which tests run (i.e., part of
the algorithm when getTest() is called.)
-Implemented by: Max

P0 Java Controller, PHP Controller - When a test encounters an error it should return "ERROR"
with the error text. In this case the Java Controller should send the error text to the server,
which should be stored in the database.
-Implemented by: Max

P1 PHP Command and Control - States in the test history table should be: IN_PROGRESS with
start timestamp SUCCESS or FAIL with finish timestamp
-Implemented by: Max

P2 PHP Command and Control - Email one or more users when a test fails so the user can take
immediate action.
-Implemented by: Max
