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
<link href="css/editor.dataTables.min.css" rel="stylesheet" media="screen">
<link href="css/select.dataTables.min.css" rel="stylesheet" media="screen">
<link href="css/buttons.dataTables.min.css" rel="stylesheet" media="screen">
<link href="css/jquery.dataTables.min.css" rel="stylesheet" media="screen">


<script type="text/javascript" src="js/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="js/dataTables.select.min.js"></script>
<script type="text/javascript" src="js/dataTables.editor.min.js"></script>


<!-- <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.0.min.js"></script> -->
<!-- <script type="text/javascript" src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script> -->
<!-- <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.1.2/js/dataTables.buttons.min.js"></script> -->
<!-- <script type="text/javascript" src="https://cdn.datatables.net/select/1.1.2/js/dataTables.select.min.js"></script> -->
<!-- <script type="text/javascript" src="https://editor.datatables.net/extensions/Editor/js/dataTables.editor.min.js"></script> -->

<title>MicroLoans PK app</title>
</head>
<body>

<table id="lender" class="display" width="100%" cellspacing="0">
        <thead>
            <tr>
                <th>AA</th>
                <th>Όνομα</th>
                <th>Πόλη</th>
                <th>Οδός</th>
                <th>Αριθμός</th>
                <th>Ταχ. Κώδικας</th>
                <th>ΑΦΜ</th>
                <th>Τελ. ενημέρωση</th>
            </tr>
        </thead>
        <tfoot>
            <tr>
                <th>AA</th>
                <th>Όνομα</th>
                <th>Πόλη</th>
                <th>Οδός</th>
                <th>Αριθμός</th>
                <th>Ταχ. Κώδικας</th>
                <th>ΑΦΜ</th>
                <th>Τελ. ενημέρωση</th>
            </tr>
        </tfoot>
    </table>
<script>
var editor; // use a global for the submit and return data rendering in the examples

$(document).ready(function() {
    editor = new $.fn.dataTable.Editor( {
        ajax: {
            create: {
                type: 'POST',
                url:  '/microloans/Lender'
            },
            edit: {
                type: 'POST',
                url:  '/microloans/Lender'
            },
            remove: {
                type: 'DELETE',
                url:  '/microloans/Lender',
                
                	
            }
        },
        idSrc: "id",
        table: "#lender",
        fields: [ {
                label: "AA:",
                name: "id"
            }, {
            	label: "Όνομα:",
                name: "name"
            }, {
                label: "Πόλη:",
                name: "town"
            }, {
                label: "Οδός:",
                name: "streetname"
            }, {
                label: "Αριθμός:",
                name: "streetnumber"
            }, {
                label: "Ταχ. Κώδικας:",
                name: "postalcode"
            }, {
                label: "ΑΦΜ:",
                name: "vat"
            }, {
                label: "Τελευταία Ενημέρωση:",
                name: "lastupdate",
                type: "datetime"
            }
        ]
    } );

    $('#lender').DataTable( {
        dom: "Bfrtip",
        ajax:{
     "url":"/microloans/Lender",
     "dataSrc":"lenders"
        } ,
   //     	"/microloans/Lender",
        columns: [
            { data: "id" },
            { data: "name" },
            { data: "town" },
            { data: "streetname" },
            { data: "streetnumber" },
            { data: "postalcode" },
            { data: "vat" },
            { data: "lastupdate" }
        ],
        select: true,
        buttons: [
            { extend: "create", editor: editor },
            { extend: "edit",   editor: editor },
            { extend: "remove", editor: editor }
        ]
    } );
} );
</script>


</body>
</html>
