<%-- 
    Document   : PizzaOrderSummary
    Created on : 07.10.2020, 11:37:32
    Author     : Adrian
--%>

<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.concurrent.atomic.AtomicInteger"%>
<%@page import="at.htlkaindorf.beans.Pizza"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style.css" />
    </head>
    <body>
        <div class="inner">
            <h1>Pizza le Gut</h1>
            <table>
                <tr>
                    <th>Pizza</th>
                    <th>Preis</th>
                    <th>Stück</th>
                    <th>Gesamt</th>
                </tr>
                <%
                    Map<Pizza, Integer> order = (Map<Pizza, Integer>)session.getAttribute("order");
                    for(Pizza p : order.keySet()) {
                        out.print(p.getName() + " -> " + order.get(p));
                        out.print(String.format("<tr><td>%s</td><td>%s</td><td>%d</td><td>%s</td></tr>", p.getName(), NumberFormat.getCurrencyInstance(Locale.GERMANY).format(p.getPrice()), order.get(p), NumberFormat.getCurrencyInstance(Locale.GERMANY).format(p.getPrice()*order.get(p))));
                    }
                    %>
            </table>
            
            <button><a href="./">Zurück</a></button>
        </div>
    </body>
</html>
