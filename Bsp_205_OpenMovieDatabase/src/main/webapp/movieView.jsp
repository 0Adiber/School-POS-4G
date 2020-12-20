<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="./" method="POST">
            <input type="text" name="search" />
            <button type="submit">Search!</button>
        </form>
        
        <ul>
            <c:forEach var="m" items="${movies}">
                <li>
                    <img src="${m.poster}" width="150px" />
                    <p>${m.title}</p>
                </li>
            </c:forEach>
        </ul>

    </body>
</html>
