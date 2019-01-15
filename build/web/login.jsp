<%-- 
    Document   : login
    Created on : Jan 10, 2019, 9:04:50 AM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Авторизация</title>
    </head>
    <body>
        <h1>Register!</h1>
        <form action="j_security_check" method="POST">
            Login:<br>
            <input type="text" name="username">
            <br>
            Password:<br>
            <input type="password" name="password">
            <br>
            <input type="submit" values="go">
            
        </form>
    </body>
</html>
