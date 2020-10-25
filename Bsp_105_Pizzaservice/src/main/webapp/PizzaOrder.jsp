<%-- 
    Document   : PizzaOrder
    Created on : 07.10.2020, 11:27:28
    Author     : Adrian
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="at.htlkaindorf.bl.LanguageSelect"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
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
        <title>PizzaService</title>
        <script src="order.js" type="text/javascript"></script>
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
            <input type="hidden" name="page" value="order" />
        </form>

        <form action="./" method="POST" onsubmit="return validate()">
            <ul class="pizza-list">
                <%--
                    Map<Pizza,Integer> orders = (HashMap<Pizza,Integer>)session.getAttribute("order");
                    for(Pizza p : (List<Pizza>)application.getAttribute("pizzas")) {
                        int amount = 0;
                        if(orders != null && orders.get(p) != null)
                            amount = orders.get(p);
                        out.println(p.toHTML(amount,(String)pageContext.getAttribute("language")));
                    }
                    --%>
                    
                <c:forEach var="p" items="${pizzas}">
                    
                    <c:choose>
                        <c:when test="${order != null && order.get(p) != null}">
                            <c:out escapeXml="false" value="${p.toHTML(order.get(p), language)}" />
                        </c:when>
                        <c:otherwise>
                            <c:out escapeXml="false" value="${p.toHTML(0, language)}" />
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ul>
            <div class="address">
                ${translations.get("address")} <input id="address" name="address" value="<%= session.getAttribute("address")!=null ? session.getAttribute("address"):"" %>" required/>
                <button type="submit" id="bt-order">${translations.get("order")}</button>
            </div>
        </form>
         
        <c:if test="${error != null}">
            <div id="error">${translations.get(error)}</div>
        </c:if>
        <%--
            if(request.getAttribute("error") != null) {
                String err = (String)request.getAttribute("error");
                out.println("<div id=\"error\">" + translations.get(err) + "</div>");
            }
           --%>
    </body>
</html>
