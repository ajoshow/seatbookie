package com.seatbookie.svg.upload.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seatbookie.svg.parse.ConnectFirebase;

/**
 * Servlet implementation class BookSeatServlet
 */
public class BookSeatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookSeatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long graphId=Long.parseLong(request.getParameter("graphId"));
		String[] seatNames=request.getParameterValues("seatName");
		
		response.getWriter().println(bookSeat(graphId, seatNames));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long graphId=Long.parseLong(request.getParameter("graphId"));
		String[] seatNames=request.getParameterValues("seatName");
		
		response.getWriter().println(bookSeat(graphId, seatNames));
	}
	
	private synchronized boolean bookSeat(long graphId, String[] seatNames){
		boolean result=ConnectFirebase.updateSeat(graphId, ConnectFirebase.SeatState.BOOKED, seatNames);
		return result;
	}

}
