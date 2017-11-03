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
import java.util.Scanner;

public class JavaController {
	private static Scanner sc;

	public static void main(String[] args) {
		try {
			sc = new Scanner(System.in);
			System.out.println("Enter the System Under Testing ID");
			int sut = sc.nextInt();
			// Create a url with a query string to get the next test to execute
			// on this client
			URL url = new URL("http://cosc4345-team6.com/master/getNextTest.php?action=getScriptName&sut=" + sut);
			// System.out.println(url);
			// Open the connection to the web server
			URLConnection sock = url.openConnection();
			// Create a BufferedReader object to read the return results
			BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			String result = null;
			String script = null;
			String pythonResults = null;
			String pythonErrors = null;
			String scriptName = null;
			// Read the return results and create a string with the python
			// command to execute
			while ((result = reader.readLine()) != null) {
				result = result.replace("\"", "");
				script = "python " + result;
			}
			scriptName = script.replaceAll("python ", "");
			reader.close();
			// Now spawn another process to execute the Python script
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar getTime = Calendar.getInstance();
			String startTime = df.format(getTime.getTime());
			
			Process p = Runtime.getRuntime().exec(script);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			// System.out.println(stdInput.readLine());
			// System.out.println(stdError.readLine());
			// System.out.println(stdInput.readLine());

			String endTime = df.format(getTime.getTime());
			// We need to know if the test script succeeded or failed
			// Replace this code with something that checks the return value for
			// "SUCCESS"
			// For now, just print the return results
			pythonErrors = stdError.readLine();
			pythonResults = stdInput.readLine();
			System.out.println("Python Errors is: " + pythonErrors);
			System.out.println("Pythron Results is: " + pythonResults);
			if (pythonErrors == null) {
				if (pythonResults.contains("SUCCESS")) {
					pythonResults = "SUCCESS";
					pythonErrors = pythonErrors.toUpperCase();
				}
			}
			else {
				pythonResults = "FAILURE";
			}
			System.out.println("action=putResults&result=" + pythonResults + "&error=" + pythonErrors + "&startTime="
					+ startTime + "&endTime=" + endTime + "&scriptName=" + scriptName);
			// You may need to add more code here to return the SUCCESS or FAIL
			// back to the database for tracking purposes. That code should go
			// here
			URL submitUrl = new URL("http://cosc4345-team6.com/master/putResults.php?action=putResults&result=" + pythonResults + "&error=" + pythonErrors + "&startTime=" + startTime + "&endTime=" + endTime + "&scriptName=" + scriptName);
			URLConnection submitSock = submitUrl.openConnection();
			BufferedReader newReader = new BufferedReader(new InputStreamReader(submitSock.getInputStream()));
			newReader.close();
			System.out.println(submitUrl);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);

		}
	}
}
