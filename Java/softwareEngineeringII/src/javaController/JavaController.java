package javaController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class JavaController {
	public static void main(String[] args) {
		try {
			int sut = -1;
			
			// Create a url with a query string to get the next test to execute on this client
			URL url = new URL("http://cosc4345-team6.com/master/getNextTest.php?action=getScriptName&sut="+ sut);
			// Open the connection to the web server
			URLConnection sock = url.openConnection();
			// Create a BufferedReader object to read the return results
			BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			String result = null;
			String s = null;
			String script = null;
			String errors = null;
			String pythonResults = null;
			String pythonErrors = null;
			// Read the return results and create a string with the python command to execute
			while ((result = reader.readLine()) != null) {
				result = result.replace("\"", "");
				script = "python " + result;
			}
			reader.close();
			// Now spawn another process to execute the Python script
			DateFormat df = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
			Calendar getTime = Calendar.getInstance();
			String startTime = df.format(getTime.getTime());
			Process p = Runtime.getRuntime().exec(script);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			String endTime = df.format(getTime.getTime());
			// We need to know if the test script succeeded or failed
			// Replace this code with something that checks the return value for
			// "SUCCESS"
			// For now, just print the return results
			pythonErrors = stdError.readLine().toLowerCase();
			System.out.println(stdError.readLine());
			System.out.println(stdError != null);
			if (stdError != null) {
				if (pythonErrors.contains("success")){
					pythonResults = "SUCCESS";
				} else {
					pythonResults = "FAILURE";
				}
			}
			System.out.println("result="+pythonResults + "&error="+pythonErrors+"&startTime="+startTime+"&endTime="+endTime);
			// You may need to add more code here to return the SUCCESS or FAIL
			// back to the database for tracking purposes. That code should go here

		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);

		}
	}
}
