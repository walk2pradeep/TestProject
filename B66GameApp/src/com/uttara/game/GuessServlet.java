package com.uttara.game;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GuessServlet
 */
public class GuessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuessServlet() {
        super();
        System.out.println("in no-arg constr of GuessServlet");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in doGet() of GS");
		/*
		 * 1. access existing session for client. 
		 * if it does not exist, show error msg
		 * 2. pull data - email,tries,genVal from session
		 * 3. access user input guessNum from request
		 * 4. if tries < 0, show error msg, game over as attempts are used up!
		 * 5. if tries > 0, compare guessNum with genVal, accordingly show a msg if unequal
		 * 6. if equal, show success msg, store in context, in the leaderboard info about the winning client!
		 * 		
		 */
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession(false);
		if(session==null)
		{
			// we do not know who the user is! show error
			pw.write("<html><body><h1>Error</h1><b>Who are you?? stranger. Start the game again <a href='HomePage.html'>Click to go back to HomePage</a></b></body></html>");
			
		}
		else
		{
			//pull the data from session
			String email = (String)session.getAttribute("user");
			int genVal = (Integer) session.getAttribute("genVal");
			int tries = (Integer) session.getAttribute("tries");
			//get guessed num from request
			String gNum = request.getParameter("guessNum");
			int guessNum = Integer.parseInt(gNum);
			//check if user is not cheating by verifying tries value!
			if(tries==0)
			{
				session.removeAttribute("user");
				session.removeAttribute("tries");
				session.removeAttribute("genVal");
				session.invalidate();
				
				pw.write("<html><body><h1>Error</h1><b>You cheater! You cannot attempt more than 3 times! If you are interested, go to homepage and start again!<a href='HomePage.html'>Click to go back to HomePage</a></b></body></html>");
				
			}
			else
			{
				// its a valid guess!
				if(guessNum==genVal)
				{
					session.removeAttribute("user");
					session.removeAttribute("tries");
					session.removeAttribute("genVal");
					session.invalidate();
					
					//update his score in leaderboard!
					ServletContext sc = getServletContext();
					if(sc.getAttribute("pointsMap")==null)
					{
						Map<String,Integer> pointsMap = new HashMap<String,Integer>();
						pointsMap.put(email, 5);
						sc.setAttribute("pointsMap", pointsMap);
					}
					else
					{
						Map<String,Integer> pointsMap = (Map<String,Integer>)sc.getAttribute("pointsMap");
						if(pointsMap.get(email)!=null)
						{
							int val = pointsMap.get(email);
							val = val + 5;
							pointsMap.put(email, val);
						}
						else
						{
							pointsMap.put(email,5);
						}
						sc.setAttribute("pointsMap", pointsMap);
					}
					
					//user has guessed correctly! give him points and store info in leaderboard
					pw.write("<html><body><h1>Success</h1><b>Hooray!You are our own new PCSorkar only! go to homepage and start again!<a href='HomePage.html'>Click to go back to HomePage</a></b></body></html>");
				}
				else
				{
					if(guessNum > genVal)
					{						
						pw.write("<html><body><h1>Oops, you have guessed wrongly. Try lower next time!</h1></body></html>");
					}
					else
					{
						pw.write("<html><body><h1>Oops, you have guessed wrongly. Try higher next time!</h1></body></html>");
					}
					tries--;
					session.setAttribute("tries", tries);
				}
			}
			
			
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
