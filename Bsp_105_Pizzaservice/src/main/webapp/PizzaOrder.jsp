<%-- 
    Document   : PizzaOrder
    Created on : 07.10.2020, 11:27:28
    Author     : Adrian
--%>

<%@page import="at.htlkaindorf.bl.LanguageSelect"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="at.htlkaindorf.beans.Pizza"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PizzaService</title>
        <script src="order.js" type="text/javascript"></script>
        <link rel="stylesheet" href="style.css" />
    </head>
    <body>
        
        <div class="inner">
            <h1>Pizza le Gut</h1>
            <form action="./" method="POST">
                <select name="language" onchange="submit()">
                    <option <% out.print(LanguageSelect.getCurrent().equals("de")?"selected":""); %>>de</option>
                    <option <% out.print(LanguageSelect.getCurrent().equals("en")?"selected":""); %>>en</option>
                </select>
            </form>

            <form action="./" method="POST" onsubmit="return validate()">
                <ul class="pizza-list">
                    <%
                        Map<Pizza,Integer> orders = (HashMap<Pizza,Integer>)session.getAttribute("order");
                        for(Pizza p : (List<Pizza>)application.getAttribute("pizzas")) {
                            int amount = 0;
                            if(orders != null && orders.get(p) != null)
                                amount = orders.get(p);
                            out.println(p.toHTML(amount));
                        }
                        %>
                </ul>
                <div class="address">
                    Lieferadresse: <input id="address" name="address" value="<%= session.getAttribute("address")!=null ? session.getAttribute("address"):"" %>"/>
                    <button type="submit">Bestellen</button>
                </div>
            </form>
        </div>
        
    </body>
</html>
