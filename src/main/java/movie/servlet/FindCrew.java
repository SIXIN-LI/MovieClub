package movie.servlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.dao.CrewDao;
import movie.model.Crew;

/**
 * Servlet implementation class FindCrew
 */
@WebServlet("/findcrew")
public class FindCrew extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected CrewDao crewDao;

	@Override
	public void init() throws ServletException {
		crewDao = CrewDao.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        Crew crew;
        // Retrieve and validate name.
        // crewId is retrieved from the URL query string.
        String crewId = req.getParameter("crewid");
        if (crewId == null || crewId.trim().isEmpty()) {
        	throw new ServletException("404 Error: Not found");
        } else {
        	// Retrieve Crew, and store as a message.
        	try {
        		crew = crewDao.getCrewByCrewId(crewId);
        		System.out.println(crew);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	String successMessage = String.format("Displaying information for '%s'", crew.getName());
        	messages.put("success",successMessage);
        }
        req.setAttribute("crew", crew);
        req.getRequestDispatcher("/FindCrew.jsp").forward(req, resp);
	}
}

