package by.gradomski.parsing.exception;

public class ContentNameNotPresentException extends Exception {
    static final long serialVersionUID = 1L;

    public ContentNameNotPresentException(){
        super();
    }

    public ContentNameNotPresentException(String message){
        super(message);
    }

    public ContentNameNotPresentException(Exception e){
        super(e);
    }

    public ContentNameNotPresentException(String message, Exception e){
        super(message, e);
    }
}
