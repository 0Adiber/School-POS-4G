<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <tr>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Gender</th>
                <th>Date of Birth</th>
                <th>Company</th>
            </tr>
            <c:forEach var="i" begin="1" end="30">
                <c:catch var="e">
                    <c:set var="c" value="${contacts.get((page*30)+i -1)}" />
                </c:catch>
                <c:if test="${e == null}">
                    <tr>
                        <td>${c.id}</td>
                        <td>${c.firstname}</td>
                        <td>${c.lastname}</td>
                        <td>${c.gender}</td>
                        <td>${c.dateOfBirth}</td>
                        <td>${c.company.name}</td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
        <form method="POST" action="./">
            <button type="submit" name="page" value="${page-1}">&lt;</button>
            <span>${page+1}</span>
            <button type="submit" name="page" value="${page+1}">&gt;</button>
        </form>
    </body>
</html>
