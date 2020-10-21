<%-- 
    Document   : PizzaOrderSummary
    Created on : 07.10.2020, 11:37:32
    Author     : Adrian
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="at.htlkaindorf.bl.LanguageSelect"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.concurrent.atomic.AtomicInteger"%>
<%@page import="at.htlkaindorf.beans.Pizza"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- to use LanguageSelect: -->
<jsp:useBean id="langsel" class="at.htlkaindorf.bl.LanguageSelect"/>

<!DOCTYPE html>
<c:set scope="page" var="language" value="${requestScope.language == null ? 'de' : requestScope.language}" />
<c:set scope="page" var="translations" value="${langsel.getLanguage(language)}" />

<%
    //String language = request.getAttribute("language")==null ? "de" : (String)request.getAttribute("language");
    
    //Map<String,String> translations = LanguageSelect.getLanguage((String)pageContext.getAttribute("language"));
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
                <option <c:if test="${pageScope.language == 'de'}">selected</c:if> value="de">Deutsch</option>
                <option <c:if test="${pageScope.language == 'en'}">selected</c:if> value="en">English</option>
            </select>
            <input type="hidden" name="page" value="summary" />
        </form>

        <table>
            <tr>
                <th>${translations.get("pizza")}</th>
                <th>${translations.get("price")}</th>
                <th>${translations.get("amount")}</th>
                <th>${translations.get("total")}</th>
            </tr>
            <fmt:setLocale value="de_DE" />
            <c:forEach var="p" items="${order.keySet()}">
                <tr><td>${p.getName(language)}</td><td><fmt:formatNumber value="${p.price}" type="currency" /></td><td>${order.get(p)}</td><td><fmt:formatNumber value="${p.price * order.get(p)}" type="currency" /></td></tr>
                <c:set scope="page" var="sum" value="${sum+p.getPrice()*order.get(p)}"></c:set>
            </c:forEach>
                <tr><td></td><td></td><td>${translations.get("sum")}</td><td><fmt:formatNumber value="${sum}" type="currency" /></td></tr>
            <%--
                Map<Pizza, Integer> order = (Map<Pizza, Integer>)session.getAttribute("order");
                double sum = 0;
                for(Pizza p : order.keySet()) {
                    out.print(String.format("<tr><td>%s</td><td>%s</td><td>%d</td><td>%s</td></tr>", p.getName(language), NumberFormat.getCurrencyInstance(Locale.GERMANY).format(p.getPrice()), order.get(p), NumberFormat.getCurrencyInstance(Locale.GERMANY).format(p.getPrice()*order.get(p))));
                    sum+=p.getPrice()*order.get(p);
                }
                out.print(String.format("<tr><td></td><td></td><td>%s</td><td>%s</td></tr>",translations.get("sum"), NumberFormat.getCurrencyInstance(Locale.GERMANY).format(sum)));
                --%>
        </table>
                <p id="order-address"> ${translations.get("address")}: ${address}</p>
        <div id="backs">
            <a href="./"><button id="back">${translations.get("back")}</button></a>
            <a href="./?save"><button id="back">${translations.get("backsave")}</button></a>
        </div>
        
    </body>
</html>
