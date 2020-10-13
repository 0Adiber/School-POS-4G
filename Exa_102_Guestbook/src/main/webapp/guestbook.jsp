<%-- 
    Document   : guestbook
    Created on : 24.09.2020, 08:41:39
    Author     : Adrian
--%>

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
                            <%= 
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
        <% 
            //es gibt session satt request
            //application => für application scope
            List<GuestBookEntry> entries = (ArrayList<GuestBookEntry>)request.getAttribute("guestbookEntires");
            if(entries == null) {
                return;
            }
            for(GuestBookEntry e : entries) {
                out.println(e);
            }
            %>
    </body>
</html>
