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
        <link rel="stylesheet" href="styles.css">
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
                            <button type="submit" name="unsubscribe" value="${f.url}">unsubscribe</button>
                        </li>
                    </c:forEach>
                </ul>

                <input type="url" name="feed" />

                <button type="submit">subscribe</button>
            </form>
        </c:if>
            
        <c:if test="${result != null}">
            <h2><a href="${result.channel.link}">${result.channel.title}</a></h2>
            <form action="./RssFeedController" method="POST">
                <ul id="articles">
                    <c:forEach items="${result.channel.items}" var="i">
                        <li>
                            <c:if test="${fn:contains(i.enclosure.type, 'image')}">
                                <img src="${i.enclosure.url}" />
                            </c:if>
                            <div class="middle">
                                <p class="title"><a href="${i.link}" target="_blank">${i.title}</a>, ${i.pubDate}</p>
                                <p class="description">${i.description}</p>
                            </div>
                            <div>
                                <c:if test="${i.read == false}">
                                    <button type="submit" name="read" value="${i.guid}"><img src="img/eye-regular.svg"></button>
                                </c:if>
                                <c:if test="${i.read == true}">
                                    <button type="submit" name="unread" value="${i.guid}"><img src="img/eye-slash-regular.svg"></button>
                                </c:if>
                                <button type="submit" name="remove" value="${i.guid}"><img src="img/times-solid.svg"></button>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </form>
        </c:if>
        
    </body>
</html>
