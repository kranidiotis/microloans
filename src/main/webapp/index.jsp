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
	<h2>Εργασία για το μάθημα Βάσεων Δεδομένων ge08903 </h2>
	
	Η εργασία στην παρούσα μορφής της δεν είναι ολοκληρωμένη.
	
	Παρέχει δυνατότητητα:
	<li> Δημιουργίας ενός νέου Δανειζομένου  
	Η σχετική σελίδα βρίσκεται <a href="./lender.jsp">Εδώ</a>
	<li> Δημιουργίας νέου Δανειστή
	Η σχετική σελίδα βρίσκεται <a href="./borrower.jsp">Εδώ</a>
	<li> Δημιουργίας νέου Δανείου και κατάθεσης προσφοράς από το Δανειστή
	Η σχετική σελίδα βρίσκεται <a href="./loanrequest.jsp">Εδώ</a>
	
	Η εφαρμογή έχει φτιαχτεί με την χρήση maven σε περιβάλλον eclipse με τεχνολογίες JSP , Servlets, και Javascript.
	Η βάση δεδομένων είναι η Mysql.
</body>
</html>
