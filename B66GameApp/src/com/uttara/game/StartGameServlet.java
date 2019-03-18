package com.uttara.game;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class StartGameServlet
 */
public class StartGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartGameServlet() {
        super();
        System.out.println("in SGS no-arg constr");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in doGet() of SGS");
		/*
		 * 1.access email input from the request
		 * 2.perform input validations
		 * 3.generate the random num between 0-9
		 * 4.ask WS to create a session for the client,
		 * store genVal, email and tries(3) into session!
		 * 5.forward the control to Guess.html using RD!
		 */
		String email = request.getParameter("email");
		PrintWriter pw = response.getWriter();
		if(email==null || email.equals("") || !email.contains("@"))
			pw.write("<html><body><h1>Error!</h1><b>If you cannot even give your own email id as input, what will you acheive in life?!!</b></body></html>");
		else
		{
			//email input is valid!
			int val = (int)(Math.random()*10);
			System.out.println("gen val = "+val);
			int tries = 3;
			
			//ask WS to create session for client!
			HttpSession session = request.getSession(true);
			session.setAttribute("user", email);
			session.setAttribute("genVal", val);
			session.setAttribute("tries", tries);
			
			//forward control to Guess.html
			RequestDispatcher rd = request.getRequestDispatcher("Guess.html");
			rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
