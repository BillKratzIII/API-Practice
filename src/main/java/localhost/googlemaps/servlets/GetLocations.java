package localhost.googlemaps.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import localhost.googlemaps.directions.Request;

/**
 * Servlet implementation class GetLocations. Servlet takes in information from
 * form on index.html, calls methods from Request class, redirects and displays
 * data on displayDirections.jsp
 * 
 * @author William Kratz
 * @since 9-6-16
 */
@WebServlet("/GetLocations")
public class GetLocations extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetLocations() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 *      method takes in data from the form on the page index.html and sets
	 *      the starting and ending points to be used in the URL for the Google
	 *      Maps API. Once set we call the makeRequest() method to get the
	 *      results from the API. Then the program redirects to
	 *      displayDirections.jsp where the results are shown.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String startPoint = request.getParameter("startPoint");
		String endPoint = request.getParameter("endPoint");
		Request.setParams(startPoint, endPoint);
		Request.makeRequest();

		request.getRequestDispatcher("displayDirections.jsp").forward(request, response);
	}

}
