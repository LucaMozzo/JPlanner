package utils.exceptions;

/**
 * Created by LUCA on 15/06/2016.
 *
 * Exception that is thrown whenever you try to apply an operation to a type which doesn't support it
 */
public class OperationNotSupportedException extends Exception {

    /**
     * Default constructor
     */
    public OperationNotSupportedException(){
        super();
    }

    /**
     * Constructor that includes a message for the exception
     * @param message the message
     */
    public OperationNotSupportedException(String message){
        super(message);
    }
}
