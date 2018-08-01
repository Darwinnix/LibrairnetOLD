
package Classes;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


    public class mesOutils {

    static public void checkNom(String nom) throws LibExceptions {

        if (nom == null) {
            throw new LibExceptions(1, "Un nom ne peut être null");
        }
        if (nom.trim().isEmpty()) {
            throw new LibExceptions(2, "Un nom ne peut être vide");
        }
        if (nom.trim().length() < 3) {
            throw new LibExceptions(3, "Un nom doit faire au moins 2 caractères");
        }

    }
    static public void checkPrenom(String cliPrenom) throws LibExceptions {
         if (cliPrenom == null) {
            
            throw new LibExceptions(4, "un Prenom ne peut pas être null");
        }
        if (cliPrenom.trim().isEmpty()) {
            
            throw new LibExceptions(5, "Un Prenom ne peut être vide");
        }
        if (cliPrenom.trim().length() < 3) {
            
            throw new LibExceptions(6, "Un Prenom doit faire au moins 2 caractères");
        }
    }
    static public void checkGenre(String cliGenre) throws LibExceptions {
       if (cliGenre == null) {
            throw new LibExceptions(7, "Un Genre ne peut etre null");
        }
        if (cliGenre.trim().isEmpty()) {
            throw new LibExceptions(8, "Un Genre ne peut etre vide");
        }
        if (cliGenre.trim().length() != 1) {
            throw new LibExceptions(9, "Un Genre doit faire un caractère");
        }
        if (!(cliGenre.equalsIgnoreCase("m")|| cliGenre.equalsIgnoreCase("n") || cliGenre.equalsIgnoreCase("f"))) {
            throw new LibExceptions(10, "il faut choisir un genre entre : M(Masculin) , F(Féminin) et N(Neutre)");
        }
    }
    static public void checkDate(Date cliDate) throws LibExceptions {
      Date dat = new Date(19200101);
        if (cliDate == null) {
            throw new LibExceptions(11, "Une Date de Naissance ne peut etre null");
        }
        if (cliDate.toString().isEmpty()) {
            throw new LibExceptions(12, "Une Date de Naissance ne peut etre vide");
        }
//        if (cliDate.before(dat)) {
//            throw new LibExceptions(13, "Désolé Date invalide, choisir une Date supérieur à 1920/01/01 ");
//        }
        
    }
     static public void checkMail(String cliMail) throws LibExceptions {
       if (cliMail == null) {
            throw new LibExceptions(14, "Un E-Mail ne peut etre null");
        }
        if (cliMail.trim().isEmpty()) {
            throw new LibExceptions(15, "Un E-Mail ne peut etre vide");
        }
//        if (cliMail.length() >= 10) {
//            throw new LibExceptions(9, "Un E-Mail doit faire 10 caractères au minimum");
//        }
//         if(!(cliMail.matches("#^[\\w.-]+@[\\w.-]+\\.[a-z]{2,6}$#i"))){
//                throw new LibExceptions(16,"Format Email invalide");
//        }
       
    }
   static public void checkLogin1(String clilogin) throws LibExceptions, NumberFormatException {
      int j = 0;
      int k = 0;
      int d = 0;
       if (clilogin == null) {
            throw new LibExceptions(17, "Un Login ne peut etre null");
        }
        if (clilogin.trim().isEmpty()) {
            throw new LibExceptions(18, "Un Login ne peut etre vide");
        }
        if (clilogin.length()<4 ) {
            throw new LibExceptions(19, "Un Login doit avoir 4 caractères au minimum");
        }
        if (clilogin.length()>10 ) {
            throw new LibExceptions(20, "Un Login doit avoir 10 caractères au maximum");
        }
          
          for(int i = 0; i < clilogin.length();i++){
                 if(Character.isDigit(clilogin.charAt(0))){
                    if(Integer.parseInt(clilogin.charAt(0)+"") <1 ){
                     throw new LibExceptions(22, "Format interdit: 0 au debut du login");
                 }
                 }
                 if(clilogin.charAt(i) > 64 && clilogin.charAt(i) < 91){
                    j++;
                 }
                 if(Character.isDigit(clilogin.charAt(i))){
                 if(Integer.parseInt(clilogin.charAt(i)+"") >= 0 && Integer.parseInt(clilogin.charAt(i)+"") <=9 ){
                     k++;
                 }
                 }
                 if(clilogin.charAt(i) < 97 && clilogin.charAt(i) > 122){
                     d++;
                 }
             }
           if( j == 0){
                 throw new LibExceptions(23,"il faut une lettre en Majuscule au minimum.");
             }
           if(k == 0){
                 throw new LibExceptions(24,"il faut chiffre au minimum.");
             }
           
            
             if(d == 10 || k == 10 || j== 10 ){
                 throw new LibExceptions(25,"Format login invalide:Lettres en Miniscule,Majuscule et des chiffres.");
             }
             if(!(clilogin.matches("[a-zA-Z0-9].{4,10}"))){
                throw new LibExceptions(26,"Format login invalide");
             }
       
            }

      public static boolean checkLogin2( String user, String password) {
        if( user==null) return false;
        if( password==null) return false;
        if( user.trim().isEmpty()) return false;
        if( password.trim().isEmpty()) return false;
//        
//        if( user.equals( "admin"))
//            if( password.equals("root"))
//                return true;
            
        return false;
    }
    static public void checkPwd(String cliPwd) throws LibExceptions {
        if (cliPwd == null) {
            throw new LibExceptions(27, "Un Mots de passe ne peut etre null");
        }
        if (cliPwd.trim().isEmpty()) {
            throw new LibExceptions(28, "Un Mots de passe ne peut etre vide");
        }
        if(!(cliPwd.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[-+!*$@%_])([-+!*$@%_\\w]{8,15})$"))){
           throw new LibExceptions(29, "Format Password invalide");
       }
        
    }
     static public void checkObsr(String cliObr) throws LibExceptions {
       if(cliObr.length() > 150){
           throw new LibExceptions(30, "500 caractères maximum");
       }
        
    }
    
    static public java.sql.Date checkDate(String cliDate) throws LibExceptions {
       
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd" );
        dateFormat.setLenient(false);
        java.util.Date date = null;
         
        try {
            date = dateFormat.parse(cliDate);
            
            //date = new java.sql.Date(date.getTime());
        } catch (ParseException ex) {
             throw new LibExceptions(14, "Format Entré ne correspond pas à une date valide");
            
        }
       return new java.sql.Date(date.getTime());
    }
    static public void checkCodePostal(String code) throws LibExceptions{
        if (code == null) {
            throw new LibExceptions(27, "Un Code postal ne peut etre null");
        }
        if (code.trim().isEmpty()) {
            throw new LibExceptions(28, "Un Code postal ne peut etre vide");
        }
        if(!(code.matches("[0-9]{2,5}"))){
           throw new LibExceptions(29, "Format code postal invalide");
       }
    }
       static public void checkVille(String ville) throws LibExceptions{
        if (ville == null) {
            throw new LibExceptions(27, "Une ville ne peut etre null");
        }
        if (ville.trim().isEmpty()) {
            throw new LibExceptions(28, "Une ville ne peut etre vide");
        }
        if(!(ville.matches("[a-zA-Z\\s].{4,50}"))){
           throw new LibExceptions(29, "Format ville invalide");
       }
    }
       static public void checkPays(String Pays) throws LibExceptions{
        if (Pays == null) {
            throw new LibExceptions(27, "Un Pays ne peut etre null");
        }
        if (Pays.trim().isEmpty()) {
            throw new LibExceptions(28, "Un Pays ne peut etre vide");
        }
        if(!(Pays.matches("[a-zA-Z].{4,50}"))){
           throw new LibExceptions(29, "Format Pays  invalide");
       }
    }  
       public static String StringNull(String input){
           return input == null ? "" : input; 
       }
    static public int parseInt(String s)  {
        int i = 0;
        try {
            i = Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            throw new mesRuntimeException( "Format numérique invalide");
        }

        return i;
    }
       

}
