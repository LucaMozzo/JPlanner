package plan.domain;

/**
 * Created by LUCA on 01/05/2016.
 *
 * A precondition is a fact that has to be satisfied in order to apply the action
 */
public class Precondition<E> {

    private Variable<E> var;
    private E value;

    /**
     * The constructor takes the variable and its expected value
     * @param var the variable
     * @param value the expected value
     */
    public Precondition(Variable var, E value){
        this.var = var;
        this.value = value;
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

    /**
     * Determines if the variable got the exprected result
     * @return whether is satisfied or not
     */
    public boolean isSatisfied(){
        return value.equals(var.getValue());
    }

    @Override
    public String toString(){
        return "[\"" + var.getName() + "\" : " + value.toString();
    }
}
