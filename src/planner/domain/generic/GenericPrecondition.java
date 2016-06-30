package planner.domain.generic;

import planner.domain.Variable;
import planner.problem.State;
import planner.types.Object;

/**
 * Created by LUCA on 30/06/2016.
 *
 * A generic precondition that works with every instance of that object in the problem
 */
public class GenericPrecondition<T extends Object> {

    private final T TYPE;
    private Variable variable;

    /**
     * The constructor takes the type of the target and a target variable on one of its properties
     * @param type the type
     * @param variable the taget variable
     */
    public GenericPrecondition(T type, Variable variable){
        this.TYPE = type;
        this.variable = variable;
    }

    /**
     * Determine if the precondition is satisfied with at least one object
     * @param state the state
     * @return whether it's satisfied
     */
    public boolean isSatisfied(State state){
        for(Variable v : state.getInstanceVariables())
            if(v.getValue().getClass().equals(TYPE.getClass()))
                if(variable.getName().equals(v.getName()) && variable.getValue().equals(v.getValue()))
                    return true;
        return false;
    }
}
