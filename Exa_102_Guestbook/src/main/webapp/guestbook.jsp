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
    </head>
    <body style="background-color: aquamarine;">
        <h1>Welcome to our Guestbook</h1>
        <form action="GuestBookController" method="POST">
            <table border="0">
                <tbody>
                    <tr>
                        <td>Nickname: </td>
                        <td><input type="text" name="nickname" value="Spiderman" /></td>
                    </tr>
                    <tr>
                        <td>Email: </td>
                        <td><input type="text" name="email" value="spiderman@marvel.com" /></td>
                    </tr>
                    <tr>
                        <td>Comment: </td>
                        <td><textarea name="comment" rows="4" cols="25">
Great guestbook
                            </textarea></td>
                    </tr>
                    <input type="submit" value="Send" />
                </tbody>
            </table>
        </form>
        <br />
        <hr>
        <br />
        <% 
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
