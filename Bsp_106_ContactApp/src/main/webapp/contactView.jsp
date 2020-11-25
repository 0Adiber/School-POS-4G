<%@page import="at.htlkaindorf.beans.Sortings"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <form id="filter" method="POST" action="./">
            <label for="company">
                <p>Company: </p>
                <select name="company">
                    <option value="NONE">NONE</option>
                    <c:forEach var="c" items="${companies}">
                        <option value="${c.name}${c.stockmarket}" ${curcompany == c ? 'selected' : ''}>${c.name} - ${c.stockmarket}</option>
                    </c:forEach>
                </select>
            </label>
            <label for="gender">
                <p>Gender: </p>
                <select name="gender">
                    <option value="NONE">NONE</option>
                    <c:forEach var="g" items="${genders}">
                        <option value="${g}" ${curgender == g ? 'selected' : ''}>${g}</option>
                    </c:forEach>
                </select>
            </label>
            <label for="fname">
                <p>Name: </p>
                <input name="fname" type="text" value="${curname}"/>
            </label>
            <button type="submit">Filter</button>
        
            <table>
                <tr>
                    <th>ID <input type="checkbox" name="sort_ID" onChange="submit();" ${sortStr.contains("ID") ? 'checked':''}/></th>
                    <th>Firstname <input type="checkbox" name="sort_FIRSTNAME" onChange="submit();" ${sortStr.contains("FIRSTNAME") ? 'checked':''}/></th>
                    <th>Lastname <input type="checkbox" name="sort_LASTNAME" onChange="submit();" ${sortStr.contains("LASTNAME") ? 'checked':''}/></th>
                    <th>Gender <input type="checkbox" name="sort_GENDER" onChange="submit();" ${sortStr.contains("GENDER") ? 'checked':''}/></th>
                    <th>Date of Birth <input type="checkbox" name="sort_BIRTH" onChange="submit();" ${sortStr.contains("BIRTH") ? 'checked':''}/></th>
                    <th>Company <input type="checkbox" name="sort_COMPANY" onChange="submit();" ${sortStr.contains("COMPANY") ? 'checked':''}/></th>
                    <th>E-Mail</th>
                </tr>
                <c:forEach var="c" items="${contacts}">
                    <tr>
                        <td>${c.id}</td>
                        <td>${c.firstname}</td>
                        <td>${c.lastname}</td>
                        <td>${c.gender}</td>
                        <td>${c.dateOfBirth}</td>
                        <td>${c.company.name} - ${c.company.stockmarket}</td>
                        <td>
                            <select>
                                <c:forEach var="e" items="${c.email}">
                                    <option>${e}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            
            <button type="submit" name="page" value="${page-1}">&lt;</button>
            <span>${page+1}</span>
            <button type="submit" name="page" value="${page+1}">&gt;</button>
        </form>
    </body>
</html>
