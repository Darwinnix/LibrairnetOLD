/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exception;

/**
 *
 * @author cdi314
 */
public class LibException extends Exception {

    final static int CHAINE_VIDE = 0;
    
    
    int errNo;
    
    public LibException() {
    }

    public LibException(String message) {
        super(message);
    }
    
    public LibException(int errNo, String message){
        super(message);
        this.errNo = errNo;
    }

    public int getErrNo() {
        return errNo;
    }
    
    
    
}
