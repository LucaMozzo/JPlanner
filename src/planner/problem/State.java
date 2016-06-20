package planner.problem;

import planner.domain.Variable;
import planner.types.DefaultDataType;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by LUCA on 17/05/2016.
 *
 * A state represents an initial state, a goal or whatever falls between these two
 */
public class State {

    protected LinkedList<Variable> variables;

    /**
     * The constructor initializes the list of variables
     */
    public State(){
        variables = new LinkedList<>();
    }

    /**
     * Overloaded constructor allows to inherit variables from another state
     * This method also has to create NEW objects to prevent the parent's variables to point to the children's ones
     * @param vars the variables
     */
    public State(LinkedList<Variable> vars){
        variables = new LinkedList<>();
        for(Variable v : vars)
            variables.add(new Variable(v.getName(), v.getValue()));
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
    public void setInstanceVariableValue(Variable var, DefaultDataType value){
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

    /**
     * Utility to convert a State to a TreeState
     * @param state the state to be converted
     * @return the converted TreeState
     */
    public static TreeState convertToTreeState(State state){
        TreeState ts = new TreeState();
        ts.addVariables(state.getInstanceVariables());

        return ts;
    }

    /**
     * Returns a variable with the specified value
     * @param name the name
     * @return the variable, if exists
     */
    public Variable getVariableByName(String name){
        for(Variable v : variables)
            if(v.getName().equals(name))
                return v;
        return null;
    }

    @Override
    public String toString(){
        String tmp = "Variables: [";
        for(Variable v : variables)
            tmp += v + ", ";
        return tmp.length() > 13 ? tmp.substring(0, tmp.length() - 2) + "]" : tmp + "]";
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof State){
            //checking the size first saves time if it's not necessary to loop through the array
            if(variables.size() != ((State) other).variables.size())
                return false;

            Iterator iterator = variables.iterator();
            Iterator otherIterator = ((State) other).variables.iterator();

            while(iterator.hasNext()){
                if(!iterator.next().equals(otherIterator.next()))
                    return false;
            }

            return true;
        }
        else
            return false;
    }
}
