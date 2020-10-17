<%-- 
    Document   : PizzaOrderSummary
    Created on : 07.10.2020, 11:37:32
    Author     : Adrian
--%>

<%@page import="at.htlkaindorf.bl.LanguageSelect"%>
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
        <div id="header">
            <img src="logo.png" alt="logo"/>
            <h1>Pizzeria di Metro</h1>
        </div>
        
        <form action="./" method="POST" id="lang-select">
            <select name="language" onchange="submit()">
                <option <% out.print(LanguageSelect.getCurrent().equals("de")?"selected":""); %>>de</option>
                <option <% out.print(LanguageSelect.getCurrent().equals("en")?"selected":""); %>>en</option>
            </select>
        </form>

        <table>
            <tr>
                <th>Pizza</th>
                <th>Preis</th>
                <th>Stück</th>
                <th>Gesamt</th>
            </tr>
            <%
                Map<Pizza, Integer> order = (Map<Pizza, Integer>)session.getAttribute("order");
                double sum = 0;
                for(Pizza p : order.keySet()) {
                    out.print(String.format("<tr><td>%s</td><td>%s</td><td>%d</td><td>%s</td></tr>", p.getName(), NumberFormat.getCurrencyInstance(Locale.GERMANY).format(p.getPrice()), order.get(p), NumberFormat.getCurrencyInstance(Locale.GERMANY).format(p.getPrice()*order.get(p))));
                    sum+=p.getPrice()*order.get(p);
                }
                out.print(String.format("<tr><td></td><td></td><td>Summe</td><td>%s</td></tr>",NumberFormat.getCurrencyInstance(Locale.GERMANY).format(sum)));
                %>
        </table>
        <p id="order-address">Lieferadresse: <%= session.getAttribute("address") %></p>
        <a href="./"><button id="back">Zurück</button></a>
        
    </body>
</html>
