package planner.domain;

import planner.problem.State;

import java.util.LinkedList;

/**
 * Created by LUCA on 01/05/2016.
 *
 * This class puts everything together to form a domain with all its components
 */
public class Domain {

    private LinkedList<Action> actions;
    private LinkedList<Type> types;

    /**
     * The constructor only initializes the linked lists
     */
    public Domain(){
        actions = new LinkedList<>();
        types = new LinkedList<>();
    }

    /**
     * Adds an action to the domain
     * @param action the action
     */
    public void addAction(Action action){
        actions.add(action);
    }

    /**
     * Adds a variable type in the domain
     * @param type the type
     */
    public void addType(Type type){
        if(!types.contains(type))
            types.add(type);
    }

    /**
     * Return a list of applicable actions on a state
     * @param state the state
     * @return the applicable actions
     */
    public LinkedList<Action> getApplicableActions(State state){

        LinkedList<Action> applicableActions = new LinkedList<>();

        //check for each action specified in the domain which can be applied in the given state
        for(Action a : actions)
            if(a.isApplicable(state))
                applicableActions.add(a);

        return applicableActions;
    }
}
