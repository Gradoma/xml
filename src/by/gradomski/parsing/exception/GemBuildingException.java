package by.gradomski.parsing.exception;

public class GemBuildingException extends Exception{
    static final long serialVersionUID = 1L;

    public GemBuildingException(){
        super();
    }

    public GemBuildingException(String message){
        super(message);
    }

    public GemBuildingException(Exception e){
        super(e);
    }

    public GemBuildingException(String message, Exception e){
        super(message, e);
    }
}
