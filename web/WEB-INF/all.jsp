<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">




        <link rel="stylesheet" href="css/css.css">

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>


        <title>JSP Page</title>
    </head>

    <body>
        <%@include file="/WEB-INF/nav.jsp" %>

        <div class="flex-container">
            <c:forEach var="l" items="${search}">

                <div class="card" style="width: 10rem;">

                    <img height="200px" width="70px" class="card-img-top" src="${l.couverture}" alt="${l.titre}">

                    <div></div>
                    <div class="card-body">
                        <h5 class="card-title">${l.titre}</h5>
                        <p class="card-text">${l.sousTitre}</p>

                    </div>
                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#a${l.isbn}"><i class="fa fa-search"></i><span class="glyphicon glyphicon-eye-open"></span> Détaille</button>
                    <a href="Controller?add=${l.isbn}" class="btn btn-primary"><span class="glyphicon  glyphicon-shopping-cart"></span> Ajouter au panier</a>
                </div>

                <div class="modal fade product_view" id="a${l.isbn}">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <a href="#" data-dismiss="modal" class="class pull-right"><span class="glyphicon glyphicon-remove"></span></a>
                                <h3 class="modal-title">${l.titre}</h3>
                            </div>
                            <div class="modal-body">
                                <div class="row">
                                    <div class="col-md-6 product_img">
                                        <img src="${l.couverture}" class="img-responsive">
                                    </div>
                                    <div class="col-md-6 product_content">
                                        <h4><span>${l.sousTitre}</span></h4>

                                        <h5>Auteur: <span>${l.auteur}</span></h5>
                                        <h5>Editeur: <span>${l.editeur}</span></h5>

                                        <p>${l.resume}</p>
                                        <h3 class="cost"><span class="glyphicon glyphicon-euro"></span> ${l.prix} </h3>


                                        <h6>ISBN: <span>${l.isbn}</span></h6>
                                        <div class="ratings">
                                            <p></p>
                                            <p>
                                                ${l.etoile}

                                            </p>
                                        </div>
                                        <div class="space-ten"></div>
                                        <div class="btn-ground">
                                            <button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-shopping-cart"></span> Add To Cart</button>
                                            <button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-heart"></span> Add To Wishlist</button>
                                        </div>

                                        <c:forEach var="cr" items="${l.critiques}">

                                            <div class="media comment-box">

                                                <div class="media-body">
                                                    <h4 class="media-heading">${cr.nom} </h4>
                                                    <p><c:if test="${!cr.videNote}">
                                                             Note: ${cr.note}/5
                                                             <br>
                                                        </c:if>
                                                        <c:if test="${!cr.videCommentaire}">
                                                            ${cr.commentaire}
                                                        </c:if>
                                                       </p>
                                                </div>
                                            </div>

                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                </div>
            </c:forEach>
        </div>
        <%@include file="/WEB-INF/Bottom.jsp" %>
    </body>
</html>
