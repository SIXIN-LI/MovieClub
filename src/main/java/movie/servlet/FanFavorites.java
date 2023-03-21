package movie.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.dao.FanFavoritesDao;
import movie.model.Movies;


@WebServlet("/fanfavorites")
public class FanFavorites extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected FanFavoritesDao fanFavoritesDao;
	
	@Override
	public void init() throws ServletException {
		fanFavoritesDao = FanFavoritesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        List<Movies> fanFavorites = new ArrayList<Movies>();
		// Retrieve fanFavorites.
        try {
        	fanFavorites = fanFavoritesDao.getTopFanFavorites();
        	
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("fanFavorites", fanFavorites);
        req.getRequestDispatcher("/FanFavorites.jsp").forward(req, resp);
	}
}
