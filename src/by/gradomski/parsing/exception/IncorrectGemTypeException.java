package by.gradomski.parsing.exception;

public class IncorrectGemTypeException extends Exception {
    static final long serialVersionUID = 1L;

    public IncorrectGemTypeException(){
        super();
    }

    public IncorrectGemTypeException(String message){
        super(message);
    }

    public IncorrectGemTypeException(Exception e){
        super(e);
    }

    public IncorrectGemTypeException(String message, Exception e){
        super(message, e);
    }
}
