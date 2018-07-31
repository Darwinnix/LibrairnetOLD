package Beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Commande implements Serializable {

    private int comNum;
    private int cliId;
    private int adrIdLivraison;
    private int adrIdFacturation;
    private Date comDate;
    private int comStatut;
    private String comObservation;
    private float comFraisPort;
    private String comIp;
    private HashMap<String, LigneCommande> panier = null;
    private int tot;
    private double prixTotHt;
    private double prixTotTtc;
    private double remiseTot;

    //constructeur
    public Commande() {
        this.panier = new HashMap<>();
    }

    //methodes
    public void change(LigneCommande maLigne, int qty) {
        maLigne.setLigQuantite(maLigne.getLigQuantite() + qty);
    }

    public void add(LigneCommande ligne, int qty) {
        if (panier.containsKey(ligne.getLivIsbn())) {
            ligne = panier.get(ligne.getLivIsbn());
            change(ligne, qty);
        } else {
            panier.put(ligne.getLivIsbn(), ligne);
            ligne.setLigQuantite(1);
        }
        if (ligne.getLigQuantite() < 1) {
            delete(ligne);
        }
    }

    public void add(LigneCommande ligne, String qty) {
        int qte = Integer.parseInt(qty);
        add(ligne, qte);
    }

    public void add(LigneCommande ligne) {
        add(ligne, 1);
    }

    public void less(LigneCommande ligne) {
        add(ligne, -1);
    }

    public void less(LigneCommande ligne, int qty) {
        add(ligne, -qty);
    }

    public void delete(LigneCommande ligne) {
        panier.remove(ligne.getLivIsbn());
    }

    public void clear() {
        panier.clear();
    }

    public boolean isEmpty() {
        return panier.isEmpty();
    }

    public int size() {
        return panier.size();
    }

    public int quantiteTotal() {
        tot = 0;
        for (Map.Entry<String, LigneCommande> col : panier.entrySet()) {
            tot = tot + col.getValue().getLigQuantite();
        }
        return tot;
    }

    public double prixTotalHt() {
        prixTotHt = 0;
        for (Map.Entry<String, LigneCommande> col : panier.entrySet()) {
            prixTotHt = Math.rint((prixTotHt + col.getValue().getPrixHT()) * 100) / 100;
        }
        return prixTotHt;
    }

    public double prixTotalTtc() {
        prixTotTtc = 0;
        for (Map.Entry<String, LigneCommande> col : panier.entrySet()) {
            prixTotTtc = Math.rint((prixTotTtc + col.getValue().getPrixTTC()) * 100) / 100;
        }
        return prixTotTtc;
    }

    public double getRemiseTot() {
        remiseTot = 0;
        for (Map.Entry<String, LigneCommande> col : panier.entrySet()) {
            remiseTot = Math.rint((remiseTot + (col.getValue().getLigPrix() * (col.getValue().getLigRemise() / 100)) * col.getValue().getLigQuantite()) * 100) / 100;
        }
        System.out.println(remiseTot);
        return remiseTot;
    }

    public Collection getPanier() {
        return panier.values();
    }

    public ArrayList<LigneCommande> listerLivrePanier(BeanCatalogue cat) {
        BeanCatalogue c = new BeanCatalogue();
        ArrayList<LigneCommande> listeLivre = new ArrayList();
        for (Map.Entry<String, LigneCommande> col : panier.entrySet()) {
            int qte = col.getValue().getLigQuantite();
            Livre monLivre = c.getLivre(col.getKey());
            LigneCommande laLigne = new LigneCommande(monLivre, qte);
            listeLivre.add(laLigne);
        }
        return listeLivre;
    }

    public LigneCommande getLigneCommande(String isbn) {
        LigneCommande maLigne = panier.get(isbn);
        return maLigne;
    }

    public void validerCommande(Connection co) {

        try {
            String request = "INSERT INTO Commande(cliId, adrIdLivraison, adrIdFacturation, comDate, comFraisPort, comIp)"
                    + " Values (?,?,?,?,?,?)";
            try (PreparedStatement pst = co.prepareStatement(request)) {
                pst.setInt(1, 0);
                pst.setInt(2, 0);
                pst.setInt(3, 0);
                pst.setDate(4, new java.sql.Date(System.currentTimeMillis()));
                pst.setFloat(5, 0);
                pst.setString(6, "");
                request = "INSERT INTO LigneCommande(livIsbn,comNum,ligPrix,ligQuantite,ligRemise,ligMontantTVA)"
                        + "Values (?,?,?,?,?,?)";
                for (Map.Entry<String, LigneCommande> col : panier.entrySet()) {
                    pst.setString(1, col.getValue().getLivIsbn());
                    pst.setInt(2, 0);
                    pst.setDouble(3, col.getValue().getLigPrix());
                    pst.setInt(4, col.getValue().getLigQuantite());
                    pst.setDouble(5, col.getValue().getLigRemise());
                    pst.setDouble(6, col.getValue().getLigMontantTva());
                }
                pst.close();
                co.close();
            }
        } catch (SQLException ex) {
            System.err.println("Erreur: SQL: " + ex.getMessage());
        }
    }

    //accesseurs
    public int getComNum() {
        return comNum;
    }

    public void setComNum(int comNum) {
        this.comNum = comNum;
    }

    public int getCliId() {
        return cliId;
    }

    public void setCliId(int cliId) {
        this.cliId = cliId;
    }

    public int getAdrIdLivraison() {
        return adrIdLivraison;
    }

    public void setAdrIdLivraison(int adrIdLivraison) {
        this.adrIdLivraison = adrIdLivraison;
    }

    public int getAdrIdFacturation() {
        return adrIdFacturation;
    }

    public void setAdrIdFacturation(int adrIdFacturation) {
        this.adrIdFacturation = adrIdFacturation;
    }

    public Date getComDate() {
        return comDate;
    }

    public void setComDate(Date comDate) {
        this.comDate = comDate;
    }

    public int getComStatut() {
        return comStatut;
    }

    public void setComStatut(int comStatut) {
        this.comStatut = comStatut;
    }

    public String getComObservation() {
        return comObservation;
    }

    public void setComObservation(String comObservation) {
        this.comObservation = comObservation;
    }

    public float getComFraisPort() {
        return comFraisPort;
    }

    public void setComFraisPort(float comFraisPort) {
        this.comFraisPort = comFraisPort;
    }

    public String getComIp() {
        return comIp;
    }

    public void setComIp(String comIp) {
        this.comIp = comIp;
    }

    public void setPanier(HashMap<String, LigneCommande> panier) {
        this.panier = panier;
    }

    //overrides
    @Override
    public String toString() {
        return "Commande{" + "comNum=" + comNum + ", cliId=" + cliId + ", adrIdLivraison=" + adrIdLivraison + ", adrIdFacturation=" + adrIdFacturation + ", comDate=" + comDate + ", comStatut=" + comStatut + ", comObservation=" + comObservation + ", comFraisPort=" + comFraisPort + ", comIp=" + comIp + '}';
    }
}
