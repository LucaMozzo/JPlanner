package utils.exceptions;

/**
 * Created by LUCA on 13/07/2016.
 *
 * An exception for handling the case in which the user adds 2 variables with the same name
 */
public class DuplicateVariableNameException extends Exception{

    /**
     * Default constructor
     */
    public DuplicateVariableNameException(){
        super();
    }

    /**
     * Constructor that includes a message for the exception
     * @param message the message
     */
    public DuplicateVariableNameException(String message) {
        super(message);
    }
}
