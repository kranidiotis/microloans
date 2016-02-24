<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="css/mycss.css" rel="stylesheet" media="screen">

<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.1.2/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/select/1.1.2/js/dataTables.select.min.js"></script>
<script type="text/javascript" src="https://editor.datatables.net/extensions/Editor/js/dataTables.editor.min.js"></script>

<title>MicroLoans PK app</title>
</head>
<body>
	<h2>Γειά σου Ιάσονα</h2>
	<%
		try {
			/*here mysqltest is the database name. you have to give the database name you have created and 3306 is the default sql port */

			String connectionURL = "jdbc:mysql://localhost:3306/mydb?useUnicode=true&characterEncoding=utf-8";

			Connection connection = null;

			Class.forName("com.mysql.jdbc.Driver").newInstance();

			/* "root" and "root" are the mysql username and password . if you have a different user name and password you have to change that in code */

			connection = DriverManager.getConnection(connectionURL, "root",
					"panasgiotis");

			if (!connection.isClosed()) {

				String insertForm = request.getParameter("insert");
				
				if (insertForm!=null && insertForm.equals("insert")) {
					String insertName = request.getParameter("name");
					String insertTown = request.getParameter("town");
					String myinsert = "insert into Lender (Name,Town) values ('"
							+ insertName + "', '" + insertTown + "');";

					PreparedStatement pst = connection
							.prepareStatement(myinsert);
					int i = pst.executeUpdate();
					if (i != 0) {
						out.println("<br>Data has been inserted");
					} else {
						out.println("failed!");
					}

				}
	%>
	<div id="table">
		<div class="header-row row">
			<span class="cell">Name</span> <span class="cell">Town</span><span class="cell">Ενέργειες</span>
		</div>

		<%
			String mysl = "SELECT idLender, Name, Town FROM Lender;";

					PreparedStatement ps = connection.prepareStatement(mysl);
					ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						int idLender = rs.getInt("idLender");
						String name = rs.getString("Name");
						String town = rs.getString("Town");
						out.print("<div id = \"firstdiv\" class=\"row\"><span class=\"cell\">"
								+ name
								+ "</span><span class=\"cell\">"
								+ town
								+ "</span>"
								+ "<span class=\"cell\"> <button onclick=\"deleteLender("+idLender+")\">Διαγραφή</button></span>"
								+"</div>");

					}
					connection.close();
				}
			} catch (Exception ex) {
				out.println(ex.getStackTrace());
				ex.printStackTrace();

			}
		%>
	</div>
	<form action="index.jsp">
		Name:<br> <input type="text" id="name" name="name"><br>
		Town:<br> <input type="text" id="town" name="town"> <input
			type="hidden" name="insert" value="insert"> <input
			type="submit" value="Submit">


	</form>



 	<script> 

		var deleteLender = function(idLender){
			alert(idLender);
			  var xhttp = new XMLHttpRequest();
			  xhttp.onreadystatechange = function() {
			    if (xhttp.readyState == 4 && xhttp.status == 200) {
			    	var data = xhttp.responseText;
//		            alert(data);			    
			    }
			  };
			  xhttp.open("POST", "http://localhost:8080/microloans/DeleteLender?lenderId="+idLender, true);  //lenderId
			  xhttp.send();
		}		
	</script> 

</body>
</html>
