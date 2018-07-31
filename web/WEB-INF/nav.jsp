<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <c:if test="${isconnected}">
        Bienvenue ${client.nom} ${client.prenom} <a href="Controller?deconnection">se déconnecter</a>
    </c:if>
    <c:if test="${!isconnected}">
        <a href="Controller?connection">se connecter</a>
    </c:if>
</div>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#"><img src="images/librairnet.png" width="50" height="50" alt=""></a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="Controller?all">Afficher tout les livres <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="index.jsp">Accueil</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Dropdown
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    
                    <a class="dropdown-item" href="Controller?section=panier">panier</a>
                    <input type="submit" value="panierbutton" />
                    <a class="dropdown-item" href="#">Another action</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="#">Disabled</a>
            </li>
        </ul> 
        <form action="Controller" method="POST" class="form-inline my-2 my-lg-0">
            <div class="form-group">
                <label for="sel1">Recherche par : </label>
                <select name="select" class="form-control" id="sel1">
                    <option value="tout">Tout</option>
                    <option value="theme">Thème</option>
                    <option value="motscle">Mots Clés</option>
                    <option value="auteur">Auteur</option>
                    <option value="isbn">ISBN</option>
                    <option value="titre">Titre</option>
                </select>
            </div> 
            <input name="mot" class="form-control mr-sm-2" type="search" placeholder="Recherche de livre" aria-label="Search">
            <button name="recherche" class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>

        </form>
    </div>
</nav>