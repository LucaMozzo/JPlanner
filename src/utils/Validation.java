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
}
