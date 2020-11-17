<%-- 
    Document   : guestbook
    Created on : 24.09.2020, 08:41:39
    Author     : Adrian
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="at.htlkaindorf.guestbook.beans.GuestBookEntry"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="guestbook.js" type="text/javascript"></script>
    </head>
    <%!
        //wird statisch 
    %>
    <body style="background-color: aquamarine;">
        
        <h1>Welcome to our Guestbook</h1>
        <form action="GuestBookController" method="POST" onsubmit="return validate()">
            <table border="0">
                <tbody>
                    <tr>
                        <td>Nickname: </td>
                        <td><input type="text" name="nickname" value="Spiderman" id="nickname"/></td>
                    </tr>
                    <tr>
                        <td>Email: </td>
                        <td><input type="text" name="email" value="spiderman@marvel.com" id="email" /></td>
                    </tr>
                    <tr>
                        <td>Comment: </td>
                        <td><textarea name="comment" rows="4" cols="25" id="comment">
Great guestbook
                            </textarea>
                            <%=String.format("JSP ist ein HURENSOHn") 
                                //fügt string ein z.B. String.format(..)
                            %></td>
                    </tr>
                    <input type="submit" value="Send" />
                </tbody>
            </table>
        </form>
        <br />
        <hr>
        <br />
        <%-- 
            //es gibt session satt request
            //application => für application scope
            List<GuestBookEntry> entries = (ArrayList<GuestBookEntry>)request.getAttribute("guestbookEntires");
            if(entries == null) {
                return;
            }
            for(GuestBookEntry e : entries) {
                //out.println(e);
            }
            --%>
            
            <c:forEach var="i" begin="50" end="100" step="5">
                ${i mod 10 == 0}
            </c:forEach>
            he
            ${guestbookEntries[0] < guestbookEntries[1]}
                                                                             <!-- sessionScope oder applicationScope auch -->
            <c:forEach var="entry" items="${requestScope.guestbookEntries}"> <!--requestScope muss man nicht angeben; wenn weggelassen dann sucht er-->
                <c:if test="${entry.nickname != ''}">                        <!-- falls guestbookEntries null, dann keine ausgabe, aber keine exception! -->
                    ${entry.nickname} says: ${entry.comment}<br>
                </c:if>
            </c:forEach>
                    <br>        
            Cookie: ${cookie.JSESSIONID.value}
            Cookie: ${cookie['JSESSIONID'].value} <!-- bei maps kann man so oder so zugreifen -->
            <br>
            <ul>
            <c:forEach var="c" items="${cookie}">
                <li>key: ${c.key} - value: ${c.value.value}</li>
            </c:forEach>
            </ul>
            
            <c:forTokens var="str" items="${'17.11.2020 10:37'}" delims=". :"> <!-- mehrere delimiter möglich -->
                <c:out value="${st}" escapeXml="false">unknown</c:out> <!-- "unknown" is der default wert, falls das andere null -->
                <%-- ${str} <br> --%>
            </c:forTokens>
                
            <c:set var="salary" scope="session" value="1234.56"></c:set>
            
            <!-- c:if  c:otherwise -->
    </body>
</html>
