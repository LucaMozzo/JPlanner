package utils;

/**
 * Created by LUCA on 05/05/2016.
 *
 * Contains static methods for validation
 */
public final class Validation {

    /**
     * Check whether a name is suitable or not (only alphanumeric chars and digits are allowed)
     * @param name the name to be checked
     * @return true if the input name is suitable
     */
    public static boolean checkName(String name){
        if(name == null)
            return false;
        else
            //we want only letters and numbers to be part of the name of a predicate
            for(char c : name.toCharArray())
                if((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
                    continue;
                else
                    return false;
        return true;
    }

    /**
     * Check whether the chosen operation can be applied with an argument
     * @param operation the operation
     * @param hasArgument true if an argument is given
     * @return if the type got the correct number of arguments (0 or 1)
     */
    public static boolean checkType(Operations operation, boolean hasArgument){
        if(operation == Operations.INCREASE || operation == Operations.DECREASE || operation == Operations.SET_TRUE
                || operation == Operations.SET_FALSE || operation == Operations.SQRT || operation == Operations.SQUARE)
            return hasArgument ? false : true; //if a variable of any of those types has an argument return false
        else
            return hasArgument ? true : false;
    }
}
