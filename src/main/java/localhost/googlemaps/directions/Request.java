package localhost.googlemaps.directions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Class prepares a URL to sends necessary information to the Google Maps API to
 * return navigation directions
 * 
 * @author William Kratz
 * @since 9-6-16
 */
public class Request {
	// endpoint is the beginning of the URL to access the google map API
	protected static String endpoint = "https://maps.googleapis.com/maps/api/directions/";
	// charset is set to UTF-8 formatting
	protected static String charset = "UTF-8";
	// key is the google key needed to access the API
	protected static String key = "AIzaSyAI-b0OwKFzq2tHeLht0JiYzgN2kF6k_l8";
	// avoid property allows users to avoid certain things on their routes such
	// as tolls, highways, etc.
	protected static String avoid = "highways";
	// origin is the location where the directions begin from. This is set by
	// the form on the index.html web page
	static String origin = null;
	// destination is the location where the directions end at. This is set by
	// the form on the index.html web page
	static String destination = null;
	// return type determines the format the API returns it's results in.
	static String returnType = "json";
	// directions hold the results the API returns to be displayed to the user
	static ArrayList<String> directions = new ArrayList<>();

	// method takes in a starting point and ending point and set's the origin
	// and destination strings to be used in the URL request to the API
	public static void setParams(String startPoint, String endPoint) {
		origin = startPoint;
		destination = endPoint;
	}

	// method prepares the complete URL, opens the connection to the API and
	// stores the results in the directions array list
	public static void makeRequest() {

		try {
			// query string holds the parameters to pass to the API
			String queryString = String.format("origin=%s&destination=%s&key%s&avoid=%s",
					URLEncoder.encode(origin, charset), URLEncoder.encode(destination, charset),
					URLEncoder.encode(key, charset), URLEncoder.encode(avoid, charset));

			// googleDirections holds the complete URL to be passed sent to the
			// API
			URL googleDirections = new URL(endpoint + returnType + "?" + queryString);

			// opens connection with API, sends data via URL googleDirections
			HttpURLConnection connection = (HttpURLConnection) googleDirections.openConnection();
			connection.setRequestMethod("GET");

			// if anything other than a successful connection occurs, stop
			// program and display the error code
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}

			// br stores the input the API returns
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

			// loop goes through the buffered reader line by line and stores the
			// data in the directions array list
			while (br.readLine() != null) {
				System.out.println(br.readLine());
				directions.add(br.readLine());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//makeRequest()

	// method returns the directions array
	public static ArrayList<String> getDirections() {
		return directions;

	}

}//class
