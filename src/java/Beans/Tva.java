
package Beans;

import java.io.Serializable;


public class Tva implements Serializable{
    
    private int id;
    private double tva;

    public Tva(){
        
    }
    
    public Tva(int id, double tva) {
        this.id = id;
        this.tva = tva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTva() {
        return tva;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }

    @Override
	public boolean equals(Object obj) {
        if(obj.toString().equals(""+tva)) return true; else  return false;
        
        //return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    @Override
    public String toString() {
        return ""+tva;
    }
    
    
    
}
