<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link href="css/css.css" rel="stylesheet">
        
        
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
    </head>
    <body id="LoginForm">
        <%@include file="nav.jsp" %>
        <div class="container">
            <h1 class="form-heading">login Form</h1>
            <div class="login-form">
                <div class="main-div">
                    <div class="panel">
                        <h2>Connection</h2>
                        <p>Veuillez entrer votre login et mot de passe</p>
                    </div>
                    <form action="Controller" method="POST" id="Login">

                        <div class="form-group">


                            <input name="login" type="text" class="form-control" id="inputEmail" placeholder="Login">

                        </div>

                        <div class="form-group">

                            <input name="password" type="password" class="form-control" id="inputPassword" placeholder="Mot de passe">

                        </div>
                        
                        <button name="connecter" type="submit" class="btn btn-primary">Login</button>

                    </form>
                </div>
                <p class="botto-text"> Designed by Sunil Rajput</p>
            </div></div></div>


</body>
</html>

