package Classes;

public class LibExceptions extends Exception{
    private int noError;

    public LibExceptions(String message) {
        super(message);
    }

    public LibExceptions(int noError, String message) {
        super(message);
        this.noError = noError;
    }
    
}
