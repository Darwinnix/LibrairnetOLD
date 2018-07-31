<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Librairnet</title>
        <%@include file="/WEB-INF/nav.jsp" %>
        <jsp:include page="Header.jsp"></jsp:include>
        </head>
        <body>
            <div class="container">
                <h5>Accueil > <a style="color: black" href="Controller?section=catalogue">Catalogue</a> > <a style="color: black" href = "Controller?section=panier">Panier</a></h5>
                <hr>
                <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
                <link rel="stylesheet" href="css/css.css">
                <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
                <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
                <div class="row" style="text-align: center;display: flex;align-items:center;border-radius: 4px;">
                    <div class="col-md1">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </div>
                    <div class="col-md-4">
                        titre
                    </div>
                    <div class="col-md-2">
                        quantité
                    </div>
                    <div class="col-md-2">
                        prix hors taxes
                    </div>
                    <div class="col-md-1">
                        TVA
                    </div>
                    <div class="col-md-2">
                        Prix TTC
                    </div>
                </div>
                <hr>
            <c:if test="${isEmpty}">
                <p>Le panier est vide!</p>
            </c:if>
            <c:if test="${!isEmpty}">

                <c:forEach var="lp" items="${listePanier}">
                    <form action="Controller">
                        <div class ="row" style="background-color: powderblue">
                            <div class="col-md-1">
                                <div class="couv"><img src="${lp.monLivre.couverture}" width="50" height="77"/></div>
                                    <c:if test="${lp.monLivre.evt.eveRemise == 10.0}">
                                    <div class="dix"><img src="/LibrairnetWeb/Images/10.png" /></div>
                                    </c:if>
                                    <c:if test="${lp.monLivre.evt.eveRemise == 20.0}">
                                    <div class="vingt"><img src="/LibrairnetWeb/Images/20.png" /></div>
                                    </c:if>
                                    <c:if test="${lp.monLivre.evt.eveRemise == 30.0}">
                                    <div class="trente"><img src="/LibrairnetWeb/Images/30.png" /></div>
                                    </c:if>
                                    <c:if test="${lp.monLivre.evt.eveRemise == 40.0}">
                                    <div class="quarante"><img src="/LibrairnetWeb/Images/40.png" /></div>
                                    </c:if>
                            </div>
                            <div class="col-md-4">
                                <div class="titre">${lp.monLivre.titre}<br>
                                    <div class="auteur" style="font-size: 12px"> (${lp.monLivre.idAuteur})</div>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="row">
                                    <div class="col-md-2">
                                        <div class="qte">${lp.ligQuantite}</div>
                                    </div>
                                    <div class="col-md-6">
                                        <input type="submit" value="+" name="plus" class="more" />
                                        <input type="submit" value="-" name="moins" class="less" />
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="prixHT">${lp.prixHT}€ HT</div>

                            </div>
                            <div class="col-md-1">
                                <div class="tva">${lp.monLivre.taux}%</div>
                            </div>
                            <div class="col-md-2">
                                <div class="prixTTC">${lp.prixTTC}€ TTC</div>
                            </div>
                            <input type="hidden" value="panier" name="section">
                            <input type="hidden" value="${lp.monLivre.isbn}" name="isbnLigne" />
                            <input type="submit" value="X" name="suppr" class="del"/>
                        </div>
                        <br>
                    </form>
                </c:forEach>
                <form action="Controller">
                    <input type="hidden" value="panier" name="section">
                    <input style= "margin-left: 460px;margin-top: 50px; position: absolute; width: 12%" type="submit" value="Vider le panier" name="clear" class="boutonvider"/>
                    <br>
                </form>
                <br>
                <div class="row">
                    <form action="Controller" style="margin-top: -130px">
                        <input type="submit" value="< Poursuivre mes achats" name="retourCat" class="retourCat" />
                        <input type="hidden" value="catalogue" name="section">
                    </form>
                    <form action="Controller" style="margin-top: -130px;">
                        <input type="submit" value="Valider ma commande >" name="validCom" class="validCom"/>
                        <input type="hidden" value="paiement" name="section">
                    </form>
                    <div class="TOT" style="display: flex; align-items: center; margin-top: -60px;">
                        <div class="totaux" style="display: block; float: right; text-align: right">
                            <div class="txtTotal" style="font-size: 26px">total =</div>
                            <div class="txtTotalHt" style="font-size: 20px">total HT =</div>
                            <div class="txtTotalRem" style="font-size: 18px">total remise =</div>
                            <div class="txtnbArticle" style="font-size: 14px">nombre d'article =</div>    
                        </div>
                        <div class="totauxNb" style="display: block; text-align: right; margin: 10px; padding: 10px;">
                            <div class="nbTotal" style="font-size: 26px"> ${totTTC}€</div>
                            <div class="nbTotalHt" style="font-size: 20px"> ${totHT}€</div>
                            <div class="nbTotRem" style="font-size: 18px"> ${totRemise}€</div>
                            <div class="nbArticle" style="font-size: 14px"> ${nbarticle}</div>
                        </div>
                    </div>
                </div>
            </c:if>             
        </div>
        <hr>
    </body>
    <br>
    <jsp:include page="Bottom.jsp"></jsp:include>
</html>
