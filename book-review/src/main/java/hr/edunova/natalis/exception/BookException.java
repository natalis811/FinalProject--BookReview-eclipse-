package hr.edunova.natalis.exception;

public class BookException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;
    
    public BookException (String message){
        super();
        if (message != null && message.length() > 2 && message.endsWith(", ")) {
            this.message = message.substring(0, message.length()-2);
        } else {
            this.message = message;
        }
        
    }

    public String getMessage() {
        return message;
    }
	
}