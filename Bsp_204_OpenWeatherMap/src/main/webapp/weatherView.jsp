<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Weather</title>
        <link rel="stylesheet" href="styles.css" />
        <link rel="stylesheet" href="rain.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>
        
        <c:if test="${result == null}">
            <c:if test="${error != null}">
                <div class="error">
                    ${error}
                </div>
            </c:if>
            <div id="default">
                <div id="rain">
                    <div class="rain front-row"></div>
                    <div class="rain back-row"></div>
                </div>
                <div id="def-search">
                    <form action="./" method="POST">
                        <input class="search" name="city" type="text" placeholder="Heiligenkreuz am Waasen" value="${city}" />
                    </form>
                </div>
            </div>
        </c:if>
        <c:if test="${result != null}">
            <div id="img" style="background-image: url('img/${img}.jpg');"></div>
            <div id="search">
                <form action="./" method="POST">
                    <input class="search" name="city" type="text" placeholder="Heiligenkreuz am Waasen" value="${city}" />
                    <div id="language">
                        ${lang == "de" ? 'Sprache' : 'Language'}: <select name="lang" onchange="submit();">
                            <option value="de" ${lang == "de" ? 'selected': ''}>DE</option>
                            <option value="en" ${lang == "en" ? 'selected': ''}>EN</option>
                        </select>
                    </div>
                </form>
            </div>
            <div id="upper">
                <div class="inner">
                    <div class="flex-row">
                        <h1>${Math.round(result.temp.value*10)/10}°C</h1>
                        <div >
                            <h2>${result.city.name}</h2>
                            <p>Real Feel: ${Math.round(result.feeling.value*10)/10}</p>
                            <p>${result.weather.value}</p>
                        </div>    
                    </div>
                </div>
            </div>
            <div id="lower">
                <div><img src="http://openweathermap.org/img/wn/${result.weather.icon}@2x.png" /></div>
                <div class="right">
                    <div>
                        <p>Wind</p>
                        <p>${Math.round(result.wind.speed.value * 36)/10} km/h ➜ ${result.wind.direction.code}</p>
                    </div>
                    <div>
                        <p>${lang == "de" ? 'Luftfeuchtigkeit' : 'Humidity'}</p>
                        <p>${result.humidity.value}%</p>
                    </div>
                    <div>
                        <p>${lang == "de" ? 'Wolken' : 'Clouds'}</p>
                        <p>${result.clouds.value}%</p>
                    </div>
                    <div>
                        <p>${lang == "de" ? 'Luftdruck' : 'Pressure'}</p>
                        <p>${result.pressure.value} ${result.pressure.unit}</p>
                    </div>
                </div>
            </div>
        </c:if>
    </body>
    <script src="rain.js"></script>
</html>
