<%-- 
    Document   : feed
    Created on : 03.03.2021, 11:41:40
    Author     : Papa
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${error != null}">
            <h3>Error: ${error}</h3>
        </c:if>
        
        <c:if test="${result == null}">
            <form action="./RssFeedController" method="POST">

                <ul>
                    <c:forEach items="${feeds}" var="f">
                        <li>
                            <a href="${f.url}">${f.title}</a>
                            <button type="submit" name="rss" value="${f.url}">OPEN</button>
                        </li>
                    </c:forEach>
                </ul>

                <input type="url" name="feed" />

                <button type="submit">subscribe</button>
            </form>
        </c:if>
            
        <c:if test="${result != null}">
            <h2><a href="${result.channel.link}">${result.channel.title}</a></h2>
            <ul>
                <c:forEach items="${result.channel.items}" var="i">
                    <li>
                        <c:if test="${fn:contains(i.enclosure.type, 'image')}">
                            <img src="${i.enclosure.url}" />
                        </c:if>
                        <p class="title"><a href="${i.link}">${i.title}</a>, ${i.pubDate}</p>
                        <p class="description">${i.description}</p>
                    </li>
                </c:forEach>
            </ul>
        </c:if>
        
    </body>
</html>
