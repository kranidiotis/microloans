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

<table id="loanrequest" class="display" width="100%" cellspacing="0">
        <thead>
            <tr>
                <th>Aρ Αίτησης Δανείου</th>
                <th>Αιτούμενο Ποσό</th>
                <th>Ημερομηνία Αίτησης</th>
                <th>Ημερομηνία Αποπληρωμής</th>
                <th>Επιτόκιο</th>
                <th>Ημερομηνία Έκταμίευσης</th>
                <th>Σκοπός</th>
                <th>Δανειζόμενος</th>
            </tr>
        </thead>
        <tfoot>
            <tr>
                <th>Aρ Αίτησης Δανείου</th>
                <th>Αιτούμενο Ποσό</th>
                <th>Ημερομηνία Αίτησης</th>
                <th>Ημερομηνία Αποπληρωμής</th>
                <th>Επιτόκιο</th>
                <th>Ημερομηνία Έκταμίευσης</th>
                <th>Σκοπός</th>
                <th>Δανειζόμενος</th>
            </tr>
        </tfoot>
    </table>
    
<table id="commitment" class="display" width="100%" cellspacing="0">
        <thead>
            <tr>
                <th>Aρ Χρηματοδότησης Δανείου</th>
                <th>Αρ Αίτησης Δανείου</th>
                <th>Ημερομηνία Αίτησης</th>
                <th>Ποσό</th>
                <th>Ταυτότητα Δανειστή</th>
            </tr>
        </thead>
        <tfoot>
            <tr>
                <th>Aρ Χρηματοδότησης Δανείου</th>
                <th>Αρ Αίτησης Δανείου</th>
                <th>Ημερομηνία Αίτησης</th>
                <th>Ποσό</th>
                <th>Ταυτότητα Δανειστή</th>
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
                url:  '/microloans/LoanRequest'
            },
            edit: {
                type: 'POST',
                url:  '/microloans/LoanRequest'
            },
            remove: {
                type: 'DELETE',
                url:  '/microloans/LoanRequest',
                
                	
            }
        },
        idSrc: "loanreqid",
        table: "#loanrequest",
        fields: [ {
                label: "AA:",
                name: "loanreqid"
            }, {
            	label: "Αιτούμενο ποσό:",
                name: "loanreqamount"
            }, {
                label: "Ημερομηνία Αίτησης:",
                name: "loanreqdate",
                type: "date"
            }, {
                label: "Ημερομηνία Αποπληρωμής:",
                name: "loanreqdatepay",
                type: "date"
            }, {
                label: "Επιτόκιο:",
                name: "loanreqper"
            }, {
                label: "Ημερομηνία Έκταμίευσης:",
                name: "loanreqpayout"
            }, {
                label: "Σκοπός:",
                name: "loanreqdsc"
    		}, {
        		label: "Δανειζόμενος:",
        		name: "borrowerid"
    }
        ]
    } );

    $('#loanrequest').DataTable( {
        dom: "Bfrtip",
        ajax:{
     "url":"/microloans/LoanRequest",
     "dataSrc":"loanrequests"
        } ,
   //     	"/microloans/LoanRequest",
        columns: [
            { data: "loanreqid" },
            { data: "loanreqamount" },
            { data: "loanreqdate" },
            { data: "loanreqdatepay" },
            { data: "loanreqper" },
            { data: "loanreqpayout" },
            { data: "loanreqdsc" },
            { data: "borrowerid" }
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
<script>
var commiteditor; // use a global for the submit and return data rendering in the examples

$(document).ready(function() {
	commiteditor = new $.fn.dataTable.Editor( {
        ajax: {
            create: {
                type: 'POST',
                url:  '/microloans/Commitment'
                
                	
            }
        },
        idSrc: "commitid",
        table: "#commitment",
        fields: [ {
                label: "Aρ Χρηματοδότησης Δανείου:",
                name: "commitid"
            }, {
            	label: "Αρ Αίτησης Δανείου:",
                name: "commitloanreqid"
            }, {
                label: "Ημερομηνία Αίτησης:",
                name: "commitaggdate",
                type: "date"
            }, {
                label: "Ποσό:",
                name: "commitpropamount"
            }, {
                label: "Ταυτότητα Δανειστή:",
                name: "commitlenderid"
    }
        ]
    } );

    $('#commitment').DataTable( {
        dom: "Bfrtip",
        ajax:{
     "url":"/microloans/Commitment",
     "dataSrc":"commitments"
        } ,
   //     	"/microloans/LoanRequest",
        columns: [
            { data: "commitid" },
            { data: "commitloanreqid" },
            { data: "commitaggdate" },
            { data: "commitpropamount" },
            { data: "commitlenderid" }
        ],
        select: true,
        buttons: [
            { extend: "create", editor: commiteditor }
        ]
    } );
} );
</script>

</body>
</html>
