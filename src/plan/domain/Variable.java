package plan.domain;

/**
 * Created by LUCA on 01/05/2016.
 */
public class Variable<E> {

    private String name;
    private E value;

    /**
     * The constructor gives a name to the predicate
     * @param name
     */
    public Variable(String name){
        if(name != null && !checkName(name))
            this.name = name;
        else
            throw new IllegalArgumentException("The name of the predicate is not valid. Only numbers and characters are allowed");
    }

    /**
     * Getter for the name
     * @return the name
     */
    public String getName(){
        return name;
    }

    /**
     * Getter for the value
     * @return the value
     */
    public E getValue(){
        return value;
    }

    /**
     * Setter for the value
     * @param newValue the new value
     */
    public void setValue(E newValue){
        value = newValue;
    }

    @Override
    public boolean equals(Object otherVariable){
        if(otherVariable instanceof Variable)
            //check whether name and value are the same (delegates the objects to check primitive equality)
            if(getName().equals(((Variable) otherVariable).getName()) &&
                    getValue().equals(((Variable) otherVariable).getValue()))
                return true;
        return false;
    }

    //returns true if the name is suitable, otherwise false
    private boolean checkName(String name){
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
