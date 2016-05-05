package plan.problem;

import plan.domain.Domain;
import plan.domain.Variable;

import java.util.LinkedList;

/**
 * Created by LUCA on 01/05/2016.
 *
 * Represents a problem with all its components
 */
public class Problem {

    private Domain domain;
    private LinkedList<Variable> variables;

    /**
     * Default constructor
     * @param domain the domain
     */
    public Problem(Domain domain){

        this.domain = domain;
    }

    /**
     * Return all the variables of this instance
     * @return the variables
     */
    public LinkedList<Variable> getInstanceVariables(){
        return variables;
    }

    /**
     * Given the variable and a new value, it replaces the old value of the given variable with the given one
     * @param var the variable
     * @param value the new value
     */
    public void setInstanceVariableValue(Variable var, Object value){
        int tmpIndex;
        //if the variable exists in the current problem
        if((tmpIndex = variables.indexOf(var)) != -1){
            Variable tmpVar = variables.get(tmpIndex);
            //if the old value and the new one are of the same type
            if(tmpVar.getValue().getClass() == value.getClass())
                tmpVar.setValue(value);
        }
    }
}
