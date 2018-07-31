
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link rel="stylesheet" href="css/css.css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->

        <div class="col-md-6 offset-md-3">
            <span class="anchor" id="formPayment"></span>
            <hr class="my-5">

            <!-- form card cc payment -->
            <div class="card card-outline-secondary">
                <div class="card-body">
                    <h3 class="text-center">Paiement par carte</h3>
                    <hr>
                    <div class="alert alert-info p-2 pb-3">
                        <a class="close font-weight-normal initialism" data-dismiss="alert" href="#"><samp>×</samp></a> 
                        code CVC requis.
                    </div>
                    <form class="form" role="form" autocomplete="off">
                        <div class="form-group">
                            <label for="cc_name">Nom du titulaire</label>
                            <input type="text" class="form-control" id="cc_name" pattern="\w+ \w+.*" title="Prénom et Nom" required="requis">
                        </div>
                        <div class="form-group">
                            <label>Numéro de carte</label>
                            <input type="text" class="form-control" autocomplete="off" maxlength="20" pattern="\d{16}" title="Numéro de carte de crédit" required="">
                        </div>
                        <div class="form-group row">
                            <label class="col-md-12">Date d'expiration</label>
                            <div class="col-md-4">
                                <select class="form-control" name="cc_exp_mo" size="0">
                                    <option value="01">01</option>
                                    <option value="02">02</option>
                                    <option value="03">03</option>
                                    <option value="04">04</option>
                                    <option value="05">05</option>
                                    <option value="06">06</option>
                                    <option value="07">07</option>
                                    <option value="08">08</option>
                                    <option value="09">09</option>
                                    <option value="10">10</option>
                                    <option value="11">11</option>
                                    <option value="12">12</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <select class="form-control" name="cc_exp_yr" size="0">
                                    <option>2018</option>
                                    <option>2019</option>
                                    <option>2020</option>
                                    <option>2021</option>
                                    <option>2022</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <input type="text" class="form-control" autocomplete="off" maxlength="3" pattern="\d{3}" title="Cryptogramme au dos de la carte" required="" placeholder="CVC">
                            </div>
                        </div>
                        <div class="row">
                            <label class="col-md-12">Montant</label>
                        </div>
                        <div class="form-inline">
                            <div class="input-group">
                                <div class="input-group-prepend"><span class="input-group-text">€</span></div>
                                <input type="text" class="form-control text-right" id="exampleInputAmount" placeholder="${totTTC}">

                            </div>
                        </div>
                        <hr>
                        <div class="form-group row">
                            <div class="col-md-6">
                                <button type="reset" class="btn btn-default btn-lg btn-block">Effacer</button>
                            </div>
                            <div class="col-md-6">
                                <button type="submit" class="btn btn-success btn-lg btn-block">Valider</button>
                            </div>
                        </div>
                    </form>
                    <div class="form-group row">
                        <div class="col-md-12">
                            <form action="Controller">
                                <input type="hidden" value="panier" name="section">
                                <input class="AnnulerPaiement" type="submit" value="Annuler"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /form card cc payment -->
    </body>
</html>
