<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style type="text/css">
             body{
                
                background-image: url("pictures/lib.jpg");
                background-size: cover;
            }
        </style>
        <link rel="stylesheet" href="FilesCss/SignIn.css">
    </head>
    <body>
        <div class="aa"> 
            <h2>Login</h2>
            <form action="Controller" method="post">
                <input type="text" name="login" placeholder="Please Enter Username..." /><br><br>
                <input type="password" name="password" placeholder="Please Enter Password..." /><br><br>
                <input type="submit" value="Sign In" name="doIt" /><br><br>
                <%--<a href="#">Forget Password !</a>--%>
                <font color='red'>${message}</font>
                <span><a href="Controller?inscription">Inscription</a></span>
                
            </form>
            
        </div>
       
    </body>
    
</html>