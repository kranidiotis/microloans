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
public class Lender extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Lender() {
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
				    
				      String query = "SELECT * FROM Lender;";
				      
				      response.setContentType("application/json");
				      response.setCharacterEncoding("utf-8");
				      PrintWriter out = response.getWriter();
				      
				      JSONObject lenders = new JSONObject();
				      JSONArray jArray = new JSONArray();

				      pstmt = connection.prepareStatement(query); // create a statement
				     // pstmt.setInt(1, 1001); // set input parameter
				      rs = pstmt.executeQuery();
				      // extract data from the ResultSet
				      while (rs.next()) {
				    	String id = rs.getString(1);
				        String name = rs.getString(2);
				        String town = rs.getString(3);
				        String streetname = rs.getString(4);
				        String streetnumber = rs.getString(5);
				        String postalcode = rs.getString(6);
				        String vat = rs.getString(7);
				        String lastupdate = rs.getString(8);
				        
				        JSONObject json = new JSONObject();
				        json.put("id",id);
				        json.put("name",name);
				        json.put("town",town);
				        json.put("streetname",streetname);
				        json.put("streetnumber",streetnumber);
				        json.put("postalcode", postalcode);
				        json.put("vat",vat);
				        json.put("lastupdate", lastupdate);
				        jArray.add(json);
				        
				        
				        //out.println(name + "\t" + town + "\t" + streetname + "\t" + streetnumber + "\t" + postalcode + "\t" + vat + "\t" + lastupdate  );
				      }
				      lenders.put("lenders",jArray);
				      
				      out.print(lenders.toString());
				      System.out.println(lenders.toString());
				      
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
//				String json = "{ \"loanrequest\":[[\"name\",\"town\",\"streetname\",\"postalcode\",\"vat\",\"lastupdate\"],[\"name\",\"town\",\"streetname\",\"postalcode\",\"vat\",\"lastupdate\"]]}";
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
			String id = null;
	        String name = null;
	        String town = null;
	        String streetname = null;
	        String streetnumber = null;
	        String postalcode = null;
	        String vat = null;
	//        String lastupdate = null;
	        PrintWriter out = response.getWriter();
	        
	        Enumeration enumeration = request.getParameterNames();
	        
	        while (enumeration.hasMoreElements()) {
		    	
		    	
		        String parameterName = (String) enumeration.nextElement();
		        System.out.println(parameterName);
		        if(parameterName.contains("[id]")){
		        	id = request.getParameter(parameterName);
		        }else if(parameterName.contains("action")){
		        	action = request.getParameter(parameterName);
		        }else if(parameterName.contains("[name]")){
			       	name = request.getParameter(parameterName);
		        }else if(parameterName.contains("[town]")){
		        	town = request.getParameter(parameterName);
		        }else if(parameterName.contains("[streetname]")){
		        	streetname = request.getParameter(parameterName);
		        }else if(parameterName.contains("[streetnumber]")){
		        	streetnumber = request.getParameter(parameterName);
		        }else if(parameterName.contains("[postalcode]")){
		        	postalcode = request.getParameter(parameterName);
		        }else if(parameterName.contains("[vat]")){
		        	vat = request.getParameter(parameterName);
		        }
		        
		        
		    }
	        
	        
	        String sqlLender = null;
			if (action != null  && action.equals("edit")  ) {
				 sqlLender = "UPDATE Lender SET Name='"+name+"',Town='"+town+
				"',StreetName='"+streetname+"',StreetNumber='"+streetnumber+
				"', VAT='"+vat+"', PostalCode='"+postalcode+"' WHERE idLender="+id+";";
			
	
					
			}else if(action != null  && action.equals("create")  ){
				sqlLender = "INSERT INTO Lender (Name,Town,StreetName,StreetNumber,VAT,PostalCode) VALUES('"+name+"','"+town+
				"','"+streetname+"','"+streetnumber+
				"', '"+vat+"','"+postalcode+"') ;";

			}
			
			PreparedStatement ps=connection.prepareStatement(sqlLender);
			ps.executeUpdate();
			
			JSONObject record = new JSONObject();
			
			JSONObject json = new JSONObject();
	        json.put("id",id);
	        json.put("name",name);
	        json.put("town",town);
	        json.put("streetname",streetname);
	        json.put("streetnumber",streetnumber);
	        json.put("postalcode", postalcode);
	        json.put("vat",vat);
//	        json.put("lastupdate", lastupdate);
	        
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
			String lenderId = request.getParameter("id");
			
			String stringId = null; 
			PrintWriter out = response.getWriter();
			
			 Enumeration enumeration = request.getParameterNames();
			    while (enumeration.hasMoreElements()) {
			        String parameterName = (String) enumeration.nextElement();
			        if(parameterName.contains("[id]")){
			        	stringId = request.getParameter(parameterName);
			        }
			        System.out.println(parameterName);
			    }
			
			
			System.out.println("Servlet parameter = "+ stringId);
			String deleteLender = "DELETE FROM Lender WHERE idLender="+stringId+";";
			PreparedStatement ps=connection.prepareStatement(deleteLender);
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
		String connectionURL = "jdbc:mysql://localhost:3306/mydb?useUnicode=true&characterEncoding=utf-8";

		Connection connection = null;

		Class.forName("com.mysql.jdbc.Driver").newInstance();

		/* "root" and "root" are the mysql username and password . if you have a different user name and password you have to change that in code */

		connection = DriverManager.getConnection(connectionURL, "root",
				"panasgiotis");
		return connection;
	}
}
