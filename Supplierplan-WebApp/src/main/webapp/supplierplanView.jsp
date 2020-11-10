<%-- 
    Document   : supplierplanView
    Created on : 10.11.2020, 08:57:16
    Author     : Papa
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Supplierplan</title>
    </head>
    <body>
        <h1>Supplierplan</h1>
        <h2>${bl.klasse}</h2>
        <form action="SupplierplanController" method="POST">
            <p>Tag: </p>
        </form>
        
        <table id="timetable">
            <thead>
                <tr>
                    <th />
                    <c:forEach items="${days}" var="d">
                        
                    </c:forEach>
                </tr>
            </thead>
        </table>
        
    </body>
</html>
