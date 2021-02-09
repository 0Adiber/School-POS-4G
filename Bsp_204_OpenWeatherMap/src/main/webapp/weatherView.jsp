<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Weather</title>
    </head>
    <body>
        <div>
            <h1>${result.temp.value}°C</h1>
            <div>
                <h2>${result.city.name}</h2>
                <p>Real Feel: ${result.feeling.value}</p>
            </div>
        </div>
        <div>
            <h2>Details</h2>
        </div>
    </body>
</html>
