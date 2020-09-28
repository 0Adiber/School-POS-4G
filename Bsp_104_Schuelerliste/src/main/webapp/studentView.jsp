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
    </head>
    <body>
        <form action="StudentController" method="POST">
            Filter: <input type="text" name="filter" value="<% request.getAttribute("filter"); %>"/>
            <input type="submit" name="set_filter" value="Filter setzten" />
            <input type="submit" name="remove_filter" value="Filter entfernen" />
            Schüler auswählen: <select name="uid" onchange="submit();">
                <% List<Student> students = (ArrayList)request.getAttribute("students");
                    if(students == null)
                        return;
                    
                    for(Student s : students) {
                        out.println(s);
                    }
                    %>
            </select>
        </form>
        <% Student student = (Student)request.getAttribute("student");
            if(student == null) return;
            out.println(student.getFirstname());
            %>
    </body>
</html>
