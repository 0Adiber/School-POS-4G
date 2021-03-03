<%-- 
    Document   : feed
    Created on : 03.03.2021, 11:41:40
    Author     : Papa
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="./RssFeedController" method="POST">
            <select name="feed">
                <c:forEach items="${allfeeds}" var="f">
                    <option value="${f.id}">${f.title}</option>
                </c:forEach>
            </select>
            <button type="submit" name="subscribe" value="1">subscribe</button>
            <button type="submit" name="subscribe" value="-1">unsubscribe</button>
        </form>
        
    </body>
</html>
