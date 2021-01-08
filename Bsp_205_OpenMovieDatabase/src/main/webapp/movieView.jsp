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
        <p>Funktion: Suche -> 10 results; load more -> liefert die nächsten 10; pagination mit 20 / seite (falls genügend geladen); filter & sort auf ganze geladene liste; sort in der reihenfolge, in der angeklickt</p>
        
        <div id="form-search-outer">
            <form action="./" method="POST" id="form-search">
                <input type="text" name="search" />
                <button type="submit">Search!</button>
            </form>
        </div>
        
        <form action="./" method="POST">
            <div id="controls" class="${movies.size() == 0 ? 'hidden' : ''}">
                <div id="controls-inner">
                    <select name="filter" onchange="submit();">
                        <option value="all">All</option>
                        <c:forEach items="${genres}" var="g">
                            <c:if test="${g == filter}">
                                <option value="${g}" selected>${g}</option>
                            </c:if>
                            <option value="${g}">${g}</option>
                        </c:forEach>
                    </select>
                    <p>Title <input type="checkbox" name="sort_TITLE" onchange="submit();" ${sortStr.contains("TITLE") ? 'checked' : ''} /></p>
                    <p>Release Date <input type="checkbox" name="sort_DATE" onchange="submit();" ${sortStr.contains("DATE") ? 'checked' : ''} /></p>
                </div>
            </div>
            
            <ul id="movies">
                <c:forEach var="m" items="${movies}">
                    <li>
                        <div class="movie-small">
                            <img class="poster" src="${m.poster}" />
                            <div class="movie-info">
                                <p class="title">${m.title}</p>
                                <p class="year">${m.year}</p>
                                <p class="type">${m.type}</p>
                                <p><b>Genre(s):</b> <c:forEach items="${m.genre}" var="g">${g},</c:forEach></p>

                                <details>
                                    <div class="details">
                                        <h3>${m.title}</h3> 
                                        <div class="img">
                                            <img src="${m.poster}" />
                                        </div>
                                        <p class="plot">${m.plot}</p>
                                        <br />
                                        <p><b>Release date:</b> ${m.released}</p>
                                        <p><b>Runtime:</b> ${m.runtime}</p>
                                        <p><b>Actor(s):</b> ${m.actors}</p>
                                        <p><b>Language(s):</b> ${m.language}</p>
                                        <p><b>Director(s):</b> ${m.director}</p>
                                        <p><b>Writer(s):</b> ${m.writer}</p>
                                        <br />
                                        <table>
                                            <thead>
                                                <th></th>
                                                <c:forEach items="${m.ratings}" var="r">
                                                    <th>${r.source}</th>
                                                </c:forEach>
                                            </thead>
                                            <tr>
                                                <td><b>Rating</b></td>
                                                <c:forEach items="${m.ratings}" var="r">
                                                    <td>${r.value}</td>
                                                </c:forEach>
                                            </tr>
                                        </table>
                                    </div>
                                </details>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>

            <div id="navigation" class="${movies.size() == 0 ? 'hidden' : ''}">
                <button name="page" value="${page-1}" type="submit">-</button>
                <span>${page}/${maxpage}</span>
                <button name="page" value="${page+1}" type="submit">+</button>

                <button type="submit" name="curres" value="${curres+1}">load more</button> 
            </div>
        </form>

    </body>
</html>
