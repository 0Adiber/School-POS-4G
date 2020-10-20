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
<%
    String language = request.getAttribute("language")==null ? "de" : (String)request.getAttribute("language");
    
    Map<String,String> translations = LanguageSelect.getLanguage(language);
    %>
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
                <option <% out.print(language.equals("de")?"selected":""); %> value="de">Deutsch</option>
                <option <% out.print(language.equals("en")?"selected":""); %> value="en">English</option>
            </select>
            <input type="hidden" name="page" value="summary" />
        </form>

        <table>
            <tr>
                <th><%= translations.get("pizza") %></th>
                <th><%= translations.get("price") %></th>
                <th><%= translations.get("amount") %></th>
                <th><%= translations.get("total") %></th>
            </tr>
            <%
                Map<Pizza, Integer> order = (Map<Pizza, Integer>)session.getAttribute("order");
                double sum = 0;
                for(Pizza p : order.keySet()) {
                    out.print(String.format("<tr><td>%s</td><td>%s</td><td>%d</td><td>%s</td></tr>", p.getName(language), NumberFormat.getCurrencyInstance(Locale.GERMANY).format(p.getPrice()), order.get(p), NumberFormat.getCurrencyInstance(Locale.GERMANY).format(p.getPrice()*order.get(p))));
                    sum+=p.getPrice()*order.get(p);
                }
                out.print(String.format("<tr><td></td><td></td><td>%s</td><td>%s</td></tr>",translations.get("sum"), NumberFormat.getCurrencyInstance(Locale.GERMANY).format(sum)));
                %>
        </table>
        <p id="order-address"> <%= translations.get("address") + ": " + session.getAttribute("address") %></p>
        <div id="backs">
            <a href="./"><button id="back"><%= translations.get("back") %></button></a>
            <a href="./?save"><button id="back"><%= translations.get("backsave") %></button></a>
        </div>
        
    </body>
</html>
