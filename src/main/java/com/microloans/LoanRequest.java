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
 * Servlet implementation class LoanRequest
 */
public class LoanRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoanRequest() {
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
				    
				      String query = "SELECT * FROM LoanRequest;";
				      
				      response.setContentType("application/json");
				      response.setCharacterEncoding("utf-8");
				      PrintWriter out = response.getWriter();
				      
				      JSONObject loanrequests = new JSONObject();
				      JSONArray jArray = new JSONArray();

				      pstmt = connection.prepareStatement(query); // create a statement
				     // pstmt.setInt(1, 1001); // set input parameter
				      rs = pstmt.executeQuery();
				      // extract data from the ResultSet
				      while (rs.next()) {
				    	String loanreqid = rs.getString(1);
				        String loanreqamount = rs.getString(2);
				        String loanreqdate = rs.getString(3);
				        String loanreqdatepay = rs.getString(4);
				        String loanreqper = rs.getString(5);
				        String loanreqpayout = rs.getString(6);
				        String loanreqdsc = rs.getString(7);
				        String borrowerid = rs.getString(8);
				        
				        JSONObject json = new JSONObject();
				        json.put("loanreqid",loanreqid);
				        json.put("loanreqamount",loanreqamount);
				        json.put("loanreqdate",loanreqdate);
				        json.put("loanreqdatepay",loanreqdatepay);
				        json.put("loanreqper",loanreqper);
				        json.put("loanreqpayout", loanreqpayout);
				        json.put("loanreqdsc", loanreqdsc);
				        json.put("borrowerid", borrowerid);
				        jArray.add(json);
				        
				        
				        //out.println(loanreqamount + "\t" + loanreqdate + "\t" + loanreqdatepay + "\t" + loanreqper + "\t" + loanreqpayout + "\t" + loanreqcol + "\t" + lastupdate  );
				      }
				      loanrequests.put("loanrequests",jArray);
				      
				      out.print(loanrequests.toString());
				      System.out.println(loanrequests.toString());
				      
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
			String loanreqid = null;
	        String loanreqamount = null;
	        String loanreqdate = null;
	        String loanreqdatepay = null;
	        String loanreqper = null;
	        String loanreqpayout = null;
	        String loanreqdsc= null;
	        String borrowerid = null;
	        PrintWriter out = response.getWriter();
	        
	        Enumeration enumeration = request.getParameterNames();
	        
	        while (enumeration.hasMoreElements()) {
		    	
		    	
		        String parameterName = (String) enumeration.nextElement();
		        System.out.println(parameterName);
		        if(parameterName.contains("[loanreqid]")){
		        	loanreqid = request.getParameter(parameterName);
		        }else if(parameterName.contains("action")){
		        	action = request.getParameter(parameterName);
		        }else if(parameterName.contains("[loanreqamount]")){
			       	loanreqamount = request.getParameter(parameterName);
		        }else if(parameterName.contains("[loanreqdate]")){
		        	loanreqdate = request.getParameter(parameterName);
		        }else if(parameterName.contains("[loanreqdatepay]")){
		        	loanreqdatepay = request.getParameter(parameterName);
		        }else if(parameterName.contains("[loanreqper]")){
		        	loanreqper = request.getParameter(parameterName);
		        }else if(parameterName.contains("[loanreqpayout]")){
		        	loanreqpayout = request.getParameter(parameterName);
		        }else if(parameterName.contains("[loanreqdsc]")){
			        loanreqdsc = request.getParameter(parameterName);
	        	}else if(parameterName.contains("[borrowerid]")){
	        		borrowerid = request.getParameter(parameterName);
	        	}
		        
		        
		    }
	        
	        
	        String sqlLoanRequest = null;
			if (action != null  && action.equals("edit")  ) {
				 sqlLoanRequest = "UPDATE LoanRequest SET LoanRequestAmount='"+loanreqamount+"',LoanRequestDate='"+loanreqdate+
				"',LoanRequestDateofPayment='"+loanreqdatepay+"',LoanRequestPercentance='"+loanreqper+
				"', LoanRequestPayOut='"+loanreqpayout+"',LoanRequestDescription='"+loanreqdsc+"' WHERE idLoanRe="+loanreqid+";";
			
	
					
			}else if(action != null  && action.equals("create")  ){
				sqlLoanRequest = "INSERT INTO LoanRequest (LoanRequestAmount,LoanRequestDate,LoanRequestDateofPayment,LoanRequestPercentance,LoanRequestPayOut,LoanRequestDescription,Borrower_idBorrower) VALUES('"+loanreqamount+"','"+loanreqdate+
						"','"+loanreqdatepay+"','"+loanreqper+
						"','"+loanreqpayout+"','"+loanreqdsc+"','"+borrowerid+"') ;";

			}
			
			PreparedStatement ps=connection.prepareStatement(sqlLoanRequest);
			ps.executeUpdate();
			
			JSONObject record = new JSONObject();
			
			JSONObject json = new JSONObject();
	        json.put("loanreqid",loanreqid);
	        json.put("loanreqamount",loanreqamount);
	        json.put("loanreqdate",loanreqdate);
	        json.put("loanreqdatepay",loanreqdatepay);
	        json.put("loanreqper",loanreqper);
	        json.put("loanreqpayout", loanreqpayout);
	        json.put("loanreqdsc", loanreqdsc);
	        
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
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doDelete Working");
		try {
			Connection connection = getConnection();
			String loanreqid = request.getParameter("loanreqid");
			
			String stringId = null; 
			PrintWriter out = response.getWriter();
			
			 Enumeration enumeration = request.getParameterNames();
			    while (enumeration.hasMoreElements()) {
			        String parameterName = (String) enumeration.nextElement();
			        if(parameterName.contains("[loanreqid]")){
			        	stringId = request.getParameter(parameterName);
			        }
			        System.out.println(parameterName);
			    }
			
			
			System.out.println("Servlet parameter = "+ stringId);
			String deleteLoanRequest = "DELETE FROM LoanRequest WHERE idLoanRe="+stringId+";";
			PreparedStatement ps=connection.prepareStatement(deleteLoanRequest);
			ps.executeUpdate();
			out.print("{}");
			
			
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
