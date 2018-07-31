
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <ul class="menuHeader">
            <div class="imgHeader">
                <img src="/LibrairnetWeb/Images/Header.png" width="890" height="150" alt="Header"/>
            </div>
            <form action="formHeader"> 
                <div class ="boutonCompte">
                    <input type="submit" value="Mon compte" name="compte" class="boutonCompte" />
                </div>

                <div class="boutonCreerCompte">
                    <input type="submit" value="Créer un compte" name="creerCompte" class="boutonCreerCompte" />
                </div>
            </form>
            <form action ="Controller" 
                  <div class="boutonPanier">
                    <input type="hidden" name="section" value="panier"/>
                    <input type="submit" value="Panier" name="sizePanier" class="boutonPanier" />
                </div>
                  </form>
                  <div class="compteurPanier">
                    ${nbarticle}
                </div>
            


        </ul>
    </body>
</html>
