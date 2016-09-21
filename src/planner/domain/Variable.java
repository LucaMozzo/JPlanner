package planner.domain;

import planner.types.DefaultDataType;
import planner.types.Duplicable;
import utils.Validation;

/**
 * Created by LUCA on 01/05/2016.
 *
 * Represents a variable (i.e. both predicates and functions in PDDL)
 */
public class Variable<E extends Duplicable> {

    private String name;
    private E value;

    /**
     * The constructor gives a name to the value and sets it to default
     * When giving a
     * @param name the name
     * @param defaultValue the value
     */
    public Variable(String name, E defaultValue){
        if(name != null && Validation.checkName(name))
            this.name = name;
        else
            throw new IllegalArgumentException("The name of the predicate is not valid. Only numbers and characters are allowed");

        value = (E)((DefaultDataType)defaultValue).duplicate(); //to avoid parent-children variable referencing causing errors in the program
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
        if(otherVariable instanceof Variable && value.getClass() == ((Variable) otherVariable).getValue().getClass())
            //check whether name and value are the same (delegates the customObjects to check primitive equality)
            if(getName().equals(((Variable) otherVariable).getName()) &&
                    getValue().equals(((Variable) otherVariable).getValue()))
                return true;
        return false;
    }

    @Override
    public String toString(){
        return "[\"" + name + "\" : " + value + "]";
    }
}
