<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Weather</title>
        <link rel="stylesheet" href="styles.css" />
    </head>
    <body>
        <div id="img" style="background-image: url('img/${img}.jpg');">
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
            <h2>Details</h2>
            <p>Wind ${result.wind.direction.code} ${Math.round(result.wind.speed.value * 36)/10} km/h</p>
            <p>Humidity ${result.humidity.value}%</p>
            <p>Cloudy ${result.clouds.value}%</p>
            <p>Pressure ${result.pressure.value} ${result.pressure.unit}</p>
        </div>
    </body>
</html>
