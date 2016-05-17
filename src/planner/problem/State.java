package planner.problem;

import planner.domain.Variable;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by LUCA on 17/05/2016.
 *
 * A state represents an initial state, a goal or whatever falls between these two
 */
public class State {

    private LinkedList<Variable> variables;

    /**
     * The constructor initializes the list of variables
     */
    public State(){
        variables = new LinkedList<>();
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

    /**
     * Add a variable to the list
     * @param var the variable
     */
    public void addVariable(Variable var){
        variables.add(var);
    }

    /**
     * Add a collection of variables to the list
     * @param vars the collection of variables
     */
    public void addVariables(Collection<Variable> vars){
        for(Variable v : vars)
            variables.add(v);
    }
}
