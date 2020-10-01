<%-- 
    Document   : studentView
    Created on : 28.09.2020, 08:44:22
    Author     : Papa
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="at.htlkaindorf.beans.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Schülerliste</title>
        <link rel="stylesheet" href="style.css" />
    </head>
    <body>
        <form action="StudentController" method="POST">
            Filter: <input type="text" name="filter" value="<% request.getAttribute("filter"); %>"/>
            <input type="submit" name="set_filter" value="Filter setzten" />
            <input type="submit" name="remove_filter" value="Filter entfernen" />
            <div>Schüler auswählen: <select name="uid" onchange="submit();">
                <% List<Student> students = (ArrayList)request.getAttribute("students");
                    if(students == null)
                        return;
                        
                    Student current = ((Student)request.getAttribute("student"));
                    
                    for(Student s : students) {
                        String selected = current == null ? "" : current.getUid() == s.getUid() ? "selected" : "";
                        out.println(String.format("<option value=\"%d\" %s>%s %s</option>", s.getUid(), selected, s.getLastname(), s.getFirstname()));
                    }
                %>
            </select></div>
        </form>
        
            <% Student student = (Student)request.getAttribute("student");
                if(student == null) return;
                out.println("<div class=\"student\">");
                out.println("<img src=\"business.jpg\" alt=\"pfp\" width=\"200\"/>");
                out.println("<table>");
                out.println(String.format("<tr><td>Name: </td> <td>%s %s</td></tr>", student.getLastname(), student.getFirstname()));
                out.println(String.format("<tr><td>Katalognummer: </td> <td>%d</td></tr>", student.getCatnr()));
                out.println(String.format("<tr><td>Klasse: </td> <td>%s</td></tr>", student.getKlasse()));
                out.println(String.format("<tr><td>Geschlecht: </td> <td>%s</td></tr>", student.getGeschlecht()));
                out.println(String.format("<tr><td>Geburtsdatum </td> <td>%s</td></tr>", student.DTF.format(student.getBirth())));
                out.println("</table></div>");
                %>
    </body>
</html>
