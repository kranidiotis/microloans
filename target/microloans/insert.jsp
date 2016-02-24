<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JINSERT Operation</title>
</head>
<body>

	<%
try {
/*here mysqltest is the database name. you have to give the database name you have created and 3306 is the default sql port */

	String connectionURL = "jdbc:mysql://localhost:3306/mydb?useUnicode=true&characterEncoding=utf-8"; 
	
	Connection connection = null; 
	
	Class.forName("com.mysql.jdbc.Driver").newInstance(); 
	
	/* "root" and "root" are the mysql username and password . if you have a different user name and password you have to change that in code */
	
	connection = DriverManager.getConnection(connectionURL, "root", "panasgiotis");
	
	
	
	if(!connection.isClosed())
	{
		String insertName = request.getParameter("name");
		String insertTown = request.getParameter("town");
		String myinsert = "insert into Lender (Name,Town) values ('"+insertName+"', '"+insertTown+"');" ;
	
		PreparedStatement pst = connection.prepareStatement(myinsert);
	    int i = pst.executeUpdate();
	    if(i!=0){
	        out.println("<br>Data has been inserted");
	    }
	    else{
	       out.println("failed!");
	    }
	}

}
catch(Exception ex){
	out.println(ex.getStackTrace());
	ex.printStackTrace();

	}



%>


</body>
</html>