package com.microloans;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.*;
//import org.json.simple.JSONValue;

//import com.google.gson.Gson;

/**
 * Servlet implementation class Commitment
 */
public class Commitment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Commitment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
System.out.println("doGet Working");
		try {

			Connection connection = getConnection();
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			if (!connection.isClosed()) 

				try {
				    
				      String query = "SELECT * FROM Commitment;";
				      
				      response.setContentType("application/json");
				      response.setCharacterEncoding("utf-8");
				      PrintWriter out = response.getWriter();
				      
				      JSONObject commitments = new JSONObject();
				      JSONArray jArray = new JSONArray();

				      pstmt = connection.prepareStatement(query); // create a statement
				     // pstmt.setInt(1, 1001); // set input parameter
				      rs = pstmt.executeQuery();
				      // extract data from the ResultSet
				      while (rs.next()) {
				    	String commitid = rs.getString(1);
				        String commitloanreqid = rs.getString(3);
				        String commitaggdate = rs.getString(2);
				        String commitpropamount = rs.getString(4);
				        String commitlenderid = rs.getString(6);
				        
				        
				        JSONObject json = new JSONObject();
				        json.put("commitid",commitid);
				        json.put("commitloanreqid",commitloanreqid);
				        json.put("commitaggdate",commitaggdate);
				        json.put("commitpropamount",commitpropamount);
				        json.put("commitlenderid",commitlenderid);
				        jArray.add(json);
				        
				        
				        //out.println(loanreqamount + "\t" + loanreqdate + "\t" + loanreqdatepay + "\t" + loanreqper + "\t" + loanreqpayout + "\t" + loanreqcol + "\t" + lastupdate  );
				      }
				      commitments.put("commitments",jArray);
				      
				      out.print(commitments.toString());
				      System.out.println(commitments.toString());
				      
				    } catch (Exception e) {
				      e.printStackTrace();
				    } finally {
				     // try {
				        rs.close();
				        pstmt.close();
				        connection.close();
				      }
		}
			catch (SQLException e) {
				        e.printStackTrace();
				      } catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
//			
//						{
//				String loanrequest = request.getParameter("loanrequest");
//				System.out.println("Servlet parameter = "+ loanrequest);
//				String lender = "SELECT * FROM Lender ;";
//				PreparedStatement ps=connection.prepareStatement(deleteLender);
//				ps.executeUpdate();
//				PrintWriter out = response.getWriter();
//				String json = "{ \"loanrequest\":[[\"loanreqamount\",\"loanreqdate\",\"loanreqdatepay\",\"loanreqpayout\",\"loanreqcol\",\"lastupdate\"],[\"loanreqamount\",\"loanreqdate\",\"loanreqdatepay\",\"loanreqpayout\",\"loanreqcol\",\"lastupdate\"]]}";
//				out.println(json);
//				
//			}
//			
//		}catch(Exception ex){
//			
//			ex.printStackTrace();
//		}
		
	
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doPut Working");
		try {
			Connection connection = getConnection();
			String action = null;
			String commitid = null;
	        String commitloanreqid = null;
	        String commitaggdate = null;
	        String commitpropamount = null;
	        String commitlenderid = null;
	        PrintWriter out = response.getWriter();

	        
	        Enumeration enumeration = request.getParameterNames();
	        
	        while (enumeration.hasMoreElements()) {
	            
		        String parameterName = (String) enumeration.nextElement();
		        System.out.println(parameterName);
		        if(parameterName.contains("[commitid]")){
		        	commitid = request.getParameter(parameterName);
		        }else if(parameterName.contains("action")){
		        	action = request.getParameter(parameterName);
		        }else if(parameterName.contains("[commitloanreqid]")){
		        	commitloanreqid = request.getParameter(parameterName);
		        }else if(parameterName.contains("[commitaggdate]")){
		        	commitaggdate = request.getParameter(parameterName);
		        }else if(parameterName.contains("[commitpropamount]")){
		        	commitpropamount = request.getParameter(parameterName);
		        }else if(parameterName.contains("[commitlenderid]")){
		        	commitlenderid = request.getParameter(parameterName);
		        
	        	}
		        
		        
		    }
	        
	        
	        String sqlCommitment= null;
			
			if(action != null  && action.equals("create")  ){
				sqlCommitment= "INSERT INTO Commitment (LoanRequest_idLoanRe,"
						+ "CommitmentDateofAggrement,CommitmentProposedAmount,"
						+ "Lender_idLender)"+"VALUES('"+commitloanreqid+"' , '"+commitaggdate+
						"' , '"+commitpropamount+"' , '"+commitlenderid+"') ;";

			}
			
			PreparedStatement ps=connection.prepareStatement(sqlCommitment);
			ps.executeUpdate();
			
			JSONObject record = new JSONObject();
			
			JSONObject json = new JSONObject();
	        json.put("commitid",commitid);
	        json.put("commitloanreqid",commitloanreqid);
	        json.put("commitaggdate",commitaggdate);
	        json.put("commitpropamount",commitpropamount);
	        json.put("commitlenderid",commitlenderid);
	        
	        record.put("data", json);
	        System.out.println(json);
	        
			
			
			out.print(record);

			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	
	public Connection getConnection() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		String connectionURL = "jdbc:mysql://localhost:3306/mydb?zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8";

		Connection connection = null;

		Class.forName("com.mysql.jdbc.Driver").newInstance();

		/* "root" and "root" are the mysql username and password . if you have a different user loanreqamount and password you have to change that in code */

		connection = DriverManager.getConnection(connectionURL, "root",
				"panasgiotis");
		return connection;
	}
}
