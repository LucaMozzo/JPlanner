package planner.domain;

import planner.types.DefaultDataType;
import planner.types.Duplicable;

/**
 * Created by LUCA on 05/05/2016.
 *
 * Abstraction of precondition (a fact that has to be true) and operation (a fact that becomes true after an action) common methods
 */
public abstract class Fact<E extends Duplicable> {
    protected String varName;
    protected E value;

    /**
     * The constructor takes the variable and a value
     * @param varName the variable name
     * @param value the expected value
     */
    public Fact(String varName, E value) {
        this.value = value;
        this.varName = varName;
    }

    /**
     * Constructor that takes only the variable
     * @param varName the variable name
     */
    public Fact(String varName) {
        this.varName = varName;
    }

    /**
     * Getter for the variable name
     * @return the name of the variable
     */
    public String getVariableName(){
        return varName;
    }

    /**
     * Getter for the value
     * @return the value
     */
    public E getValue(){
        return value;
    }

    /**
     * Setter for the variable
     * @param newVariableName the new variable name
     */
    public void setVariableName(String newVariableName){
        this.varName = newVariableName;
    }

    /**
     * Setter for the value
     * @param newValue the new value
     */
    public void setValue(E newValue){
        this.value = newValue;
    }

    @Override
    public String toString(){
        return "[\"" + varName + "\" : " + value.toString();
    }
}
