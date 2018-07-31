/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.BeanCatalogue;
import Beans.BeanConnexion;
import Beans.Client;
import Beans.Commande;
import Beans.Connexion;
import Beans.LigneCommande;
import Beans.Livre;
import Beans.Search;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    private Cookie getCookie(Cookie[] cookies, String name) {
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(name)) {
                    return c;
                }
            }
        }
        return null;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("isconnected") == null) {
            session.setAttribute("isconnected", false);
        }
        Connexion con = (Connexion) this.getServletContext().getAttribute("connection");
        if (this.getServletContext().getAttribute("connection") == null) {
            con = new Connexion();
            ServletContext application = this.getServletContext();
            application.setAttribute("connection", con);
        }
        //BEAN CONNEXION
        ServletContext application = this.getServletContext();
        BeanConnexion bcApplication = (BeanConnexion) application.getAttribute("beanApplication");
        if (bcApplication == null) {
            bcApplication = new BeanConnexion();
            application.setAttribute("beanApplication", bcApplication);
        }
        //----------------------------------------------------------------------

        //----------JULIEN-------------------//
        String url = "/WEB-INF/index.jsp";

        // String url = "WEB-INF/Catalogue.jsp";
        if ("catalogue".equals(request.getParameter("section"))) {
            url = "/WEB-INF/Catalogue.jsp";
        }
        Livre monLivre = (Livre) request.getAttribute("livre");
        LigneCommande maLigne = (LigneCommande) request.getAttribute("ligne");
        Commande panier = (Commande) session.getAttribute("panier");
        BeanCatalogue catalogue = (BeanCatalogue) request.getAttribute("catalogue");
        if (monLivre == null) {
            monLivre = new Livre();
            request.setAttribute("livre", monLivre);
        }
        if (maLigne == null) {
            maLigne = new LigneCommande();
            request.setAttribute("ligneCommande", maLigne);
        }
        if (panier == null) {
            panier = new Commande();
            session.setAttribute("panier", panier);
        }

        if (request.getParameter("add") != null) {
            monLivre = new Livre(request.getParameter("add"), con);
            maLigne.add(monLivre);
            panier.add(maLigne);
            System.out.println("isbn = " + request.getParameter("add"));
            System.out.println("panier = " + panier.getLigneCommande(request.getParameter("add")));
            
        }

        if ("panier".equals(request.getParameter("section"))) {
            url = "/WEB-INF/Panier.jsp";

        }
        if (request.getParameter("panierbutton") != null) {
            url = "/WEB-INF/Panier.jsp";
        }

        if ("paiement".equals(request.getParameter("section"))) {
            url = "/WEB-INF/Paiement.jsp";
        }
        if (request.getParameter("ajout") != null) {
            monLivre = catalogue.getLivre(request.getParameter("livre"));
            maLigne = new LigneCommande(request.getParameter("livre"), monLivre.getPrix(), monLivre.getEvt().getEveRemise(), monLivre.getTaux().getTva());
            maLigne.setMonLivre(monLivre);
            panier.add(maLigne);

        }
        if (request.getParameter("plus") != null) {
            maLigne = panier.getLigneCommande(request.getParameter("isbnLigne"));
            panier.add(maLigne);
        }
        if (request.getParameter("moins") != null) {
            maLigne = panier.getLigneCommande(request.getParameter("isbnLigne"));
            panier.less(maLigne);
        }
        if (request.getParameter("suppr") != null) {
            maLigne = panier.getLigneCommande(request.getParameter("isbnLigne"));
            panier.delete(maLigne);
        }
        if (request.getParameter("clear") != null) {
            panier.clear();
        }
        request.setAttribute("isEmpty", panier.isEmpty());
        request.setAttribute("listePanier", panier.getPanier());
        request.setAttribute("listeLivres", panier.listerLivrePanier(catalogue));
        request.setAttribute("nbarticle", panier.quantiteTotal());
        request.setAttribute("totHT", panier.prixTotalHt());
        request.setAttribute("totTTC", panier.prixTotalTtc());
        request.setAttribute("totRemise", panier.getRemiseTot());

        if (request.getParameter("valider") != null) {

        }

        //-----------------------FIN JULIEN------------//
        //-----------------------YAVUZ------------//
        //url = "/WEB-INF/index.jsp";
//        if (request.getParameter("search") != null) {
//            url = "/WEB-INF/search.jsp";
//
//        }
        if (request.getParameter("all") != null) {
            url = "/WEB-INF/all.jsp";
            Search sr = new Search();
            //ServletContext application = this.getServletContext();
            //Connexion con = (Connexion) application.getAttribute("connection");
            sr.setSearch(Search.ALL, con);
            request.setAttribute("search", sr.getSearch());
        }

        if (request.getParameter("recherche") != null) {
            url = "/WEB-INF/all.jsp";
            Search sr = new Search();
            //ServletContext application = this.getServletContext();
            //Connexion con = (Connexion) application.getAttribute("connection");
            if ("tout".equals(request.getParameter("select"))) {
                sr.setSearch(Search.ALL, con, request.getParameter("mot"));
            }

            if ("theme".equals(request.getParameter("select"))) {
                sr.setSearch(Search.THEME, con, request.getParameter("mot"));
            }
            if ("motscle".equals(request.getParameter("select"))) {
                sr.setSearch(Search.MOTCLE, con, request.getParameter("mot"));
            }
            if ("auteur".equals(request.getParameter("select"))) {
                sr.setSearch(Search.AUTEUR, con, request.getParameter("mot"));
            }
            if ("isbn".equals(request.getParameter("select"))) {
                sr.setSearch(Search.ISBN, con, request.getParameter("mot"));
            }
            if ("titre".equals(request.getParameter("select"))) {
                sr.setSearch(Search.TITRE, con, request.getParameter("mot"));
            }

            request.setAttribute("search", sr.getSearch());

        }

        //  CONECTION CLIENT :::::////////
        if (request.getParameter("connection") != null) {
            url = "/WEB-INF/connection.jsp";
        }

        if (request.getParameter("connecter") != null) {
            url = "/WEB-INF/index.jsp";
            session.setAttribute("isconnect", false);
            Client client = (Client) session.getAttribute("client");
            if (client == null) {
                client = new Client(request.getParameter("login"), request.getParameter("password"), con);

            }
            if (client.isConnected()) {
                session.setAttribute("client", client);
                session.setAttribute("isconnected", client.isConnected());
            }
        }

        if (request.getParameter("deconnection") != null) {
            url = "/WEB-INF/index.jsp";
            session.removeAttribute("client");
            session.setAttribute("isconnected", false);
        }

        //-----------------------FIN YAVUZ------------//
        request.getRequestDispatcher(url).include(request, response);

    }

    private Connection getinstance() {
        DataSource ds = null;
        Connection connexion = null;
        try {
            InitialContext context = new InitialContext();
            ds = (DataSource) context.lookup("jdbc/Librairnet");
        } catch (NamingException ex) {
//                message = "Problème de base de données";
            System.out.println("Oops 1 Naming:" + ex.getMessage());

        }
        if (connexion == null) {
            try {

                connexion = ds.getConnection();

            } catch (SQLException ex) {
//            message = "Problème de base de données";
                System.out.println("Oops1 Connection:" + ex.getMessage());

            }

        }

        return connexion;

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
