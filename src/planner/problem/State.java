package planner.problem;

import planner.domain.Variable;
import planner.types.DefaultDataType;
import planner.types.Object;
import utils.exceptions.DuplicateVariableNameException;

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
    protected LinkedList<Object> objects;

    /**
     * The constructor initializes the list of variables
     */
    public State(){
        variables = new LinkedList<>();
        objects = new LinkedList<>();
    }

    /**
     * Overloaded constructor allows to inherit variables from another state
     * This method also has to create NEW objects to prevent the parent's variables to point to the children's ones
     * @param vars the variables
     */
    public State(LinkedList<Variable> vars){
        variables = new LinkedList<>();
        objects = new LinkedList<>();
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
     * Return all the objects of this instance
     * @return the objects
     */
    private LinkedList<Object> getInstanceObjects() {
        return objects;
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
    public void addVariable(Variable var) throws DuplicateVariableNameException {
        for(Variable v : variables)
            if(v.getName().equals(var.getName()))
                throw new DuplicateVariableNameException("Two variables can't have the same name");

        variables.add(var);
    }

    /**
     * Add an object to the list
     * @param obj the object
     */
    public void addObject(Object obj) {
        if(!objects.contains(obj))
            objects.add(obj);
    }

    /**
     * Add a collection of variables to the list
     * @param vars the collection of variables
     */
    public void addVariables(Collection<Variable> vars) throws DuplicateVariableNameException {
        for(Variable v : vars)
            addVariable(v);
    }

    /**
     * Add a collection of objects to the list
     * @param objs the collection of objects
     */
    public void addObjects(Collection<Object> objs){
        for(Object o : objs)
            addObject(o);
    }

    /**
     * Utility to convert a State to a TreeState
     * @param state the state to be converted
     * @return the converted TreeState
     */
    public static TreeState convertToTreeState(State state) throws DuplicateVariableNameException {
        TreeState ts = new TreeState();
        ts.addVariables(state.getInstanceVariables());
        ts.addObjects(state.getInstanceObjects());

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

    /**
     * Returns all the objects of the given type
     * @param type the type
     * @return
     */
    public LinkedList<Object> getObjectsByType(Class type){
        LinkedList<Object> tmp = new LinkedList<>();

        for(Object o : objects)
            if(o.getClass() == type)
                tmp.add(o);

        return tmp;
    }

    @Override
    public String toString(){
        String tmp = "Variables: [";
        for(Variable v : variables)
            tmp += v + ", ";
        return tmp.length() > 13 ? tmp.substring(0, tmp.length() - 2) + "]" : tmp + "]";
    }

    @Override
    public boolean equals(java.lang.Object other){
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
