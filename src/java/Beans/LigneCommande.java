
package Beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

public class LigneCommande implements Serializable{
    
    private int ligId;
    private String livIsbn;
    private double ligPrix;
    private int ligQuantite;
    private float ligRemise;
    private double ligMontantTva;
    private HashMap<String, Livre> map = null;
    private Livre monLivre = new Livre();
    private double prixHT;
    private double prixTTC;
    
    //CONSTRUCTEURS
    public LigneCommande() {
        this.map = new HashMap<>();
    }
    
    public LigneCommande(String livIsbn){
        this.livIsbn = livIsbn;
       
    }
    
    public LigneCommande(Livre monLivre, int ligQuantite) {
        this.monLivre = monLivre;
        this.ligQuantite = ligQuantite;
        
    }
    
    public LigneCommande(String livIsbn, int ligQuantite) {
        this.livIsbn = livIsbn;
        this.ligQuantite = ligQuantite;
        
    }
    
    public LigneCommande(String livIsbn, double ligPrix, float ligRemise, double ligMontantTva) {
        this.livIsbn = livIsbn;
        this.ligPrix = ligPrix;
        this.ligRemise = ligRemise;
        this.ligMontantTva = ligMontantTva;
    }

    public LigneCommande(int ligId, String livIsbn, float ligPrix, int ligQuantite, float ligRemise, float ligMontantTva) {
        this.ligId = ligId;
        this.livIsbn = livIsbn;
        this.ligPrix = ligPrix;
        this.ligQuantite = ligQuantite;
        this.ligRemise = ligRemise;
        this.ligMontantTva = ligMontantTva;
    }
    
    //Methodes
    
    
    public double getPrixHT(){
        this.prixHT = Math.rint(((this.ligPrix - (this.ligPrix * monLivre.getEvt().getEveRemise())/100)*this.ligQuantite)*100.0)/100.0;
        return prixHT;
    }
    
    public double getPrixTTC(){
        this.prixTTC = Math.rint((prixHT + (prixHT * (this.ligMontantTva/100)))*100.0)/100.0;
        return prixTTC;
    }
    
    public void change( int qty) {
        this.ligQuantite+= qty;
    }
    
    public void add(Livre unLivre, int qty) {
        if (map.containsKey(unLivre.getIsbn())) {
            unLivre = map.get(unLivre.getIsbn());
            change(qty);
        } else {
            
            map.put(unLivre.getIsbn(), unLivre);
            ligQuantite = 1;
        }
        if( this.ligQuantite < 1) 
            delete(unLivre);
    }
    
    public void add(Livre unLivre, String qty){
        int qte = Integer.parseInt(qty);
        add(unLivre,qte);
    }
    public void add(Livre unLivre){
        add(unLivre,1);
    }
    
    public void less(Livre unLivre){
        add(unLivre,-1);
    }
    
    public void less(Livre unLivre, int qty){
        add(unLivre,-qty);
    }
    public void delete(Livre unLivre){
        map.remove(unLivre.getIsbn());
    }
    public void clear(){
        map.clear();
    }
    public boolean isEmpty(){
        return map.isEmpty();
    }
    
    public int size(){
        return map.size();
    }
    
    public Collection getLivres(){
        return map.values();
    }
    
    //ACCESSEURS

    public int getLigId() {
        return ligId;
    }

    public void setLigId(int ligId) {
        this.ligId = ligId;
    }

    public String getLivIsbn() {
        return livIsbn;
    }

    public void setLivIsbn(String livIsbn) {
        this.livIsbn = livIsbn;
    }

    public double getLigPrix() {
        return ligPrix;
    }

    public void setLigPrix(float ligPrix) {
        this.ligPrix = ligPrix;
    }

    public int getLigQuantite() {
        return ligQuantite;
    }

    public void setLigQuantite(int ligQuantite) {
        this.ligQuantite = ligQuantite;
    }

    public float getLigRemise() {
        return ligRemise;
    }

    public void setLigRemise(float ligRemise) {
        this.ligRemise = ligRemise;
    }

    public double getLigMontantTva() {
        return ligMontantTva;
    }

    public void setLigMontantTva(float ligMontantTva) {
        this.ligMontantTva = ligMontantTva;
    }

    public HashMap<String, Livre> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Livre> map) {
        this.map = map;
    }

    public Livre getMonLivre() {
        return monLivre;
    }

    public void setMonLivre(Livre monLivre) {
        this.monLivre = monLivre;
    }
    
    //Overrides

    @Override
    public String toString() {
        return "LigneCommande{" + "ligId=" + ligId + ", livIsbn=" + livIsbn + ", ligPrix=" + ligPrix + ", ligQuantite=" + ligQuantite + ", ligRemise=" + ligRemise + ", ligMontantTva=" + ligMontantTva + ", map=" + map + ", monLivre=" + monLivre + '}';
    }
    
    

}
