<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style.css" />
    </head>
    <body>
        
        <h1>Movie Search</h1>
        
        <form action="./" method="POST">
            <input type="text" name="search" />
            <button type="submit">Search!</button>
        </form>
        
        <ul id="movies">
            <c:forEach var="m" items="${movies}">
                <li>
                    <div class="movie-small">
                        <img class="poster" src="${m.poster}" />
                        <div class="movie-info">
                            <p class="title">${m.title}</p>
                            <p class="year">${m.year}</p>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>

        <form action="./" method="POST">
            <button name="page" value="${page-1}" type="submit">-</button>
            <span>${page}/${maxpage}</span>
            <button name="page" value="${page+1}" type="submit">+</button>
        </form>
                            
        <form action="./" method="POST">
            <button type="submit" name="curres" value="${curres+1}">load more</button> 
        </form>

    </body>
</html>
