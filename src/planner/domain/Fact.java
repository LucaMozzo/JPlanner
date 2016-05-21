package planner.domain;

/**
 * Created by LUCA on 05/05/2016.
 *
 * Abstraction of precondition (a fact that has to be true) and effect (a fact that becomes true after an action) common methods
 */
public abstract class Fact<E> {
    protected Variable<E> var;
    protected E value;

    /**
     * The constructor takes the variable and a value
     * @param var the variable
     * @param value the expected value
     */
    public Fact(Variable var, E value) {
        this.value = value;
        this.var = var;
    }

    /**
     * Constructor that takes only the variable
     * @param var the variable
     */
    public Fact(Variable var) {
        this.var = var;
    }

    /**
     * Getter for the variable
     * @return the variable
     */
    public Variable getVariable(){
        return var;
    }

    /**
     * Getter for the variable name
     * @return the name of the variable
     */
    public String getVariableName(){
        return var.getName();
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
     * @param newVariable the new variable
     */
    public void setVariable(Variable newVariable){
        this.var = newVariable;
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
        return "[\"" + var.getName() + "\" : " + value.toString();
    }
}
