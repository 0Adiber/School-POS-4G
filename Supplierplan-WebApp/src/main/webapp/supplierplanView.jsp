<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Supplierplan</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <div id="box">
            
            <h1>Supplierplan</h1>
            <h2>${bl.klasse}</h2>
            <form action="./SupplierplanController" method="POST" onsubmit="return validate();">
                <label for="day">
                    <p>Tag: </p>
                    <select name="day" id="day">
                        <c:forEach var="d" items="${days}">
                            <option value="${d}">${d}</option>
                        </c:forEach>
                    </select>
                    <div class="error">${dayError}</div>
                </label>
                <label for="lesson">
                    <p>Stunde: </p>
                    <select name="lesson" id="lesson">
                        <c:forEach begin="1" end="10" var="i">
                            <option value="${i}">${i}</option>
                        </c:forEach>
                    </select>
                    <div class="error">${lessonError}</div>
                </label>
                <label for="subject">
                    <p>Fach: </p>
                    <input name="subject" id="subject" type="text" />
                    <div class="error" id="subject-error">${subjectError}</div>
                </label>
                <label for="teacher">
                    <p>Lehrer: </p>
                    <input name="teacher" id="teacher" type="text" />
                    <div class="error" id="teacher-error">${teacherError}</div>
                </label>
                <input type="submit" value="Übernehmen" />
            </form>

            <table id="timetable">
                <thead>
                    <tr>
                        <th />
                        <c:forEach items="${days}" var="d">
                            <th class="header">${d.toString().substring(0,2)}</th>
                        </c:forEach>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach begin="1" end="10" var="i">
                        <tr>
                            <td>${i}</td>

                            <c:forEach items="${days}" var="d">
                                <c:set var="lesson" value="${bl.getLesson(d,i)}" />
                                <td class="${lesson.supplierung ? 'suppliert' : ''}">
                                    <span class="fach">${lesson.fach}</span><br>
                                    <span class="teacher">${lesson.getTeachers()}</span>
                                </td>
                            </c:forEach>

                        </tr>
                    </c:forEach>
                <tbody>
            </table>
            <br>
            <div class="error">${error}</div>
        
        </div>
    <script src="supplierplan.js"></script>
    </body>
</html>
