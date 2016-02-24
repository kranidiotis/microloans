package com.microloans;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class DeleteLender
 */
public class DeleteLender extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DeleteLender() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		try {
			/*here mysqltest is the database name. you have to give the database name you have created and 3306 is the default sql port */

			String connectionURL = "jdbc:mysql://localhost:3306/mydb?useUnicode=true&characterEncoding=utf-8";

			Connection connection = null;

			Class.forName("com.mysql.jdbc.Driver").newInstance();

			/* "root" and "root" are the mysql username and password . if you have a different user name and password you have to change that in code */

			connection = DriverManager.getConnection(connectionURL, "root",
					"panasgiotis");
			
			if (!connection.isClosed()) {
				String lenderId = request.getParameter("lenderId");
				System.out.println("Servlet parameter = "+ lenderId);
				String deleteLender = "DELETE FROM Lender WHERE idLender="+lenderId+";";
				PreparedStatement ps=connection.prepareStatement(deleteLender);
				ps.executeUpdate();
				
				
			}
			
		}catch(Exception ex){
			
			ex.printStackTrace();
		}
			
	}

}
