<%@page import="at.htlkaindorf.beans.Sortings"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contacts</title>
        <link rel="stylesheet" href="styles.css" />
    </head>
    <body>
        <h1>Contacts</h1>
        <h2 id="help" title="Sortierung wird in der Reihenfolge, in der sie angeklickt wurde angewandt">hover me</h2>
        
        <form method="POST" action="./">
            <div id="filters">
                <div class="filter-name" id="company-label"><p>Company: </p></div>
                <select name="company" class="filter-select" id="copmany-select">
                    <option value="NONE">ALL</option>
                    <c:forEach var="c" items="${companies}">
                        <option value="${c.name}${c.stockmarket}" ${curcompany == c ? 'selected' : ''}>${c.name} - ${c.stockmarket}</option>
                    </c:forEach>
                </select>
                <div class="filter-name" id="gender-label"><p>Gender: </p></div>
                <select name="gender" class="filter-select" id="gender-select">
                    <option value="NONE">ALL</option>
                    <c:forEach var="g" items="${genders}">
                        <option value="${g}" ${curgender == g ? 'selected' : ''}>${g}</option>
                    </c:forEach>
                </select>
                <div class="filter-name" id="name-label"><p>Name: </p></div>
                <input name="fname" type="text" value="${curname}" class="filter-input" id="name-input"/>
                <button type="submit" id="filter-bt">Filter</button>
            </div>
        
            <table>
                <tr>
                    <th></th>
                    <th>ID</th>
                    <th>Firstname <input type="checkbox" name="sort_FIRSTNAME" onChange="submit();" ${sortStr.contains("FIRSTNAME") ? 'checked':''}/></th>
                    <th>Lastname <input type="checkbox" name="sort_LASTNAME" onChange="submit();" ${sortStr.contains("LASTNAME") ? 'checked':''}/></th>
                    <th>Gender <input type="checkbox" name="sort_GENDER" onChange="submit();" ${sortStr.contains("GENDER") ? 'checked':''}/></th>
                    <th>Date of Birth <input type="checkbox" name="sort_BIRTH" onChange="submit();" ${sortStr.contains("BIRTH") ? 'checked':''}/></th>
                    <th>Company <input type="checkbox" name="sort_COMPANY" onChange="submit();" ${sortStr.contains("COMPANY") ? 'checked':''}/></th>
                    <th>E-Mail</th>
                    <th></th>
                </tr>
                <c:set var="i" value="0" />
                <c:forEach var="c" items="${contacts}">
                    <tr>
                        <td><input type="checkbox" value="${c.id}" name="c_list_${i}"/></td>
                        <td>${c.id}</td>
                        <td>${c.firstname}</td>
                        <td>${c.lastname}</td>
                        <td>${c.gender}</td>
                        <td>${c.dateOfBirth}</td>
                        <td>${c.company.name} - ${c.company.stockmarket}</td>
                        <td>
                            <select class="email">
                                <c:forEach var="e" items="${c.email}">
                                    <option>${e}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${c.favorite == true}">
                                    <svg color="rgba(194,80,193,1)" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="star" class="svg-inline--fa fa-star fa-w-18" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512"><path fill="currentColor" d="M259.3 17.8L194 150.2 47.9 171.5c-26.2 3.8-36.7 36.1-17.7 54.6l105.7 103-25 145.5c-4.5 26.3 23.2 46 46.4 33.7L288 439.6l130.7 68.7c23.2 12.2 50.9-7.4 46.4-33.7l-25-145.5 105.7-103c19-18.5 8.5-50.8-17.7-54.6L382 150.2 316.7 17.8c-11.7-23.6-45.6-23.9-57.4 0z"></path></svg>
                                </c:when>
                                <c:otherwise>
                                    <svg color="#4d4d4d" aria-hidden="true" focusable="false" data-prefix="far" data-icon="star" class="svg-inline--fa fa-star fa-w-18" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512"><path fill="currentColor" d="M528.1 171.5L382 150.2 316.7 17.8c-11.7-23.6-45.6-23.9-57.4 0L194 150.2 47.9 171.5c-26.2 3.8-36.7 36.1-17.7 54.6l105.7 103-25 145.5c-4.5 26.3 23.2 46 46.4 33.7L288 439.6l130.7 68.7c23.2 12.2 50.9-7.4 46.4-33.7l-25-145.5 105.7-103c19-18.5 8.5-50.8-17.7-54.6zM388.6 312.3l23.7 138.4L288 385.4l-124.3 65.3 23.7-138.4-100.6-98 139-20.2 62.2-126 62.2 126 139 20.2-100.6 98z"></path></svg>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <c:set var="i" value="${i+1}" />
                </c:forEach>
            </table>
            
            <div id="controls">
                <button type="submit" name="page" value="${page-1}" class="page-bt" id="pagedown">&lt;</button>
                <div id="page"><p>${page+1}/${maxpage+1}</p></div>
                <button type="submit" name="page" value="${page+1}" id="pageup">&gt;</button>
                <button type="submit" name="action" value="delete" class="action-bt" id="delete">Delete</button>
                <button type="submit" name="action" value="favorite" class="action-bt" id="favorite">Make favorite</button>
                <button type="submit" name="action" value="savefavorite" id="save-bt">Save favorite</button>
            </div>
        </form>
    </body>
</html>
